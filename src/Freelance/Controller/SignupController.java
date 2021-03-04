package Freelance.Controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Freelance.bean.Account;
import Freelance.bean.signupAccount;
import Freelance.bean.Users;

@Transactional
@Controller
@RequestMapping("signup")
public class SignupController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping()
	public String signup(ModelMap model, HttpSession httpSS) {
		model.addAttribute("userLogin", httpSS.getAttribute("userLogin"));
		model.addAttribute("sign", new signupAccount());
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String search(ModelMap model, @Validated @ModelAttribute("sign") signupAccount acc, BindingResult errors, HttpSession httpSS) {
		model.addAttribute("userLogin", httpSS.getAttribute("userLogin"));
//		String text = kS;
		if(errors.hasErrors()) {
			model.addAttribute("sign", acc);
			return "signup";
		}
		else {
			if(acc.getPass().equals(acc.getRepass())) {
				Session session = factory.openSession();
				Transaction tr = session.beginTransaction();
				try {
					String hql = "FROM Users AS US WHERE US.email = :email";
					Users newUser = (Users) session.createQuery(hql).setParameter("email", acc.getEmail()).uniqueResult();
					if(newUser != null) {
						model.addAttribute("message", "Email bị trùng!");
				    	model.addAttribute("sign", acc);
				    	return "signup";
					}
					else {
						Account test = new Account(acc.getAcc(), acc.getPass());
						
						Users user = new Users(acc.getName(), acc.getEmail(), acc.isType());
						user.setAccount(test);
						test.setUsers(user);
						
						session.save(test);
						session.save(user);
						tr.commit();
						model.addAttribute("message", "Thêm mới thành công!");
						httpSS.setAttribute("userLogin", user);
						return "redirect:/index.htm";
					}
					
					
				} catch (Exception e) {
					Throwable t = e.getCause();
//					    while ((t != null) && !(t instanceof ConstraintViolationException)) {
//					        t = t.getCause();
//					        System.out.println(t.toString());
//					    }
					    if (t != null && t.toString().contains("duplicate")) {
					    	model.addAttribute("message", "Tài khoản bị trùng!");
					    	model.addAttribute("sign", acc);
					    }
					    else {
					    	tr.rollback();
							model.addAttribute("message", e.toString() + "Thêm mới thất bại!");
					    }
					
					
				} finally {
					session.close();
				}
				return "signup";
			}
			else {
				
				model.addAttribute("message", "Mật khẩu xác nhận không đúng!");
				model.addAttribute("sign", acc);
				return "signup";
			}
		}
		
	}
}
