package portit.model.dto;

public class Message {
	
	
	private int msg_id;				
	private int mem_id_sender;		
	private int mem_id_receiver;	
	private String msg_date;		
	private String msg_content;		
	private String msg_isread;		
	
	
	
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
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
	public String getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_isread() {
		return msg_isread;
	}
	public void setMsg_isread(String msg_isread) {
		this.msg_isread = msg_isread;
	}
}
