<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="portit.model.dao.*"%>
<%@page import="portit.model.dto.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<link href="assets/css/profpfproj.css" rel="stylesheet">
<script>
<%
	List<Portfolio> myport = PortfolioDao.getInstance()
		.selectListByMemId(Integer.parseInt(request.getSession().getAttribute("loginId").toString()));
	if(myport.isEmpty()){
		myport= new ArrayList();
}
%>	
</script>
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
							<!-- <b>정렬</b>&nbsp; &nbsp;&nbsp;&nbsp;
							<a href="">등록순</a>&nbsp;&nbsp;&nbsp;
							<a href="">인기순</a>-->
						</span>
						<span class="pull-right">
							<button type="button" class="btn common" onclick="location.href='/page?page=myPfRegister'">포트폴리오 등록하기</button>
						</span>
					</div>
					<div class="pflist_box clearfix">
						<% 
          					for(int i=0; i<myport.size(); i++){
          						Portfolio portfo = myport.get(i);
         					%>	
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg" >
		            					<img src="<%=portfo.getMl_path2() %>"/>   
				         			</div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<% for(int j=0; j<portfo.getTags().size(); j++) { %>
													<a href="">#<%= portfo.getTags().get(j)%></a>&nbsp;
													<%} %>
												</div>
											<div class="pfTitle">
												<a href=""><%=portfo.getPf_title() %></a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href=""><%=portfo.getProf_nick() %></a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=portfo.getPf_like() %></span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover" >
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover" id ="hover" onclick="location.href='/pfList?cmd=MYPORTFOLIODELETE&pf_id=<%=portfo.getPf_id() %>'">
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
				<!-- /row -->


			</section>
			<!-- /wrapper -->
		</section>