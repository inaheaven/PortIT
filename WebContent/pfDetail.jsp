<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="portit.model.dto.*"%>
<!DOCTYPE html>
<html lang="ko">
<jsp:useBean id="portfolio" class="portit.model.dto.Portfolio" />
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><%=portfolio.getPf_title()%></title>

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
		<section id="pfHeader">
			<div class="container">
				<h1><%=portfolio.getPf_title()%></h1>
				<h4 class="tags">
					<%
						for (Tag tag : portfolio.getPf_tags_language()) {
					%>
					<span>#<a href=""><%=tag.getTag_name()%></a>&nbsp;
					</span>
					<%
						}
					%>
				</h4>
				<h4>
					<i class="fa fa-heart"></i>
					<%=portfolio.getPf_like()%>
				</h4>
				<div class="actions">
					<button type="button" class="btn common"
						onclick="location.href='#'">
						<i class="fa fa-heart"></i>
					</button>
					<button type="button" class="btn common"
						onclick="location.href='#'">
						<i class="fa fa-bookmark"></i>
					</button>
					<button type="button" class="btn common"
						onclick="location.href='#'">
						<i class="fa fa-share-alt"></i>
					</button>
				</div>
			</div>
		</section>
		<!-- /Profile header -->

		<!-- Portfolio nav -->
		<nav class="navbar navbar-default" id="portfolioNavbar">
			<div class="container">
				<div class="text-center">
					<ul class="nav navbar-nav">
						<li><a href="#pfAbout">About</a></li>
						<li><a href="#Collaborators">Collaborators</a></li>
						<li><a href="#Recommendations">Recommendations</a></li>
						<li><a href="#" id="BackToTop"><i
								class="fa fa-angle-double-up" aria-hidden="true"></i></a>
					</ul>
				</div>
			</div>
		</nav>
		<!-- /Portfolio nav -->

		<!-- About -->
		<section id="pfAbout">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-center">About</h2>
					</div>
					<div class="col-md-offset-2 col-md-8 pfMem">
						<div class="col-xs-2">
							<a href=""><img src="<%=portfolio.getPf_authorIcon()%>"
								alt="<%=portfolio.getPf_authorName()%>"
								class="img-circle uploaderImage" /></a>
						</div>
						<div class="col-xs-6">
							<h3 class="uploaderName">
								<a href=""><%=portfolio.getPf_authorName()%></a>
							</h3>
							<div class="tags">
								<%
									for (Tag tag : portfolio.getPf_tags_language()) {
								%>
								<span>#<a href=""><%=tag.getTag_name()%></a>&nbsp;
								</span>
								<%
									}
								%>
								<span>#<a href="">태그</a>&nbsp;
								</span> <span>#<a href="">태그</a>&nbsp;
								</span> <span>#<a href="">태그</a>&nbsp;
								</span> <span>#<a href="">태그</a>&nbsp;
								</span>
							</div>
						</div>
						<div class="col-xs-4 text-right">
							<div class="actions">
								<button type="button" class="btn common"
									onclick="location.href='#'">
									<i class="fa fa-envelope"></i>
								</button>
								<button type="button" class="btn common"
									onclick="location.href='#'">
									<i class="fa fa-comment"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-offset-2 col-md-4">
					<div class="intro">
						<%=portfolio.getPf_intro()%>
					</div>
				</div>
				<div class="col-md-4">
					<div class="intro">
						<table class="table">
							<tr>
								<th>기간</th>
								<td><%=portfolio.getPf_startdate()%> ~ <%=portfolio.getPf_enddate()%></td>
							</tr>
							<tr>
								<th>개발환경</th>
								<td>
									<% for (Tag tag : portfolio.getPf_tags_env()) { %>
									<span><a href=""><%= tag.getTag_name() %></a>&nbsp;</span>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>사용언어</th>
								<td>
									<% for (Tag tag : portfolio.getPf_tags_language()) { %>
									<span><a href=""><%= tag.getTag_name() %></a>&nbsp;</span>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>사용도구</th>
								<td>
									<% for (Tag tag : portfolio.getPf_tags_tool()) { %>
									<span><a href=""><%= tag.getTag_name() %></a>&nbsp;</span>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>인원</th>
								<td><%=portfolio.getPf_numofperson()%>명</td>
							</tr>
							<tr>
								<th>담당분야</th>
								<td>
									<% for (Tag tag : portfolio.getPf_tags_field()) { %>
									<%= tag.getTag_name() %>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>저장소</th>
								<td><a href="<%=portfolio.getPf_url()%>"><%=portfolio.getPf_url()%></a></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-offset-2 col-md-8 pfMedia">
					<div id="Screenshots" class="carousel slide" data-ride="carousel">
						<%
							List<Media> mediae = portfolio.getPf_mediae();
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
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#Screenshots"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</section>
		<!-- /About -->
		<hr />
		<!-- Collaborators -->
		<section id="pfCollaborators">
			<div class="container">
				<div class="row">
					<h2 class="text-center">Collaborators</h2>
				</div>
				<%
					List<Profile> coworkers = portfolio.getPf_coworkers();
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
				%>
			</div>
		</section>
		<!-- /Collaborators -->
		<hr />
		<!-- Recommendations -->
		<section id="Recommendations">
			<div class="container">
				<div class="row">
					<h2 class="text-center">Recommendations</h2>
				</div>
				<%
					List<Portfolio> recommends = portfolio.getPf_recommends();
					for (int idx = 0; idx < recommends.size(); idx++) {
						Portfolio r = recommends.get(idx);
						if (idx == 0 || idx % 4 == 0) {
				%>
				<div class="row recoList">
					<%
						}
					%>
					<div class="col-md-3 mb">
						<div class="portfolio-simple">
							<div class="pfImg"></div>
							<div class="pfInfo">
								<div class="simple-content">
									<div class="pfTag">
										<%
											for (Tag tag : r.getPf_tags_language()) {
										%>
										#<a href=""><%=tag.getTag_name()%>&nbsp;</a>
										<%
											}
										%>
									</div>
									<div class="pfTitle">
										<a href=""><%=r.getPf_title()%></a>
									</div>
									<div class="pfBottom">
										<span class="pfmemName"><a href=""><%=r.getPf_authorName()%></a></span>
										<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=r.getPf_like()%></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- portfolio-simple end -->
					<%
						if (idx == 0 || idx % 4 == 0) {
					%>
				</div>
				<%
					}
					}
				%>
			</div>
		</section>
		<!-- /Recommendations -->

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
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.sticky/1.0.4/jquery.sticky.min.js"></script>
	<!-- Custom JS -->
	<script type="text/javascript">
		// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
		$("#portfolioNavbar").sticky({
			topSpacing : 0,
			zIndex : '50'
		});

		// 스크롤 맨 위로
		$("#BackToTop").on("click", function(e) {
			e.preventDefault();
			$("html,body").animate({
				scrollTop : 0
			}, 500);
		});
	</script>

</body>
</html>
