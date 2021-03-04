package Freelance.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import Freelance.bean.Cagetory;
import Freelance.bean.JobCagetory;
import Freelance.bean.Skills;


@Transactional
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "/skills")
	public String skill(ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Skills";
		try {
			List<Skills> skillList =  session.createQuery(hql).list(); 
			if(skillList != null) {
				model.addAttribute("skillList", skillList);
			}
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		model.addAttribute("skillInfo", new Skills());
		return "admin/skills";
	}
	
	@RequestMapping(value = "/skills", method = RequestMethod.POST, params = "submit")
	public String submitSkill(ModelMap model, @ModelAttribute("skillInfo") Skills skill) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(skill);
			tr.commit();
			model.addAttribute("message", "Thêm Kỹ Năng Thành Công!");
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", "Thêm Kỹ Năng Thất Bại!");
			
		} finally {
			session.close();
		}
		return skill(model);
	}
	
	@RequestMapping(value = "/skills/{id}")
	public String editSkill(ModelMap model, @PathVariable int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Skills";
		try {
			List<Skills> skillList =  session.createQuery(hql).list(); 
			if(skillList != null) {
				model.addAttribute("skillList", skillList);
			}
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		}
		
		try {
			Skills info =  (Skills) session.get(Skills.class, id); 
			model.addAttribute("skillInfo", info);
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Load Kỹ Năng Thất Bại!");
			model.addAttribute("skillInfo", new Skills());
		} finally {
			session.close();
		}

		return "admin/skills";
	}
	
	@RequestMapping(value = "/skills/{id}", method = RequestMethod.POST, params = "submit")
	public RedirectView submitEditSkill(ModelMap model, @ModelAttribute("skillInfo") Skills skill, @PathVariable int id) {
		submitSkill(model, skill);
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("/FREELANCE/admin/skills.htm");
	    return redirectView;
	}
	
	
	//================================================================================
	
	@RequestMapping(value = "/job-cagetory")
	public String cagetory(ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Cagetory";
		try {
			List<Cagetory> cagetoryList =  session.createQuery(hql).list(); 
			if(cagetoryList != null) {
				model.addAttribute("cagetoryList", cagetoryList);
			}
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		model.addAttribute("cagetoryInfo", new Cagetory());
		return "admin/cagetory";
	}
	
	@RequestMapping(value = "/job-cagetory", method = RequestMethod.POST, params = "submit")
	public String submitCagetory(ModelMap model, @ModelAttribute("cagetoryInfo") Cagetory cage) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(cage);
			tr.commit();
			model.addAttribute("message", "Thêm Lĩnh Vực Thành Công!");
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", "Thêm Lĩnh Vực Thất Bại!");
			
		} finally {
			session.close();
		}
		return cagetory(model);
	}
	
	@RequestMapping(value = "/job-cagetory/{id}")
	public String editCagetory(ModelMap model, @PathVariable int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Cagetory";
		try {
			List<Cagetory> cagetoryList =  session.createQuery(hql).list(); 
			if(cagetoryList != null) {
				model.addAttribute("cagetoryList", cagetoryList);
			}
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		}
		
		try {
			Cagetory info =  (Cagetory) session.get(Cagetory.class, id); 
			model.addAttribute("cagetoryInfo", info);
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Load Lĩnh Vực Thất Bại!");
			model.addAttribute("cagetoryInfo", new Cagetory());
		} finally {
			session.close();
		}

		return "admin/cagetory";
	}
	
	@RequestMapping(value = "/job-cagetory/{id}", method = RequestMethod.POST, params = "submit")
	public RedirectView submitEditCage(ModelMap model, @ModelAttribute("cagetoryInfo") Cagetory cage, @PathVariable int id) {
		submitCagetory(model, cage);
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("/FREELANCE/admin/job-cagetory.htm");
	    return redirectView;
	}
	
	
	//================================================================================
	
	
	@RequestMapping(value = "/job-type")
	public String jobtype(ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Cagetory";
		String hql2 = "FROM JobCagetory";
		try {
			List<Cagetory> cagetoryList =  session.createQuery(hql).list(); 
			List<JobCagetory> jobList =  session.createQuery(hql2).list();
			model.addAttribute("cagetoryList", cagetoryList);
			model.addAttribute("jobinfoList", jobList);
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		} finally {
			session.close();
		}
		model.addAttribute("jobInfo", new JobCagetory());
		return "admin/jobtype";
	}
	
	@RequestMapping(value = "/job-type", method = RequestMethod.POST, params = "submit")
	public String submitJobCagetory(ModelMap model, @ModelAttribute("jobInfo") JobCagetory JC, @RequestParam("cagetoryId") int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Cagetory cage = (Cagetory) session.get(Cagetory.class, id);
			
			
			cage.getJobCagetories().add(JC);
			JC.setCagetory(cage);
			
			
			session.saveOrUpdate(cage);
			session.saveOrUpdate(JC);

			tr.commit();
			model.addAttribute("message", "Thêm Lĩnh Vực Thành Công!");
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Thêm Lĩnh Vực Thất Bại!");
			
		} finally {
			session.close();
		}
		return jobtype(model);
	}
	
	@RequestMapping(value = "/job-type/{id}")
	public String editJobinfo(ModelMap model, @PathVariable int id) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Cagetory";
		String hql2 = "FROM JobCagetory";
		try {
			List<Cagetory> cagetoryList =  session.createQuery(hql).list(); 
			model.addAttribute("cagetoryList", cagetoryList);
			List<JobCagetory> jobList =  session.createQuery(hql2).list(); 
			model.addAttribute("jobinfoList", jobList);

		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Lỗi Load!");
			
		}
		
		try {
			JobCagetory JC =  (JobCagetory) session.get(JobCagetory.class, id); 
			model.addAttribute("jobInfo", JC);
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", e.toString() + "Load Lĩnh Vực Thất Bại!");
			model.addAttribute("cagetoryInfo", new Cagetory());
		} finally {
			session.close();
		}

		return "admin/jobtype";
	}
	
	@RequestMapping(value = "/job-type/{id}", method = RequestMethod.POST, params = "submit")
	public RedirectView submitEditJob(ModelMap model, @ModelAttribute("jobInfo") JobCagetory JC, 
			@PathVariable int id, @RequestParam("cagetoryId") int cageId) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Cagetory cage = (Cagetory) session.get(Cagetory.class, cageId);
			JobCagetory tmp = (JobCagetory) session.get(JobCagetory.class, id);
			cage.getJobCagetories().remove(tmp);
			cage.getJobCagetories().add(JC);
			
			JC.setCagetory(cage);
			session.merge(cage);
			session.merge(JC);

			tr.commit();
			model.addAttribute("message", "Thêm Lĩnh Vực Thành Công!");
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("message", "Thêm Lĩnh Vực Thất Bại!");
			
		} finally {
			session.close();
		}
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("/FREELANCE/admin/job-type.htm");
	    return redirectView;
	}
}
