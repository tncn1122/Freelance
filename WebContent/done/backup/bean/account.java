package Freelance.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity 
public class account {
	@NotBlank(message = "Không được để trống tài khoản!")
	@Column(unique = true)
	private String acc;
	
	@NotBlank(message = "Không được để trống mật khẩu")
	private String pass;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	public account(String acc, String pass, int id) {
		super();
		this.acc = acc;
		this.pass = pass;
		this.id = id;
	}
	public account(String acc, String pass) {
		super();
		this.acc = acc;
		this.pass = pass;
	}
	public account() {
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
