package Freelance.bean;

import java.io.Serializable;

public class listFav {
	private int id;
	private Serializable jobName;
	private Serializable userName;
	int offers;
	public listFav(int id, Serializable jobName, Serializable userName, int offers) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.userName = userName;
		this.offers = offers;
	}
	public listFav() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Serializable getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Serializable getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getOffers() {
		return offers;
	}
	public void setOffers(int offers) {
		this.offers = offers;
	}
	
	
}
