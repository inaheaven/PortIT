<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="portit.model.dto.*"%>
<!DOCTYPE html>
<html lang="ko">
<%
request.setCharacterEncoding("UTF-8");
Profile profile = (Profile) request.getAttribute("profile");
%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>${profile.prof_name} - PortIT</title>

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
		<jsp:include page="header.jsp" />
      	<!--header end-->

		
		<!-- Profile header -->
		<section id="memHeader">
			<div class="headerContainer">
				<img src="${profile.prof_img}" alt="${profile.prof_name}" class="img-circle" />
				<h1>${profile.prof_name}</h1>
				<h3 class="username">${profile.prof_nick}</h3>
				<h4 class="tags">
					<span>
						<a href="">${profile.prof_language}</a>
						<a href="">${profile.prof_tool}</a>
						<a href="">${profile.prof_field}</a>
					</span>
				</h4>
				<h4>
					<i class="fa fa-user"></i>
					${profile.prof_follower}
				</h4>
				<div class="actions">
					<button type="button" class="btn common" onclick="location.href='#'">+ Follow</button>					
				</div>
			</div>
		</section><!-- /Profile header -->
	
		<!-- Profile nav -->
		<nav class="navbar navbar-default" id="profileNavbar">
			<div class="container">
				<div class="text-center">
					<ul class="nav navbar-nav">
						<li><a href="#About">About</a></li>
						<li><a href="#Portfolios">Portfolios</a></li>
						<li><a href="#Projects">Projects</a></li>
						<li><a href="#Contact">Contact</a></li>
						<li><a href="#" id="BackToTop"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
					</ul>
				</div>
			</div>
		</nav>
		<!-- /Profile nav -->
		
		<!-- About -->
		<section id="memAbout">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-center">About</h2>
					</div>
					<div class="col-md-offset-2 col-md-8">
						<div class="intro">
							${profile.prof_intro}
						</div>
					</div>
					<div class="col-md-offset-2 col-md-8">
						<div class="skillset">
							<% for (int i = 0; i < profile.getTag_skill().size(); i++) { %>
							<div class="skill clearfix">
								<div class="col-xs-3 text-center">
									<span class=""><%= profile.getTag_skill().get(i) %></span>
								</div>
								<div class="col-xs-9">
									<div class="progress">
										<div class="progress-bar" style="width:<%=100 * ((Integer.parseInt(profile.getProf_skill_level().get(i).toString())) / 10)%>%;"></div>
									</div>
								</div>
							</div>
							<% } %>
						</div>
					</div>
				</div>
			</div>
		</section><!-- /About -->
		<hr />
		<!-- Portfolios -->
		<section id="Portfolios">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Portfolios</h2>
				</div>
				<div class="row recoList">
					<c:choose>
						<c:when test="${!empty profile.prof_myPf}">
							<c:forEach var="mypf" items="${profile.prof_myPf}">
								<div class="col-md-3 mb">
									<div class="portfolio-simple">
										<div class="pfImg">
											<a href="/view?type=portfolio&id=${mypf.pf_id}"><img src="${mypf.mediaList[0].ml_path}" /></a>
										</div>
										<div class="pfInfo">
											<div class="simple-content">
												<div class="pfTag">
													<c:if test="${!empty mypf.pf_tags_language}">
														<c:forEach var="pf_tag_language" items="${mypf.pf_tags_language}">
															#<a href="">${pf_tag_language}</a>
														</c:forEach>
													</c:if>
													#<a href="">&nbsp;</a>
												</div>
												<div class="pfTitle">
													<a href="/view?type=portfolio&id=${mypf.pf_id}">${mypf.pf_title}</a>
												</div>
												<div class="pfBottom">
													<span class="pfmemName"><a href="/view?type=profile&id=${mypf.pf_prof_nick}">${mypf.pf_prof_name}</a></span>
													<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- portfolio-simple end -->
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="text-center">아직 등록한 포트폴리오가 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</section><!-- /Portfolios -->
		<hr />
		<!-- Projects -->
		<section id="Projects">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Projects</h2>
				</div>
				<div class="row">
					<c:choose>
						<c:when test="${!empty profile.prof_myProj}">
							<c:forEach var="myproj" items="${profile.prof_myProj}">
								<div class="col-md-12 mb">
									<div class="project-list">
										<div class="pjInfoText">
											<div class="pjTitle">
												<a href="/view?type=project&id=${myproj.proj_id}">${myproj.proj_title}</a>
											</div>
											<div class="pjmemName">
												<span class="fa fa-user"></span>&nbsp;&nbsp;<a href="/view?type=profile?id=${myproj.prof_name}">${myproj.prof_name}</a>
											</div>
											<div class="pjIntro">${myproj.proj_intro}</div>
											<div class="pjTag">
												<c:forEach var="tagLang" items="${myproj.proj_language}">
													<span><a href="">${tagLang}</a>&nbsp;</span>
												</c:forEach>
												<c:forEach var="tagTool" items="${myproj.proj_tool}">
													<span><a href="">${tagTool}</a>&nbsp;</span>
												</c:forEach>
											</div>
										</div>
										<div class="pjInfoTable">
											<table class="table text-center">
												<% for (int i = 0; i < myproj.getProj_field().size(); i++) { %>
												<tr>
													<td><span><a href=""><%=myproj.getProj_field().get(i)%></a>&nbsp;</span>
														/ <%=myproj.getProj_numofperson().get(i)%> 명</td>
												</tr>
												<% } %>
												<tr>
													<td>마감일</td>
												</tr>
												<tr>
													<td><fmt:formatDate value="${myproj.proj_period}" pattern="yyyy-MM-dd" /></td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="text-center">아직 등록한 프로젝트가 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- project-list end -->
			</div>
		</section><!-- /Projects -->
		<hr />
		<!-- Contact -->
		<section id="Contact">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Contact</h2>
				</div>
				<div class="row">
					<div class="col-md-8 center">
						<div class="actions col-md-12 text-center">
							<a href="${profile.prof_website}" title="Website"><span class="fa fa-globe"></span></a>
							<a href="${profile.prof_github}" title="GitHub"><span class="fa fa-github"></span></a>
							<a href="${profile.prof_facebook}" title="Facebook"><span class="fa fa-facebook-square"></span></a>
							<a href="mailto:${profile.prof_email}" title="Email"><span class="fa fa-envelope"></span></a>
							<a href="#" title="Message"><span class="fa fa-comment"></span></a>
							<a data-toggle="collapse" href="#Share" title="Share"><span class="fa fa-share-alt"></span></a>
							<div class="collapse pull-right" id="Share">
								<div class="actions">
									<a href="#" title="Copy URL"><span class="fa fa-clipboard"></span></a>
									<a href="#" title="Facebook"><span class="fa fa-facebook-square"></span></a>
									<a href="#" title="Email with this URL"><span class="fa fa-envelope"></span></a>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</section><!-- /Contact -->
		
		<!--footer start-->
		<jsp:include page="footer.jsp" />
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
		$(document).ready(function(){
			// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
			$("#profileNavbar").sticky({topSpacing:0, zIndex: '50'});
			
			// 스크롤 맨 위로
			$("#BackToTop").on("click", function(e){
				e.preventDefault();
				$("html,body").animate({ scrollTop : 0 }, 500);
			});
			
		});	
		
		
	</script>

</body>
</html>
