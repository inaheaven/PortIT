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
					
					System.out.println("JSP(detail)");
					MessageDto firstMsg=null;
					ArrayList chatroom= (ArrayList)session.getAttribute("chatroom");
					
					if(null!=chatroom){
					 firstMsg= (MessageDto) chatroom.get(0);//첫번째 메세지
				
					
					
					//Request값 수신.
					
					
					//int profile_id=Integer.parseInt((String)request.getAttribute("profile_id"));
					//int loginId = Integer.parseInt((String)request.getAttribute("loginId")); 
					
					int loginId = (int)request.getAttribute("loginId"); 
					//int profile_id=(int)request.getAttribute("profile_id");
					String mem_img=(String) request.getAttribute("mem_img");
					String mem_nick=(String) request.getAttribute("mem_nick"); 
				
					//int log_id=Integer.parseInt(loginId);
					System.out.println("JSP2222="+loginId);
					
					
					
					
				
					System.out.println("JSP333="+loginId);
					
					//대화방 첫번째 메세지의 발신자 ID,이름.
					int mem_id=firstMsg.getMem_id_sender();
					String name= firstMsg.getSender_Name();	
				
					

					// 페이징 기능 추가
					int totalRecord = chatroom.size();		//총 대화방의 수.
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
				
				
					
					System.out.println("JSP444="+loginId);
					//내가 첫번째 발산지라면 수신자를 기록.
					if(String.valueOf(firstMsg.getMem_id_sender()).equals(loginId)){	
						System.out.println("JSP555="+loginId);
						
						//수신자의 mem_id_recever저장.
						mem_id=firstMsg.getMem_id_receiver();
						//한글화 convert
						name=dao.convertToName(String.valueOf(mem_id));
					}
					
					%>
						<div class="panel panel-default msgBox">
							<div class="panel-heading">
								<span class="pull-left">
									<a onclick="window.open('/view?type=profile&id=<%=mem_nick%>');">
										<img src="<%=mem_img%>" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender"><%=name %></span>
									</a>
								</span>
                
								<form name="msgSend" method="post" action="/msg?cmd=send">
									<span class="pull-right"> 
										<button type="submit" class="btn" >메세지 보내기</button>
										<input type="hidden" name="mem_id_sender" value="<%=mem_id%>" />
										&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn" onclick="location.href='/msg?cmd=list&renewal=n'">목록</button>
											<input type="hidden" name="renewal" value="n" />
									</span>		
								</form>						
							</div>
							<div class="panel-body">
							
							
							
							<% 
							
							System.out.println("[JSP]갯수출력="+chatroom.size());
								//출력범위를 정한다.
								for(int i=beginPerPage; i<numPerPage+beginPerPage; i++){
									if(i == totalRecord)
										break;
								
								
								MessageDto msg = (MessageDto) chatroom.get(i);
								String msg_id= String.valueOf(msg.getMsg_id());
							%>
							<!--  #######반복 시작 ############# -->
									
								<div class="msgContentBox mb clearfix" > 
								
								<%
									String dir_1="right";
									String dir_2="left";
									
									//방향바꾸셈.
									if(loginId==msg.getMem_id_receiver()){
										 dir_1="left";
										 dir_2="right";
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
								}	//반복끝..
							%>
								
								<!-- 페이지네이션 -->
								<div align="center">
									Go to Page
									<%		if (nowBlock > 0) { %>
									<a href="msg?cmd=lpDetail&nowBlock=<%=nowBlock - 1%>&nowPage=<%=pagePerBlock * (nowBlock - 1)%>">이전<%=pagePerBlock%>개
									</a>
									<% } %>
									:::
									<%
										for (int i = 0; i < pagePerBlock; i++) {
											if ((nowBlock * pagePerBlock) + i == totalPage)
												break;
									%>
									<a href="msg?cmd=lpDetail&nowPage=<%=(nowBlock * pagePerBlock) + i%>&nowBlock=<%=nowBlock%>"><%=(nowBlock * pagePerBlock) + i + 1%></a>&nbsp;&nbsp;&nbsp;
									<% } %>
									:::
									<% if (totalBlock > nowBlock + 1) { %>
									<a href="msg?cmd=lpDetail&nowBlock=<%=nowBlock + 1%>&nowPage=<%=pagePerBlock * (nowBlock + 1)%>">다음<%=pagePerBlock%>개
									</a>
									<% }
									%>
								</div>
												
								
								
								
												
							</div>			
								
						</div> <!-- END - msgBoxOpen -->
							<%} %>
					</div> <!-- END - msgBox -->			
						
						
					</div>
				</div>

				<!--  chat box end -->


			</section>
			<! --/wrapper -->
		</section>
		<!-- /MAIN CONTENT -->

