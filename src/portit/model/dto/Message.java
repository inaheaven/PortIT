package portit.model.dto;

public class Message {
	
	
	private int msg_id;				//MSG iD	
	private int mem_id_sender;		//발신 FROM
	private int mem_id_receiver;	//수신 TO
	private String msg_date;		//발신시간
	private String msg_content;		//내용
	private String msg_isread;		//수신여부
	
	
	
	public int getMsg_id() {
		return msg_id;
	}
	public Message setMsg_id(int msg_id) {
		this.msg_id = msg_id;
		return this;
	}
	public int getMem_id_sender() {
		return mem_id_sender;
	}
	public Message setMem_id_sender(int mem_id_sender) {
		this.mem_id_sender = mem_id_sender;
		return this;
	}
	public int getMem_id_receiver() {
		return mem_id_receiver;
	}
	public Message setMem_id_receiver(int mem_id_receiver) {
		this.mem_id_receiver = mem_id_receiver;
		return this;
	}
	public String getMsg_date() {
		return msg_date;
	}
	public Message setMsg_date(String msg_date) {
		this.msg_date = msg_date;
		return this;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public Message setMsg_content(String msg_content) {
		this.msg_content = msg_content;
		return this;
	}
	public String getMsg_isread() {
		return msg_isread;
	}
	public Message setMsg_isread(String msg_isread) {
		this.msg_isread = msg_isread;
		return this;
	}
}
