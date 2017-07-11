<%@page import="portit.model.dto.Member"%>
<%@page import="portit.model.dto.Project"%>
<%@page import="java.util.List"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="assets/css/search.css" rel="stylesheet">
<jsp:useBean id="searchDao" class="portit.model.dao.SearchDao"></jsp:useBean>
<section class="container">
	<section class="wrapper site-min-height">
		<div class="col-md-12 mt search" id="search">
			<h2>
				<span class="fa fa-search"></span> <span class="keyword">${search}</span>
			</h2>
			<!-- 조건 검색 box -->
			<div class="searchSorting col-md-12 mt">
				<form class="" id="sea" name="" method="post" action="">

					<div class="">
						<div class="sortKey col-md-1">
							<b>구분</b>
						</div>
						<div class="col-md-11">
							<a href="#pfResult">포트폴리오</a> <a href="#memResult">인물</a> <a
								href="#projResult">프로젝트</a>
						</div>
					</div>
					<br>
					<br>
					<!-- 						
							<div class="mb">
								<span class="sortKey">정렬</span>
									<a href="#" class="active">최신순</a>
									<a href="#">인기순</a>
									<a href="#">랜덤</a>
							</div>
							 -->

					<div class="sortKey col-md-1">
						<b>태그</b>
					</div>
					<div class="col-md-11">
						<!-- 인기 태그 6개 띄우기 -->
						<input class="btn poptag" type="button" value="JAVA" name="language" onclick="fnAppendItem('JAVA')" /> 
						<input class="btn poptag" type="button" value="C" name="language"	onclick="fnAppendItem('C')" /> 
						<input class="btn poptag" type="button" value="c++" name="language" onclick="fnAppendItem('C++')" /> 
						<input class="btn poptag" type="button" value="Eclipse" name="language" onclick="fnAppendItem('ECLIPSE')" /> 
						<input class="btn poptag" type="button" value="jsp" name="language" onclick="fnAppendItem('jsp')" /> 
						<input class="btn poptag" type="button" value="servlet" name="language" onclick="fnAppendItem('servlet')'" /> .....
					</div>
					<br>
					<br>
					<div class="col-md-offset-1 col-md-4">
						<input type="text" class="form-control taginput" id="language"
							name="language" placeholder="검색하고 싶은 태그를 입력하세요."
							onchange="fnAppendItem2()" />
					</div>
					<br>
					<br>
					<hr />
					<div id="itemList" class="col-md-9"></div>
					<button type="submit" class="btn common col-md-2">조건 검색하기</button>
				</form>
			</div>
		</div>
			<!-- END - 조건 검색 box -->
			<br> <br>
			<hr class="resultLine" id="pfResult" />
	<%
		List portfolio = (List)request.getAttribute("port_list");
		List member = (List)request.getAttribute("mem_list");
		List project = (List)request.getAttribute("proj_list");
	%>

			<!-- 포트폴리오 결과 (4건 이하일 때) -->
			<div class="pfResult mt mb" id="pfResult">
				<div class="col-md-12 mb">
					<h4>
						포트폴리오(${port_list.size()}건)&nbsp;&nbsp;&nbsp;<i
							class="fa fa-angle-double-right"></i>
					</h4>
	<%	
		if(portfolio.size() < 4){
			for(int i=0; i< portfolio.size(); i++){
				Portfolio port = (Portfolio) portfolio.get(i);
	%>


					<div class="col-md-3 mb">
						<div class="portfolio-simple">
							<div class="pfImg">
            					<img src="assets/img/profile-02.jpg"/>   
		         			</div>
							<div class="pfInfo">
								<div class="simple-content">

									<div class="pfTag">
										<% for(int j=0; j<port.getTags().size(); j++) { %>
										<a href="javascript:tag(<%= port.getTags().get(j)%>)">
											#<%= port.getTags().get(j)%>
										</a>&nbsp;
										<%} %>
									</div>

									<div class="pfTitle">
										<a href=""><%=port.getPf_title()%></a>
									</div>
									<div class="pfBottom">

										<span class="pfmemName"><a href=""><%=port.getProf_name()%></a></span>
										<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=port.getPf_like()%></span>
									</div>
								</div>
							</div>
						</div>
					</div>
<%   		}
		}
		else if(portfolio.size()>=4){
%>

		<!-- 포트폴리오 결과 (4건 이상일 때) -->
	<%		
			for(int i=0; i< 4; i++){
				Portfolio port = (Portfolio) portfolio.get(i);
	%>


					<div class="col-md-3 mb">
						<div class="portfolio-simple">
							<div class="pfImg">
            					<img src="assets/img/profile-02.jpg"/>   
		         			</div>
							<div class="pfInfo">
								<div class="simple-content">

									<div class="pfTag">
										<% for(int j=0; j<port.getTags().size(); j++) { %>
										<a href="">#<%= port.getTags().get(j)%></a>&nbsp;
										<%} %>
									</div>

									<div class="pfTitle">
										<a href=""><%=port.getPf_title()%></a>
									</div>
									<div class="pfBottom">

										<span class="pfmemName"><a href=""><%=port.getProf_name()%></a></span>
										<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=port.getPf_like()%></span>
									</div>
								</div>
							</div>
						</div>
					</div>
<%			}
		}else if(portfolio.size() == 0 ){
%>
		검색 결과가 없습니다.
<%			
		}
%>
				</div>

				<form method="post" name="move" action="/SearchView">
					<input type="hidden" name="totalsearch" value="${search}" /> 
					<input type="hidden" name="cmd" value="PFSEARCH" />

					<div class="text-right">
						<button type="submit" class="btn moreBtn">더 보기</button>
					</div>
				</form>
			</div>
		
		<hr class="resultLine" id="memResult" />

		<!-- 멤버 결과 -->
		<div class="memResult mt mb">
			<div class="col-md-12 mb">
				<h4>
					멤버(${mem_list.size()}명)&nbsp;&nbsp;&nbsp;<i
						class="fa fa-angle-double-right"></i>
				</h4>
				
				<!-- 검색 결과가 4개 이상일때 -->
<%	
		if(member.size() < 4){
			for(int i=0; i< member.size(); i++){
				Member mem = (Member) member.get(i);
%>
				
						<!-- member -->
				<div class="col-md-3 mb">
	          				<div class="member-simple">
		          				<div class="simple-content text-center">	      
			          				<img class="memImg img-circle" alt="avatar" src="<%=mem.getProf_img()%>"/>   
			         				<div>
			         					<div class="memName"><a href=""> <%=mem.getProf_name()%></a></div>
			         					<div class="memTag">
			         					<% for(int j=0; j<mem.getTags().size(); j++) { %>
										<a href="">#<%= mem.getTags().get(j)%></a>&nbsp;
										<%} %>
			         					</div>
			         					<div class="memFollow">
			         						<span class="fa fa-user"></span>&nbsp;&nbsp;
			         						<span class="memFollowCount"><%=mem.getProf_follower() %></span>
			         					</div>
			         				</div>
		          				</div>          				
	          				</div>
	          			</div> 	
<%
			}	
		}
		else if(member.size() >=4){
			for(int i=0; i< 4; i++){
				Member mem = (Member) member.get(i);
%>
				<!-- member -->
						<div class="col-md-3 mb">
	          				<div class="member-simple">
		          				<div class="simple-content text-center">	      
			          				<img class="memImg img-circle" alt="avatar" src="<%=mem.getProf_img()%>"/>   
			         				<div>
			         					<div class="memName"><a href=""> <%=mem.getProf_name()%></a></div>
			         					<div class="memTag">
			         					<% for(int j=0; j<mem.getTags().size(); j++) { %>
										<a href="">#<%= mem.getTags().get(j)%></a>&nbsp;
										<%} %>
			         					</div>
			         					<div class="memFollow">
			         						<span class="fa fa-user"></span>&nbsp;&nbsp;
			         						<span class="memFollowCount"><%=mem.getProf_follower() %></span>
			         					</div>
			         				</div>
		          				</div>          				
	          				</div>
	          			</div> 	
<%
		}
	}
%>
	
			</div>
			<form method="post" name="move" action="/SearchView">
				<input type="hidden" name="totalsearch" value="${search}" /> 
				<input type="hidden" name="cmd" value="MEMSEARCH" />

				<div class="text-right">
					<button type="submit" class="btn moreBtn">더 보기</button>
				</div>
			</form>

		</div>

		<hr class="resultLine" id="projResult" />

		<!-- 프로젝트 결과 -->
		<div class="projResult mt mb">
			<h4>
				프로젝트(${proj_list.size()}건)&nbsp;&nbsp;&nbsp;<i
					class="fa fa-angle-double-right"></i>
			</h4>
			<!-- 검색 결과가 4개 이히일때 -->
<%	
		if(project.size() < 4){
			for(int i=0; i< project.size(); i++){
				Project proj = (Project) project.get(i);
%>

					<!-- 프로젝트 -->
					<div class="col-md-12 mb">
						<div class="project-list">
							<div class="col-md-9 mb">
								<span class="pjInfoText">
									<div class="pjTitle">
										<a href="javascript:proj('${proj_list[i].proj_id}')"><%=proj.getProj_title() %></a>
									</div>
									<div class="pjmemName">
										<a href=""> <span class="fa fa-user"><%=proj.getProf_name() %></span>&nbsp;&nbsp;
										</a>
									</div>
									<div class="pjIntro"><%=proj.getProj_intro() %></div>
									<br>
								<br>
								<br>
									<div class="pjTag">
			          					<% for(int j=0; j<proj.getTags().size(); j++) { %>
									<a href="">#<%= proj.getTags().get(j)%></a>&nbsp;
									<%} %>
			          				</div>
								</span>
							</div>
							<div class="col-md-3">
								<span class="pjInfoTable">
									<table class="table text-center">
										<tr>
											<td><//%proj.getTags2().get(0); %></td>
										</tr>
										<tr>
											<td><%=proj.getProj_to() %> 명</td>
										</tr>
										<tr>
											<td>마감일까지 D&nbsp;-&nbsp;<%=proj.getD_day() %></td>
										</tr>
										<tr>
											<td></td>
										</tr>
									</table>
								</span>
							</div>
						</div>
					</div>
<%
			}
		}
		else if(project.size() >= 4){
			for(int i=0; i< 4; i++){
				Project proj = (Project) project.get(i);
%>					
				
			<!-- 검색 결과가 4개 이상일때 -->
			
					<!-- 프로젝트 -->
						<!-- 프로젝트 -->
					<div class="col-md-12 mb">
						<div class="project-list">
							<div class="col-md-9 mb">
								<span class="pjInfoText">
									<div class="pjTitle">
										<a href="javascript:proj('${proj_list[i].proj_id}')"><%=proj.getProj_title() %></a>
									</div>
									<div class="pjmemName">
										<a href=""> <span class="fa fa-user"><%=proj.getProf_name() %></span>&nbsp;&nbsp;
										</a>
									</div>
									<div class="pjIntro"><%=proj.getProj_intro() %></div>
									<br>
								<br>
								<br>
									<div class="pjTag">
			          					<% for(int j=0; j<proj.getTags().size(); j++) { %>
									<a href="">#<%= proj.getTags().get(j)%></a>&nbsp;
									<%} %>
			          				</div>
								</span>
							</div>
							<div class="col-md-3">
								<span class="pjInfoTable">
									<table class="table text-center">
										<tr>
											<td>백엔드개발자</td>
										</tr>
										<tr>
											<td><%=proj.getProj_to() %> 명</td>
										</tr>
										<tr>
											<td>마감일까지 D&nbsp;-&nbsp;<%=proj.getD_day() %></td>
										</tr>
										<tr>
											<td></td>
										</tr>
									</table>
								</span>
							</div>
						</div>
					</div>
<%
			}
		}
%>

			<form method="post" name="move" action="/SearchView">
				<input type="hidden" name="totalsearch" value="${search}" /> <input
					type="hidden" name="cmd" value="PROJSEARCH" />

				<div class="text-right">
					<button type="submit" class="btn moreBtn">더 보기</button>
				</div>
			</form>
		</div>
		</div>
	</section>
	<!--/wrapper -->
</section>

<!-- detail search bar -->
<script src="assets/js/search.js"></script>

<!-- 상세페이지로 이동하기 위한 폼 -->
<form name="pf_info" method="post" action="/detailView">
	<input type="hidden" name="pf_id" /> 
	<input type="hidden" name="cmd" value="PORTFOLIO" />
</form>
<form name="tag_info" method="post" action="/detailView">
	<input type="hidden" name="tag_name" /> 
	<input type="hidden" name="cmd" value="TAG" />
</form>
<form name="prof_info" method="post" action="/detailView">
	<input type="hidden" name="prof_id" /> 
	<input type="hidden" name="cmd" value="PROJECT" />
</form>
<form name="proj_info" method="post" action="/detailView">
	<input type="hidden" name="proj_id" /> 
	<input type="hidden" name="cmd" value="MEMBER" />
</form>

<!-- 포트폴리오 정보 -->
<script>	
	function pf(pf_id){
		document.pf_info.pf_id.value = pf_id;
		document.pf_info.submit();
	}
	function tag(tag_name){
		document.tag_info.tag_name.value = tag_name;
		document.tag_info.submit();	
		alert(tag_name);
	}
	function prof(prof_id){
		document.prof_info.prof_id.value = prof_id;
		document.prof_info.submit();		
	}
	function proj(proj_id){
		document.proj_info.proj_id.value = proj_id;
		document.proj_info.submit();
	}
</script>
