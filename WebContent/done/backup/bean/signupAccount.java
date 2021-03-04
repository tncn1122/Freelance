package Freelance.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class signupAccount {
	@NotBlank(message = "Không được để trống tài khoản!")
	private String acc;
	@NotBlank(message = "Không được để trống mật khẩu")
	private String pass;
	@NotBlank(message = "Không được để trống mật khẩu")
	private String repass;
	@NotBlank(message = "Không được để trống tên")
	private String name;
	@NotBlank(message = "Không được để trống Email")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$", message = "Nhập đúng định dạng Email")
	private String email;
	
	public signupAccount(String acc, String pass, String repass, String name, String email) {
		super();
		this.acc = acc;
		this.pass = pass;
		this.repass = repass;
		this.name = name;
		this.email = email;
	}

	public signupAccount() {
		super();
	}
	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
