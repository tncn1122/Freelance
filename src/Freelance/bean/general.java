package Freelance.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class general implements Comparable{
	

	
	private int id;
	private Serializable avt;
	private Serializable hoTen;
	private Serializable chucDanh;
	private Serializable gioiThieu;
	private float rating;
	private Set<UserSkill> skills;
	private Long thuNhap;
	private int chaoGia;
	private int hoanThanh;
	
	public general(int id, Serializable avt, Serializable hoTen, Serializable chucDanh, Serializable gioiThieu, float rating, Set<UserSkill> skills, Long thuNhap,
			int chaoGia, int hoanThanh) {
		super();
		this.id = id;
		this.hoTen = hoTen;
		this.avt = avt;
		this.chucDanh = chucDanh;
		this.gioiThieu = gioiThieu;
		this.rating = rating;
		this.skills = skills;
		this.thuNhap = thuNhap;
		this.chaoGia = chaoGia;
		this.hoanThanh = hoanThanh;
	}
	
	


	public Serializable getHoTen() {
		return hoTen;
	}

	public void setHoTen(Serializable hoTen) {
		this.hoTen = hoTen;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public general() {
		super();
		this.chaoGia = 0;
		this.hoanThanh = 0;
	}
	
	public Serializable getAvt() {
		return avt;
	}

	public void setAvt(Serializable serializable) {
		this.avt = serializable;
	}

	public Serializable getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(Serializable chucDanh) {
		this.chucDanh = chucDanh;
	}

	public Serializable getGioiThieu() {
		return gioiThieu;
	}

	public void setGioiThieu(Serializable gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Set<UserSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<UserSkill> set) {
		this.skills = new HashSet<UserSkill>();
		for (UserSkill skills : set) {
			this.skills.add(new UserSkill(skills.getSkills()));
		}
	}

	public Long getThuNhap() {
		return thuNhap;
	}

	public void setThuNhap(Long thuNhap) {
		this.thuNhap = thuNhap;
	}

	public int getChaoGia() {
		return chaoGia;
	}

	public void setChaoGia(int i) {
		this.chaoGia = i;
	}

	public int getHoanThanh() {
		return hoanThanh;
	}

	public void setHoanThanh(int hoanThanh) {
		this.hoanThanh = hoanThanh;
	}




	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(this.chaoGia == ((general)o).chaoGia){
            return 0;
        } else if(this.chaoGia < ((general)o).chaoGia){
            return -1;
        } else{
            return 1;
        }
	}

	
	
	
	
}
