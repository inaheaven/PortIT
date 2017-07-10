package portit.model.dto;

import portit.model.dao.MassageDao;

public class MessageDto {
	//0705
	//발신자 수신자중 누가 '대화파트너'인가??
	
	public MessageDto(){
	}

	//Dto에 누가 수신자인지 명시하기 위함.
	public MessageDto(String login_id){
		this.login_id=login_id;
	}
	
	private int msg_id;				//MSG iD	
	private int mem_id_sender;		//발신 FROM
	private int mem_id_receiver;	//수신 TO
	private String msg_date;		//발신시간
	private String msg_content;		//내용
	private String msg_isread;		//수신여부
	
	private String login_id;
	
	private String sender_Nick;		//발신자 별명
	private String sender_Name;		//발신자 이름
	
	//싱글톤 실험.
	MassageDao dao = MassageDao.getInstance();
	
	//대화상대의 아이디를 아이디를 리턴한다.
	public String getPartner(String login_id){
		
		//가정: 파트너가 송신자다.
		String partner_id=String.valueOf(mem_id_sender);
	
		//가능성: 내가 송신자라면 파트너는 수신자다.
		if(mem_id_sender==Integer.parseInt(login_id)){
			partner_id=String.valueOf(mem_id_receiver);
		}
		return partner_id;
	}
	public String getPartner(){
		
		//가정: 파트너가 송신자다.
		String partner_id=String.valueOf(mem_id_sender);
		
		//가능성: 내가 송신자라면 파트너는 수신자다.
		if(mem_id_sender==Integer.parseInt(login_id)){
			partner_id=String.valueOf(mem_id_receiver);
		}
		return partner_id;
	}
	
	

	
	public String getLogin_id() {
		return login_id;
	}

	public MessageDto setLogin_id(String login_id) {
		this.login_id = login_id;
		return this;
	}
	
	
	
	
	
	public String getSender_Nick() {
		return sender_Nick;
	}
	public MessageDto setSender_Nick(String sender_Nick) {
		this.sender_Nick = sender_Nick;
		return this;
	}
	public String getSender_Name() {
		return sender_Name;
	}
	public MessageDto setSender_Name(String sender_Name) {
		this.sender_Name = sender_Name;
		return this;
	}
	public int getMsg_id() {
		return msg_id;
	}
	public MessageDto setMsg_id(int msg_id) {
		this.msg_id = msg_id;
		return this;
	}
	public int getMem_id_sender() {
		return mem_id_sender;
	}
	public MessageDto setMem_id_sender(int mem_id_sender) {
		//대화방에서 마지막 발송자가 '나'일 경우에는 어떻게 처리되는가?????
		
		
		//send_id값이 저장되는순간 convert 되어 이름이 저장된다.
		this.sender_Name=dao.convertToName(String.valueOf(mem_id_sender));
		
		
		this.mem_id_sender = mem_id_sender;
		return this;
	}
	public int getMem_id_receiver() {
		return mem_id_receiver;
	}
	public MessageDto setMem_id_receiver(int mem_id_receiver) {
		this.mem_id_receiver = mem_id_receiver;
		return this;
	}
	public String getMsg_date() {
		return msg_date;
	}
	public MessageDto setMsg_date(String msg_date) {
		this.msg_date = msg_date;
		return this;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public MessageDto setMsg_content(String msg_content) {
		this.msg_content = msg_content;
		return this;
	}
	public String getMsg_isread() {
		return msg_isread;
	}
	public MessageDto setMsg_isread(String msg_isread) {
		this.msg_isread = msg_isread;
		return this;
	}
}
