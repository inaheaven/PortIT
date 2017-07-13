<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="portit.model.dto.*"%>
<!DOCTYPE html>
<html lang="ko">
<%
request.setCharacterEncoding("UTF-8");
Portfolio portfolio = (Portfolio) request.getAttribute("portfolio");
%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>${portfolio.pf_title}</title>

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
				<h1>${portfolio.pf_title}</h1>
				<h4 class="tags">
					<c:if test="${!empty portfolio.pf_tags_language}">
						<c:forEach var="pf_tag_language" items="${portfolio.pf_tags_language}">
							<span> <a href="">${pf_tag_language.tag_name}</a> </span>
						</c:forEach>
					</c:if>
				</h4>
				
				<h4>
					<i class="fa fa-heart"></i>
				</h4>
				<div class="actions">
					<button type="button" class="btn common" id="likeToggle" value="like">
						<i class="fa fa-heart-o"></i>
					</button>	
					<button type="button" class="btn common" id="bookmarkToggle" value="bookmark">
						<i class="fa fa-bookmark-o"></i>
					</button>
					<!-- <button type="button" class="btn common" onclick="location.href='#'">
						<i class="fa fa-share-alt"></i>
					</button> -->
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
						<li><a href="#" id="BackToTop"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
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
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-8 pfMem">
						<div class="col-xs-2">
							<img src="${portfolio.pf_prof_img}"
								alt="${portfolio.pf_prof_name}"
								class="img-circle uploaderImage" />
						</div>
						<div class="col-xs-6">
							<h3 class="uploaderName">
								<a href="">${portfolio.pf_prof_name}</a>
							</h3>
							<div class="tags">
							
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
				<div class="row">
					<div class="col-md-offset-2 col-md-4">
						<div class="intro">
							${portfolio.pf_intro}
						</div>
					</div>
					<div class="col-md-4">
						<div class="intro">
							<table class="table">
								<tr>
									<th>기간</th>
									<td></td>
								</tr>
								<tr>
									<th>사용언어</th>
									<td>
										<c:if test="${!empty portfolio.pf_tags_language}">
											<c:forEach var="pf_tag_language" items="${portfolio.pf_tags_language}">
												<span> <a href="">${pf_tag_language.tag_name}</a> </span>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>사용도구</th>
									<td>
										<c:if test="${!empty portfolio.pf_tags_tool}">
											<c:forEach var="pf_tag_tool" items="${portfolio.pf_tags_tool}">
												<span> <a href="">${pf_tag_tool.tag_name}</a> </span>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>인원</th>
									<td>${portfolio.pf_numofperson}명</td>
								</tr>
								<tr>
									<th>담당분야</th>
									<td>
										<c:if test="${!empty portfolio.pf_tags_field}">
											<c:forEach var="pf_tag_field" items="${portfolio.pf_tags_field}">
												<span> <a href="">${pf_tag_field.tag_name}</a> </span>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>저장소</th>
									<td><a href="${portfolio.pf_url}">${portfolio.pf_url}</a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<c:if test="${!empty portfolio.pf_mediaList}">
						<div class="col-md-offset-2 col-md-8 pfMedia">
							<div id="Screenshots" class="carousel slide" data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<c:forEach var="pf_media" items="${portfolio.pf_mediaList}" varStatus="status">
										<li data-target="#Screenshots" data-slide-to="${status.index}">
									</c:forEach>	
								</ol>
								<!-- Wrapper for slides -->
								<div class="carousel-inner">
									<c:forEach var="pf_media" items="${portfolio.pf_mediaList}" varStatus="status">
										<div class="item">
											<img src="${pf_media.ml_path}" />
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
		</section>
		<!-- /About -->
		<hr />
		<!-- Collaborators -->
		<section id="pfCollaborators">
			<div class="container">
				<div class="row">
					<h2 class="text-center">Collaborators</h2>
				</div>
				<div class="row collaboList">
					<c:choose>
						<c:when test="${!empty portfolio.pf_coworkers}">						
							<c:forEach var="pf_coworker" items="${portfolio.pf_coworkers}">
								<div class="col-md-3 mb">
									<div class="member-simple">
										<div class="simple-content text-center">
											<a href="/view?type=profile&id=${pf_coworker.prof_nick}"><img class="memImg img-circle" alt="avatar" src="${pf_coworker.prof_img}" /></a>
											<div>
												<div class="memName">
													<a href="/view?type=profile&id=${pf_coworker.prof_nick}">${pf_coworker.prof_name}</a>
												</div>
												<div class="memTag">
													<c:if test="${!empty pf_coworker.prof_tags_language}">
														<c:forEach var="prof_tag_language" items="${pf_coworker.prof_tags_language}">
															#<a href="">${prof_tag_language}</a>
														</c:forEach>
													</c:if>
												</div>
												<div class="memFollow">
													<span class="fa fa-user"></span>&nbsp;&nbsp; <span
														class="memFollowCount">${pf_coworker.prof_follower}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- member-simple end -->
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="text-center">이 작업을 같이 수행한 사람이 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>				
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
				<div class="row recoList">
					<c:choose>
						<c:when test="${!empty portfolio.pf_recommends}">
							<c:forEach var="pf_recommend" items="${portfolio.pf_recommends}">
								<div class="col-md-3 mb">
									<div class="portfolio-simple">
										<div class="pfImg">
											<a href="/view?type=portfolio&id=${pf_recommend.pf_id}"><img src="${pf_recommend.mediaList[0].ml_path}" /></a>
										</div>
										<div class="pfInfo">
											<div class="simple-content">
												<div class="pfTag">
													<c:if test="${!empty pf_recommend.pf_tags_language}">
														<c:forEach var="pf_tag_language" items="${pf_recommend.pf_tags_language}">
															#<a href="">${pf_tag_language}</a>
														</c:forEach>
													</c:if>
													#<a href="">&nbsp;</a>
												</div>
												<div class="pfTitle">
													<a href="/view?type=portfolio&id=${pf_recommend.pf_id}">${pf_recommend.pf_title}</a>
												</div>
												<div class="pfBottom">
													<span class="pfmemName"><a href="/view?type=profile&id=${pf_coworker.pf_prof_nick}">${pf_recommend.pf_prof_name}</a></span>
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
							<p class="text-center">이 작업과 비슷한 작업이 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
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
		$(document).ready(function() {
			// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
			$("#portfolioNavbar").sticky({
				topSpacing : 0,
				zIndex : "50"
			});

			// 스크롤 맨 위로
			$("#BackToTop").on("click", function(e) {
				e.preventDefault();
				$("html,body").animate({
					scrollTop : 0
				}, 500);
			});
			
			// 좋아요 토글
			$("#likeToggle").click(function() {
				var value = $("#likeToggle").attr("value");
				var mem_id = ${sessionScope.loginId};
				var pf_id = ${portfolio.pf_id}
				
				var param;
				if (value == "like") {
					param = {
							"cmd" : value,
							"mem_id" : mem_id,
							"pf_id" : pf_id
							};
				} else if (value == "dislike") {
					param = {
							"cmd" : value,
							"mem_id" : mem_id,
							"pf_id" : pf_id
							};
				}
				
				var request = $.ajax({
					type : "POST",
					url : "/like",
					data : param,
					dataType : "text"
				});
				
				request.done(function(likes) {
					if (value == "like") {
						$("#likeToggle").empty();						
						$("#likeToggle").append("<i class=\"fa fa-heart\"></i>");
						$("#likeToggle").attr("value", "dislike");
					} else {
						$("#likeToggle").empty();
						$("#likeToggle").append("<i class=\"fa fa-heart-o\"></i>");
						$("#likeToggle").attr("value", "like");
					}
				});
			});
			
			// 북마크 토글
			$("#bookmarkToggle").click(function() {
				var value = $("#bookmarkToggle").attr("value");
				var mem_id = ${sessionScope.loginId};
				var pf_id = ${portfolio.pf_id}
				
				var param;
				if (value == "bookmark") {
					param = {
							"cmd" : value,
							"mem_id" : mem_id,
							"pf_id" : pf_id
							};
				} else if (value == "unbookmark") {
					param = {
							"cmd" : value,
							"mem_id" : mem_id,
							"pf_id" : pf_id
							};
				}
				
				var request = $.ajax({
					type : "POST",
					url : "/bmk",
					data : param,
					dataType : "text"
				});
				
				request.done(function(bookmark) {
					if (value == "bookmark") {
						$("#bookmarkToggle").empty();						
						$("#bookmarkToggle").append("<i class=\"fa fa-bookmark\"></i>");
						$("#bookmarkToggle").attr("value", "unbookmark");
					} else {
						$("#bookmarkToggle").empty();
						$("#bookmarkToggle").append("<i class=\"fa fa-bookmark-o\"></i>");
						$("#bookmarkToggle").attr("value", "bookmark");
					}
				});
			});
		});		
	</script>

</body>
</html>
