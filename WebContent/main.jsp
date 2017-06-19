<%@page import="portit.model.dto.Timeline"%>
<%@page import="portit.model.dto.Project"%>
<%@page import="portit.model.dto.Developer"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>DASHGUM - Bootstrap Admin Template</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">

<script src="assets/js/chart-master/Chart.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<jsp:useBean id="portfolio" class="portit.model.dao.Portfolio_ViewDao" />
<jsp:useBean id="developer" class="portit.model.dao.Developer_ViewDao" />
<jsp:useBean id="project" class="portit.model.dao.Proj_viewDao" />
<jsp:useBean id="timeline" class="portit.model.dao.Timeline_ViewDao" />

<body>
	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
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
					<li><a href="">Project</a> <li>
					
					<li><a href="">Community</a></li>
				</ul>
				<!--  menu end -->
			</div>
			
			<div class="top-menu-right">
				<ul class="nav pull-right top-menu">
					<li>
						<form class="form-inline top-menu-search" method="post"
							action="/Project2/search?cmd=SEARCH">
							<div class="input-group">
								<input type="text" class="form-control round-form" name="search"
									size="20" placeholder="통합 검색" />
								<span class="input-group-btn">
									<button type="submit" class="btn btn-default round-form">
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
							<img alt="avatar" src="assets/img/ui-danro.jpg"
							class="img-circle">
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
		<!--header end-->
	</section>



	<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
	<!--main content start-->
	<section class="container">
		<section class="wrapper site-min-height">
			<div class="col-md-12 col-sm-12 col-xs-12 mt" id="main">	
				<div class="recommend">
					<b>추천</b>&nbsp;&nbsp;&nbsp; 
					<a href="#pfRecommend" data-toggle="tab">포트폴리오</a> &nbsp;&nbsp; 
					<a href="#memRecommend" data-toggle="tab">개발자</a> &nbsp;&nbsp; 
					<a href="#projRecommend" data-toggle="tab">모집</a> &nbsp;&nbsp;
				</div>		
				<div class="tabs-below tabbable col-md-12">
					<div class="tab-content">
					
						<!-- 포트폴리오 패널 -->
						<div class="tab-pane active" id="pfRecommend">
							<div class="row mt">
					
<%
	List list = portfolio.portfolio_info();
	//request.setAttribute("list", list);
	for (int i = 0; i < 4; i++) {
		Portfolio port = (Portfolio) list.get(i);
%>			
								<!-- 첫번째 포트폴리오 -->
								<div class="col-md-3 mb">
									<div class="portfolio-simple">
										<div class="pfImg"></div>	
										<div class="pfInfo">
											<div class="simple-content">
												<div class="pfTag">
													<a href=""># <%=port.getTag_name()%>&nbsp;</a></div>
						
											<div class="pfTitle">
													<a href=""><%=port.getPf_title()%></a>
												</div>
												<div class="pfBottom">
													<span class="pfmemName"><a href=""><%=port.getProf_name()%></a></span> 
													<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=port.getPf_like()%></span>
												</div>
											</div>
										</div>
									</div>
								</div>	
<%
	}
%>							
							</div>
						</div>

		
					<!-- 개발자 패널 -->
						<div class="tab-pane" id="memRecommend">
							<div class="row mt">
<%
	List list2 = developer.developer_info();
	for (int i = 0; i < 4; i++) {
		Developer dev = (Developer) list2.get(i);
%>				
								<!-- 첫 번째 member-->
								<div class="col-md-3 mb">
									<div class="member-simple">
										<div class="simple-content text-center">
											<img class="memImg img-circle" alt="avatar"
												src="<%=dev.getProf_img()%>" />
											<div>
												<div class="memName">
													<a href=""><%=dev.getProf_name()%></a>
												</div>
												<div class="memTag">
													<a href="">#<%=dev.getTag_name()%>&nbsp;</a>
												</div>
	
												<div class="memFollow">
													<span class="fa fa-user"></span>&nbsp;&nbsp; <span
														class="memFollowCount"><%=dev.getProf_follower()%></span>
												</div>
											</div>
										</div>
									</div>
								</div>
<%
	}
%>							
		
							</div>
						</div>
		
						<!-- 모집 패널 -->
						<div class="tab-pane" id="projRecommend">
							<div class="row mt">
								<div class="col-lg-12">
									<div class="row">
<%
	List list3 = project.project_info();
	for (int i = 0; i < list3.size(); i++) {
		Project proj = (Project) list3.get(i);
	
%>			
										<!-- 첫번째 모집 -->
										<div class="col-md-3 mb">
											<div class="project-simple">
												<div class="simple-content text-center">
													<div class="pjTag">
														<a href=""> #<%=proj.getTag_name() %>&nbsp;</a>
													</div>
													<div class="pjTitle">
														<a href=""> </a>
													</div>
													<div class="pjInfo">
														<span class="pjField"><a href="">모집 분야(태그)</a></span>&nbsp;/&nbsp;
														<span class="pjTo"><%=proj.getProj_to() %></span>
													</div>
													<div class="pjRegiEndDate">
														<span>마감일까지</span>&nbsp;&nbsp; 
														<span class="pjDday">D&nbsp;-&nbsp;<span>// 시간 함수로 (마감일 - 현재일)</span></span>
													</div>
												</div>
											</div>
										</div>
<%} %>		
							
		
									</div>									
								</div>
							</div>
						</div>
					</div>
				</div>	
					
					<!--/END PORTPOLIO ROW OF PANELS -->
				<hr class="mainLine col-md-12" />

			<!-- Timeline page start -->
				<div class="timeline col-md-12">
					<h3 align="center">
						<b> Timeline </b>
					</h3>
					
<%
	List list4 = timeline.timeline_info();
	for (int i = 0; i < list4.size(); i++) {
		Portfolio time = (Portfolio) list4.get(i);
	
%>	
					<!-- 첫번째 타임라인 -->
					<div class="col-md-12 mt">
						<div class="portfolio-timeline">
							<div class="pfTlType">
								<span class="glyphicon glyphicon-heart"></span> <span
									class="pfTypeText">[멤버이름]님이 내 포트폴리오를 좋아합니다.</span>
							</div>
							<span class="pfInfo">
								<div>
									<span class="pfTitle"><a href=""><%=time.getPf_title() %></a></span> <span
										class="pfLike"> <span class="glyphicon glyphicon-heart"></span>
										<span class="pfLikeCount">234</span>
									</span>
								</div>
								<div class="pfmemName">
									<span class="fa fa-user"><%=time.getProf_name() %></span>&nbsp;&nbsp; <span><a
										href=""></a></span>
								</div>
								<div class="pfTag">
									<a href="">#<%=time.getTag_name() %>&nbsp;</a>
								</div>
							</span> <span class="pfImage"> <span><img
									src="<%=time.getMl_path() %>" /></span> <span><img
									src="assets/img/instagram.jpg" /></span> <span><img
									src="assets/img/instagram.jpg" /></span>
							</span>
						</div>
					</div>
<%} %>
					
				</div>
			</div>
		</section>
		<!-- Timeline page end -->
	</section>

	<! --/wrapper -->
	<!--main content end-->
	<!--footer start-->
	<footer class="site-footer">
		<div class="text-center">
			2014 - Alvarez.is <a href="blank.html#" class="go-top"> <i
				class="fa fa-angle-up"></i>
			</a>
		</div>
	</footer>
	<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/jquery-3.2.1.min.js"></script>

	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>
</body>
</html>
