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
			}
			
			else if(cmd.equals("CART")){
				
				//url="/WEB-INF/exam2/cart.jsp";
				url="WEB-INF/exam2/bookShop.jsp";

				//if(bookList==null){
				//		bookList= new ArrayList();
				//}
				//booklist는 접속끊키기 전까지 보관되어야하기때문에 session에 담겨있어야한다.
			//	bookList.add(getBook(req));
			}
			
			
			
			
			//session.setAttribute("bookList",bookList);
			RequestDispatcher view=	req.getRequestDispatcher(url);
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}