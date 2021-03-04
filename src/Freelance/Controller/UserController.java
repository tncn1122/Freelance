package Freelance.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import Freelance.bean.Account;
import Freelance.bean.Favorites;
import Freelance.bean.Jobs;
import Freelance.bean.JobsUser;
import Freelance.bean.Rating;
import Freelance.bean.RatingFa;
import Freelance.bean.Skills;
import Freelance.bean.UserSkill;
import Freelance.bean.Users;
import Freelance.bean.general;
import Freelance.bean.listFav;

@Transactional
@Controller
@RequestMapping("user")
public class UserController {
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg");
	@Autowired
	ServletContext context;
	@Autowired
	SessionFactory factory;
	@Autowired
	HttpSession HttpSS;
	
	
	
	@RequestMapping(value = "/{id}")
	public String user(ModelMap model, @PathVariable int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			String hql = "FROM Users AS US WHERE US.id = :id";
			Users user = (Users) session.createQuery(hql).setParameter("id", id).uniqueResult();
			if (user == null) {
				model.addAttribute("userMess", "Tài Khoản Không Tồn Tại!");
			}
			else {
				// load thông tin
				model.addAttribute("userInfo", user);
				model.addAttribute("userSkill", getUserSkill(id));
				
				// load danh sách yêu thích
				hql = "FROM Favorites AS F WHERE F.id.userId = :id";
				List<Favorites> listFav =  session.createQuery(hql).setParameter("id", id).list(); 
				List<listFav> favJobs = new ArrayList<listFav>();
				for(Favorites x : listFav) {
					favJobs.add(new listFav(
							x.getJobs().getId(), 
							x.getJobs().getTen(), 
							x.getJobs().getAccount().getUsers().getHoTen(), 
							getJobOffersCount(x.getJobs().getId())));
				}
				Collections.reverse(favJobs);
				model.addAttribute("fav", favJobs);
		
				if(user.getType()) {
					// freelancer =======================================================
					int createdJobs = 0;
					int doneJobs = 0;
					List<Jobs> userJobs = new ArrayList<Jobs>();
					hql = "FROM JobsUser AS JU WHERE JU.id.userId = :id";
					List<JobsUser> listJobs =  session.createQuery(hql).setParameter("id", id).list();
					for(JobsUser x : listJobs) {
						createdJobs++;
						if(x.getJobs().getHoanThanh()) {
							doneJobs++;
						}
						userJobs.add(new Jobs(x.getJobs()));
					}
					Collections.reverse(userJobs);
					model.addAttribute("createdJobs", createdJobs);
					model.addAttribute("doneJobs", doneJobs);
					model.addAttribute("userJobs", userJobs);
				}
				else {
					// nhà tuyển dụng ====================================================
					int createdJobs = 0;
					int doneJobs = 0;
					List<Jobs> userJobs = new ArrayList<Jobs>();
					hql = "FROM Jobs AS J WHERE J.account.id = :id ORDER BY id Desc";
					List<Jobs> listJobs =  session.createQuery(hql).setParameter("id", user.getId()).list();
					for(Jobs x : listJobs) {
						createdJobs++;
						if(x.getHoanThanh()) {
							doneJobs++;
						}
						userJobs.add(new Jobs(x));
					}
					//Collections.reverse(userJobs);
					model.addAttribute("createdJobs", createdJobs);
					model.addAttribute("doneJobs", doneJobs);
					model.addAttribute("userJobs", userJobs);
				}
				// tính điểm rating
				model.addAttribute("userRating", getRatingUser(id));
				List<general> ratingList = getListRatingUser(id);
				if(ratingList.size() == 0) {
					model.addAttribute("ratingMess", "Chưa Có");
				}
				else {
					model.addAttribute("ratingList", ratingList);
				}
				
			}
			
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			model.addAttribute("message", e.toString() + "Thao Tác Thất Bại!");
			
		} finally {
			session.close();
		}
		return "user/user";
	}
	
	
	@RequestMapping(value = "/{id}/rating")
	public String ratingFa(ModelMap model, @PathVariable int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			RatingFa FA = (RatingFa) session.get(RatingFa.class, id);
			if (FA == null) {
				model.addAttribute("ratingContent", new RatingFa(id));
			}
			else {
				model.addAttribute("ratingContent", FA);
			}
			
			return "user/rating";
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Thao Tác Thất Bại!");
			return "user/rating";
		} finally {
			session.close();
		}
		
	}
	
	@RequestMapping(value = "/{id}/rating", method = RequestMethod.POST)
	public String ratingFaSub(ModelMap model, @ModelAttribute("ratingContent") RatingFa content, @PathVariable int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Account Ac = (Account) session.get(Account.class, id);
			RatingFa rating = (RatingFa) session.get(RatingFa.class, id);
			Ac.setRatingFa(content);
			content.setAccount(Ac);
			
			session.update(Ac);
			if(rating == null) {
				session.save(content);
			}else {
				session.merge(content);
			}
			tr.commit();
			model.addAttribute("message", "Gửi Thành Công!");
			return "user/rating";
			}		
		catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Thao Tác Thất Bại!");
			return "user/rating";
		} finally {
			session.close();
		}
		
	}
	
	
	@RequestMapping(value = "/{id}/account")
	public String changeInfoAccount(ModelMap model, @PathVariable int id, HttpSession httpSS) {
		Users user = (Users) httpSS.getAttribute("userLogin");
		if(user != null) {
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try {
				Users now = (Users) session.get(Users.class, user.getId());
				model.addAttribute("user", now);
				return "user/account";
			} catch (Exception e) {
				Throwable t = e.getCause();
		    	tr.rollback();
			} finally {
				session.close();
			}
		}
		
		return "redirect:/login.htm";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	
	@RequestMapping(value = "/{id}/account", method = RequestMethod.POST)
	public String submitInfoAccount(ModelMap model, @PathVariable int id, @ModelAttribute("user") Users user, 
			@RequestParam("image") MultipartFile image, BindingResult errors) {
		
		// check image
		if(!image.isEmpty()) {
			String fileContentType = image.getContentType();
		    if(contentTypes.contains(fileContentType)) {
		    	String newName = Integer.toString(id) + "." + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
		    	String linkToAvt = "img/userAvatar/" + newName;
		        String path = this.context.getRealPath(linkToAvt);
		        
		        try {
					image.transferTo(new File(path));
					user.setAvt(linkToAvt);
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IllegalStateException e) {
					model.addAttribute("avata", "Lỗi Ghi File!");
					e.printStackTrace();
				} catch (IOException e) {
					model.addAttribute("avata", "Lỗi Ghi File!");
					e.printStackTrace();
				} catch(MaxUploadSizeExceededException e) {
		        	model.addAttribute("avata", "File vượt quá 2mb!");
		        	e.printStackTrace();
		        }
		    } else {
		    	model.addAttribute("avata", "Tệp Không Được Hỗ Trợ!");
		    }
		}
		else {
			user.setAvt(((Users) HttpSS.getAttribute("userLogin")).getAvt());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("message", "Lưu Thành Công!");
		}
		
		// validator
		if(user.getHoTen().toString().length() == 0) {
			errors.rejectValue("hoTen", "user");
		}
		if(user.getEmail().isEmpty() || !user.getEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$")) {
			errors.rejectValue("email", "user");
		}
		if(user.getSdt().length() > 0 && !user.getSdt().matches("^[0-9]*$")) {
			errors.rejectValue("sdt", "user");
		}
		if(errors.hasErrors()) {
			model.addAttribute("user", user);
			
			return "user/account";
		}
		
		else{
			
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try {
				String hql = "FROM Users AS US WHERE US.email = :email";
				Users newUser = (Users) session.createQuery(hql).setParameter("email", user.getEmail()).uniqueResult();
				if(newUser != null && newUser.getId() != user.getId()) {
					model.addAttribute("message", "Email bị trùng!");
					model.addAttribute("user", user);
					return "user/account";
				}
				Users now = (Users) session.get(Users.class, user.getId());
				now.setAvt(user.getAvt());
				now.setHoTen(user.getHoTen());
				now.setEmail(user.getEmail());
				now.setSdt(user.getSdt());
				now.setNgaySinh(user.getNgaySinh());
				now.setDiaChi(user.getDiaChi());
				session.update(now);
				HttpSS.setAttribute("userLogin", now);
				
				tr.commit();
				model.addAttribute("message", "Lưu Thành Công!");
				model.addAttribute("user", now);
				return "user/account";
			} catch (Exception e) {
				Throwable t = e.getCause();
		    	tr.rollback();
				model.addAttribute("message", e.toString() + "Lưu Thất Bại!");
			} finally {
				session.close();
			}
		}

		return "user/account";
	}
	
	@RequestMapping(value = "/{id}/change-pass")
	public String changePass(ModelMap model, @PathVariable int id, HttpSession httpSS) {
		Users user = (Users) httpSS.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("user", user);
			
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try {
				Users now = (Users) session.get(Users.class, user.getId());
				model.addAttribute("user", now);
				return "user/changePass";
			} catch (Exception e) {
				Throwable t = e.getCause();
		    	tr.rollback();
			} finally {
				session.close();
			}
			
		}
		
		return "redirect:/login.htm";
	}
	
	@RequestMapping(value = "/{id}/change-pass", method = RequestMethod.POST)
	public String submitChangePass(ModelMap model, @PathVariable int id,
			@RequestParam("oldPass") String oldPass,
			@RequestParam("newPass") String newPass,
			@RequestParam("rePass") String rePass,
			HttpSession httpSS) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Account ac = (Account) session.get(Account.class, id);
			if(oldPass.equals(ac.getPass())) {
				if (newPass.equals(rePass)) {
					ac.setPass(newPass);
					session.update(ac);
					model.addAttribute("message",  "Đổi Mật Khẩu Thành Công!");
				}else {
					model.addAttribute("message",  "Mật Khẩu Xác Nhận Không Đúng!");
				}
			}
			else {
				model.addAttribute("message",  "Mật Khẩu Cũ Không Đúng!");
			}
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message",  e.toString() + "Thao Tác Thất Bại!");
			
		} finally {
			session.close();
		}
		model.addAttribute("user", ((Users) httpSS.getAttribute("userLogin")));
		return "user/changePass";
	}
	
	@RequestMapping(value = "/{id}/change-info")
	public String changeInfoUser(ModelMap model, @PathVariable int id, HttpSession httpSS) {
		Users user = (Users) httpSS.getAttribute("userLogin");
		if(user != null) {
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM Skills";
			try {
				Users now = (Users) session.get(Users.class, user.getId());
				List<Skills> listSkill =  session.createQuery(hql).list();
				Map<Skills, Boolean> userSkill = new HashMap<Skills, Boolean>();
				for(Skills x : listSkill) {
					userSkill.put(x, false);
				}
				for(Skills x : getUserSkill(id)) {
					userSkill.put(x, true);
				}
				model.addAttribute("userSkill", userSkill);
				model.addAttribute("user", now);
				return "user/changeInfo";
			} catch (Exception e) {
				Throwable t = e.getCause();
				model.addAttribute("message", e.toString() + "Lưu Thất Bại!");
		    	tr.rollback();
			} finally {
				session.close();
			}
			model.addAttribute("user", user);
			return "user/changeInfo";
		}
		return "redirect:/login.htm";
	}
	
	@RequestMapping(value = "/{id}/change-info", method = RequestMethod.POST)
	public String submitInfoUser(ModelMap model, @PathVariable int id, @ModelAttribute("user") Users user) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Users now = (Users) session.get(Users.class, user.getId());
			now.setChucDanh(user.getChucDanh());
			now.setGioiThieu(user.getGioiThieu());
			session.update(now);		
			tr.commit();
			model.addAttribute("message", "Lưu Thành Công!");
		} catch (Exception e) {
			Throwable t = e.getCause();
	    	tr.rollback();
			model.addAttribute("message",  "Lưu Thất Bại!");
		} finally {
			session.close();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return changeInfoUser(model, id, HttpSS);
	}
	
	private List<Skills> getUserSkill(int id){
		Session session = factory.openSession();
		List<Skills> userSkill = new ArrayList<Skills>();
			
		try {
			String hql = "FROM UserSkill AS US WHERE US.id.id = :id";
			List<UserSkill> list = session.createQuery(hql).setParameter("id", id).list();
			for(UserSkill x : list) {
				if(x != null) {
					userSkill.add(x.getSkills());
				}
			}
		} catch (Exception e) {
			
		}
		return userSkill;
	}
	
	@RequestMapping(value = "/{id}/change-info", method = RequestMethod.POST, produces = "application/json")
	public void saveSkill(@RequestBody List<String> listSkill) {
		for(String x : listSkill) {
			System.out.println(x);
		}

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
	
	private List<general> getListRatingUser(int userId) {
		Session session = factory.openSession();

		List<general> res = new ArrayList<general>();
		try {
			String hql = "FROM Rating AS R WHERE R.id.userId = :id";
			List<Rating> list = session.createQuery(hql).setParameter("id", userId).list();
			for(Rating x : list) {
				general newInfo = new general();
				newInfo.setAvt(x.getAccountById().getUsers().getAvt());
				newInfo.setRating(x.getRate());
				newInfo.setGioiThieu(x.getCmt());
				newInfo.setId(x.getId().getId());
				newInfo.setHoTen(x.getAccountById().getUsers().getHoTen());
				res.add(newInfo);
			}
			
		} catch (Exception e) {
			
		}
		return res;
	}
	
	private int getJobOffersCount(int jobId){
		List<general> listOffers = new ArrayList<general>();
		Session session = factory.openSession();
		int res = 0;
		try {
			String hql = "FROM JobsUser AS JU WHERE JU.id.jobId = :jobId";
			List<JobsUser> listJU = session.createQuery(hql).setParameter("jobId", jobId).list();
			res = listJU.size();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		} finally {
			session.close();
		}
		
		return res;
	}

}
