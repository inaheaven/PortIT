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
		this.dao= new MassageDao(login_id);
	}
	
	
	//메세지를 보낸다.
	public void insertMessage() {
		/*
		 * 1.보내기 페이지에서 'MessageSend'page에서 request가 들어온다. 
		 * 2.request에서 데이터를 뽑아낸다getParameter 
		 * 3.그 내용을 dto에 저장한다. 
		 * 4.dto를 리턴한다.
		 * 5.리턴된 내용은 dao를 통해 DB에 저장된다.
		 * 6.또 view에 출력된다.
		 */

		dto = new MessageDto();
		
		//1.login할때 ID를 session에 저장한다.
		//2.session에 저장된 ID를  mem_id_sender로 dto에 저장한다.
		//3.우선 임의의 ID를 직접 입력한다.
		//5.session은 JSP, Servelet에서만 접근이 가능하다
		//6.controller에서 ID를 전달하자.
		
		
		dto.setMem_id_sender(this.login_id);
		dto.setMem_id_receiver(Integer.parseInt(req.getParameter("msgReceiver")));
		dto.setMsg_content(req.getParameter("msgText"));
		dto.setMsg_isread("n");

		//DB에 Input.
		dao.insertMessage(dto);
	}
	
	
	
	// msgList.jsp
	//~에게 받은 메세지.
	public ArrayList getReciveMsg(String Msg_Sender){
		this.list = new ArrayList();
		
		//dao 변수1: Type(이름,nick), 변수2: 검색어.
		this.list=dao.getReciveMsg(null,null, Msg_Sender);
		
		return list;
	}
	
	
	//msgDetail.jsp
	//~와 주고받은 메세지
	public ArrayList getListAll(int Msg_Sender){
		//0. DAO를 선언할때 생성자에 Mem_id를 전달한다.
		//1.DB에서 현재 로그인된 사용자의 msgList를 얻어온다.
		//list를 반환한다.
		
		
		this.list = new ArrayList();
		
		//dao 변수1: Type(이름,nick), 변수2: 검색어.
		this.list=dao.getMessageListAll(null,null,Msg_Sender);
		return this.list;
	}

	

	
	//msgList.jsp
	//발신자 목록!
	public ArrayList getSenderList(){
		
		
		this.list = new ArrayList();
		this.list=dao.getMsgSender(this.login_id);
				
		/*
		 이름은 키값이 아니기때문에 식별자로써 할수없다.
		 그럼 이름과 mem_id를 묶어서 키값으로 사용할 수 있다.
		->DB에 접근할때. 이것을 통일된것이 닉네임.
		 */
		
		
		/*//한글이름으로 변환.
		for(int i=0; i<list.size();i++){
		
			list.set(i, dao.convertToName((String)list.get(i)));
			
			1.list에서 mem_id를 꺼내온다.
			2.DB에서 해당하는 한글이름을 출력받는다.
			3.list에 다시 setting해준다. 
		}*/
		
		
		convertToName(list);
		
		return this.list;
	}
	
	
	
	
	
	
	public ArrayList convertToName(ArrayList _list){
		/*
		1.list에서 mem_id를 꺼내온다.
		2.DB에서 해당하는 한글이름을 출력받는다.
		3.list에 다시 setting해준다. 
		4.변환된 한글화 List를 반환한다.
		*/
		
		//한글이름으로 변환.
		for(int i=0; i<_list.size();i++){
			_list.set(i, dao.convertToName((String)_list.get(i)));
		}
		return _list;
	}
}






