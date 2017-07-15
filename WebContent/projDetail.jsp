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

<title>${project.proj_title}</title>

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
		<jsp:include page="header.jsp"></jsp:include>
		<!--header end-->

		
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!-- Profile header -->
		<section id="projHeader">
			<div class="container">
				<h4 class="tags">
					
				</h4>
				<h1>${project.proj_title}</h1>
				<h3 class="username"><a href="/view?type=portfolio&id=${project.prof_name}">${project.prof_name}</a></h3>
				<h4 class="field">
					모집 분야 / ${project.proj_to}명
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
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<div class="intro">${project.proj_intro}</div>
					</div>
				</div>
				<div class="row">					
					<c:if test="${!empty project.media_path}">
						<div class="col-md-offset-2 col-md-8 projMedia">
							<div id="Screenshots" class="carousel slide" data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<c:forEach var="pf_media" items="${project.media_path}" varStatus="status">
										<li data-target="#Screenshots" data-slide-to="${status.index}">
									</c:forEach>	
								</ol>
								<!-- Wrapper for slides -->
								<div class="carousel-inner">
									<c:forEach var="path" items="${project.media_path}" varStatus="status">
										<div class="item">
											<img src="${path}" />
										</div>
									</c:forEach>				
								</div>
								<!-- Controls -->
								<a class="left carousel-control" href="#Screenshots" data-slide="prev">
									<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									<span class="sr-only">Previous</span>
								</a>
								<a class="right carousel-control" href="#Screenshots" data-slide="next">
									<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
						</div>
					</c:if>
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
								<td>${project.prof_name}</td>
							</tr>
							<tr>
								<th>등록일</th>
								<td>
									<fmt:formatDate value="${project.proj_regdate}" pattern="yyyy-MM-dd HH:mm:ss" var="proj_regdate" />
									<fmt:formatDate value="${project.proj_regenddate}" pattern="yyyy-MM-dd HH:mm:ss" var="proj_regenddate" />
									<c:out value="${proj_regdate}" /><br />
									<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="now" />
									마감일까지 D-<c:out value="${proj_regenddate - now}" />
								</td>
							</tr>
							<tr>
								<th>모집분야</th>
								<td>
									<% for (int i = 0; i < project.getProj_field().size(); i++) { %>
									<p><span><a href=""><%= project.getProj_field().get(i) %></a>&nbsp;</span>
									/ <%= project.getProj_numofperson().get(i) %>명</p>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>요구기술</th>
								<td>
									<c:forEach var="lang" items="${project.proj_language}" varStatus="status">
									<span><a href="">${lang}</a>&nbsp;</span>
									</c:forEach>
									<c:forEach var="tool" items="${project.proj_tool}" varStatus="status">
									<span><a href="">${tool}</a>&nbsp;</span>
									</c:forEach>
								</td>
							</tr>							
							<tr>
								<th>예상작업기간</th>
								<td>${project.proj_period}일</td>
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
				<div class="row collaboList">
					<c:choose>
						<c:when test="${!empty project.pf_coworkers}">						
							<c:forEach var="proj_applicant" items="${project.proj_applicants}">
								<div class="col-md-3 mb">
									<div class="member-simple">
										<div class="simple-content text-center">
											<a href="/view?type=profile&id=${proj_applicant.prof_nick}"><img class="memImg img-circle" alt="avatar" src="${pf_coworker.prof_img}" /></a>
											<div>
												<div class="memName">
													<a href="/view?type=profile&id=${proj_applicant.prof_nick}">${proj_applicant.prof_name}</a>
												</div>
												<div class="memTag">
													<span>#<a href="">${proj_applicant.prof_language}</a></span>
													<span>#<a href="">${proj_applicant.prof_tool}</a></span>
													<span>#<a href="">${proj_applicant.prof_field}</a></span>
												</div>
												<div class="memFollow">
													<span class="fa fa-user"></span>&nbsp;&nbsp; <span
														class="memFollowCount">${proj_applicant.prof_follower}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- member-simple end -->
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="text-center">이 작업을 같이 수행할 사용자가 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</section><!-- /Collaborators -->		
		
		<!--footer start-->
		<jsp:include page="footer.jsp"></jsp:include>
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