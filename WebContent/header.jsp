<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<header class="header black-bg">
	<!--logo start-->
	<a href="/page?page=main" class="logo"><b>Port IT</b></a>
	<!--logo end-->
	<div class="nav notify-row" id="top_menu">
		<!--  menu start -->
		<ul class="nav top-menu">
			<!-- settings start -->
			<li><a href="/page?page=memSearch">Member</a></li>
			<li><a href="/page?page=pfSearch">Portfolio</a></li>
			<li><a href="/page?page=projSearch">Project</a></li>
			<!-- <li><a href="">Community</a></li> -->
		</ul>
		<!--  menu end -->
	</div>
	<div class="top-menu-right">
		<ul class="nav pull-right top-menu">
			<li>
				<form class="form-inline top-menu-search" method="post" action="">
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
					<i class="fa fa-bell"></i> <span class="badge bg-theme"></span>
				</a>
				<ul class="dropdown-menu extended inbox">
					<div class="notify-arrow notify-arrow-yellow"></div>
					<li>
						<p class="yellow">Notification</p>
					</li>
					<li><a href=""> <span>구분</span>&nbsp;/&nbsp; <span
							class="time">Just now</span> <span class="message">[누구누구]님이
								내 포트포리오를 좋아합니다.</span>
					</a></li>
					<li><a href="/page?page=myNotification">더보기</a></li>
				</ul>
			</li>
			<li id="header_inbox_bar" class="dropdown mypage">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<img alt="avatar" src="assets/img/ui-danro.jpg" class="img-circle">
				</a>
				<ul class="dropdown-menu extended inbox">
					<div class="notify-arrow notify-arrow-yellow"></div>
					<li>
						<p class="yellow">김수연 님</p> <!-- 로그인한 -->
					</li>
					<li><a href="/page?page=myProf">내 프로필</a></li>
					<li><a href="/page?page=myPfList">내 포트폴리오</a></li>
					<li><a href="/page?page=myProjList">내 프로젝트</a></li>
					<li><a href="/page?page=myBookmark">북마크</a></li>
					<li><a href="/page?page=myMsgList">메세지</a></li>
					<li><a href="/page?page=myAccount">계정 설정</a></li>
					<li><a class="logout" href="index.jsp">로그아웃</a></li> <!-- 로그아웃 처리해야함 - session 지우기 -->
				</ul>
			</li>
		</ul>
	</div>
</header>