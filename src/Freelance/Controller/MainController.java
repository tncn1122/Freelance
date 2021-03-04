package Freelance.Controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import Freelance.bean.Account;
import Freelance.bean.Favorites;
import Freelance.bean.FavoritesId;
import Freelance.bean.Jobs;
import Freelance.bean.JobsSkill;
import Freelance.bean.JobsSkillId;
import Freelance.bean.Skills;
import Freelance.bean.UserSkill;
import Freelance.bean.UserSkillId;
import Freelance.bean.Users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Controller
@EnableWebMvc
public class MainController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("logout")
	public String index(HttpSession session) {
		session.invalidate();
		return "redirect:/index.htm";
	}
	
	@RequestMapping("error")
	public String error() {
		
		return "error";
	}
	
	@RequestMapping(value = "ajax/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void saveSkill(@RequestBody String listSkill, @PathVariable int id) {
		Gson gsonReceiver = new Gson();
		List<String> obj = gsonReceiver.fromJson(listSkill, List.class);
		//System.out.println(listSkill);
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Account acc = (Account) session.get(Account.class, id);
			String hql = "FROM UserSkill AS US WHERE US.account.id = :id";
			List<UserSkill> oldList = (List<UserSkill>) session.createQuery(hql).setParameter("id", id).list();
			for(UserSkill oldSkill : oldList) {
				session.delete(oldSkill);
			}
			for(String skillId : obj) {
				Skills skillName = (Skills) session.get(Skills.class, Integer.parseInt(skillId));
				UserSkillId UIid = new UserSkillId(id, Integer.parseInt(skillId));
				UserSkill US = new UserSkill(UIid, acc, skillName);
				
				session.merge(US);
			}
			tr.commit();
			
		} catch (Exception e) {
			tr.rollback();
			System.out.println(e.toString());
		} finally {
			session.close();
		}
		

	}
	
	
	@RequestMapping(value = "ajax/job/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void saveJobSkill(@RequestBody String listSkill, @PathVariable int id) {
		Gson gsonReceiver = new Gson();
		List<String> obj = gsonReceiver.fromJson(listSkill, List.class);
//		System.out.println(id);
//		System.out.println(listSkill);

		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		id++;
		try {
			TimeUnit.SECONDS.sleep(1);
			Jobs jobInfo = (Jobs) session.get(Jobs.class, id);
			String hql = "FROM JobsSkill AS JS WHERE JS.id.id = :id";
			List<JobsSkill> oldList = (List<JobsSkill>) session.createQuery(hql).setParameter("id", id).list();
			for(JobsSkill oldSkill : oldList) {
				session.delete(oldSkill);
			}
			for(String skillId : obj) {
				Skills skillName = (Skills) session.get(Skills.class, Integer.parseInt(skillId));
				JobsSkillId UIid = new JobsSkillId(id, Integer.parseInt(skillId));
				JobsSkill JS = new JobsSkill(UIid, jobInfo, skillName);
				
				session.merge(JS);
			}
			
			tr.commit();
//			Jobs test = (Jobs) session.get(Jobs.class, id);
//			for(JobsSkill x : test.getJobsSkills()) {
//				System.out.println(x.getSkills().getSkill());
//			}
			
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			session.close();
		}
	}
	
	
	@RequestMapping(value = "favorite/{jobId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void addFavorite(HttpSession HttpSS, @PathVariable int jobId){
		Users thisUser = (Users) HttpSS.getAttribute("userLogin");
		
		if(thisUser != null) {
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try {
				String hql = "FROM Favorites AS FA WHERE FA.id.jobId = :id";
				Favorites myFav =  (Favorites) session.createQuery(hql).setParameter("id", jobId).uniqueResult();
				hql = "FROM Jobs AS J WHERE J.id = :id";
				Jobs job =  (Jobs) session.createQuery(hql).setParameter("id", jobId).uniqueResult();
//				
				if(myFav == null) {
					//System.out.println(jobId);
					myFav = new Favorites();
					Account acc = thisUser.getAccount();
					myFav.setJobs(job);
					myFav.setAccount(acc);
					myFav.setId(new FavoritesId(thisUser.getId(), jobId));
					session.save(myFav);
					session.update(acc);
				}
				else {
					session.delete(myFav);
				}
				
				tr.commit();			
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
				System.out.println(e.toString());
			} finally {
				session.close();
			}
		}
		
	}

}
