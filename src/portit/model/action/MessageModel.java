package portit.model.action;

import javax.servlet.http.HttpServletRequest;
import portit.model.dto.Message;

public class MessageModel {

	
	private HttpServletRequest req;
	public MessageModel(HttpServletRequest _req){
		
		this.req=_req;
	}
	
	// VIEW
	public Message getMessage() {
		/*
		 * 1.보내기 페이지에서 'MessageSend'page에서 request가 들어온다. 
		 * 2.request에서 데이터를 뽑아낸다getParameter 
		 * 3.그 내용을 dto에 저장한다. 
		 * 4.dto를 리턴한다.
		 * 5.리턴된 내용은 dao를 통해 DB에 저장된다.
		 * 6.또 view에 출력된다.
		 */

		Message dto = new Message();

		dto.setMem_id_receiver(Integer.parseInt(req.getParameter("mem_id_sender")));
		dto.setMem_id_sender(Integer.parseInt(req.getParameter("mem_id_sender")));
		dto.setMsg_content(req.getParameter("msg_content"));
		dto.setMsg_isread(req.getParameter("n"));

		return dto;
	}
	
}
