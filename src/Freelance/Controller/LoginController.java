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
import Freelance.bean.Users;

@Transactional
@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping()
	public String loginIndex(ModelMap model, HttpSession httpSS) {
		model.addAttribute("login", new Account());
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(ModelMap model, @Validated @ModelAttribute("login") Account acc, BindingResult errors, HttpSession httpSS) {
//		String text = kS;
		if(errors.hasErrors()) {
			model.addAttribute("login", acc);
			return "login";
		}
		else {
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try {
				String hql = "FROM Account AS AC WHERE AC.acc = :acc";
				Account user = (Account) session.createQuery(hql).setParameter("acc", acc.getAcc()).uniqueResult();
				if (user == null) {
					model.addAttribute("message", "Tài Khoản Không Tồn Tại!");
				}
				else {
					if (user.getPass().equals(acc.getPass())) {
						Users info = (Users) session.get(Users.class, user.getId());
						httpSS.setAttribute("userLogin", info);
						return "redirect:/index.htm";
					}
					else {
						model.addAttribute("message", "Sai Mật Khẩu!");
					}
				}
				
			} catch (Exception e) {
				tr.rollback();
				model.addAttribute("message", e.toString() + "Đăng Nhập Thất Bại!");
				
			} finally {
				session.close();
			}
			return "login";
		}
	}
}
