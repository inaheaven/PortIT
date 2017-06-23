<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/search.css" rel="stylesheet">
		<section class="container">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt search" id="search">
					<h2>
						<span class="fa fa-search"></span>
						<span class="keyword">${param.search}</span>						
					</h2>
					<!-- 조건 검색 box -->					
					<div class="searchSorting col-md-12 mt">	
						<form class="" method="post" action="">
							
							<div class="">
								<div class="sortKey col-md-1"><b>구분</b></div>
								<div class="col-md-11">
									<a href="#pfResult">포트폴리오</a>
									<a href="#memResult">인물</a> 
									<a href="#projResult">프로젝트</a>					
								</div>
							</div>
							<br><br>
									
							<div class="sortKey col-md-1"><b>태그</b></div>										
							<div class="col-md-11">	
								<!-- 인기 태그 6개 띄우기 -->							
								<input class="btn poptag" type="button"  value="java" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button"  value="c" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button"  value="c++" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button"  value="c#" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button"  value="jsp" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button"  value="servlet" onclick="fnAppendItem()"/>								
								.....
							</div>
							<br><br>
							<div class="col-md-offset-1 col-md-4">
								<input type="text" class="form-control taginput" id="language" placeholder="검색하고 싶은 태그를 입력하세요." onchange="fnAppendItem2()" />
							</div>		
							<br><br>
							<hr />
							<div id="itemList" class="col-md-9"></div>		
							<button type="submit" class="btn common col-md-2">조건 검색하기</button>									
						</form>
					</div> <!-- END - 조건 검색 box -->	
					<br>	
					<br>
					<hr class="resultLine" id="pfResult"/>
					<!-- 포트폴리오 결과 -->
					<div class="pfResult mt mb" id="pfResult">
						<h4>포트폴리오(200개)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
						<%
							List list = portfolio.portfolio_info();
							//request.setAttribute("list", list);
							for (int i = 0; i < 4; i++) {
								Portfolio port = (Portfolio) list.get(i);
						%>						
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#<%=port.getTag_name() %>&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href=""><%=port.getPf_title() %></a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href=""><%=port.getProf_name() %></a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=port.getPf_like() %></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%} %>
									
						<div class="text-right">
							<button type="button" class="btn moreBtn" onclick="location.href='port_search.html'">더 보기</button>							
						</div>
					</div>
				
					<hr class="resultLine" id="memResult"/>

					<!-- 인물 결과 -->
					<div class="memResult mt mb" >
						<h4>인물(150명)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
						
						<%
							List list2 = developer.developer_info();
							//request.setAttribute("list", list);
							for (int i = 0; i < 4; i++) {
								Developer dev = (Developer) list2.get(i);
						%>	
						<!-- 첫 번째 member-->
						<div class="col-md-3 mb">
	          				<div class="member-simple">
		          				<div class="simple-content text-center">	      
			          				<img class="memImg img-circle" alt="avatar" src="<%=dev.getProf_img()%>"/>   
			         				<div>
			         					<div class="memName"><a href=""><%=dev.getProf_name() %></a></div>
			         					<div class="memTag"><a href="">#<%=dev.getTag_name() %>&nbsp;</a></div>
			         					<div class="memFollow">
			         						<span class="fa fa-user"></span>&nbsp;&nbsp;
			         						<span class="memFollowCount"><%=dev.getProf_follower() %></span>
			         					</div>
			         				</div>
		          				</div>          				
	          				</div>
	          			</div> 
	          			<%} %>
						
	
						<div class="text-right">
							<button type="button" class="btn moreBtn" onclick="location.href='person_search.html'">더 보기</button>							
						</div>
					</div>
						
					<hr class="resultLine" id="projResult"/>

					<!-- 프로젝트 결과 -->
					<div class="projResult mt mb" >
						<h4>프로젝트(60건)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
						
						<%
							List list3 = project.project_info();
							//request.setAttribute("list", list);
							for (int i = 0; i < list3.size(); i++) {
								Project proj = (Project) list3.get(i);
							
						%>
						<div class="col-md-12 mb">
	          				<div class="project-list">
		          				<span class="pjInfoText">
		          					<div class="pjTitle"><a href=""><%=proj.getProj_title() %></a></div>
		          					<div class="pjmemName"><span class="fa fa-user"></span>&nbsp;&nbsp;<a href="">개설자 이름</a></div>
		          					<div class="pjIntro">프로젝트 소개글이 출력됩니다. 글자 수는 제한합니다............</div>
		          					<div class="pjTag"><a href="">#<%=proj.getTag_name() %>&nbsp;</a></div>         					
	          					</span>
	          					<span class="pjInfoTable">
	          						<table class="table text-center">
	          							<tr><td>백엔드개발자</td></tr>
	          							<tr><td>3 명</td></tr>
	          							<tr><td>마감일까지 D&nbsp;-&nbsp;5</td></tr>
	          							<tr><td></td></tr>
	          						</table>
	          					</span>
	          				</div>          			
						</div>
						<br><br>
						<%} %>						
						
						<div class="text-right">
							<button type="button" class="btn moreBtn" onclick="location.href='proj_search.html'">더 보기</button>							
						</div>
					</div>
				</div>
			</section>
			<!--/wrapper -->
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
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	
	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!-- detail search bar -->
	<script src="assets/js/search.js"></script>
	
</body>
