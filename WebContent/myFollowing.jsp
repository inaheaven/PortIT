<%@page import="portit.model.dto.Follow"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="assets/css/following.css" rel="stylesheet">
<script src="assets/js/search.js"></script>
<script language="javascript">

<%
	List<Follow> followList = (List) request.getAttribute("follow");
	
%>	
</script>

<%--sidenavbar start--%>
<jsp:include page="my.jsp"></jsp:include>
<%--sidenavbar end--%>

<section id="main-content">
	<section class="wrapper site-min-height">
		<div class="col-md-12 col-sm-12 col-xs-12 mt fwlist">
			<h3 class="formTitle text-center">팔로잉</h3>
			<div class="fwlist_box clearfix">

				<% for(int i=0; i<followList.size(); i++){
  				%>
				<div class="col-md-4 mb">
					<!-- 반복 -->
					<div class="image-hover">
						<div class="member-simple">
							<div class="simple-content text-center">
								<img class="memImg img-circle" alt="avatar"
									src="assets/img/friends/fr-06.jpg" />
								<div>
									<div class="memName">
										<a href="searchAll.html"><%=followList.get(i).getProf_name() %></a>
									</div>
									<div class="memTag">
										<a href="">#태그&nbsp;</a>
									</div>
									<div class="memFollow">
										<span class="fa fa-user"></span>&nbsp;&nbsp; <span
											class="memFollowCount">135</span>
									</div>
								</div>
							</div>
							<div class="top-hover-right">
								<div class="after-hover">
									<button type="button" id ="hover"<%=i %> class="btn btn-hover" class="btn btn-hover" onclick="location.href='/follow?cmd=FOLLOWDELETE&fw_id=<%=followList.get(i).getFw_id() %>'">
										<span class="glyphicon glyphicon-remove"></span>
									</button>
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
	</section>
	<!--/wrapper -->
</section>
<!-- /MAIN CONTENT -->

