<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<header class="header black-bg">
	<!--logo start-->
	<a href="index.html" class="logo"><b>Port IT</b></a>
	<!--logo end-->
	<div class="nav notify-row" id="top_menu">
		<!--  menu start -->
		<ul class="nav top-menu">
			<!-- settings start -->
			<li><a href="">Member</a></li>
			<li><a href="">Portfolio</a></li>
			<li><a href="">Project</a>
			<li>
			<li><a href="">Community</a></li>
		</ul>
		<!--  menu end -->
	</div>
	<div class="top-menu-right">
		<ul class="nav pull-right top-menu">
			<li>
				<form class="form-inline top-menu-search" method="post" action="">
					<div class="input-group">
						<input type="text" class="form-control round-form" name="search"
							size="20" placeholder="통합 검색" /> <span class="input-group-btn">
							<button type="submit" class="btn btn-default round-form" style="height: 34px;">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</form>
			</li>
			<li id="header_inbox_bar" class="dropdown"><a
				data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
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
					<li><a href="">더보기</a></li>
				</ul></li>
			<li id="header_inbox_bar" class="dropdown mypage"><a
				data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
					<img alt="avatar" src="assets/img/ui-danro.jpg" class="img-circle">
			</a>
				<ul class="dropdown-menu extended inbox">
					<div class="notify-arrow notify-arrow-yellow"></div>
					<li>
						<p class="yellow">김수연 님</p>
					</li>
					<li><a href="">내 프로필</a></li>
					<li><a href="">팔로잉한 멤버</a> <a href="">북마크한 포트폴리오</a></li>
					<li><a href="">개인정보 설정</a></li>
					<li><a class="logout" href="login.html">로그아웃</a></li>
				</ul></li>
		</ul>
	</div>
</header>