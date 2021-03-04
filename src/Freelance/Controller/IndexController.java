package Freelance.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Freelance.bean.Cagetory;
import Freelance.bean.Favorites;
import Freelance.bean.Jobs;
import Freelance.bean.Rating;
import Freelance.bean.RatingFa;
import Freelance.bean.Users;
import Freelance.bean.general;



@Transactional
@Controller
@RequestMapping(value = "index")
public class IndexController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	HttpSession HttpSS;
	
	
	@RequestMapping()
	public String index(ModelMap model) {
		model.addAttribute("message", "empty");
		loadNhanXet(model);
		loadCagetories(model);
		loadJobs(model);
		if(HttpSS.getAttribute("userLogin") != null) {
			loadFavorite(model);
		}
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search(ModelMap model, @RequestParam("keySearch") String ks) {
		model.addAttribute("userLogin", HttpSS.getAttribute("userLogin"));
		System.out.println("pressed");
		model.addAttribute("message", ks);
		return "index";
	}
	
	public void loadNhanXet(ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM RatingFa";
		try {
			
			List<RatingFa> ratingList =  session.createQuery(hql).list(); 
			model.addAttribute("ratingList", ratingList);
			if(ratingList.size() == 0) {
				model.addAttribute("ratingMess", "Chưa Có");
			}
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("ratingMess", "Lỗi Load");
			
		} finally {
			//session.close();
		}
	}
	
	public void loadCagetories(ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Cagetory";
		try {
			List<Cagetory> cagetoryList =  session.createQuery(hql).list(); 
			model.addAttribute("listCagetories", cagetoryList);
			
			List<general> list = new ArrayList<general>();
			for(Cagetory x : cagetoryList) {
				general newItem = new general();
				newItem.setHoTen(x.getLinhVuc());
				hql = "FROM Jobs AS J WHERE J.jobCagetory.cagetory.id = :id";
				List<Jobs> resJobs =  session.createQuery(hql).setParameter("id", x.getId()).list();
				newItem.setHoanThanh(resJobs.size());
				newItem.setChaoGia(x.getId());
				list.add(newItem);
			}
			Collections.sort(list);
			//System.out.println(list.size());
			model.addAttribute("list", list);
			
			
			
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			
		} finally {
			//session.close();
		}
	}
	
	
	public void loadJobs(ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Jobs";
		try {
			
			List<Jobs> jobsList =  session.createQuery(hql).list(); 
			Collections.reverse(jobsList);
			model.addAttribute("listJobs", jobsList);
			
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("jobsMess", "Lỗi Load!");
			
		} finally {
			//session.close();
		}
	}
	
	public void loadFavorite (ModelMap model) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		String hql = "FROM Favorites AS F WHERE F.id.userId = :id";
		try {
			Users user = (Users) HttpSS.getAttribute("userLogin");
			List<Favorites> favJobs =  session.createQuery(hql).setParameter("id", user.getId()).list(); 
			Set<Integer> fav = new HashSet<Integer>();
			for(Favorites jobId : favJobs) {
				fav.add(jobId.getId().getJobId());
			}
			model.addAttribute("fav", fav);
			
			//System.out.println(fav.contains(68));
			
		} catch (Exception e) {
			tr.rollback();
			model.addAttribute("jobsMess", "Lỗi Load Fav!");
			e.printStackTrace();
		} finally {
			//session.close();
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
	
}
