<%@ page import="portit.model.dto.Notification" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/notification.css" rel="stylesheet">
<script>
	$(document).ready(function(){
		// tr 반복 -> nt_isread가 t일때 -> css 바꾸기
		$("tr").each(function(){
			var ntid = $(this).find("input").val();
			var tr = $(this);
			
			$.ajax({
				url: "/nt", 
				type: "post",
				data: {act: "getread", id: ntid},
				success: function(result){							
		            if(result.charAt(0) == 't') {
		            	tr.find(".sort").css("color", "#797979");
		            	tr.find(".content > a").css("font-weight", "normal");
		            }
	        }});
		});
	});

	
	function fnSubmit() {
		input.submit();
	}

</script>
<jsp:useBean id="dao" class="portit.model.dao.NotificationDao" />
<%
	int loginId = (int)session.getAttribute("loginId");
	String sort = request.getParameter("sort");
	
%>
<c:set var="ntList" value="<%= dao.myNoti(loginId, sort) %>" /> 
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt noti">	
					<h3 class="formTitle text-center">알림</h3>				
					<div class="notiTable">			
						<div class="form-group col-md-2">
						<form name="input" method="post" action="/nt?act=select">
						  <select class="form-control pull-left" id="sort" name="sort" onchange="fnSubmit()">
						    <option value="all" <% if(sort.equals("all")) {%>selected<%} %> >전체</option>
						    <option value="like" <% if(sort.equals("like")) {%>selected<%} %>>좋아요</option>
						    <option value="upload" <% if(sort.equals("upload")) {%>selected<%} %>>업로드</option>
						    <option value="project" <% if(sort.equals("project")) {%>selected<%} %>>신청</option>
						    <option value="message" <% if(sort.equals("message")) {%>selected<%} %>>메세지</option>
						    <option value="follow" <% if(sort.equals("follow")) {%>selected<%} %>>팔로잉</option>
						  </select>
						</form>
						</div>	
						
						<table class="table">							
	                        <thead>
	                         <tr>
	                             <th>#</th>
	                             <th>내용</th>
	                             <th>날짜</th>
	                             <th></th>
	                         </tr>
	                        </thead>
	                        <tbody>
	                        <c:if test="${ntList.size() != 0 && ntList.size() > 0}">	          	
		                        <c:forEach begin="0" end="${ntList.size()-1}" var="i">
	                   				<tr>
	                   					<input type="hidden" value="${ntList[i].nt_id}">
	                  					<c:if test="${ntList[i].nt_type eq 'like' }">
	                  						<td class="sort"><span class="glyphicon glyphicon-heart"></span></td>
	                       					<td class="content"><a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">${dao.getSenderName(ntList[i].mem_id_sender)}님이 내 포트폴리오를 좋아합니다.</a></td>
	                  					</c:if>
	                  					<c:if test="${ntList[i].nt_type  eq 'upload' }">
	                  						<td class="sort"><span class="glyphicon glyphicon-upload"></span></td>
	                        				<td class="content"><a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">${dao.getSenderName(ntList[i].mem_id_sender)}님이 새로운 포트폴리오를 업로드했습니다.</a></td>
	                  					</c:if>
	                  					<c:if test="${ntList[i].nt_type  eq 'message' }">
	                  						<td class="sort"><span class="glyphicon glyphicon-envelope"></span></td>
	                      			    	<td class="content"><a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('/msg?cmd=ntdetail&sender=${ntList[i].mem_id_sender}');">${dao.getSenderName(ntList[i].mem_id_sender)}님이 메세지를 보냈습니다.</a></td>
	                  					</c:if>
	                  					<c:if test="${ntList[i].nt_type  eq 'project' }">
	                  					    <td class="sort"><span class="glyphicon glyphicon-ok-sign"></span></td>
	                        				<td class="content"><a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">${dao.getSenderName(ntList[i].mem_id_sender)}님이 내 프로젝트에 신청했습니다.</a></td>
	                  					</c:if>
	                  					<c:if test="${ntList[i].nt_type  eq 'follow' }">
	                  						<td class="sort"><span class="glyphicon glyphicon-plus"></span></td>
	                       		        	<td class="content"><a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">${dao.getSenderName(ntList[i].mem_id_sender)}님이 나를 팔로잉했습니다.</a></td>
	                  					</c:if>
	                   					<td class="date">${ntList[i].nt_date }</td>
	                        			<td class="delete"><button type="button" class="btn" onclick="location.href='/nt?act=delete&id=${ntList[i].nt_id}'">삭제</button></td>
	                   				</tr>
	                       		</c:forEach>
                       		</c:if>
                       		<c:if test="${ntList.size() == 0 }">
	                        	<tr>
                   					<td colspan="4" align="center">알림이 없습니다.</td>
                   				</tr>
	                        </c:if>                       		
	                        </tbody>	                        
                       	</table>
                       	<!-- 
						<div class="row" style="text-align:center;">
							<a href="#"><i class="fa fa-angle-left"></i> 이전글</a>&nbsp;&nbsp;&nbsp;
								1 2 3 4 5&nbsp;&nbsp;&nbsp;
							<a href="#">다음글 <i class="fa fa-angle-right"></i></a>
						</div>
						 -->
					</div>	
				</div>
			</section>
			<!-- /wrapper --> 
		</section>
		

