package portit.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			HttpSession session = req.getSession();
			String cmd= req.getParameter("cmd");
			String keyField = req.getParameter("keyField");
			String keyWord = req.getParameter("keyWord");
			String url= "WebContent/msgList.html";
			String pageName ="memberSearch.jsp";	//test page
			
			//Login된 Id session에서 추출.
			//모델 : Request와 login_id전달
			
			int login_id=Integer.parseInt((String)session.getAttribute("longin_id"));
			
			
			//logind에서 받아온 값... 근데 어디 있냐..
			//int loginId=Integer.parseInt((String)session.getAttribute("loginId"));
			System.out.println(login_id);
			
			MessageModel model = new MessageModel(req,login_id);
			
			
			ArrayList msgSenderList= (ArrayList)session.getAttribute("msgSenderList");
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			
			
		
			
			
			//msgList.jsp
			//발신자목록  request!
			 if(cmd.equals("list")){
				//From msgDetail,msgSender
				 
				url="myMsgList.jsp";
				
				
				
				try{
				//2.RoomList
				list=model.roomList(keyField,keyWord);
				session.setAttribute("RoomList", list);
				}
				
				catch(Exception err){
					System.out.println("MessageController_cmd=list()에서 오류");
					err.printStackTrace();
				}
				
			}
			
			
			else if(cmd.equals("list_send")){
				//From msgSend
				
				
				
				url="myMsgList.jsp";
				
				//DB_InPut Msg
				//입력확인.
				Boolean InputCheck =model.insertMessage();
				
				//Msg 입력에 대한 경고메세지. JSP에서 Script function으로 수정하자.
				if(InputCheck==true){
					url="myMsgList.jsp";
					out.println("<script>alert('메세지가 발송되었습니다..'); location.href='/msg?cmd=list_send';</script>");
				}else{
					
					System.out.println("[CTRL]: 메세지를 다시 입력하세요");
					out.println("<script>alert('메세지를 입력해주세요!'); location.href='/msg?cmd=send';</script>");
					url="myMsgSend.jsp";
				}
				
				
				
				/*
				최초에 list페이지에서 발신자 목록을 가지고왔기때문에 상관이 없다.
				list페이지에서 나타나는 list는 발,수신자 list가 아니라 발신자 list다
				따라서 내가 메세지를 송신하더라도 이 순위에는 영향이 생기지 않는다.
				 
				//발신자 목록.
				msgSenderList=model.getSenderList();
				session.setAttribute("msgSenderList", msgSenderList);*/
				
			}
			
			else if(cmd.equals("detail")){
				//1.List에서 발신자이름을 받아온다.
				String mem_id_Sender= req.getParameter("mem_id_sender");
				
				
				url="myMsgDetail.jsp";

				//대화방 with "mem_id_sender"
				//2.발신자의 대화방을 얻어온다.
				list=model.getChatRoom(mem_id_Sender);
				session.setAttribute("chatroom", list);
				
				
				
			}
			
			
			
			
			else if(cmd.equals("send")){
				//from msgList, msgDetail
				url="myMsgSend.jsp";
			}
			 
			 
			 
			else if(cmd.equals("delete")){
				//from msgList, msgDetail
				
				String msg_id= req.getParameter("msg_id");
				
				
				model.deleteMsg(msg_id);
				
				
				list=model.roomList(keyField,keyWord);
				session.setAttribute("RoomList", list);
				
				url="myMsgList.jsp";
			}
			
			 
			 
			 
			 
			 
			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}