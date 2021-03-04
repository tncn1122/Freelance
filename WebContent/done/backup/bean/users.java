package Freelance.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class users {
	
	@Id
	private int id;
	@OneToOne
	@PrimaryKeyJoinColumn(name = "account_id")
	private account account;
	
	private String ho_ten;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngay_sinh;
	private String email;
	private int sdt;
	private String dia_chi;
	private boolean type;
	private String chuc_danh;
	private String gioi_thieu;
	private long thu_chi;
	private String avt;
	public users(int id, Freelance.bean.account account, String ho_ten, Date ngay_sinh, String email, int sdt,
			String dia_chi, boolean type, String chuc_danh, String gioi_thieu, long thu_chi, String avt) {
		super();
		this.id = id;
		this.account = account;
		this.ho_ten = ho_ten;
		this.ngay_sinh = ngay_sinh;
		this.email = email;
		this.sdt = sdt;
		this.dia_chi = dia_chi;
		this.type = type;
		this.chuc_danh = chuc_danh;
		this.gioi_thieu = gioi_thieu;
		this.thu_chi = thu_chi;
		this.avt = avt;
	}
	public users() {
		super();
	}
	public users(String name, String email) {
		super();
		this.avt = "/FREELANCE/WebContent/img/avtDefault.jpg";
		this.ho_ten = name;
		this.email = email;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public account getAccount() {
		return account;
	}
	public void setAccount(account account) {
		this.account = account;
	}
	public String getHo_ten() {
		return ho_ten;
	}
	public void setHo_ten(String ho_ten) {
		this.ho_ten = ho_ten;
	}
	public Date getNgay_sinh() {
		return ngay_sinh;
	}
	public void setNgay_sinh(Date ngay_sinh) {
		this.ngay_sinh = ngay_sinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	public String getDia_chi() {
		return dia_chi;
	}
	public void setDia_chi(String dia_chi) {
		this.dia_chi = dia_chi;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public String getChuc_danh() {
		return chuc_danh;
	}
	public void setChuc_danh(String chuc_danh) {
		this.chuc_danh = chuc_danh;
	}
	public String getGioi_thieu() {
		return gioi_thieu;
	}
	public void setGioi_thieu(String gioi_thieu) {
		this.gioi_thieu = gioi_thieu;
	}
	public long getThu_chi() {
		return thu_chi;
	}
	public void setThu_chi(long thu_chi) {
		this.thu_chi = thu_chi;
	}
	public String getAvt() {
		return avt;
	}
	public void setAvt(String avt) {
		this.avt = avt;
	}
}
