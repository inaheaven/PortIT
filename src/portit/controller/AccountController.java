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

import portit.model.action.AccountModel;
import portit.model.action.MessageModel;

@WebServlet("/account")
public class AccountController extends HttpServlet {
	//컨트롤러
	
	
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
			
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			//모델 : Request와 login_id전달, 
			AccountModel model = new AccountModel(req, login_id);
			
			
			
			 if(cmd.equals("delete")){
				//From msgDetail,msgSender
				url="myDeleteAccount.jsp";
				
				model.deleteAccount();
				
				try{
				}
				
				catch(Exception err){
					err.printStackTrace();
				}
			}
			
			
			else if(cmd.equals("alter")){
				try{
			
				url="myAccount.jsp";
				model.alterAccount();
				
				}
				catch(Exception err){
					err.printStackTrace();
				}
			}
			 
			 
			 
				req.setAttribute("pageName", url);
				RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
				
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}