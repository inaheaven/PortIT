package portit.model.action;

import javax.servlet.http.HttpServletRequest;

import portit.model.dao.MassageDao;
import portit.model.dto.Message;

public class MessageModel {


	
	private HttpServletRequest req;
	private int login_id;
	private MassageDao dao;
	
	public MessageModel(HttpServletRequest _req, int _login_id){
		this.req=_req;
		this.login_id=_login_id;
		
		
		//모델_DAO
		this.dao= new MassageDao();
	}
	
	
	// VIEW
	public void getMessage() {
		/*
		 * 1.보내기 페이지에서 'MessageSend'page에서 request가 들어온다. 
		 * 2.request에서 데이터를 뽑아낸다getParameter 
		 * 3.그 내용을 dto에 저장한다. 
		 * 4.dto를 리턴한다.
		 * 5.리턴된 내용은 dao를 통해 DB에 저장된다.
		 * 6.또 view에 출력된다.
		 */

		Message dto = new Message();
		
		//1.login할때 ID를 session에 저장한다.
		//2.session에 저장된 ID를  mem_id_sender로 dto에 저장한다.
		//3.우선 임의의 ID를 직접 입력한다.
		//4.MSG_Send.jsp 에서 'session.setAttribute("longin_id","CaterJo");'
		//5.session은 JSP, Servelet에서만 접근이 가능하다
		//6.controller에서 ID를 전달하자.
		
		
		dto.setMem_id_sender(this.login_id);
		dto.setMem_id_receiver(Integer.parseInt(req.getParameter("msgReceiver")));
		dto.setMsg_content(req.getParameter("msgText"));
		dto.setMsg_isread("n");

		
		//DB에 전달.
		dao.insertMessage(dto);
	}
}
