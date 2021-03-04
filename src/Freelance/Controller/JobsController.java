package Freelance.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.TreeMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Freelance.bean.Account;
import Freelance.bean.JobCagetory;
import Freelance.bean.Jobs;
import Freelance.bean.JobsSkill;
import Freelance.bean.JobsUser;
import Freelance.bean.JobsUserId;
import Freelance.bean.Rating;
import Freelance.bean.Skills;
import Freelance.bean.Users;
import Freelance.bean.general;

@Transactional
@Controller
@RequestMapping("job")
public class JobsController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	HttpSession HttpSS;
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping(value = "/post")
	public String jobPost(ModelMap model) {
		Users user = (Users) HttpSS.getAttribute("userLogin");
		Jobs newjob = new Jobs(user.getAccount());
			
		
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		
		String hql = "FROM JobCagetory";
		try {
			session.save(newjob);
			//tạo list loại việc
			List<JobCagetory> jobList =  session.createQuery(hql).list();
			model.addAttribute("jobTypeList", jobList);
			
			// 
			List<Skills> listSkill =  session.createQuery("FROM Skills").list();
			Map<Skills, Boolean> jobSkill = new TreeMap<Skills, Boolean>(
					(Comparator<? super Skills>) new Comparator<Skills>() {

	                    @Override
	                    public int compare(Skills o1, Skills o2) {
	                        return ((String) o1.getSkill()).compareTo((String) o2.getSkill());
	                    }

	                });
			for(Skills x : listSkill) {
				jobSkill.put(x, false);
			}
//			for(Entry<Skills, Boolean> x : jobSkill.entrySet()) {
//				System.out.println(x.getKey());
//			}
			model.addAttribute("jobSkill", jobSkill);
			model.addAttribute("newJobId", newjob.getId());
			model.addAttribute("jobInfo", session.get(Jobs.class, newjob.getId()));
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		
		
		return "job/post";
	}
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String submitJobPost(ModelMap model, @ModelAttribute("jobInfo") Jobs newJob,
			@RequestParam("newJobID") int newJobID,
			@RequestParam("jobTypeId") int jobTypeId,
			BindingResult errors) {	
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		// validator
		if(newJob.getTen().toString().length() == 0) {
			errors.rejectValue("ten", "jobInfo");
		}
		if(newJob.getThongTin().toString().length() == 0) {
			errors.rejectValue("thongTin", "jobInfo");
		}
		if(newJob.getNganSachTu().length() == 0) {
			errors.rejectValue("nganSachTu", "jobInfo");
		}
		if(newJob.getNganSachDen().length() == 0) {
			errors.rejectValue("nganSachDen", "jobInfo");
		}
		if(newJob.getNganSachDen().length() > 0 && newJob.getNganSachTu().length() > 0) {
			Long from = Long.parseLong(newJob.getNganSachTu());
			Long to = Long.parseLong(newJob.getNganSachDen());
			if(from > to) {
				errors.rejectValue("nganSachDen", "jobInfo");
			}
			
		}
		
		if(errors.hasErrors()) {
			String hql = "FROM JobCagetory";
			try {
				System.out.println("has error");
				session.save(newJob);
				//tạo list loại việc
				List<JobCagetory> jobList =  session.createQuery(hql).list();
				model.addAttribute("jobTypeList", jobList);
				
				// 
				List<Skills> listSkill =  session.createQuery("FROM Skills").list();
				Map<Skills, Boolean> jobSkill = new HashMap<Skills, Boolean>();
				for(Skills x : listSkill) {
					jobSkill.put(x, false);
				}
				model.addAttribute("jobTypeId", jobTypeId);
				model.addAttribute("jobSkill", jobSkill);
				model.addAttribute("newJobId", newJob.getId());
				model.addAttribute("jobInfo", newJob);
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
				model.addAttribute("message", e.toString() + "Lỗi Load!");
				
			} finally {
				session.close();
			}
			return "job/post";
		}
		else {
			System.out.println("no error");
			try {
				Account acc = (Account) session.get(Account.class, ((Users) HttpSS.getAttribute("userLogin")).getId());
				newJob.setJobCagetory((JobCagetory)session.get(JobCagetory.class, jobTypeId));
				newJob.setHoanThanh(false);
				newJob.setId(newJobID);
				newJob.setAccount(acc);
	
							
				session.save(newJob);
				session.update(acc);
				//tạo list loại việc
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
				//System.out.println("POST" + e.toString());
				return "job/post";
			} finally {
				session.close();
			}
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:" + String.valueOf(newJobID+1) +".htm";
	}
	
	private List<Skills> getJobSkill(int id){
		Session session = factory.openSession();
		List<Skills> jobSkill = new ArrayList<Skills>();
			
		try {
			String hql = "FROM JobsSkill AS JS WHERE JS.id.id = :id";
			List<JobsSkill> list = session.createQuery(hql).setParameter("id", id).list();
			for(JobsSkill x : list) {
				if(x != null) {
					jobSkill.add(x.getSkills());
				}
			}
		} catch (Exception e) {
			
		}
		return jobSkill;
	} 
	
	
	@RequestMapping(value = "/{jobId}")
	public String job(ModelMap model, @PathVariable int jobId) {	
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Jobs thisJob = (Jobs) session.get(Jobs.class, jobId);
			Users user = (Users) HttpSS.getAttribute("userLogin");
			Users userInfo = thisJob.getAccount().getUsers();
			model.addAttribute("jobInfo", thisJob);
			model.addAttribute("userInfo", userInfo);
			
			// tạo thông tin ng tuyển
			int createdJobs = 0;
			int doneJobs = 0;
			List<Jobs> userJobs = new ArrayList<Jobs>();
			String hql = "FROM Jobs AS J WHERE J.account.id = :id";
			List<Jobs> listJobs =  session.createQuery(hql).setParameter("id", userInfo.getId()).list();
			for(Jobs x : listJobs) {
				createdJobs++;
				if(x.getHoanThanh() != null && x.getHoanThanh()) {
					doneJobs++;
				}
				userJobs.add(new Jobs(x));
			}

			
			model.addAttribute("userRating", getRatingUser(userInfo.getId()));
			model.addAttribute("acceptedOffer", getAcceptedOffer(jobId));
			model.addAttribute("listOffers", getJobOffers(jobId));
			model.addAttribute("createdJobs", createdJobs);
			model.addAttribute("doneJobs", doneJobs);
			
			
			//cho user đã login
			Users userLogin = (Users) HttpSS.getAttribute("userLogin");
			if( userLogin != null) {
				hql = "FROM Favorites AS F WHERE F.id.userId = :userId AND F.id.jobId = :jobId";
				model.addAttribute("isFav", session.createQuery(hql).setParameter("userId", user.getId()).setParameter("jobId", jobId).uniqueResult() != null);
				if(userLogin.getType()) {
					hql = "FROM JobsUser AS JU WHERE JU.id.userId = :userId AND JU.id.jobId = :jobId";
					JobsUser offer = (JobsUser) session.createQuery(hql).setParameter("userId", userLogin.getId()).setParameter("jobId", jobId).uniqueResult();
					model.addAttribute("hasOffer", offer != null);
					if(offer == null) {
						//offer = new JobsUser(new JobsUserId(jobId, userLogin.getId()), userLogin.getAccount(), thisJob);
						offer = new JobsUser();
						offer.setChaoGia(thisJob.getNganSachTu());
					}
					model.addAttribute("offer", offer);
				}
			}
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		
		
		return "job/jobInfo";
	}
	
	
	
	@RequestMapping(value = "/edit/{jobId}")
	public String editJob(ModelMap model, @PathVariable int jobId) {
		Users user = (Users) HttpSS.getAttribute("userLogin");

		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		
		String hql = "FROM JobCagetory";
		try {
			Jobs thisJob = (Jobs) session.get(Jobs.class, jobId);
			if(thisJob.getAccount().getId() != user.getId()) {
				return "redirect:/index.htm";
			}
			//tạo list loại việc
			List<JobCagetory> jobList =  session.createQuery(hql).list();
			model.addAttribute("jobTypeList", jobList);
			
			// 
			List<Skills> listSkill =  session.createQuery("FROM Skills").list();
			Map<Skills, Boolean> jobSkill = new TreeMap<Skills, Boolean>(
					(Comparator<? super Skills>) new Comparator<Skills>() {

	                    @Override
	                    public int compare(Skills o1, Skills o2) {
	                        return ((String) o1.getSkill()).compareTo((String) o2.getSkill());
	                    }

	                });
			for(Skills x : listSkill) {
				jobSkill.put(x, false);
			}
			//System.out.println(jobId);
			for(Skills x : getJobSkill(jobId)) {
				jobSkill.put(x, true);
				//System.out.println(x.getSkill());
			}
			
			model.addAttribute("jobOffer", getAcceptedOffer(jobId));
			model.addAttribute("newJobId", thisJob.getId()-1);
			model.addAttribute("jobSkill", jobSkill);
			model.addAttribute("jobInfo", thisJob);
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		return "job/edit";
	}
	
	@RequestMapping(value = "/edit/{jobId}", method = RequestMethod.POST, params = "deleteBtn")
	public String submitJobDelete(ModelMap model, @ModelAttribute("jobInfo") Jobs thisJob,
			@PathVariable int jobId,
			@RequestParam("jobTypeId") int jobTypeId,
			BindingResult errors) {	
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		
		try {
			thisJob.setAccount((Account) session.get(Account.class, ((Users) HttpSS.getAttribute("userLogin")).getId()));
			thisJob.setJobCagetory((JobCagetory)session.get(JobCagetory.class, jobTypeId));
			thisJob.setId(jobId);
			thisJob.setHoanThanh(true);
			session.update(thisJob);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			
			
		} finally {
			session.close();
		}
		
		
		return job(model, jobId);
	}
	@RequestMapping(value = "/edit/{jobId}", method = RequestMethod.POST, params = "submitBtn")
	public String submitJobEdit(ModelMap model, @ModelAttribute("jobInfo") Jobs thisJob,
			@PathVariable int jobId,
			@RequestParam("jobTypeId") int jobTypeId,
			BindingResult errors) {	
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		
		// validator
		if(thisJob.getTen().toString().length() == 0) {
			errors.rejectValue("ten", "jobInfo");
		}
		if(thisJob.getThongTin().toString().length() == 0) {
			errors.rejectValue("thongTin", "jobInfo");
		}
		if(thisJob.getNganSachTu().length() == 0) {
			errors.rejectValue("nganSachTu", "jobInfo");
		}
		if(thisJob.getNganSachDen().length() == 0) {
			errors.rejectValue("nganSachDen", "jobInfo");
		}
		if(thisJob.getNganSachDen().length() > 0 && thisJob.getNganSachTu().length() > 0) {
			Long from = Long.parseLong(thisJob.getNganSachTu());
			Long to = Long.parseLong(thisJob.getNganSachDen());
			if(from > to) {
				errors.rejectValue("nganSachDen", "jobInfo");
			}
			
		}
		
		if(errors.hasErrors()) {
			String hql = "FROM JobCagetory";
			try {
				System.out.println("has error");
				//tạo list loại việc
				List<JobCagetory> jobList =  session.createQuery(hql).list();
				model.addAttribute("jobTypeList", jobList);
				
				// 
				List<Skills> listSkill =  session.createQuery("FROM Skills").list();
				Map<Skills, Boolean> jobSkill = new HashMap<Skills, Boolean>();
				for(Skills x : listSkill) {
					jobSkill.put(x, false);
				}
				//System.out.println(jobId);
				for(Skills x : getJobSkill(jobId)) {
					jobSkill.put(x, true);
					//System.out.println(x.getSkill());
				}
				thisJob.setId(jobId);
				model.addAttribute("newJobId", thisJob.getId()-1);
				model.addAttribute("jobSkill", jobSkill);
				model.addAttribute("jobInfo", thisJob);
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
				model.addAttribute("message", e.toString() + "Lỗi Load!");
				
			} finally {
				session.close();
			}
			return "job/edit";
		}
		
		else {
			try {
				thisJob.setAccount((Account) session.get(Account.class, ((Users) HttpSS.getAttribute("userLogin")).getId()));
				thisJob.setJobCagetory((JobCagetory)session.get(JobCagetory.class, jobTypeId));
				thisJob.setId(jobId);
				thisJob.setHoanThanh(false);
				session.update(thisJob);
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();

				
			} finally {
				session.close();
			}
		}
		
		
		return job(model, jobId);
	}
	
	
	@RequestMapping(value = "/{jobId}", method = RequestMethod.POST, params = "submit")
	public String submitJobOffer(ModelMap model,
			@ModelAttribute("offer") JobsUser offer,
			@PathVariable int jobId,
			BindingResult errors
			) {	
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		Jobs thisJob = (Jobs) session.get(Jobs.class, jobId);
		//validator
		if(offer.getChaoGia().length() > 0) {
			Long chaoGia = Long.parseLong(offer.getChaoGia());
			Long from = Long.parseLong(thisJob.getNganSachTu());
			Long to = Long.parseLong(thisJob.getNganSachDen());
			if (chaoGia < from || chaoGia > to) {
				errors.rejectValue("chaoGia", "offer");
			}
		}else {
			errors.rejectValue("chaoGia", "offer");
		}
		
		if(offer.getDeXuat().toString().length() == 0) {
			errors.rejectValue("deXuat", "offer");
		}
		if(offer.getHoanThanhTrong().length() == 0) {
			errors.rejectValue("hoanThanhTrong", "offer");
		}
		
		if(errors.hasErrors()) {
			Users user = (Users) HttpSS.getAttribute("userLogin");
			Users userInfo = thisJob.getAccount().getUsers();
			model.addAttribute("jobInfo", thisJob);
			model.addAttribute("userInfo", userInfo);
			
			// tạo thông tin ng tuyển
			int createdJobs = 0;
			int doneJobs = 0;
			List<Jobs> userJobs = new ArrayList<Jobs>();
			String hql = "FROM Jobs AS J WHERE J.account.id = :id";
			List<Jobs> listJobs =  session.createQuery(hql).setParameter("id", userInfo.getId()).list();
			for(Jobs x : listJobs) {
				createdJobs++;
				if(x.getHoanThanh() != null && x.getHoanThanh()) {
					doneJobs++;
				}
				userJobs.add(new Jobs(x));
			}

			
			model.addAttribute("userRating", getRatingUser(userInfo.getId()));
			model.addAttribute("acceptedOffer", getAcceptedOffer(jobId));
			model.addAttribute("listOffers", getJobOffers(jobId));
			model.addAttribute("createdJobs", createdJobs);
			model.addAttribute("doneJobs", doneJobs);
			
			
			//cho user đã login
			Users userLogin = (Users) HttpSS.getAttribute("userLogin");
			if( userLogin != null) {
				hql = "FROM Favorites AS F WHERE F.id.userId = :userId AND F.id.jobId = :jobId";
				model.addAttribute("isFav", session.createQuery(hql).setParameter("userId", user.getId()).setParameter("jobId", jobId).uniqueResult() != null);
				model.addAttribute("offer", offer);
			}
			System.out.println("error");
			return "job/jobInfo";
		}
		else {
			try {
				thisJob = (Jobs) session.get(Jobs.class, jobId);
				Account acc = (Account) session.get(Account.class, ((Users)HttpSS.getAttribute("userLogin")).getId());
				offer.setAccount(acc);
				offer.setJobs(thisJob);
				offer.setDangLam(false);
				offer.setId(new JobsUserId(jobId, acc.getId()));
				acc.getJobsUsers().add(offer);
				
				session.merge(offer);
				session.update(acc);
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();

				
			} finally {
				session.close();
			}
		}
		
		
		return job(model, jobId);
	}
	
	@RequestMapping(value = "/{jobId}", method = RequestMethod.POST, params = "delete")
	public String deleteJobOffer(ModelMap model,
			@PathVariable int jobId
			) {	
		
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		
		try {
//			Jobs thisJob = (Jobs) session.get(Jobs.class, jobId);
			Account acc = (Account) session.get(Account.class, ((Users)HttpSS.getAttribute("userLogin")).getId());

			JobsUser delOffer = (JobsUser) session.get(JobsUser.class, new JobsUserId(jobId, acc.getId()));
			session.delete(delOffer);
			session.update(acc);
			//System.out.println("deleted");
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			
			
		} finally {
			session.close();
		}
		return job(model, jobId);
	}
	
	@RequestMapping(value = "/{jobId}/{userId}", method = RequestMethod.POST)
	public String acceptOffer(ModelMap model, 
			@PathVariable int jobId,
			@PathVariable int userId) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {

			//load offer của freelancer
			
			String hql = "FROM JobsUser AS JU WHERE JU.id.userId = :userId AND JU.id.jobId = :jobId";
			JobsUser offer = (JobsUser) session.createQuery(hql).setParameter("userId", userId).setParameter("jobId", jobId).uniqueResult();
			offer.setDangLam(true);
			
			session.update(offer);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		return job(model, jobId);
	}
	
	
	@RequestMapping(value = "/{jobId}/{userId}")
	public String viewOffer(ModelMap model, 
			@PathVariable int jobId,
			@PathVariable int userId) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Jobs thisJob = (Jobs) session.get(Jobs.class, jobId);
			Users user = (Users) HttpSS.getAttribute("userLogin");
			Users userInfo = (Users) session.get(Users.class, userId);
			model.addAttribute("ratingUser", getRatingUser(userId));
			model.addAttribute("jobInfo", thisJob);
			model.addAttribute("userInfo", userInfo);
			
			// tạo thông tin freelancer
			int createdJobs = 0;
			int doneJobs = 0;
			List<Jobs> userJobs = new ArrayList<Jobs>();
			String hql = "FROM JobsUser AS JU WHERE JU.id.userId = :id";
			List<JobsUser> listJobs =  session.createQuery(hql).setParameter("id", userId).list();
			for(JobsUser x : listJobs) {
				createdJobs++;
				if(x.getJobs().getHoanThanh()) {
					doneJobs++;
				}
				userJobs.add(new Jobs(x.getJobs()));
			}

			model.addAttribute("createdJobs", createdJobs);
			model.addAttribute("doneJobs", doneJobs);
			
			
			//load offer của freelancer
			
			hql = "FROM JobsUser AS JU WHERE JU.id.userId = :userId AND JU.id.jobId = :jobId";
			JobsUser offer = (JobsUser) session.createQuery(hql).setParameter("userId", userId).setParameter("jobId", jobId).uniqueResult();
			//System.out.println(offer.getDeXuat());
			
			model.addAttribute("offer", offer);
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		return "job/offer";
	}
	
	private List<general> getJobOffers(int jobId){
		List<general> listOffers = new ArrayList<general>();
		Session session = factory.openSession();
		
		try {
			String hql = "FROM JobsUser AS JU WHERE JU.id.jobId = :jobId";
			List<JobsUser> listJU = session.createQuery(hql).setParameter("jobId", jobId).list();
			for(JobsUser info : listJU) {
				general offerInfo = new general();
				offerInfo.setId(info.getAccount().getId());
				offerInfo.setAvt(info.getAccount().getUsers().getAvt());
				offerInfo.setHoTen(info.getAccount().getUsers().getHoTen());
				offerInfo.setChucDanh(info.getAccount().getUsers().getChucDanh());
				offerInfo.setGioiThieu(info.getAccount().getUsers().getGioiThieu());
				offerInfo.setSkills(info.getAccount().getUserSkills());
				offerInfo.setThuNhap(info.getAccount().getUsers().getThuChi());
				offerInfo.setRating(getRatingUser(offerInfo.getId()));
				List<Jobs> listJobs = getJobsAttempByUser(offerInfo.getId());
				int done = 0;
				for(Jobs job : listJobs) {
					if(job.getHoanThanh()) {
						done++;
					}
				}
				offerInfo.setChaoGia(listJobs.size());
				offerInfo.setHoanThanh(done);
				listOffers.add(offerInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		} finally {
			session.close();
		}
		
		return listOffers;
	}
	
	private List<Jobs> getJobsAttempByUser(int userId) {
		Session session = factory.openSession();
		List<Jobs> userJobs = new ArrayList<Jobs>();
		try {
			String hql = "FROM JobsUser AS JU WHERE JU.id.userId = :id";
			List<JobsUser> listJobs =  session.createQuery(hql).setParameter("id", userId).list();
			for(JobsUser x : listJobs) {
				userJobs.add(new Jobs(x.getJobs()));
			}
		}catch (Exception e) {
			e.printStackTrace();
	
		} finally {
			session.close();
		}

		return userJobs;
	}
	
	private general getAcceptedOffer(int jobId) {
		general offerInfo = null;
		Session session = factory.openSession();
		try {
			String hql = "FROM JobsUser AS JU WHERE JU.id.jobId = :jobId AND JU.dangLam = true";
			JobsUser info = (JobsUser) session.createQuery(hql).setParameter("jobId", jobId).uniqueResult();
			if(info != null) {
				offerInfo = new general();
				offerInfo.setId(info.getAccount().getId());
				offerInfo.setAvt(info.getAccount().getUsers().getAvt());
				offerInfo.setHoTen(info.getAccount().getUsers().getHoTen());
				offerInfo.setChucDanh(info.getAccount().getUsers().getChucDanh());
				offerInfo.setGioiThieu(info.getAccount().getUsers().getGioiThieu());
				offerInfo.setSkills(info.getAccount().getUserSkills());
				offerInfo.setThuNhap(info.getAccount().getUsers().getThuChi());
				offerInfo.setRating(getRatingUser(offerInfo.getId()));
				
				List<Jobs> listJobs = getJobsAttempByUser(offerInfo.getId());
				int done = 0;
				for(Jobs job : listJobs) {
					if(job.getHoanThanh()) {
						done++;
					}
				}
				offerInfo.setChaoGia(listJobs.size());
				offerInfo.setHoanThanh(done);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		} finally {
			session.close();
		}
		
		return offerInfo;
		
	}
	
	
	@RequestMapping(value = "/finish/{jobId}/{freeId}", method = RequestMethod.POST)
	public String finishJob(ModelMap model,
			@PathVariable int jobId,
			@PathVariable int freeId){	
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();		
		try {
			Jobs thisJob = (Jobs) session.get(Jobs.class, jobId);
			Users freelance = (Users) session.get(Users.class, freeId);
			Users employer = (Users) session.get(Users.class, ((Users)HttpSS.getAttribute("userLogin")).getId());
			String hql = "FROM JobsUser AS JU WHERE JU.id.userId = :userId AND JU.id.jobId = :jobId";
			JobsUser offer = (JobsUser) session.createQuery(hql).setParameter("userId", freeId).setParameter("jobId", jobId).uniqueResult();
			
			freelance.setThuChi(freelance.getThuChi() + Long.parseLong(offer.getChaoGia()));
			employer.setThuChi(employer.getThuChi() + Long.parseLong(offer.getChaoGia()));
			thisJob.setHoanThanh(true);
			
			session.update(freelance);
			session.update(employer);
			session.update(thisJob);
			//System.out.println("zô");
			finishJobEmail(freelance, thisJob);
			tr.commit();
			return "redirect:" + "/rating/" + String.valueOf(employer.getId()) + "/" + String.valueOf(freeId) +".htm";
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();			
		} finally {
			session.close();
		}

		return "error";
	}
	
	
	private float getRatingUser(int userId) {
		Session session = factory.openSession();
		float res = 0;
			
		try {
			String hql = "FROM Rating AS R WHERE R.id.userId = :id";
			List<Rating> list = session.createQuery(hql).setParameter("id", userId).list();
			float total = 0;
			if(list != null && list.size() > 0) {
				for (Rating x : list) {
					total += x.getRate();
				}
				float sumR =  (float) Math.floor(((total*10)/list.size()+4)/5);
				//System.out.println(sumR);
				res =  (sumR*5)/10;
			}
			
		} catch (Exception e) {
			
		}
		return res;
	}
	
	private void finishJobEmail(Users user, Jobs job) {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			
			
			//gửi mail
			helper.setFrom("n17dccn115@student.ptithcm.edu.vn", "Freelance");
			helper.setTo(user.getEmail());
			helper.setReplyTo("n17dccn115@student.ptithcm.edu.vn", "Freelance");
			helper.setSubject("[CÔNG VIỆC ĐÃ HOÀN TẤT]");
			helper.setPriority(1);
			String body = 
					"Chúc mừng " + user.getHoTen() + ",\n"
							+ "Công việc  " + job.getTen().toString().toUpperCase() + " của bạn đã được xác nhận hoàn thành!\n"
							+ "Lương của bạn đã được cộng vào tài khoản! \n";
			helper.setText(body, false);
			mailer.send(mail);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
