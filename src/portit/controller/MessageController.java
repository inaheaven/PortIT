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
			
			int login_id = (int) session.getAttribute("loginId");
			
			
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
				//굉장히 헤비한 요청...(Delete,Insert 등의 변화가 생기지 않으면 하지 않는게 좋다.)
				//좀 라이트한 방식으로 변경할 필요성이 있다. 인터넷이 불안정하면 오류가 날 확률이 높다.
					
				//처음MsgList에 접근했을때
				//삭제 이후 돌아왔을때
				//검색이후 돌아왔을때
				//갱신요청 유무를 정한다...
					
				String renewal= req.getParameter("renewal");
				
					//목록을 갱신할 필요가 있을때.
					if(renewal==null){
						
						
						list=model.roomList(keyField,keyWord);
						session.setAttribute("RoomList", list);
					}
				}
				
				catch(Exception err){
					System.out.println("MessageController_cmd=list()에서 오류");
					err.printStackTrace();
				}
			}
			 
			 
			 //페이징 구현을 위한 경로
			 // List를 뽑아오는 작업이 필요없다.
			 else if(cmd.equals("lp")){
					url="myMsgList.jsp";
					
					try{
					}
					
					catch(Exception err){
						System.out.println("MessageController_cmd=list()에서 오류");
						err.printStackTrace();
					}
			 }
			
			 else if(cmd.equals("lpDetail")){
				 url="myMsgDetail.jsp";
				 
				 try{
				 }
				 
				 catch(Exception err){
					 System.out.println("MessageController_cmd=list()에서 오류");
					 err.printStackTrace();
				 }
			 }
			 
			
			else if(cmd.equals("list_send")){
				//From msgSend
				
				url="myMsgList.jsp";
				
				
				
				//입력확인.
				Boolean InputCheck =model.insertMessage();
				
				//Msg 입력에 대한 경고메세지. JSP에서 Script function으로 수정하자.
				if(InputCheck==true){
					System.out.println("CTRL: 메세지전달성공");
					//req.setAttribute("InputCheck","y");
					url="myMsgSend.jsp";
				}else{
					System.out.println("CTRL: 내용미입력");
					//req.setAttribute("InputCheck", "n");
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
				String mem_id_Sender=req.getParameter("mem_id_sender");
			
				
				url="myMsgDetail.jsp";

				//대화방 with "mem_id_sender"
				//2.발신자의 대화방을 얻어온다.
				list=model.getChatRoom(mem_id_Sender);
				session.setAttribute("chatroom", list);
			}
			
			else if(cmd.equals("ntdetail")){
				//1.List에서 발신자이름을 받아온다.
				int mem_id_Sender=Integer.parseInt(req.getParameter("sender"));
			
				
				url="myMsgDetail.jsp";

				//대화방 with "mem_id_sender"
				//2.발신자의 대화방을 얻어온다.
				list=model.getChatRoom(String.valueOf(mem_id_Sender));
				session.setAttribute("chatroom", list);
			}
			
			
			else if(cmd.equals("send")){
				//from msgList, msgDetail
				
				
				
				//MsgList에서 전달 받았을때. 해당 발신자의 메일주소를 담아라.
				if(req.getParameter("mem_id_sender")!=null){
				
				
				String partnerEmail=model.toEmail(req.getParameter("mem_id_sender"));
				req.setAttribute("partnerEmail", partnerEmail);
				}
				
				url="myMsgSend.jsp";
			}
			 
			 
			 
			else if(cmd.equals("delete")){
				//from msgList
				
				//JSP로부터 msg_id 전달받음.
				String msg_id= req.getParameter("msg_id");
				
				model.deleteMsg(msg_id);
				
				//request단위로 바꿔서 Test해보기.
				list=model.roomList(keyField,keyWord);
				session.setAttribute("RoomList", list);
				
				url="myMsgList.jsp";
			}
			
			 
			 
			else if(cmd.equals("delete_detail")){
				//from msgDetail
				
				//JSP로부터 msg_id 전달받음.
				String msg_id= req.getParameter("msg_id");
				
				//request를 받아와야한다.
				String mem_id_Sender=req.getParameter("mem_id_Sender");;
				
				model.deleteMsg(msg_id);
				
				//request단위로 바꿔서 Test해보기.
				list=model.getChatRoom(mem_id_Sender);
				session.setAttribute("chatroom", list);
				
				url="myMsgDetail.jsp";
			}
			 
			 
			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			view.forward(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 -페이지 이동별 불필요한 연산을 건너뛰도록 정리가 필요함.
	 -URL을 깔끔하게 만들필요가 있음.
	 */
	
	
}