package portit.model.dto;

import java.util.Date;

/**
 * 회원 DTO
 * @author gnsngck
 *
 */
public class Member {
	
	private int mem_id;
	private String mem_email;
	private String mem_password;
	private Date mem_regdate;
	private int prof_id;
	private String tag_name;
	private String prof_img;
	private String prof_name;
	private int prof_follower;
	private Date prof_regdate;
	
	
	public int getMem_id() {
		return mem_id;
	}
	public String getMem_email() {
		return mem_email;
	}
	public String getMem_password() {
		return mem_password;
	}
	public Date getMem_regdate() {
		return mem_regdate;
	}
	
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public void setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getProf_img() {
		return prof_img;
	}
	public void setProf_img(String prof_img) {
		this.prof_img = prof_img;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public int getProf_follower() {
		return prof_follower;
	}
	public void setProf_follower(int prof_follower) {
		this.prof_follower = prof_follower;
	}
	public Date getProf_regdate() {
		return prof_regdate;
	}
	public void setProf_regdate(Date prof_regdate) {
		this.prof_regdate = prof_regdate;
	}
	public int getProf_id() {
		return prof_id;
	}
	public void setProf_id(int prof_id) {
		this.prof_id = prof_id;
	}
}

