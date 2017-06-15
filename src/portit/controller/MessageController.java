package portit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.action.MessageModel;

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
		try {
			HttpSession session = req.getSession();
			String cmd= req.getParameter("cmd");
			String url= "WebContent/msgList.html";
			
			//Login된 Id session에서 추출.
			int login_id=Integer.parseInt((String)session.getAttribute("longin_id"));
			
			
			/*ArrayList msgList = (ArrayList)session.getAttribute("msgList");
			ArrayList msgListAll = (ArrayList)session.getAttribute("msgListAll");*/
			ArrayList msgSenderList= (ArrayList)session.getAttribute("msgSenderList");
			
			
			//모델 : Request와 login_id전달, 
			MessageModel model = new MessageModel(req,login_id);
			
			
			
			
			//msgSend.jsp
			if(cmd.equals("list_send")){
				//DB_InPut
				model.getMessage();
				
				url="msgList.jsp";
				
		/*		msgListAll=model.getListAll();
				session.setAttribute("msgListAll", msgListAll);
				
				msgList=model.getList();
				session.setAttribute("msgList", msgList);*/
			}
			
			
			//msgList.jsp
			//발신자목록  request!
			else if(cmd.equals("list")){
				
				url="msgList.jsp";
				
				msgSenderList=model.getSederList();
				session.setAttribute("msgSenderList", msgSenderList);
			}
			
			
			
			
			else if(cmd.equals("detail")){
				url="msgDetail.jsp";
			}
			
			
			//이름중복.
			else if(cmd.equals("send")){
				url="msgSend.jsp";
			}
			
			
			
			
			
			RequestDispatcher view=	req.getRequestDispatcher(url);
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}