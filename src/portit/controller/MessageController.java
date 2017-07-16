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
	//0705
	
	//컨트롤러
	//model에서 request로부터 뽑아서 데이터에 저장하는작업.
	// /msg?cmd=send&mem_id_sender=
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			
			resp.setContentType("text/html; charset=UTF-8");
			
			HttpSession session = req.getSession();
			String cmd= req.getParameter("cmd");
			String keyField = req.getParameter("keyField");
			String keyWord = req.getParameter("keyWord");
			String url= "WebContent/msgList.html";
			String pageName ="memberSearch.jsp";	//test page
			
			
			
			//CTRL에는 변수를 저장하면 안된다. 필요에 따라 변수를 저장하더라도 일시적으로 사용해야한다.
		
			System.out.println("[CTRL] 시작.");
			
			int loginId=-1;
			
			if(null!=session.getAttribute("loginId")){
				loginId=(int) session.getAttribute("loginId");
			System.out.println(loginId+"가 로그인 되었습니다.");
			}
			
			
			
			//인스턴스 생성!
			MessageModel model = new MessageModel(req);
			
			
			ArrayList msgSenderList= (ArrayList)session.getAttribute("msgSenderList");
			ArrayList list= (ArrayList)session.getAttribute("msgList");
			
			
		
			
			
			//msgList.jsp
			//발신자목록  request!
			 if(cmd.equals("list")){
				//From msgDetail,msgSender
				 
				 String renewal= req.getParameter("renewal");
				url="myMsgList.jsp";
				
				
				try{
					
				//2.RoomList
				//List 갱신방법에 대해서 생각할것!
					
						list=model.roomList(keyField,keyWord);
						session.setAttribute("RoomList", list);
						//리스트갱신... 
						req.setAttribute("partenrInform", model.partnerInform(keyField,keyWord));
						System.out.println("[CTRL]:리스트 조회합니다.");
						
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
						
						list=model.roomList(keyField,keyWord);
						session.setAttribute("RoomList", list);
						req.setAttribute("partenrInform", model.partnerInform(keyField,keyWord));
						
						
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
			 
			
			 //메세지를 보낼떄.
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
				
				System.out.println("디테일접근확인");
				
				//1.List에서 발신자이름을 받아온다.
				String mem_id_Sender=req.getParameter("mem_id_sender") ;
				String mem_img = req.getParameter("mem_img");
				String mem_nick = req.getParameter("mem_nick");
				String profile_id = req.getParameter("profile_id");
				
				
				System.out.println("CTRL확인="+mem_id_Sender);
				url="myMsgDetail.jsp";

				//대화방 with "mem_id_sender"
				//2.발신자의 대화방을 얻어온다.
				list=model.getChatRoom(mem_id_Sender);
				
				
				
				req.setAttribute("mem_id_Sender", mem_id_Sender);
				req.setAttribute("mem_img", mem_img);
				req.setAttribute("mem_nick", mem_nick);
				req.setAttribute("profile_id", profile_id);
				req.setAttribute("loginId",loginId);			//발신자와 수신자를 식별하기 위해서...
				
				
				System.out.println("[CTRL]mem_img="+mem_img);
				System.out.println("[CTRL]mem_nick="+mem_nick);
				System.out.println("[CTRL]profile_id="+profile_id);
				System.out.println("[CTRL]loginId="+loginId);
				
				
				System.out.println("CTRL복귀확인="+list);
				
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
			 
			 
			 //LIST
			else if(cmd.equals("delete")){
				//from msgList
				
				//JSP로부터 msg_id 전달받음.
				String msg_id= req.getParameter("msg_id");
				
				model.deleteMsg(msg_id);
				
				
				

				//리스트갱신... 
				req.setAttribute("partenrInform", model.partnerInform(keyField,keyWord));
				
				
				//request단위로 바꿔서 Test해보기.
				list=model.roomList(keyField,keyWord);
				session.setAttribute("RoomList", list);
				
				url="myMsgList.jsp";
			}
			
			 
			 
			else if(cmd.equals("delete_detail")){
				//from msgDetail
				
				
				System.out.println("CTRL:getAttribute 접근");
				
				
				//JSP로부터 msg_id 전달받음.
				String msg_id= req.getParameter("msg_id");
				
				//request를 받아와야한다.
				String mem_id_Sender=req.getParameter("mem_id_Sender");;
				
				model.deleteMsg(msg_id);
				
				
				//갱신.
				//request단위로 바꿔서 Test해보기.
				list=model.getChatRoom(mem_id_Sender);
				session.setAttribute("chatroom", list);
				
				
				
				
//				req.setAttribute("loginId",loginId);
				System.out.println("CTRL:삭제완료");
				url="myMsgDetail.jsp";
			}
			 
			 
			 
			 
			// req.setAttribute("partenrInform", model.partnerInform(keyField,keyWord));
			 
			 
			req.setAttribute("loginId",loginId);
			req.setAttribute("pageName", url);
			RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
			// resp.sendRedirect("/page?page=" + url);
			
			view.forward(req,resp);
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("MSG_CTRL 에러");
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 -페이지 이동별 불필요한 연산을 건너뛰도록 정리가 필요함.
	 -URL을 깔끔하게 만들필요가 있음.
	 */
}