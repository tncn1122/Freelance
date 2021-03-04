package Freelance.Controller;

import javax.servlet.http.HttpSession;
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

import Freelance.bean.Account;
import Freelance.bean.Rating;
import Freelance.bean.RatingId;
import Freelance.bean.Users;

@Transactional
@Controller
@RequestMapping("rating")
public class RatingController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	HttpSession HttpSS;
	
	@RequestMapping(value = "/{fromUserId}/{toUserId}")
	public String ratingUser(ModelMap model,
			@PathVariable int fromUserId,
			@PathVariable int toUserId) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		Rating rating = null;
		try {
			String hql = "FROM Rating AS R WHERE R.id.id = :fromUserId AND R.id.user = :toUserId";
//			System.out.println(fromUserId);
//			System.out.println(toUserId);
			rating = (Rating) session.get(Rating.class, new RatingId(fromUserId, toUserId));
			if(rating == null) {
				rating = new Rating();
			}
			Users infoUser = (Users) session.get(Users.class, toUserId);
			model.addAttribute("userAvt", infoUser.getAvt());
			model.addAttribute("userName", infoUser.getHoTen());
			model.addAttribute("from", fromUserId);
			model.addAttribute("to", toUserId);
			model.addAttribute("ratingContent", rating);
			
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();	
			e.getCause().printStackTrace();
			return "redirect:/error.htm";
		} finally {
			session.close();
		}
		
		
		model.addAttribute("ratingContent", rating);
		return "rating/ratingForm";
	}
	
	@RequestMapping(value = "/{fromUserId}/{toUserId}", method = RequestMethod.POST)
	public String submitRatingUser(ModelMap model,
			@ModelAttribute("ratingContent") Rating ratingContent,
			@PathVariable int fromUserId,
			@PathVariable int toUserId) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		Account from = (Account) session.get(Account.class, fromUserId);
		Account to = (Account) session.get(Account.class, toUserId);
		model.addAttribute("userAvt", to.getUsers().getAvt());
		model.addAttribute("userName", to.getUsers().getHoTen());
		model.addAttribute("from", from.getId());
		model.addAttribute("to", to.getId());
		try {
			
			from.getRatingsForId().add(ratingContent);
			to.getRatingsForUserId().add(ratingContent);
			
			ratingContent.setId(new RatingId(fromUserId, toUserId));
			ratingContent.setAccountById(from);
			ratingContent.setAccountByUserId(to);
		
			if(session.get(Rating.class, ratingContent.getId()) == null) {
				System.out.println("null");
				session.save(ratingContent);
			}
			else {
				System.out.println("not-null");
				session.merge(ratingContent);
			}
			
			session.update(from);
			session.update(to);
			
			tr.commit();
			model.addAttribute("message", "Gửi Thành Công!");
			
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();	
			model.addAttribute("message", "Gửi Thất Bại!");
		} finally {
			session.close();
		}
		
		
		model.addAttribute("ratingContent", ratingContent);
		return "rating/ratingForm";
	}

}
