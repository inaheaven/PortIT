<%@page import="portit.model.dto.Follow"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="assets/css/following.css" rel="stylesheet">
<script src="assets/js/search.js"></script>

<jsp:useBean id="dao" class="portit.model.dao.FollowDao" />
<%
	int loginId = (int)session.getAttribute("loginId");
%>
<c:set var="fwList" value="<%= dao.myFollowing(loginId) %>" />

<%--sidenavbar start--%>
<jsp:include page="my.jsp"></jsp:include>
<%--sidenavbar end--%>

<section id="main-content">
	<section class="wrapper site-min-height">
		<div class="col-md-12 col-sm-12 col-xs-12 mt fwlist">
			<h3 class="formTitle text-center mb">팔로잉</h3>
			<div class="fwlist_box clearfix">
				
				<c:if test="${fwList.size() != 0 && fwList.size() > 0}">
					<c:forEach begin="0" end="${fwList.size()-1}" var="i">
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="member-simple">
									<div class="simple-content text-center">
										<img class="memImg img-circle" alt="avatar"
											src="${fwList[i].prof_img}" height="140" width="70"/>
										<div>
											<div class="memName">			
												<a href="/detail?for=mem&id=${fwList[i].mem_id_receiver}">${fwList[i].prof_nick}</a>
											</div>
											<div class="memTag">
												<c:if test="${fwList[i].tags.size() != 0}" >
													<c:forEach begin="0" end="${fwList[i].tags.size()-1}" var="j">
														#<a href="">${fwList[i].tags[j]}</a>&nbsp;
													</c:forEach>
												</c:if>
											</div>
											<div class="memFollow">
												<span class="fa fa-user"></span>&nbsp;&nbsp; <span
													class="memFollowCount">${fwList[i].prof_follower}</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover">
											<button type="button" id ="hover" class="btn btn-hover" class="btn btn-hover" onclick="location.href='/follow?act=delete&fw_id=${fwList[i].fw_id}'">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				
				<c:if test="${fwList == null}">
					<h5 align=center>팔로잉하는 멤버가 없습니다. 관심있는 멤버들을 팔로잉하세요!</h5>
				</c:if>

			</div>
		</div>
	</section>
	<!--/wrapper -->
</section>
<!-- /MAIN CONTENT -->

