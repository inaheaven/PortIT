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

@WebServlet("/account")
public class AccountController extends HttpServlet {
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
			String pageName ="memberSearch.jsp";	//test page
			
			//Login된 Id session에서 추출.
			int login_id=Integer.parseInt((String)session.getAttribute("longin_id"));
			
			ArrayList msgSenderList= (ArrayList)session.getAttribute("msgSenderList");
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			//모델 : Request와 login_id전달, 
			MessageModel model = new MessageModel(req,login_id);
			
			
			
			 if(cmd.equals("delete")){
				//From msgDetail,msgSender
				url="myDeleteAccount.jsp";
				
				
				try{
				}
				
				catch(Exception err){
					System.out.println("MessageController_cmd=list()에서 오류");
					err.printStackTrace();
				}
			}
			
			
			else if(cmd.equals("alter")){
				//From msgSend
				
				url="myAccount.jsp";
			}
			 
			 
			 
			 
			 
				req.setAttribute("pageName", url);
				RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
				
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}