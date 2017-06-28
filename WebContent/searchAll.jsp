<%@page import="portit.model.dto.Project"%>
<%@page import="java.util.List"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>DASHGUM - Bootstrap Admin Template</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/search.css" rel="stylesheet">


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
		<header class="header black-bg">
			<!--logo start-->
			<a href="index.html" class="logo"><b>Port IT</b></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  menu start -->
				<ul class="nav top-menu">
					<!-- settings start -->
					<li><a href="">Member</a></li>
					<li><a href="">Portfolio</a></li>
					<li><a href="">Project</a> <li>
					
					<li><a href="">Community</a></li>
				</ul>
				<!--  menu end -->
			</div>
			
			<div class="top-menu-right">
				<ul class="nav pull-right top-menu">
					<li>
						<form class="form-inline top-menu-search" method="post"
							action="/PortIt/search?cmd=SEARCH">
							<div class="input-group">
								<input type="text" class="form-control round-form" name="search"
									size="20" placeholder="통합 검색" />
								<span class="input-group-btn">
									<button type="submit" class="btn btn-default round-form">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</form>
					</li>
					<li id="header_inbox_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<i class="fa fa-bell"></i> <span class="badge bg-theme"></span>
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-yellow"></div>
							<li>
								<p class="yellow">Notification</p>
							</li>
							<li><a href=""> <span>구분</span>&nbsp;/&nbsp; <span
									class="time">Just now</span> <span class="message">[누구누구]님이
										내 포트포리오를 좋아합니다.</span>
							</a></li>
							<li><a href="">더보기</a></li>
						</ul></li>
					<li id="header_inbox_bar" class="dropdown mypage"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<img alt="avatar" src="assets/img/ui-danro.jpg"
							class="img-circle">
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-yellow"></div>
							<li>
								<p class="yellow">김수연 님</p>
							</li>
							<li><a href="">내 프로필</a></li>
							<li><a href="">팔로잉한 멤버</a> <a href="">북마크한 포트폴리오</a></li>
							<li><a href="">개인정보 설정</a></li>
							<li><a class="logout" href="login.html">로그아웃</a></li>
						</ul></li>
				</ul>
			</div>
		</header>
		<!--header end-->
	</section>


		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section class="container">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt search" id="search">
					<h2>
						<span class="fa fa-search"></span>
						<span class="keyword">${search}</span> 	
					</h2>
					<!-- 조건 검색 box -->					
					<div class="searchSorting col-md-12 mt">	
						<form class="" id="sea" name="" method="post" action="">
							
							<div class="">
								<div class="sortKey col-md-1"><b>구분</b></div>
								<div class="col-md-11">
									<a href="#pfResult">포트폴리오</a>
									<a href="#memResult">인물</a> 
									<a href="#projResult">프로젝트</a>					
								</div>
							</div>
							<br><br>
							<!-- 						
							<div class="mb">
								<span class="sortKey">정렬</span>
									<a href="#" class="active">최신순</a>
									<a href="#">인기순</a>
									<a href="#">랜덤</a>
							</div>
							 -->							
							
							<div class="sortKey col-md-1"><b>태그</b></div>										
							<div class="col-md-11">	
								<!-- 인기 태그 6개 띄우기 -->							
								<input class="btn poptag" type="button" name="language" value="java" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="c" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="c++" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="c#" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="jsp" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="servlet" onclick="fnAppendItem()"/>								
								.....
							</div>
							<br><br>
							<div class="col-md-offset-1 col-md-4">
								<input type="text" class="form-control taginput" id="language" name="language" placeholder="검색하고 싶은 태그를 입력하세요." onchange="fnAppendItem2()" />
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
						<div class="col-md-12 mb">
						<h4>포트폴리오(${port_list.size()} 건)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
		
		<c:if test="${port_list.size() != 0 && port_list.size()>0}">
				<c:forEach begin="0" end="${port_list.size()-1}" var="i" >	
						<!-- 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="javascript:tag_name('${port_list[i].tag_name}')">#${port_list[i].tag_name}&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="javascript:pf_title('${port_list[i].pf_id}')">${port_list[i].pf_title} </a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName">
												<a href="javascript:prof_name('${port_list[i].prof_name}')">
													${port_list[i].prof_name} </a>
											</span> 
											<span class="pfLikeCount">
												<span class="fa fa-heart"></span>&nbsp;&nbsp;${port_list[i].pf_like}
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>		
					</c:forEach>
			</c:if>	
			<c:if test="${port_list.size() == 0 }">
				검색된 결과가 없습니다.
			</c:if>	
						</div>
						<form post="method" name="move" action="/PortIt/SearchView">
							<input type ="hidden" name="list2" value="${search}"/>
							<input type ="hidden" name="cmd" value="PFSEARCH"/>
						
							<div class="text-right">
								<button type="submit" class="btn moreBtn">더 보기</button>							
							</div>
						</form>
					</div>
			
					<hr class="resultLine" id="memResult"/>

					<!-- 멤버 결과 -->
					<div class="memResult mt mb" >
						<div class="col-md-12 mb">
						<h4>멤버(${mem_list.size()}명)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
						
		<c:if test="${mem_list.size() != 0 && mem_list.size()>0}">			
				<c:forEach begin="0" end="${mem_list.size()-1}" var="i" >	
						<!-- member -->
						<div class="col-md-3 mb">
	          				<div class="member-simple">
		          				<div class="simple-content text-center">	      
			          				<img class="memImg img-circle" alt="avatar" src="${mem_list[i].prof_img}"/>   
			         				<div>
			         					<div class="memName"><a href=""> ${mem_list[i].prof_name}</a></div>
			         					<div class="memTag"><a href="javascript:tag_name('${mem_list[i].tag_name}')"># ${mem_list[i].tag_name}&nbsp;</a></div>
			         					<div class="memFollow">
			         						<span class="fa fa-user"></span>&nbsp;&nbsp;
			         						<span class="memFollowCount">${mem_list[i].prof_follower}</span>
			         					</div>
			         				</div>
		          				</div>          				
	          				</div>
	          			</div> 
        			</c:forEach>
        	</c:if>	
			<c:if test="${mem_list.size() == 0 }">
				검색된 결과가 없습니다.
			</c:if>	
						</div>					
						<form post="method" name="move" action="/PortIt/SearchView">
							<input type ="hidden" name="list2" value="${search}"/>
							<input type ="hidden" name="cmd" value="MEMSEARCH"/>
						
							<div class="text-right">
								<button type="submit" class="btn moreBtn">더 보기</button>							
							</div>
						</form>
							
					</div>
						
					<hr class="resultLine" id="projResult"/>

					<!-- 프로젝트 결과 -->
					<div class="projResult mt mb" >
						<h4>프로젝트(${proj_list.size()}건)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>

			<c:if test="${proj_list.size() != 0 && proj_list.size()>0}">
				<c:forEach begin="0" end="${proj_list.size()-1}" var="i" >	
					<!-- 프로젝트 -->
						<div class="col-md-12 mb">
	          				<div class="project-list">
		          				<span class="pjInfoText">
		          					<div class="pjTitle"><a href="javascript:proj_title('${proj_list[i].proj_id}')">${proj_list[i].proj_title}</a></div>
		          					<div class="pjmemName"><span class="fa fa-user"></span>&nbsp;&nbsp;<a href=""></a></div>
		          		
		          					<div class="pjIntro">${proj_list[i].proj_intro}</div>
		          					<div class="pjTag"><a href="javascript:tag_name('${mem_list[i].tag_name}')">#${proj_list[i].tag_name}&nbsp;</a></div>         					
	          					</span>
	          					<span class="pjInfoTable">
	          						<table class="table text-center">
	          							<tr><td>백엔드개발자</td></tr>
	          							<tr><td>${proj_list[i].proj_to} 명</td></tr>
	          							<tr><td>마감일까지 D&nbsp;-&nbsp;5</td></tr>
	          							<tr><td></td></tr>
	          						</table>
	          					</span>
	          				</div>          			
						</div>
						<br><br>
				</c:forEach>
			</c:if>	
			<c:if test="${proj_list.size() == 0 }">
				검색된 결과가 없습니다.
			</c:if>			
						<form post="method" name="move" action="/PortIt/SearchView">
							<input type ="hidden" name="list2" value="${search}"/>
							<input type ="hidden" name="cmd" value="PROJSEARCH"/>
						
							<div class="text-right">
								<button type="submit" class="btn moreBtn">더 보기</button>							
							</div>
						</form>
					</div>
				</div>
			</section>
			<!--/wrapper -->
		</section>

		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2014 - Alvarez.is <a href="blank.html#" class="go-top"> <i
					class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
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
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	
	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!-- detail search bar -->
	<script src="assets/js/search.js"></script>
	
	<!-- 포트폴리오 정보 -->
	<script>	
	function pf_title(pf_id){
		document.pf_title.pf_id.value = pf_id;
		document.pf_title.submit();
	}
	function tag_name(tag_name){
		document.tag_name.pf_id.value = tag_name;
		document.tag_name.submit();	
	}
	function prof_name(prof_name){
		document.prof_name.pf_id.value = prof_name;
		document.prof_name.submit();
	}
	</script>

	<form name="pf_title" method="post" action="/PortIt/search?cmd=SEARCH">
		<input type="hidden" name="pf_id" />
	</form>
	<form name="tag_name" method="post" action="/PortIt/search?cmd=SEARCH">
		<input type="hidden" name="tag_name" />
	</form>
	<form name="prof_name" method="post" action="/PortIt/search?cmd=SEARCH">
		<input type="hidden" name="prof_name" />
	</form>
	
	<!-- 멤버 정보 -->
	<script>	
	function mem_name(mem_id){
		document.mem_name.pf_id.value = mem_id;
		document.mem_name.submit();
	}
	</script>

	<form name="mem_name" method="post" action="/PortIt/search?cmd=SEARCH">
		<input type="hidden" name="mem_id" />
	</form>
	
	<!-- 프로젝트 정보 -->
	<script>	
	function proj_title(proj_id){
		document.proj_title.pf_id.value = proj_id;
		document.proj_title.submit();
	}
	</script>

	<form name="proj_title" method="post" action="/PortIt/search?cmd=SEARCH">
		<input type="hidden" name="proj_id" />
	</form>

			
</body>
</html>