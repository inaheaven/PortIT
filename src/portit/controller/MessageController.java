package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		System.out.println("check1");
		try {
			HttpSession session = req.getSession();
			String cmd= req.getParameter("cmd");
			String url= "WebContent/msgList.html";
			
			//Login된 Id session에서 추출.
			int login_id=Integer.parseInt((String)session.getAttribute("longin_id"));
			
			
			
			if(cmd.equals("send")){
				
			//모델 : Request와 login_id전달.
			MessageModel model = new MessageModel(req,login_id);
			
			//모델 : DB전달	(MessageModel)
			model.getMessage();
			
			System.out.println("check2");
			}
			
			
			
			
			
			//session.setAttribute("bookList",bookList);
			RequestDispatcher view=	req.getRequestDispatcher(url);
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}