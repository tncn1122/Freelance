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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Freelance.bean.Cagetory;
import Freelance.bean.Favorites;
import Freelance.bean.Jobs;
import Freelance.bean.Users;
import Freelance.bean.general;

@Transactional
@Controller
@RequestMapping(value = "search")
public class SearchController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	HttpSession HttpSS;
	
	@RequestMapping()
	public String search(ModelMap model) {
		loadCagetories(model);
		loadJobs(model);
		if(HttpSS.getAttribute("userLogin") != null) {
			loadFavorite(model);
		}
		return "search";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String searchKey(ModelMap model,
			@RequestParam("cagetoryId") int cage,
			@RequestParam("keySearch") String keySearch) {
		loadCagetories(model);
		if(HttpSS.getAttribute("userLogin") != null) {
			loadFavorite(model);
		}
		//System.out.println(cage);
		//System.out.println(keySearch);
		List<Jobs> listJobs = search(keySearch, cage);
		if(listJobs == null || listJobs.size() == 0) {
			model.addAttribute("jobsMess", "Không Tìm Thấy Kết Quả Phù Hợp!");
		}
		
		model.addAttribute("keySearch", keySearch);
		model.addAttribute("listJobs", listJobs);
		return "search";
	}
	
	@RequestMapping(value = "/{cage}.htm")
	public String searchKey1(ModelMap model,
			@PathVariable int cage) {
		Session session = factory.openSession();
		loadCagetories(model);
		
		if(HttpSS.getAttribute("userLogin") != null) {
			loadFavorite(model);
		}
		String keySearch = (String) ((Cagetory) session.get(Cagetory.class, cage)).getLinhVuc();
		
		List<Jobs> listJobs = search(keySearch, cage);
		if(listJobs == null || listJobs.size() == 0) {
			model.addAttribute("jobsMess", "Không Tìm Thấy Kết Quả Phù Hợp!");
		}
		
		model.addAttribute("keySearch", keySearch);
		model.addAttribute("listJobs", listJobs);
		return "search";
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
			model.addAttribute("jobsMess", "Lỗi Load Fav!");
			e.printStackTrace();
		} finally {
			//session.close();
		}
	}
	
//	public List<Jobs> search(String keySearch){
//		Session session = factory.openSession();
//		List<Jobs> res = null;
//		
//		FullTextSession fullTextSession = Search.getFullTextSession(session);
//		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Jobs.class).get();
//		org.apache.lucene.search.Query query = qb
//				.keyword().onFields("ten")
//				.matching(keySearch)
//				.createQuery();
//		org.hibernate.Query hibQuery =
//				fullTextSession.createFullTextQuery(query, Jobs.class);
//		
//		res = hibQuery.list();
//		return res;
//	}
	public List<Jobs> search(String keySearch){
		Session session = factory.openSession();
		String hql = "FROM Jobs";
		List<Jobs> res = session.createQuery(hql).list();
//		FullTextSession fullTextSession = Search.getFullTextSession(session);
//		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Jobs.class).get();
//		org.apache.lucene.search.Query query = qb
//					.keyword().onFields("ten")
//					.matching(keySearch)
//					.createQuery();
//		org.hibernate.Query hibQuery =
//				fullTextSession.createFullTextQuery(query, Jobs.class);
		
//		res = hibQuery.list();
		return res;
	 }
	
	public List<Jobs> search(String keySearch, int cage){
		Session session = factory.openSession();
		List<Jobs> res = new ArrayList<Jobs>();
		if (cage != -1) {
			String hql = "FROM Jobs AS J WHERE J.jobCagetory.cagetory.id = :id";
			List<Jobs> resJobs =  session.createQuery(hql).setParameter("id", cage).list();
			if(resJobs != null) {
				for(Jobs x : resJobs) {
					res.add(new Jobs(x));
				}
			}
			
		}
		else {
			res = search(keySearch);
		}
		
		return res;
	 }
	
	public class CustomComparator {
	    public boolean compare(general thisObj, general other) {
	    		return thisObj.getHoanThanh() > other.getHoanThanh();
	    }
	}
}
