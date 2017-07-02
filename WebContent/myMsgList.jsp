<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Import -->
<%@page import="java.util.ArrayList"%>
<%@page import="portit.model.dto.MessageDto"%>
<link href="assets/css/message.css" rel="stylesheet">

<!-- JSP:useBean -->
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
							<form class="form-inline" method="post" action="/msg?cmd=list">
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
							<a href="/msg?cmd=send">
								<button class="btn">Send New Message</button>
							</a>
						</span>
					</div>
					
					<!-- Message List Body -->
					<div class="panel-group msgListContent" id="msgBoxList">
						
						
						<%
						
						ArrayList roomList; 
						
						//대화방 목록.(수신메세지만 담겨있다.)
						roomList= (ArrayList)session.getAttribute("RoomList");
						
						
						int msgNum;
						int mem_id_Sender;
						
						MessageDto dto3;
						//인스턴스 생성이 필요없다. 인스턴스주소를 받아줄 변수만 필요하다.
						
						
						
						
							// 페이징 기능 추가
							int totalRecord = roomList.size();		//총 대화방의 수.
							int numPerPage = 5;						//한  대화방/대화방
							int totalPage = 0;						//총 페이지 수.
							int nowPage = 0;						//현재 페이지
							int beginPerPage = 0;					//해당페이지의 시작점.(배열Index)
							int pagePerBlock = 3;					//Page 블럭.(단위: 3페이지)
							int totalBlock = 0;						//Total 블럭.
							int nowBlock = 0;						//현재 블럭.
							
							totalPage = (int)Math.ceil((double)totalRecord/numPerPage);
							
							//Request에서 현재 페이지위치를 확인한다.
							if(request.getParameter("nowPage")!=null)
								nowPage = Integer.parseInt(request.getParameter("nowPage"));
							
							if(request.getParameter("nowBlock")!=null)
								nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
							
							totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
							
							//시작페이지= 현재페이지*페이지당 대화방
							beginPerPage = nowPage * numPerPage;
						
						
						
						//i=발신자. j=메세지 갯수
						/* for(int i=0; i<roomList.size();i++){		//발신자 명수만큼. */
							
						
						//출력범위를 정한다.
						for(int i=beginPerPage; i<numPerPage+beginPerPage; i++){
							if(i == totalRecord)
								break;
							
							
							ArrayList chatRoom = new ArrayList(); 
						
							chatRoom= (ArrayList) roomList.get(i);	//대화방.
							dto3= (MessageDto) chatRoom.get(0);	//첫번째 메세지.(모두 수신메세지)
							String name= dto3.getSender_Name();	//메세지 발신자.
							
							mem_id_Sender = dto3.getMem_id_sender();
							
							
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
								<form name="msgSend" method="post" action="/msg?cmd=send">
									<span class="pull-right"> 
										<button type="submit" class="btn">Send</button>
										<input type="hidden" name="mem_id_sender" value="<%=mem_id_Sender%>">
										<a data-toggle="collapse" data-parent="#msgBoxList" href="#m1_<%=i%>" class="updown collapsed"> 
											<i class="fa fa-chevron-down"></i>
										</a>
									</span>		
								</form>						
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
								<form name="msgContentArr" method="post" action="/msg?cmd=delete">
									<!--  대화 내용시작. Msg_Content -->
									<div class="msgContentBox mb clearfix"> 
										<span class="pull-left col-md-10 msgContent">
												<%=dto2.getMsg_content() %>
										</span>
									<div class="pull-right col-md-2" style="height: 55px">
										<div style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i><%= dto2.getMsg_date()%></div>
										<div style="position:absolute; top: 20px; right: 0;">
												<button type="submit" class="btn">삭제</button>
												<input type="hidden" name="msg_id" value="<%=dto2.getMsg_id()%>">
										</div>											
									</div>
									</div>	
								</form>
									<!--  END_Msg_Content -->
					<%
							}	//메세지 반복.
					%>
									<form name="more" method="post" action="/msg?cmd=detail">
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


				
<br><br>
				<div align="center">
					Go to Page
					<%		if (nowBlock > 0) { %>
					<a href="msg?cmd=lp&nowBlock=<%=nowBlock - 1%>&nowPage=<%=pagePerBlock * (nowBlock - 1)%>">이전<%=pagePerBlock%>개
					</a>
					<% } %>
					:::
					<%
						for (int i = 0; i < pagePerBlock; i++) {
							if ((nowBlock * pagePerBlock) + i == totalPage)
								break;
					%>
					<a href="msg?cmd=lp&nowPage=<%=(nowBlock * pagePerBlock) + i%>&nowBlock=<%=nowBlock%>"><%=(nowBlock * pagePerBlock) + i + 1%></a>&nbsp;&nbsp;&nbsp;
					<% } %>
					:::
					<% if (totalBlock > nowBlock + 1) { %>
					<a href="msg?cmd=lp&nowBlock=<%=nowBlock + 1%>&nowPage=<%=pagePerBlock * (nowBlock + 1)%>">다음<%=pagePerBlock%>개
					</a>
					<% }%>
				</div>

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
