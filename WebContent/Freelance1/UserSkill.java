package Freelance1;
// Generated Jan 11, 2021, 8:21:37 PM by Hibernate Tools 4.3.5.Final

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
 * UserSkill generated by hbm2java
 */
@Entity
@Table(name = "user_skill", schema = "dbo", catalog = "freelance")
public class UserSkill implements java.io.Serializable {

	private UserSkillId id;
	private Account account;
	private Skills skills;

	public UserSkill() {
	}

	public UserSkill(UserSkillId id) {
		this.id = id;
	}

	public UserSkill(UserSkillId id, Account account, Skills skills) {
		this.id = id;
		this.account = account;
		this.skills = skills;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id")),
			@AttributeOverride(name = "skillId", column = @Column(name = "skill_id")) })
	public UserSkillId getId() {
		return this.id;
	}

	public void setId(UserSkillId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id", insertable = false, updatable = false)
	public Skills getSkills() {
		return this.skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

}