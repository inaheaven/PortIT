package portit.model.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import portit.model.dao.MassageDao;
import portit.model.dto.MessageDto;

public class MessageModel {
	
	private HttpServletRequest req;
	private int login_id;			//Dao로 전달할 mem_id (Who)
	private MassageDao dao;
	private MessageDto dto;
	private ArrayList list;			//Ctrl에 반환할 List 변수.
	
	
	public MessageModel(HttpServletRequest _req, int _login_id){
		this.req=_req;
		this.login_id=_login_id;
		
		//모델_DAO
		//해당 사용자에 관한 db를 얻어야하기때문에 식별자를 전달한다.

		
		//싱글톤 패턴을 위해 getInstace로 인스턴스를 공유라고 하는데 오류발생.. 원인은 모르겟음.
		this.dao=new MassageDao();
		dao.setLogin_id(_login_id);
	}
	
	
	//메세지를 보낸다.
	public boolean insertMessage() {
		/*
		 * 1.보내기 페이지에서 'MessageSend'page에서 request가 들어온다. 
		 * 2.request에서 데이터를 뽑아낸다getParameter 
		 * 3.그 내용을 dto에 저장한다. 
		 * 4.dto를 리턴한다.
		 * 5.리턴된 내용은 dao를 통해 DB에 저장된다.
		 * 6.또 view에 출력된다.
		 */

		boolean Inputconfirmation=true;
		
		String msgText=req.getParameter("msgText");
		String msgReceiverEmail=req.getParameter("msgReceiver");
		int mem_id_reciver=Integer.parseInt(dao.emailToMemId(msgReceiverEmail));
		
		if(mem_id_reciver==0||msgText.trim().equals("")){
			//사용자 미입력.
			return false;
		}
		
		
		dto = new MessageDto();
		dto.setMem_id_sender(this.login_id);
		
		
		//1.login할때 ID를 session에 저장한다.
		//2.session에 저장된 ID를  mem_id_sender로 dto에 저장한다.
		//3.우선 임의의 ID를 직접 입력한다.
		//5.session은 JSP, Servelet에서만 접근이 가능하다
		//6.controller에서 ID를 전달하자.
		
		
		
		//보내는이 미입력. 0으로 저장.
		if(msgReceiverEmail==null){
			dto.setMem_id_receiver(0);
		}else{
			dto.setMem_id_receiver(mem_id_reciver);	
		}
		
		dto.setMsg_content(msgText);
		dto.setMsg_isread("n");

		
		//값이 입정상적으로 입력되었다면.
		if((msgText!=null)&&(mem_id_reciver!=0)){
			Inputconfirmation=true;
			
			
			//DB에 Input.
			dao.insertMsg(dto);
		}else{
			Inputconfirmation=false;
		}
		
		
		return Inputconfirmation;
	}
	
	
	
	//msgList.jsp
	public ArrayList roomList(String keyField, String keyWord){
		//Login_id에 생성된 모든 대화방List를 리턴한다.
		
		//dao 변수1: Type(이름,nick), 변수2: 검색어.
		
		
		this.list=dao.roomList(keyField,keyWord);
		
		return this.list;
	}
	
	
	
	// msgDetail.jsp
	// 대화방 with Msg_Sender
	public ArrayList getChatRoom(String Msg_Sender){
		//해당 User와 대화한 대화방을 return한다.
		// (타입, 검색어, MSG_SENDER, 수신만:fail 수신+발신:true)
		
		//[청소중]인스턴스 생성을 안해도된다.
		//this.list = new ArrayList();	
		
		this.list=dao.getChatRoom(null,null, Msg_Sender,true);
		return list;
	}
	
	
	public void deleteMsg(String msg_id){
		
		//해당 msg삭제...
		dao.deleteMsg(msg_id);
	}


	
	public String toEmail(String mem_id) {
		return dao.toEamil(mem_id);
	}
>>>>>>> refs/remotes/origin/dev
	
	



	
	
	
/*	
	//msgList.jsp
	//발신자 목록!
	//DAO에서 한글화 작업을 하기때문에 필요없다.
	public ArrayList getSenderList(){
		//Login_id에게 보낸 발신자 List를 리턴한다.
		
		this.list = new ArrayList();
		this.list=dao.getSenderList(this.login_id);
				
		
		
		 이름은 키값이 아니기때문에 식별자로써 할수없다.
		 그럼 이름과 mem_id를 묶어서 키값으로 사용할 수 있다.
		->DB에 접근할때. 이것을 통일된것이 닉네임.
		 
		
		convertToName(list);
		return this.list;
	}*/
	
	
	
	
	/*
	//애초에 dao에서 한글화 작업을 하기 때문에 필요가없다.
	public ArrayList convertToName(ArrayList _list){
		
		1.list에서 mem_id를 꺼내온다.
		2.DB에서 해당하는 한글이름을 출력받는다.
		3.list에 다시 setting해준다. 
		4.변환된 한글화 List를 반환한다.
		
		
		//한글이름으로 변환.
		for(int i=0; i<_list.size();i++){
			_list.set(i, dao.convertToName((String)_list.get(i)));
		}
		return _list;
	}
	*/
}






