package Freelance.bean;
// Generated Jan 7, 2021, 1:26:51 AM by Hibernate Tools 4.3.5.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobCagetory generated by hbm2java
 */
@Entity
@Table(name = "job_cagetory", schema = "dbo", catalog = "freelance")
public class JobCagetory implements java.io.Serializable {

	private Integer id;
	private Cagetory cagetory;
	private Serializable congViec;
	private Set<Jobs> jobses = new HashSet<Jobs>(0);

	public JobCagetory() {
	}

	public JobCagetory(Cagetory cagetory, Serializable congViec, Set<Jobs> jobses) {
		this.cagetory = cagetory;
		this.congViec = congViec;
		this.jobses = jobses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cagetory_id")
	public Cagetory getCagetory() {
		return this.cagetory;
	}

	public void setCagetory(Cagetory cagetory) {
		this.cagetory = cagetory;
	}

	@Column(name = "cong_viec")
	public Serializable getCongViec() {
		return this.congViec;
	}

	public void setCongViec(Serializable congViec) {
		this.congViec = congViec;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobCagetory")
	public Set<Jobs> getJobses() {
		return this.jobses;
	}

	public void setJobses(Set<Jobs> jobses) {
		this.jobses = jobses;
	}

}
