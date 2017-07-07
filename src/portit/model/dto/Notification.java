package portit.model.dto;

import java.util.Date;

/**
 * 알림 dto
 * @author Suyeon
 *	
 *	** 변수 nt_type **
 *	1. 내 포폴 좋아요 : like
 *	2. 팔로워의 포폴 업로드 : upload
 * 	3. 메세지 : message
 * 	4. 프로젝트 신청 : project
 *  5. 나를 팔로잉 : follow
 *
 */


public class Notification {
	private int nt_id;
	private int mem_id_sender;
	private int mem_id_receiver;
	private Date nt_date;
	private String nt_type;
	private int nt_type_id;
	private char nt_isread = 'f';
	
	
	public int getNt_id() {
		return nt_id;
	}
	public int getMem_id_sender() {
		return mem_id_sender;
	}
	public int getMem_id_receiver() {
		return mem_id_receiver;
	}
	public Date getNt_date() {
		return nt_date;
	}
	public String getNt_type() {
		return nt_type;
	}
	public int getNt_type_id() {
		return nt_type_id;
	}
	public char getNt_isread() {
		return nt_isread;
	}
	
	
	public void setNt_id(int nt_id) {
		this.nt_id = nt_id;
	}
	public void setMem_id_sender(int mem_id_sender) {
		this.mem_id_sender = mem_id_sender;
	}
	public void setMem_id_receiver(int mem_id_receiver) {
		this.mem_id_receiver = mem_id_receiver;
	}
	public void setNt_date(Date nt_date) {
		this.nt_date = nt_date;
	}
	public void setNt_type(String nt_type) {
		this.nt_type = nt_type;
	}
	public void setNt_type_id(int nt_type_id) {
		this.nt_type_id = nt_type_id;
	}
	public void setNt_isread(char nt_isread) {
		this.nt_isread = nt_isread;
	}
}
