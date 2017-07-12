<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="portit.model.dto.Profile"%>
<jsp:useBean id="dao" class="portit.model.dao.NotificationDao" />
<%
	String loginEmail = (String)session.getAttribute("loginEmail");
	Profile prof = new Profile();
	int loginId = (int)session.getAttribute("loginId");
%>
<c:set var="ntList" value="<%= dao.headerNoti(loginId) %>" />
<header class="header black-bg">
	<!--logo start-->
	<a href="/page?page=main" class="logo"><b>Port IT</b></a>
	<!--logo end-->
	<div class="nav notify-row" id="top_menu">
		<!--  menu start -->
		<ul class="nav top-menu">
			<!-- settings start -->
			<li><a href="/page?page=memList">Member</a></li>
			<li><a href="/page?page=pfList">Portfolio</a></li>
			<li><a href="/page?page=projList">Project</a></li>
			<!-- <li><a href="">Community</a></li> -->
		</ul>
		<!--  menu end -->
	</div>
	<div class="top-menu-right">
		<ul class="nav pull-right top-menu">
			<li>
				<form class="form-inline top-menu-search" method="post" action="/search?cmd=SEARCH">
					<div class="input-group">
						<input type="text" class="form-control round-form" name="search" size="20" placeholder="통합 검색" /> 
							<span class="input-group-btn">
							<button type="submit" class="btn btn-default round-form" style="height: 34px;">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</form>
			</li>
			<li id="header_inbox_bar" class="dropdown">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="fa fa-bell"></i>
					<c:if test="${ntList.size() != 0 }">
						<span class="hasAlarm"></span>
					</c:if>
				</a>
				<ul class="dropdown-menu extended inbox" style="width: 260px !important; left: -200px;">
					<div class="notify-arrow notify-arrow-yellow"></div>
					<li>
						<p class="yellow">Notification</p>
					</li>
					<c:if test="${ntList.size() != 0 }">
						<c:forEach begin="0" end="4" var="i"> 
							<li>
							<c:if test="${ntList[i].nt_type eq 'like' }">
								<a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">
	          						<span class="glyphicon glyphicon-heart"></span>&nbsp;/&nbsp; 
	          						<span class="time">${ntList[i].nt_date }</span>
	               					<span class="message">${dao.getSenderName(ntList[i].mem_id_sender)}님이 내 포트폴리오를 좋아합니다.</span>
               					</a>
          					</c:if>
          					<c:if test="${ntList[i].nt_type  eq 'upload' }">
          						<a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">
	          						<span class="glyphicon glyphicon-upload"></span>&nbsp;/&nbsp; 
	          						<span class="time">${ntList[i].nt_date }</span>
	               					<span class="message">${dao.getSenderName(ntList[i].mem_id_sender)}님이 새로운 포트폴리오를 업로드했습니다.</span>
               					</a>
          					</c:if>
          					<c:if test="${ntList[i].nt_type  eq 'message' }">
          						<a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('/msg?cmd=detail&mem_id_sender=${ntList[i].mem_id_sender}');">
	          						<span class="glyphicon glyphicon-envelope"></span>&nbsp;/&nbsp; 
	          						<span class="time">${ntList[i].nt_date }</span>
	               					<span class="message">${dao.getSenderName(ntList[i].mem_id_sender)}님이 메세지를 보냈습니다.</span>
               					</a>
          					</c:if>
          					<c:if test="${ntList[i].nt_type  eq 'project' }">
          						<a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">
	          						<span class="glyphicon glyphicon-ok-sign"></span>&nbsp;/&nbsp; 
	          						<span class="time">${ntList[i].nt_date }</span>
	               					<span class="message">${dao.getSenderName(ntList[i].mem_id_sender)}님이 내 프로젝트에 신청했습니다.</span>
               					</a>
          					</c:if>
          					<c:if test="${ntList[i].nt_type  eq 'follow' }">
          						<a href="/nt?act=read&id=${ntList[i].nt_id}" onclick="window.open('');">
	          						<span class="glyphicon glyphicon-plus"></span>&nbsp;/&nbsp; 
	          						<span class="time">${ntList[i].nt_date }</span>
	               					<span class="message">${dao.getSenderName(ntList[i].mem_id_sender)}님이 나를 팔로잉했습니다.</span>
               					</a>
          					</c:if>
          					</li>
						</c:forEach>
					</c:if>
					<li><a href="/page?page=myNotification&sort=all">더보기</a></li>
				</ul>
			</li>
			<li id="header_inbox_bar" class="dropdown mypage">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<img alt="profileImage" src="<%=prof.getProf_img() %>" class="img-circle">
				</a>
				<ul class="dropdown-menu extended inbox">
					<div class="notify-arrow notify-arrow-yellow"></div>
					<li>
						<p class="yellow"><%=loginEmail %> 님</p> <!-- 로그인한 -->
					</li>
					<li><a href="/page?page=myProf">내 프로필</a></li>
					<li><a href="/page?page=myPfList">내 포트폴리오</a></li>
					<li><a href="myproj?cmd=list">내 프로젝트</a></li>
					<li><a href="/bmk?cmd=MYBOOKMARK">북마크</a></li>
					<li><a href="/page?page=myFollowing">팔로잉</a></li>
					<li><a href="/msg?cmd=list">메세지</a></li>
					<li><a href="/account?cmd=alter">계정 설정</a></li>
					<li><a class="logout" href="/logout">로그아웃</a></li> <!-- 로그아웃 처리해야함 - session 지우기 -->
				</ul>
			</li>
		</ul>
	</div>
</header>