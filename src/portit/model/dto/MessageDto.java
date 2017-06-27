package portit.model.dto;

import portit.model.dao.MassageDao;

public class MessageDto {
	
	
	private int msg_id;				//MSG iD	
	private int mem_id_sender;		//발신 FROM
	private int mem_id_receiver;	//수신 TO
	private String msg_date;		//발신시간
	private String msg_content;		//내용
	private String msg_isread;		//수신여부
	
	
	//조인해서 얻어온다.
	//이렇게 할경우 쿼리문이 복잡해진다.
	//이름을 안쓴새끼가 있기때문이다. 
	private String sender_Nick;		//발신자 별명
	private String sender_Name;		//발신자 이름
	
	
	//싱글톤 실험.
	MassageDao dao = MassageDao.getInstance();
	
	
	
	
	
	public String getSender_Nick() {
		return sender_Nick;
	}
	public void setSender_Nick(String sender_Nick) {
		this.sender_Nick = sender_Nick;
	}
	public String getSender_Name() {
		return sender_Name;
	}
	public void setSender_Name(String sender_Name) {
		this.sender_Name = sender_Name;
	}
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
		
		//send_id값이 저장되는순간 convert 되어 이름이 저장된다.
		setSender_Name(dao.convertToName(String.valueOf(mem_id_sender)));
		
		
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
