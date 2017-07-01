<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="portit.model.dao.*"%>
<%@ page import="portit.model.dto.*"%>
<!DOCTYPE html>
<html lang="ko">
<%
request.setCharacterEncoding("UTF-8");
Project project = (Project) request.getAttribute("project");
%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><%= project.getProj_title() %></title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/detailpage.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

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
                    <li><a href="">Project</a><li>
                    <li><a href="">Community</a></li>
                </ul>
                <!--  menu end -->
            </div>
            <div class="top-menu-right">
            	<ul class="nav pull-right top-menu">
            		<li>
            			<form class="form-inline top-menu-search" method="post" action="">
            				<div class="input-group">
            					<input type="text" class="form-control round-form" name="search" size="20" placeholder="통합 검색"/>
            					<span class="input-group-btn">
            						<button type="submit" class="btn btn-default round-form"><span class="glyphicon glyphicon-search" ></span></button>
            					</span>
            				</div>
            			</form>
            		</li>
            		<li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <i class="fa fa-bell"></i>
                            <span class="badge bg-theme"></span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-yellow"></div>
                            <li>
                                <p class="yellow">Notification</p>
                            </li>
                            <li>
                                <a href="">                
                                	<span>구분</span>&nbsp;/&nbsp;
                                	<span class="time">Just now</span>
                                	<span class="message">[누구누구]님이 내 포트포리오를 좋아합니다.</span>      
                                </a>
                            </li>
                            <li>
                                <a href="">더보기</a>
                            </li>
                        </ul>
                    </li>
                    <li id="header_inbox_bar" class="dropdown mypage">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <img alt="avatar" src="assets/img/ui-danro.jpg" class="img-circle">
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-yellow"></div>
                            <li>
                                <p class="yellow">김수연 님</p>
                            </li>
                            <li>
                                <a href="">내 프로필</a>
                            </li>
                            <li>
                                <a href="">팔로잉한 멤버</a>
                                <a href="">북마크한 포트폴리오</a>                             
                            </li>
                            <li>
                            	<a href="">개인정보 설정</a>
                            </li>
                            <li>
                            	<a class="logout" href="login.html">로그아웃</a>
                            </li>
                        </ul>
                    </li>
            	</ul>
            </div>
        </header>
      <!--header end-->

		
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!-- Profile header -->
		<section id="projHeader">
			<div class="container">
				<h4 class="tags">
					<% for (Tag tag : project.getProj_tags_language()) { %>
					<span><a href=""><%=tag.getTag_name()%></a>&nbsp;</span>
					<% } %>
				</h4>
				<h1><%= project.getProj_title() %></h1>
				<h3 class="username"><a href=""><%= project.getProj_authorName() %></a></h3>
				<h4 class="field">
					모집 분야 / <%= project.getProj_numofperson() %>명
				</h4>
				<h3 class="dday">D-<%= %></h3>
			</div>
		</section><!-- /Profile header -->
		
		<!-- Profile nav -->
		<nav class="navbar navbar-default" id="projectNavbar">
			<div class="container">
				<div class="text-center">
					<ul class="nav navbar-nav">
						<li><a href="#projAbout">About</a></li>
						<li><a href="#projInfo">Info</a></li>
						<li><a href="#projCollaborators">Collaborators</a></li>
						<li><a href="#" id="BackToTop"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
					</ul>
				</div>
			</div>
		</nav>
		<!-- /Profile nav -->
		
		<!-- About -->
		<section id="projAbout">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-center">About</h2>
					</div>
					<div class="col-md-offset-2 col-md-8">
						<div class="intro">
							<%= project.getProj_intro() %>
						</div>
					</div>
					<div class="col-md-offset-2 col-md-8 projMedia">
						<div id="Screenshots" class="carousel slide" data-ride="carousel">
							<%
							List<Media> mediae = project.getProj_mediae();
							for (int idx = 0; idx < mediae.size(); idx++) {
								Media media = mediae.get(idx);

								if (idx == 0) {
							%>
							<!-- Indicators -->
							<ol class="carousel-indicators">
								<%
									}
								%>
								<li data-target="#Screenshots" data-slide-to="<%=idx%>"
									<%if (idx == 0) {%> class="active" <%}%>></li>
								<%
									if (idx == 0) {
								%>
							</ol>
							<%
								}
									if (idx == 0) {
							%>
							<!-- Wrapper for slides -->
							<div class="carousel-inner">
								<%
									}
								%>
								<div class="item<%if (idx == 0) {%> active<%}%>">
									<img src="<%=media.getMl_path()%>" />
								</div>
								<%
									if (idx == 0) {
								%>
							</div>
							<%
								}
							%>
							<%
								}
							%>
		
							<!-- Controls -->
							<a class="left carousel-control" href="#Screenshots"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control" href="#Screenshots"
								data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</section><!-- /About -->
		<hr />
		<!-- Info -->
		<section id="projInfo">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Info</h2>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<table class="table">
							<tr>
								<th>개설자</th>
								<td><%= project.getProj_authorName() %></td>
							</tr>
							<tr>
								<th>등록일</th>
								<td>
									<%= project.getProj_regdate() %><br />
									마감일까지 D-<%= %>
								</td>
							</tr>
							<tr>
								<th>모집분야</th>
								<td>
									<% for (Tag tag : project.getProj_tags_field()) { %>
									<span><a href=""><%= tag.getTag_name() %></a>&nbsp;</span>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>모집인원</th>
								<td><%= project.getProj_numofperson() %>명</td>
							</tr>
							<tr>
								<th>요구기술</th>
								<td>
									<% for (Tag tag : project.getProj_tags_language()) { %>
									<span><a href=""><%= tag.getTag_name() %></a>&nbsp;</span>
									<% } %>
									<% for (Tag tag : project.getProj_tags_tool()) { %>
									<span><a href=""><%= tag.getTag_name() %></a>&nbsp;</span>
									<% } %>
								</td>
							</tr>							
							<tr>
								<th>예상작업기간</th>
								<td><%= project.getProj_period() %>일</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="actions">
						<button type="button" class="btn common" onclick="location.href=''">개설자에게 연락하기</button>&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn common" onclick="location.href=''">프로젝트 지원하기</button>
					</div>
				</div>
			</div>
		</section><!-- /Info -->
		<hr />
		<!-- Collaborators -->
		<section id="projCollaborators">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Collaborators</h2>
				</div>
				<%
					List<Profile> coworkers = project.getProj_coworkers();
					if (coworkers != null && coworkers.size() > 0) {
						for (int idx = 0; idx < coworkers.size(); idx++) {
							Profile profile = coworkers.get(idx);
							if (idx == 0 || idx % 4 == 0) {
				%>
				<div class="row collaboList">
					<%
							}
					%>
					<div class="col-md-3 mb">
						<div class="member-simple">
							<div class="simple-content text-center">
								<img class="memImg img-circle" alt="avatar"
									src="<%=profile.getProf_img()%>" />
								<div>
									<div class="memName">
										<a href=""><%=profile.getProf_name()%></a>
									</div>
									<div class="memTag">
										<%
											for (Tag tag : profile.getProf_tags_language()) {
										%>
										#<a href=""><%=tag.getTag_name()%>&nbsp;</a>
										<%
											}
										%>
									</div>
									<div class="memFollow">
										<span class="fa fa-user"></span>&nbsp;&nbsp; <span
											class="memFollowCount"><%=profile.getProf_follower()%></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- member-simple end -->
					<%
							if (idx == 0 || idx % 4 == 0) {
					%>
				</div>
				<%
							}
						}
					} else {
				%>
				<div class="row collaboList">
					<p class="text-center">아직 이 프로젝트에 지원한 사람이 없습니다.</p>
				</div>
				<%
					}
				%>
			</div>
		</section><!-- /Collaborators -->		
		
		<!--footer start-->
		<footer class="footer site-footer">
		    <div class="col-md-3 footer-logo">
		        (c) Port IT 2017 / All right reserved.
		    </div>
		    <div class="col-md-3 footer-menu">
		    	<a href="">사이트 소개</a>
		    	<a href="">도움말</a>
		    	<a href="">문의하기</a>
		    	<a href="main.html#" class="go-top">
		    		<i class="fa fa-angle-up"></i>
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


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!-- JS Libraries -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.sticky/1.0.4/jquery.sticky.min.js"></script>
	<!-- Custom JS -->
	<script type="text/javascript">
		// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
		$("#projectNavbar").sticky({topSpacing:0, zIndex: '50'});
		
		// 스크롤 맨 위로
		$("#BackToTop").on("click", function(e){
			e.preventDefault();
			$("html,body").animate({ scrollTop : 0 }, 500);
		});
	</script>

</body>
</html>