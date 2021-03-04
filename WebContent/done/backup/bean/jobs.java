package Freelance.bean;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class jobs {
	private int id;
	private int ntd_id;
	private String ten;
	private String thong_tin;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date han_chot;
	private int ngan_sach_tu;
	private int ngan_sach_den;
	private boolean hoan_thanh;
	private int loai_cong_viec;
}
