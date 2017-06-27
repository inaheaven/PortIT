<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>
<%@page import="portit.model.dto.MessageDto"%>
<%@page import="portit.model.dao.MassageDao"%>


<link href="assets/css/message.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>


<!-- Bean -->
<jsp:useBean id="dao" class="portit.model.dao.MassageDao"/>
<jsp:useBean id="dto" class="portit.model.dto.MessageDto"/>



		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt msg msgDetail">
					<div class="panel-group msgDetailContent">
					
					
					<% 
					//접속유져를 기록하는 방법 : session
					int longin_id = Integer.parseInt((String) session.getAttribute("longin_id"));
					
					
					
					ArrayList chatroom= (ArrayList)session.getAttribute("chatroom");
					MessageDto firstMsg= (MessageDto) chatroom.get(0);//첫번째 메세지
					
					
					//대화방 첫번째 메세지의 발신자 ID,이름.
					int mem_id=firstMsg.getMem_id_sender();
					String name= firstMsg.getSender_Name();	
					
					
					//내가 첫번째 발산지라면 수신자를 기록.
					if(firstMsg.getMem_id_sender()==longin_id){	
						
						//수신자의 mem_id_recever저장.
						mem_id=firstMsg.getMem_id_receiver();
						//한글화 convert
						name=dao.convertToName(String.valueOf(mem_id));
					}
					
					%>
						<div class="panel panel-default msgBox">
							<div class="panel-heading">
								<span class="pull-left">
									<a href="#">
										<img src="assets/img/you.png" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender"><%=name %></span>
									</a>
								</span>
								<form name="msgSend" method="post" action="/msg?cmd=send">
								
								<span class="pull-right"> 
									<button type="submit" class="btn">메세지 보내기</button>
									<input type="hidden" name="mem_id_sender" value="<%=mem_id%>" />
									&nbsp;&nbsp;&nbsp;
									<a href="/msg?cmd=list">
										<button type="button" class="btn">목록</button>
									</a>
								</span>		
								</form>						
							</div>
							<div class="panel-body">
							
							
							
							<% for(int i=0; i<chatroom.size(); i++){
								MessageDto msg = (MessageDto) chatroom.get(i);
								String msg_id= String.valueOf(msg.getMsg_id());
							%>
							<!--  #######반복 시작 ############# -->
									
								<div class="msgContentBox mb clearfix" > 
								
								<%
									String dir_1="left";
									String dir_2="right";
									
									//방향바꾸셈.
									if(longin_id==msg.getMem_id_receiver()){
										 dir_1="right";
										 dir_2="left";
									}
								%>
									<div class="pull-<%=dir_1%> col-md-10 msgContent clearfix">
										<%=msg.getMsg_content() %>
									</div>

							<div class="pull-<%=dir_2%> col-md-2" style="height: 55px">
								<div style="position: absolute; top: 0; right: 0;">
									<i class="fa fa-clock-o fa-fw"></i><%=msg.getMsg_date()%></div>
								<form name="msgSend" method="post" action="/msg?cmd=delete_detail">
									<div style="position: absolute; top: 20px; right: 0;">
										<button type="submit" class="btn">삭제</button>
									</div>
									<input type="hidden" name="msg_id" value="<%=msg_id%>" />
									<input type="hidden" name="mem_id_Sender" value="<%=mem_id%>" />
								</form>
							</div>
						</div>	
								
								
								
								
								
								
								<!--  ###########반복 끝############# -->
							<%
								}
							%>
								
								<!-- 페이지네이션 -->
								<div class="center"> 
									1 2 3 4 5
								</div>					
							</div>			
								
						</div> <!-- END - msgBoxOpen -->
					</div> <!-- END - msgBox -->			
						
							
					</div>
				</div>

				<!--  chat box end -->


			</section>
			<! --/wrapper -->
		</section>
		<!-- /MAIN CONTENT -->

