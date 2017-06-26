<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>
<%@page import="portit.model.dto.MessageDto"%>
<link href="assets/css/message.css" rel="stylesheet">

<!-- JSP:useBean -->
<%--<jsp:useBean id="dao" class="portit.model.dao.MassageDao"/> --%>
<jsp:useBean id="dto" class="portit.model.dto.MessageDto"/>
<jsp:useBean id="dto2" class="portit.model.dto.MessageDto"/>

<%

//컨트롤러에서 돌아옴
String keyField = request.getParameter("keyField");
String keyWord = request.getParameter("keyWord");
%>




<script>
/* 	window.onload=function(){
		//Ctrl에 접근해서 list 뽑아오기.
		if(){
		location.href="/Project2/msg?cmd=list";
		}
	}; */
</script>

	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>




		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<!--  Message List -->
				<div class="col-md-12 mt msg msgList">
					<div class="msgList_top">
						<span class="pull-left">
							<form class="form-inline" method="post" action="/empty/msg?cmd=list">
								<div class="form-group">
								
							<select name="keyField" size="1">
								<option value="search_name"
								<%if ("mem_name".equals(keyField)) {%>selected="selected" <%}%>>Name
								<option value="search_content" 
								<%if ("mem_EMAIL".equals(keyField)) {%> selected="selected" <%}%>>Content
							</select>
							
							 <input type="text" class="form-control" id="keyWord" name="keyWord"
										placeholder="Search Member">
									<button class="btn" type="submit">Search</button>
								</div>
							</form>
						</span> 
						<span class="pull-right"> 
							<a href="/empty/msg?cmd=send">
								<button class="btn">Send New Message</button>
							</a>
						</span>
					</div>
					
					<!-- Message List Body -->
					<div class="panel-group msgListContent" id="msgBoxList">
						
						
						<%
						ArrayList roomList; 
						
						//대화방 목록.
						roomList= (ArrayList)session.getAttribute("RoomList");
						
						
						int msgNum;
						int mem_id_Sender;
						
						//i=발신자. j=메세지 갯수
						for(int i=0; i<roomList.size();i++){		//발신자 명수만큼.
							ArrayList chatRoom = new ArrayList(); 
						
							chatRoom= (ArrayList) roomList.get(i);	//대화방.
							dto= (MessageDto) chatRoom.get(0);	//첫번째 메세지.
							String name= dto.getSender_Name();	//메세지 발신자.
							
							mem_id_Sender = dto.getMem_id_sender();
							
						%>
						
						
						<!--  #################목록_반복시작#################### -->
								
						
						
						
						<div class="panel panel-default msgBox" >
							<!-- Head -->
							<div class="panel-heading">
								<span class="pull-left">
									<a href="#">
										<img src="assets/img/you.png" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender"><%=name%></span>
									</a>
								</span>
								<span class="pull-right"> 
									<a href="/empty/msg?cmd=send">
										<button type="button" class="btn">Send</button>
									</a>
									<a data-toggle="collapse" data-parent="#msgBoxList" href="#m1_<%=i%>" class="updown collapsed"> 
										<i class="fa fa-chevron-down"></i>
									</a>
								</span>								
							</div>
							<!-- End_Head -->


							<div id="m1_<%=i%>" class="panel-collapse collapse">
								<div class="panel-body">
					
					
					<% 
							
							if(chatRoom.size()>3){
								msgNum=3;
							}else{
								msgNum=chatRoom.size();
							}
					
							//메세지반복.
							for(int j=0; j<msgNum;j++){	
								dto2=(MessageDto) chatRoom.get(j);
					%>
								<form name="msgContentArr" method="post" action="/empty/msg?cmd=delete">
									<!--  대화 내용시작. Msg_Content -->
									<div class="msgContentBox mb clearfix"> 
										<span class="pull-left col-md-10 msgContent">
												<%=dto2.getMsg_content() %>
										</span>
									<div class="pull-right col-md-2" style="height: 55px">
										<div style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i><%= dto2.getMsg_date()%></div>
										<div style="position:absolute; top: 20px; right: 0;">
												<button type="submit" class="btn">삭제</button>
												<input type="hidden" name="deleteMsg" value="<%=i%>,<%=j%>">
												<input type="hidden" name="msg_id" value="<%=dto2.getMsg_id()%>">
										</div>											
									</div>
									</div>	
								</form>
									<!--  END_Msg_Content -->
					<%
							}	//메세지 반복.
					%>
									<form name="more" method="post" action="/empty/msg?cmd=detail">
										<input type="hidden" name="mem_id_sender" value="<%=mem_id_Sender%>">
										<div class="pull-right">
											<button type="submit" class="btn" >More</button>
										</div>
									</form>				
								</div>
							</div> <!-- END - msgBoxOpen -->
						</div> <!-- END - msgBox -->

									
						<!-- ##################### 반복 끝 ################## -->
			<% 
			}	//모든 발신자.
			%>
						
						
						
						
						
						
						
						
					</div> <!-- END - Message List Body  -->
				</div> 
			</section>
			<!--/wrapper -->
		</section>
		<!-- /MAIN CONTENT -->







		<!--main content end-->


	<script>
		$(document).ready(function(){
			$('.updown').on('click',function(){
				var icon = $(this).find('i');
				
		        if (icon.hasClass("fa-chevron-down")){
		        	icon.addClass("fa-chevron-up").removeClass("fa-chevron-down");
		    	} 
		        else {
		        	 icon.addClass("fa-chevron-down").removeClass("fa-chevron-up");
		        }
		    });
		});
		

	    
	</script>
