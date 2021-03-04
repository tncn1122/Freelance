package Freelance.bean;
// Generated Jan 7, 2021, 1:26:51 AM by Hibernate Tools 4.3.5.Final

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JobsUser generated by hbm2java
 */
@Entity
@Table(name = "jobs_user", schema = "dbo", catalog = "freelance")
public class JobsUser implements java.io.Serializable {

	private JobsUserId id;
	private Account account;
	private Jobs jobs;
	private String hoanThanhTrong;
	private Serializable deXuat;
	private String chaoGia;
	private Boolean dangLam;

	public JobsUser() {
	}


	public JobsUser(JobsUserId id, Account account, Jobs jobs) {
		this.id = id;
		this.account = account;
		this.jobs = jobs;
		this.dangLam = false;
	}

	public JobsUser(JobsUserId id, Account account, Jobs jobs, String hoanThanhTrong, Serializable deXuat, String chaoGia,
			Boolean dangLam) {
		this.id = id;
		this.account = account;
		this.jobs = jobs;
		this.hoanThanhTrong = hoanThanhTrong;
		this.deXuat = deXuat;
		this.chaoGia = chaoGia;
		this.dangLam = dangLam;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "jobId", column = @Column(name = "job_id", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)) })
	public JobsUserId getId() {
		return this.id;
	}

	public void setId(JobsUserId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id", nullable = false, insertable = false, updatable = false)
	public Jobs getJobs() {
		return this.jobs;
	}

	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
	}

	@Column(name = "hoan_thanh_trong")
	public String getHoanThanhTrong() {
		return this.hoanThanhTrong;
	}

	public void setHoanThanhTrong(String hoanThanhTrong) {
		this.hoanThanhTrong = hoanThanhTrong;
	}

	@Column(name = "de_xuat")
	public Serializable getDeXuat() {
		return this.deXuat;
	}

	public void setDeXuat(String deXuat) {
		this.deXuat = deXuat;
	}

	@Column(name = "chao_gia")
	public String getChaoGia() {
		return this.chaoGia;
	}

	public void setChaoGia(String chaoGia) {
		this.chaoGia = chaoGia;
	}

	@Column(name = "dang_lam")
	public Boolean getDangLam() {
		return this.dangLam;
	}

	public void setDangLam(Boolean dangLam) {
		this.dangLam = dangLam;
	}

}