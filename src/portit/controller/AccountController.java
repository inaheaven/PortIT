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
			
			// getParameter Form에서 name을 식별자로 한다. ID 아님.
			String cmd= req.getParameter("cmd");
			String deletePw;
			
			
			String url= "WebContent/msgList.html";
			String pageName ="memberSearch.jsp";	//test page
			
			//Login된 Id session에서 추출.
			int login_id=(int) session.getAttribute("loginId");
			
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			//모델 : Request와 login_id전달, 
			AccountModel model = new AccountModel(req, login_id);
			
			
			
			Boolean IdentityVerification=null;
			
			 if(cmd.equals("delete")){
				 url="myDeleteAccount.jsp";
				 
				 try{
					 
				//기본값.
				url="myDeleteAccount.jsp";
	
				 deletePw=req.getParameter("userdeletepw");
				 
					if(deletePw!=null){
						IdentityVerification=model.deleteAccount();
						
							//조건: 삭제가 완료되면 메인으로
						if(IdentityVerification==true){
							System.out.println("AccountCtrl: 로그인아이디의 비밀번호와 일치합니다.");
							
							//메인화면으로
							url="index.jsp";
						}
						else{//삭제실패=다시 삭제페이지.
							System.out.println("AccountCtrl: 로그인아이디의 비밀번호와 일치하지않습니다.");
							
							url="myDeleteAccount.jsp";
						}
					}
				 }
				catch(Exception err){
					err.printStackTrace();
				}
			 }
				 
			
			else if(cmd.equals("alter")){
				try{
					String userid=req.getParameter("userid");
					String userpw=req.getParameter("userpw");
					String newpw=req.getParameter("newpw");
					String newpwcf=req.getParameter("newpwcf");
					
					url="myAccount.jsp";
				
					// 버튼을 눌렀을때만 동작해야한다.
					if (newpwcf != null) {
						model.alterAccount(userid, userpw, newpw, newpwcf);
					}
				
				
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