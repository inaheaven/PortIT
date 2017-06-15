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
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			
			//모델 : Request와 login_id전달, 
			MessageModel model = new MessageModel(req,login_id);
			
			
			
			//msgList.jsp
			//발신자목록  request!
			 if(cmd.equals("list")){
				//From msgDetail,msgSender
				url="msgList.jsp";
				
				
				//1.발신자 목록.
				msgSenderList=model.getSenderList();
				
				session.setAttribute("msgSenderList", msgSenderList);
				
				
				//2.수신메세지 목록 따오기 :From sender목록
					//화면을 처음에 띄워줄때 서버요청없이 한번에 처리해주기위해 msg리스트를 처음에 넘겨야한다.
					//여기서 msgSenderList만큼 반복을 돌아서 msg리스트를 넘겨야한다.
				
				/*	
				 	Sender_id가 한글로 되어있기때문에 문제가 생긴다 식별자 오류.
				//String[] reciveMsg= new String[msgSenderList.size()];
			
				for(int i=0; i<msgSenderList.size(); i++){
					
					//SenderList에 담긴 발신자의 대화내용 list
					list=model.getReciveMsg((String)msgSenderList.get(i));
					
					String reciveMsg="rm_";
					reciveMsg.concat(String.valueOf(i));
					
					
					 reciveMsg[i]="reciveMsg";
					reciveMsg[i]=reciveMsg[i].concat(String.valueOf(i));
					
					session.setAttribute(reciveMsg, list);
				}
				*/
			}
			
			
			else if(cmd.equals("list_send")){
				//From msgSend
				
				url="msgList.jsp";
				
				//DB_InPut Msg
				model.insertMessage();

				
				//발신자 목록.
				msgSenderList=model.getSenderList();
				session.setAttribute("msgSenderList", msgSenderList);
				
				//~발신자로 부터의 수신메세지 목록
			}
			
			
			
			
			
			else if(cmd.equals("detail")){
				url="msgDetail.jsp";
				//해당발신자의 mem_id를 어디서 넘겨받아야하나...?
				
				
				//~발신자의 수신,발신 msg목록
			}
			
			
			
			
			else if(cmd.equals("send")){
				//from msgList, msgDetail
				
				
				url="msgSend.jsp";
			}
			
			
			
			RequestDispatcher view=	req.getRequestDispatcher(url);
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}