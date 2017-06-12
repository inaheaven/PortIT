package portit.model.dto;

import java.util.Date;

/**
 * 회원 값 객체
 * @author gnsngck
 *
 */
public class Member {
	
	private int mem_id;
	private String mem_email;
	private String mem_password;
	private Date mem_regdate;
	
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
	
	public Member setMem_id(int mem_id) {
		this.mem_id = mem_id;
		return this;
	}
	public Member setMem_email(String mem_email) {
		this.mem_email = mem_email;
		return this;
	}
	public Member setMem_password(String mem_password) {
		this.mem_password = mem_password;
		return this;
	}
	public Member setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
		return this;
	}

}
