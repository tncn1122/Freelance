package Freelance.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class signupAccount {
	

	@NotBlank(message = "acc")
	private String acc;
	@NotBlank(message = "pass")
	private String pass;
	@NotBlank(message = "repass")
	private String repass;
	@NotBlank(message = "name")
	private String name;
	@NotBlank(message = "Email")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$", message = "Email")
	private String email;
	
	private boolean type;

	public signupAccount(String acc, String pass, String repass, String name, String email, boolean type) {
		super();
		this.acc = acc;
		this.pass = pass;
		this.repass = repass;
		this.name = name;
		this.email = email;
		this.type = type;
	}

	public signupAccount() {
		super();
		this.type = true;
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

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
}
