<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/profpfproj.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp">
		<jsp:param name="active" value="myPfList" />
	</jsp:include>
	<%--sidenavbar end--%>
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt pflist">
					<h3 class="formTitle text-center">내 포트폴리오</h3>	
					<!-- BASIC FORM ELELEMNTS -->
					<div class="pflist_top col-md-12 center clearfix">						
						<span class="pull-left sorting">
							<b>정렬</b>&nbsp; &nbsp;&nbsp;&nbsp;
							<a href="">등록순</a>&nbsp;&nbsp;&nbsp;
							<a href="">인기순</a>
						</span>
						<span class="pull-right">
							<button type="button" class="btn common" onclick="location.href='/page?page=myPfRegister'">포트폴리오 등록하기</button>
						</span>
					</div>
					<%
					List<Portfolio> myPf = profile.getProf_myPf();
					if (myPf != null && myPf.size() > 0) {
						for (int idx = 0; idx < myPf.size(); idx++) {
							Portfolio pf = myPf.get(idx);
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
											for (Tag tag : pf.getPf_tags_language()) {
										%>
										#<a href=""><%=tag.getTag_name()%>&nbsp;</a>
										<%
											}
										%>
									</div>
									<div class="pfTitle">
										<a href=""><%=pf.getPf_title()%></a>
									</div>
									<div class="pfBottom">
										<span class="pfmemName"><a href=""><%=pf.getPf_authorName()%></a></span>
										<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=pf.getPf_like()%></span>
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
					} else {
				%>
				<div class="row recoList">
					<p class="text-center">아직 등록한 포트폴리오가 없습니다.</p>
				</div>
				<%
					}
				%>
				</div>
				<!-- /row -->


			</section>
			<!-- /wrapper -->
		</section>
