package portit.model.dto;

import java.util.Date;

public class Follow {
	private int fw_id;
	private int mem_id_sender;
	private int mem_id_receiver;
	private Date fw_date;
	private Tag tag_name;
	  
	public int getFw_id() {
		return fw_id;
	}
	public void setFw_id(int fw_id) {
		this.fw_id = fw_id;
	}
	public Tag getTag_name() {
		return tag_name;
	}
	public void setTag_name(Tag tag_name) {
		this.tag_name = tag_name;
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
