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
					ArrayList chatroom= (ArrayList)session.getAttribute("chatroom");
					MessageDto firstMsg= (MessageDto) chatroom.get(0);//첫번째 메세지
					int id;
					String name= firstMsg.getSender_Name();	//발신자 이름.(수신발신이 섞여있음으로 안된다.)
					//만약 첫번째 메세지의 Sender가 나라면 reiver이름을 저장해라.
					
					//접속유져를 기록하는 방법 : session
					int longin_id = Integer.parseInt((String) session.getAttribute("longin_id"));
								
					
					//If last sender was me....
					if(firstMsg.getMem_id_sender()==longin_id){	
						
						//수신자의 mem_id_recever저장.
						id=firstMsg.getMem_id_receiver();
						
						//한글화 convert
						name=dao.convertToName(String.valueOf(id));
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
								<span class="pull-right"> 
									<a href="/empty/msg?cmd=send">
										<button type="button" class="btn">메세지 보내기</button>
									</a>&nbsp;&nbsp;&nbsp;
									<a href="/empty/msg?cmd=list">
										<button type="button" class="btn">목록</button>
									</a>
								</span>								
							</div>
							<div class="panel-body">
							
							
							
							<% for(int i=0; i<chatroom.size(); i++){
								MessageDto msg = (MessageDto) chatroom.get(i);
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
										<div style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i><%= msg.getMsg_date()%></div>
										<div style="position:absolute; top: 20px; right: 0;"><button type="button" class="btn">삭제</button></div>											
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

