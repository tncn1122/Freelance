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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Skills generated by hbm2java
 */
@Entity
@Table(name = "skills", schema = "dbo", catalog = "freelance")
public class Skills implements java.io.Serializable {

	private Integer id;
	private Serializable skill;
	private Set<JobsSkill> jobsSkills = new HashSet<JobsSkill>(0);
	private Set<UserSkill> userSkills = new HashSet<UserSkill>(0);

	public Skills() {
	}
	
	

	public Skills(Integer id, Serializable skill) {
		super();
		this.id = id;
		this.skill = skill;
	}



	public Skills(Serializable skill, Set<JobsSkill> jobsSkills, Set<UserSkill> userSkills) {
		this.skill = skill;
		this.jobsSkills = jobsSkills;
		this.userSkills = userSkills;
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

	@Column(name = "skill")
	public Serializable getSkill() {
		return this.skill;
	}

	public void setSkill(Serializable skill) {
		this.skill = skill;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skills")
	public Set<JobsSkill> getJobsSkills() {
		return this.jobsSkills;
	}

	public void setJobsSkills(Set<JobsSkill> jobsSkills) {
		this.jobsSkills = jobsSkills;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skills")
	public Set<UserSkill> getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(Set<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

}
