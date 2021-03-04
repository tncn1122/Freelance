package Freelance.Controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Freelance.bean.Account;
import Freelance.bean.Users;

@Transactional
@Controller
@RequestMapping("forget")
public class ForgetController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping()
	public String signup(ModelMap model) {
		return "forget";
	}
	
	@RequestMapping(params = "submit")
	public String forget(ModelMap model, @RequestParam("name") String acc) {
//		String text = kS;
		if(acc.isEmpty()) {
			model.addAttribute("message", "Vui Lòng Điền Tài Khoản!");
			return "forget";
		}
		else {
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try {
				String hql = "FROM Account AS AC WHERE AC.acc = :acc";
				Account user = (Account) session.createQuery(hql).setParameter("acc", acc).uniqueResult();
				if (user == null) {
					model.addAttribute("message", "Tài Khoản Không Tồn Tại!");
				}
				else {
					hql = "FROM Users AS US WHERE US.id = :id";
					Users info = (Users) session.createQuery(hql).setParameter("id", user.getId()).uniqueResult();
					String email = (String) info.getEmail();
					if(email == null) {
						model.addAttribute("message", "email chưa có");
					}else {
						// send email
						
						try {
							MimeMessage mail = mailer.createMimeMessage();
							MimeMessageHelper helper = new MimeMessageHelper(mail);
							
							String newPass = randomPassword();
							//set pass user
							user.setPass(newPass);
							session.update(user);
							tr.commit();
							//gửi mail
							helper.setFrom("n17dccn115@student.ptithcm.edu.vn", "Freelance");
							helper.setTo(email);
							helper.setReplyTo("n17dccn115@student.ptithcm.edu.vn", "Freelance");
							helper.setSubject("[KHÔI PHỤC MẬT KHẨU TÀI KHOẢN]");
							helper.setPriority(1);
							String body = 
									"Chào " + user.getUsers().getHoTen() + ",\n"
											+ "Đây là mật khẩu tạm của bạn: " + newPass + "\n"
											+ "Hãy đăng nhập lại hệ thống và đổi mật khẩu mới! \n";
							helper.setText(body, false);
							mailer.send(mail);
							String mailinfo = (String) email.subSequence(0, 3) + "***********";
							model.addAttribute("message", "Mật khẩu tạm đã gửi tới email: " + mailinfo);
						}catch(Exception e) {
							model.addAttribute("message", "Gửi Mail Khôi Phục Thất Bại!");
						}
						//
					}
					
					
					
				}
				
			} catch (Exception e) {
				tr.rollback();
				model.addAttribute("message", e.toString() + "Thao Tác Thất Bại!");
				
			} finally {
				session.close();
			}
			return "forget";
		}
	}
	
	private String randomPassword() {
	        String SEED = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder res = new StringBuilder();
	        Random rand = new Random();
	        for(int index = 0; index < 6; index++) {
	        	res.append(SEED.charAt(rand.nextInt(SEED.length())));
	        }
	        return res.toString();
	}
}
