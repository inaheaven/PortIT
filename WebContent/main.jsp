<%@page import="portit.model.dto.Member"%>
<%@page import="portit.model.dto.Timeline"%>
<%@page import="portit.model.dto.Project"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<link href="assets/css/main.css" rel="stylesheet">
<%
	List portfolio = (List)request.getAttribute("port_list");
	List member = (List)request.getAttribute("mem_list");
	List project = (List)request.getAttribute("proj_list");
	List timeline = (List)request.getAttribute("time_list");
%>
	<section class="container">
		<section class="wrapper site-min-height">
			<div class="col-md-12 col-sm-12 col-xs-12 mt" id="main">
				<div class="recommend">
					<b>추천</b>&nbsp;&nbsp;&nbsp; <a href="#pfRecommend"
						data-toggle="tab">포트폴리오</a> &nbsp;&nbsp; <a href="#memRecommend"
						data-toggle="tab">개발자</a> &nbsp;&nbsp; <a href="#projRecommend"
						data-toggle="tab">모집</a> &nbsp;&nbsp;
				</div>
				<div class="tabs-below tabbable col-md-12">
					<div class="tab-content">

						<!-- 포트폴리오 패널 -->
						<div class="tab-pane active" id="pfRecommend">
							<div class="row mt">
					</h4>
	<%	
		int num = 0;
		if(portfolio.size() >= 4 && portfolio.size() != 0){
			num = 4;
		}
		else if(portfolio.size() != 0 && portfolio.size() < 4){
			num = portfolio.size();
		}
			for(int i=0; i< num; i++){
				Portfolio port = (Portfolio) portfolio.get(i);
	%>
							
									<!-- 첫번째 포트폴리오 -->
										<div class="col-md-3 mb">
											<div class="portfolio-simple">
										
											<div class="pfImg">
											<% if(port.getMl_path2() != null){
													for(int j=0; j<1; j++) { %>
					            					<img src="${pageContext.request.contextPath}<%=port.getMl_path2().get(j)%>"/> 
				            				<%}} %>    
						         			</div>
												<div class="pfInfo">
													<div class="simple-content">
														<div class="pfTag">

													<% for(int j=0; j<port.getTags().size(); j++) { %>
													<a href="javascript:tag('<%=port.getTags().get(j)%>')">
													#<%= port.getTags().get(j)%></a>&nbsp;
													<%} %>
												</div>
												<div class="pfTitle">
													<a href="#" onclick="pf(<%=port.getPf_id()%>)">
													<%=port.getPf_title()%> </a>
												</div>
												<div class="pfBottom">
													<span class="pfmemName"><a href=""><%=port.getProf_name()%></a></span>
													<span class="pfLikeCount"><span
														class="fa fa-heart"></span>&nbsp;&nbsp;<%=port.getPf_like()%></span>
												</div>
											</div>
										</div>
									</div>
								</div>
		<%} %>						
							</div>
						</div>
		

						<!-- 개발자 패널 -->
						<div class="tab-pane" id="memRecommend">
							<div class="row mt">
	<%	
		if(member.size() >= 4 && member.size() != 0){
			num = 4;
		}
		else if(member.size() != 0 && member.size() < 4){
			num = member.size();
		}
			for(int i=0; i< num; i++){
				Member mem = (Member) member.get(i);
	%>						
								
										<!-- 첫 번째 member-->
										<div class="col-md-3 mb">
											<div class="member-simple">
												<div class="simple-content text-center">
													<img class="memImg img-circle" alt="avatar"
														src="<%=mem.getProf_img()%>" />
													<div>
														<div class="memName">
																<a href="#" onclick="prof(<%=mem.getProf_id()%>)">
															<%=mem.getProf_name()%></a>
														</div>
														<div class="memTag">
															<% for(int j=0; j<mem.getTags().size(); j++) { %>
															<a href="javascript:tag('<%=mem.getTags().get(j)%>')">
															#<%= mem.getTags().get(j)%></a>&nbsp;
															<%} %>
														</div>
														<div class="memFollow">
															<span class="fa fa-user"></span>&nbsp;&nbsp; <span
																class="memFollowCount"><%=mem.getProf_follower() %></span>
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
								
	<%			
			
			if(project.size() >= 4 && project.size() != 0){
				num = 4;
			}
			else if(project.size() != 0 && project.size() < 4){
				num = project.size();
			}
			for(int i=0; i<num; i++){
				Project proj = (Project) project.get(i);
	%>
											
								<!-- 첫번째 모집 -->
								<div class="col-md-3 mb">
									<div class="project-simple">
										<div class="simple-content text-center">
											<div class="pjTag">
					          					<% for(int j=0; j<proj.getTags().size(); j++) { %>
											<a href="javascript:tag('<%=proj.getTags().get(j)%>')">
											#<%= proj.getTags().get(j)%></a>&nbsp;
											<%} %>
					          				</div>
											<div class="pjTitle">
												<a  href="/detailView?cmd=PROJECT&proj_id=<%=proj.getProj_id()%>">
  												<%=proj.getProj_title() %> </a>
											</div>
											<div class="pjInfo">
												<span class="pjField"><a href="">#
													<%=proj.getTags2() %></a>
												</span>&nbsp;/&nbsp;
												<span class="pjTo"><%=proj.getProj_to() %> 명</span>
											</div>
											<div class="pjRegiEndDate">
												<span>마감일까지</span>&nbsp;&nbsp; <span class="pjDday">D&nbsp;-&nbsp;<span>
														<%=proj.getD_day() %> </span></span>
											</div>
										</div>
									</div>
								</div>
	<%} %>
									
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
			int num1 = 0;
			if(timeline.size() < 4 && timeline.size() != 0){
				num1 = timeline.size();
			}
			else if(timeline.size() != 0 && timeline.size() >= 4){
				num1 = 4;
			}
			for(int i=0; i< num1; i++){
				Timeline time = (Timeline) timeline.get(i);
	%>
				<!-- 첫번째 타임라인 -->
				<div class="col-md-12 mt">
					<div class="portfolio-timeline">
						<div class="pfTlType">
							<span class="glyphicon glyphicon-heart"></span> 
							<span class="pfTypeText">
								<%=time.getProf_id2().get(0)%>님 외 <%=time.getProf_id2().size()-1%>명이 이 포트폴리오를 좋아합니다. 
							</span>
						</div>
						<span class="pfInfo">
							<div>
								<span class="pfTitle">
									<a href="#" onclick="pf(<%=time.getPf_id()%>)">						
										<%=time.getPf_title()%></a></span>
								<span class="pfLike"> <span
									class="glyphicon glyphicon-heart"></span>
									 <span class="pfLikeCount"><%=time.getPf_like()%></span>
								</span>
							</div>
							<div class="pfmemName">
								<span class="fa fa-user"><%=time.getProf_name()%></span>&nbsp;&nbsp;
								<span><a href=""></a></span>
							</div>

							<div class="pfTag">
								<% for(int j=0; j<time.getTags().size(); j++) { %>
								<a href="javascript:tag('<%=time.getTags().get(j)%>')">
								#<%= time.getTags().get(j)%></a>&nbsp;
								<%} %>
							</div>
							
						 	
						 	
						 	</span> 
						 	<span class="pfImage"> 
							 	<% if(time.getMl_path2().get(0) != null){ %>
						 			<span><img src="${pageContext.request.contextPath}<%=time.getMl_path2().get(0)%>" /></span>
						 		<%}else{ %> 
						 			<span><img src="#" /></span>
						 		<%} %>
							 	<% if(time.getMl_path2().get(1) != null){ %>
						 			<span><img src="${pageContext.request.contextPath}<%=time.getMl_path2().get(1)%>" /></span>
						 		<%}else{ %> 
					 				<span><img src="#" /></span>
					 			<%} %>
							 	<% if(time.getMl_path2().get(2) != null){ %>
						 			<span><img src="${pageContext.request.contextPath}<%=time.getMl_path2().get(2)%>" /></span>
						 		<%}else{ %> 
				 					<span><img src="#" /></span>	
				 				<%} %>	
							</span>
						</span>
					</div>
				</div>
				<%
					}
				%>			

				</div>
			</div>
		</section>
		<!-- Timeline page end -->
	</section>	
	<script src="assets/js/paging.js"></script>
							<!-- 
								// 포트폴리오 글 주소 예:	/view?type=portfolio&id=pf_id
								// 프로필 글 주소 예:		/view?type=profile&id=prof_nick
								// 프로젝트 글 주소 예:	/view?type=project&id=proj_id
							 -->
<!-- 상세페이지로 이동하기 위한 폼 -->
<form name="pf_info" method="post" action="/view">
	<input type="hidden" name="id"  />							<!-- pf_id -->
	<input type="hidden" name="type" value="portfolio" />
</form>
<form name="tag_info" method="post" action="/detailView">
	<input type="hidden" name="tag_name" /> 
	<input type="hidden" name="cmd" value="TAG" />
</form>
<form name="prof_info" method="post" action="/view">
	<input type="hidden" name="id" />					<!-- prof_nick -->
	<input type="hidden" name="type" value="profile" />
</form>
<form name="proj_info" method="post" action="/view">
	<input type="hidden" name="id" /> 					<!-- proj_id --> 
	<input type="hidden" name="type" value="project" />
</form>
<!-- 포트폴리오 정보 -->

