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
			String pageName ="memberSearch.jsp";	//test page
			
			//Login된 Id session에서 추출.
			int login_id=Integer.parseInt((String)session.getAttribute("longin_id"));
			
			ArrayList msgSenderList= (ArrayList)session.getAttribute("msgSenderList");
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			
			//모델 : Request와 login_id전달, 
			MessageModel model = new MessageModel(req,login_id);
			
			
			//http://localhost:8080/empty/msg?cmd=list
			
			
		
			
			
			//msgList.jsp
			//발신자목록  request!
			 if(cmd.equals("list")){
				//From msgDetail,msgSender
				 
				url="myMsgList.jsp";
				pageName="myMsgList.jsp";
				
				
				try{
				//2.RoomList
				list=model.roomList();
				session.setAttribute("RoomList", list);
				}
				
				catch(Exception err){
					System.out.println("MessageController_cmd=list()에서 오류");
					err.printStackTrace();
				}
				
			/*	//테스트용
				//발신자목록확인.
				msgSenderList=model.getSenderList();
				
				for(int i=0; i<msgSenderList.size(); i++){
					//내용 확인
					System.out.println("Ctrl 발신자 ="+ msgSenderList.get(i));
				}
				*/
			}
			
			
			else if(cmd.equals("list_send")){
				//From msgSend
				
				url="myMsgList.jsp";
				pageName="myMsgList.jsp";
				
				//DB_InPut Msg
				model.insertMessage();

				/*
				최초에 list페이지에서 발신자 목록을 가지고왔기때문에 상관이 없다.
				list페이지에서 나타나는 list는 발,수신자 list가 아니라 발신자 list다
				따라서 내가 메세지를 송신하더라도 이 순위에는 영향이 생기지 않는다.
				 
				
				//발신자 목록.
				msgSenderList=model.getSenderList();
				session.setAttribute("msgSenderList", msgSenderList);*/
				
				
				//~발신자로 부터의 수신메세지 목록
			}
			
			else if(cmd.equals("detail")){
				//1.List에서 발신자이름을 받아온다.
				String mem_id_Sender= req.getParameter("mem_id_sender");
				
				
				url="myMsgDetail.jsp";

				//대화방 with "mem_id_sender"
				//2.발신자의 대화방을 얻어온다.
				list=model.getChatRoom(mem_id_Sender);
				session.setAttribute("chatroom", list);
				
				//발신자 이름 확인  Yes!
				//System.out.println("Ctrl  "+mem_id_Sender);
			}
			
			
			
			
			else if(cmd.equals("send")){
				//from msgList, msgDetail
				
				url="myMsgSend.jsp";
			}
			
			 
			 
			 
			 
			 
			 
			 
			 
				req.setAttribute("pageName", url);
				RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
				
				
			//RequestDispatcher view=	req.getRequestDispatcher(url);
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}