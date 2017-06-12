package portit.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.action.MessageModel;
import portit.model.dao.MassageDao;
import portit.model.dao.MemberDao;
import portit.model.dto.Member;
import portit.model.dto.Message;

@WebServlet("/msg")
public class MessageController extends HttpServlet {
	//컨트롤러
	//model에서 request로부터 뽑아서 데이터에 저장하는작업.
	
	//view->frontCtrl ->컨트롤러 ->모델.
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		
		try {
			// request에서 dto로 옮겨담기.
			//model에서 정보를 dto에 담는 작업을 한다.
			
			//모델 : dao저장, dto리턴.
			MessageModel model = new MessageModel(req);
			
			//모델-DTO
			Message dto= new Message();
			
			//모델_DAO
			MassageDao dao= new MassageDao();
			
			//dto를 DB와 view에 전달해야한다.
			dto=model.getMessage();
			
			//DB에 전달.
			dao.insertMessage(dto);
			
			
			
			
			
			
			req.setAttribute("viewURL", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}