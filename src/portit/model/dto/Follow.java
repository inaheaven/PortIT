package portit.model.dto;

import java.util.Date;
import java.util.List;

public class Follow {
	private int fw_id;
	private int mem_id_sender;
	private int mem_id_receiver;
	private Date fw_date;
	
	// 멤버 템플릿에 쓰일 profile과 tag 정보
	private String prof_img;
	private String prof_nick;
	private int prof_follower;
	private List<String> tags;
	
	  
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getProf_img() {
		return prof_img;
	}
	public String getProf_nick() {
		return prof_nick;
	}
	public int getProf_follower() {
		return prof_follower;
	}
	public void setProf_follower(int prof_follower) {
		this.prof_follower = prof_follower;
	}

	public void setProf_img(String prof_img) {
		this.prof_img = prof_img;
	}
	public void setProf_nick(String prof_nick) {
		this.prof_nick = prof_nick;
	}
	public int getFw_id() {
		return fw_id;
	}
	public void setFw_id(int fw_id) {
		this.fw_id = fw_id;
	}
	public int getMem_id_sender() {
		return mem_id_sender;
	}
	public void setMem_id_sender(int mem_id_sender) {
		this.mem_id_sender = mem_id_sender;
	}
	public int getMem_id_receiver() {
		return mem_id_receiver;
	}
	public void setMem_id_receiver(int mem_id_receiver) {
		this.mem_id_receiver = mem_id_receiver;
	}
	public Date getFw_date() {
		return fw_date;
	}
	public void setFw_date(Date fw_date) {
		this.fw_date = fw_date;
	}
}
