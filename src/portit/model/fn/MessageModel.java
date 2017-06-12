package portit.model.fn;

import javax.servlet.http.HttpServletRequest;
import portit.model.dto.Message;

public class MessageModel {

	
	
	
	
	
	
	
//VIEW
public Message getMessage(HttpServletRequest req){
	/*
	1.보내기 페이지에서 'MessageSend'page에서 request가 들어온다.
	2.request에서 데이터를 뽑나낸다 getParameter
	3.그 내용을 dto에 저장한다.
	4.dto를 리턴한다.
	*/

		
		
		Message dto = new Message();
		
		/*
		dto.setMem_id_receiver(req.getParameter());
		dto.setMem_id_sender(req.getParameter());
		dto.setMsg_content(req.getParameter());
		dto.setMsg_date(req.getParameter());
		dto.setMsg_id(req.getParameter());
		dto.setMsg_isread(req.getParameter());
		*/
		
		return dto;
	}
	
}
