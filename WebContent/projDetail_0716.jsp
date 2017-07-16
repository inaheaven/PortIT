<%@page import="portit.model.dto.Tag"%>
<%@page import="portit.model.dto.Profile"%>
<%@page import="java.util.Date"%>
<%@page import="portit.model.dto.project_detail"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                    <li><a href="">Project</a><li>
                    <li><a href="">Community</a></li>
                </ul>
                <!--  menu end -->
            </div>
            <div class="top-menu-right">
            	<ul class="nav pull-right top-menu">
            		<li>
            			<form class="form-inline top-menu-search" method="post" action="">
            				<div class="input-group">
            					<input type="text" class="form-control round-form" name="search" size="20" placeholder="통합 검색"/>
            					<span class="input-group-btn">
            						<button type="submit" class="btn btn-default round-form"><span class="glyphicon glyphicon-search" ></span></button>
            					</span>
            				</div>
            			</form>
            		</li>
            		<li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <i class="fa fa-bell"></i>
                            <span class="badge bg-theme"></span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-yellow"></div>
                            <li>
                                <p class="yellow">Notification</p>
                            </li>
                            <li>
                                <a href="">                
                                	<span>구분</span>&nbsp;/&nbsp;
                                	<span class="time">Just now</span>
                                	<span class="message">[누구누구]님이 내 포트포리오를 좋아합니다.</span>      
                                </a>
                            </li>
                            <li>
                                <a href="">더보기</a>
                            </li>
                        </ul>
                    </li>
                    <li id="header_inbox_bar" class="dropdown mypage">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <img alt="avatar" src="assets/img/ui-danro.jpg" class="img-circle">
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-yellow"></div>
                            <li>
                                <p class="yellow">김수연 님</p>
                            </li>
                            <li>
                                <a href="">내 프로필</a>
                            </li>
                            <li>
                                <a href="">팔로잉한 멤버</a>
                                <a href="">북마크한 포트폴리오</a>                             
                            </li>
                            <li>
                            	<a href="">개인정보 설정</a>
                            </li>
                            <li>
                            	<a class="logout" href="login.html">로그아웃</a>
                            </li>
                        </ul>
                    </li>
            	</ul>
            </div>
        </header>
      <!--header end-->

		
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!-- Profile header -->
		
		
		<%
		//데이터 정리..
		System.out.println("PJ_JPS페이지 ");
		
		
		
		//최종결과를 담는다.
				// 1) : pj정보	<Project>
				// 2) : Coworker_list <list>
				// 2-1 <Profile>
				// 2-2 <List>: 태그 
		
		
		ArrayList pj_inform= (ArrayList)request.getAttribute("pj_inform");
		
		//1.Project 정보
		project_detail dto=	(project_detail)	pj_inform.get(0);
		
		
		//1-1.프로젝트의 테그정보
		ArrayList tag_list=(ArrayList) dto.getTag_list();

		
		
		//Tag tag_dto1=(Tag)pj_tag.get(0);
		//System.out.println("[JSP_tag===]"+tag_dto1.getTag_name());
		/* Tag tag_dto1=(Tag)pj_tag.get(0);
		Tag tag_dto2=(Tag)pj_tag.get(1); */
		//Tag tag_dto3=(Tag)pj_tag.get(2);
		
		
		//생략가능한부분...
		String title = dto.getProj_title();
		String username = dto.getProf_name();
		int to =dto.getProj_to();
		int d_day= dto.getD_day();
		String intro = dto.getProj_intro();
		String img=dto.getMl_path();

		
		
		
		
		//2.Coworker정보
		ArrayList cowork_list=	(ArrayList)	pj_inform.get(1);
		
		%>
		
		
		
		<section id="projHeader">
			<div class="container">
				<h4 class="tags">
				
				<!-- 반복한다  최대 3회. -->
				<%for(int i=0; i<tag_list.size();i++){ 
					if(i>3)break;
						Tag tag_inform=(Tag)tag_list.get(i);
				%>
				<a href="<%=tag_inform.getTag_id()%>">#<%=tag_inform.getTag_name() %> </a>&nbsp;
				<%} %>
				</h4>
				<h1><%=title %></h1>
				<h3 class="username"><a href=""><%=username %></a></h3>
				<h4 class="field">
				
				<%for(int i=0; i<tag_list.size();i++){ 
					Tag tag_inform=(Tag)tag_list.get(i);
					
					//등록된 테그 중에서 filed값만 출력한다.
				if("field".equals(tag_inform.getTag_type())){
				 %>
				 	<%=tag_inform.getTag_name() %>&nbsp;
				<%} //if문
				}//for문%>
				
				
				 /<%=to%>명</h4>
				<h3 class="dday">D-<%=d_day %></h3>
			</div>
		</section><!-- /Profile header -->
		
		<!-- Profile nav -->
		<nav class="navbar navbar-default" id="projectNavbar">
			<div class="container">
				<div class="text-center">
					<ul class="nav navbar-nav">
						<li><a href="#projAbout">About</a></li>
						<li><a href="#projInfo">Info</a></li>
						<li><a href="#projCollaborators">Collaborators</a></li>
						<li><a href="#" id="BackToTop"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
					</ul>
				</div>
			</div>
		</nav>
		<!-- /Profile nav -->
		
		<!-- About -->
		<section id="projAbout">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-center">About</h2>
					</div>
					<div class="col-md-offset-2 col-md-8">
						<div class="intro">
							<%=intro %>
						</div>
					</div>
					<div class="col-md-offset-2 col-md-8 projMedia">
						<div id="Screenshots" class="carousel slide" data-ride="carousel">
							<!-- Indicators 
							<ol class="carousel-indicators">
								<li data-target="#Screenshots" data-slide-to="0"
									class="active"></li>
								<li data-target="#Screenshots" data-slide-to="1"></li>
							</ol>-->
		
							<!-- Wrapper for slides -->
							<div class="carousel-inner">
								<div class="item active">
									<img src="<%=img %>" alt="...">
									<!-- <div class="carousel-caption">...</div> -->
								</div>
							<!-- 	
								<div class="item">
									<img src="http://loremflickr.com/1280/1024/pug" alt="...">
								</div>
								 -->
							</div>
		
							<!-- Controls -->
							<a class="left carousel-control" href="#Screenshots"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control" href="#Screenshots"
								data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</section><!-- /About -->
		<hr />
		<!-- Info -->
		<section id="projInfo">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Info</h2>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<table class="table">
							<tr>
								<th>개설자</th>
								<td><%=dto.getProf_name() %></td>
							</tr>
							<tr>
								<th>등록일</th>
								<td>
									<%=dto.getProj_regdate() %><br />
									마감일까지 D-<%=dto.getD_day() %>
								</td>
							</tr>
							<tr>
								<th>모집분야</th>
								<td>
									<span>
										<%for(int i=0; i<tag_list.size();i++){ 
													Tag tag_inform=(Tag)tag_list.get(i);
												if("field".equals(tag_inform.getTag_type())){
									 %>
											<a href="<%=tag_inform.getTag_id()%>">	<%=tag_inform.getTag_name() %></a>&nbsp;
												
												<%} //if문
												}//for문%>
									</span>
								</td>
							</tr>
							<tr>
								<th>모집인원</th>
								<td><%=dto.getProj_to() %>명</td>
							</tr>
							<tr>
								<th>요구기술</th>
								<td>
								<span>
									<%for(int i=0; i<tag_list.size();i++){ 
											Tag tag_inform=(Tag)tag_list.get(i);
											if(!"field".equals(tag_inform.getTag_type())){
									 %>
											<a href="<%=tag_inform.getTag_id()%>">	<%=tag_inform.getTag_name() %></a>&nbsp;
												
									<%			} //if문
										}//for문%>
								</span>
									
									
									
									
									
								</td>
							</tr>							
							<tr>
								<th>예상작업기간</th>
								<td><%=dto.getProj_period() %>일</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="actions">
						<button type="button" class="btn common" onclick="location.href='/msg?cmd=send&mem_id_sender=<%=dto.getProf_id() %>'">개설자에게 연락하기</button>&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn common" onclick="javascript:goPage(<%=dto.getProj_id() %>)">프로젝트 지원하기</button>
					</div>
				</div>
			</div>
		</section><!-- /Info -->
		<hr />
		
		
		
		
		<!-- Collaborators -->
		<section id="projCollaborators">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Collaborators</h2>
				</div>
				<div class="row collaboList">
				
				<% 
				//동료....
				for(int i=0; i<cowork_list.size();i++){
				ArrayList cowoker=(ArrayList) cowork_list.get(i);
				
				//2-1동료정보.
				Profile inform=	(Profile)	cowoker.get(0);
				
				//2-2 동료태그정보
				ArrayList tag_inform = (ArrayList) cowoker.get(1);
					
				
				System.out.println("[JSP_appy_memId]="+inform.getMem_id());
				%>
				
					<div class="col-md-3 mb">
          				<div class="member-simple">
	          				<div class="simple-content text-center">	      
		          				<img class="memImg img-circle" alt="avatar" src="<%=inform.getProf_img()%>"/>   
		         				<div>
		         				
		         				<!-- 긍정왕:프로필경로삽입 -->
		         					<div class="memName"><a href=""><%=inform.getProf_name() %></a></div>
		         					<div class="memTag">
		         					<!-- 태그목록  -->
		         					
		         					
		         					<%for(int j=1;j<tag_inform.size();j++){ 
		         						if(j>2)break;		//최대 3개
		         					Tag tag = (Tag)tag_inform.get(j);
		         					%>
		         					<!-- 긍정왕:태그경로삽입 -->
		         					<a href="<%=tag.getTag_id()%>">#<%=tag.getTag_name()%>&nbsp;</a>
		         					<%} %>
		         					
		         					</div>
		         					<div class="memFollow">
		         						<span class="fa fa-user"></span>&nbsp;&nbsp;
		         						<span class="memFollowCount"><%=inform.getProf_follower() %></span>
		         					</div>
		         				</div>
	          				</div>          				
          				</div>
          			</div> <!-- member-simple end -->
          			
          			<%
          			}
          			%>
          			
          			
          			
          			
          			      			
				</div>
			</div>
		</section><!-- /Collaborators -->		
		
		<!--footer start-->
		<footer class="footer site-footer">
		    <div class="col-md-3 footer-logo">
		        (c) Port IT 2017 / All right reserved.
		    </div>
		    <div class="col-md-3 footer-menu">
		    	<a href="">사이트 소개</a>
		    	<a href="">도움말</a>
		    	<a href="">문의하기</a>
		    	<a href="main.html#" class="go-top">
		    		<i class="fa fa-angle-up"></i>
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


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!-- JS Libraries -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.sticky/1.0.4/jquery.sticky.min.js"></script>
	<!-- Custom JS -->
	<script type="text/javascript">
		// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
		$("#projectNavbar").sticky({topSpacing:0, zIndex: '50'});
		
		// 스크롤 맨 위로
		$("#BackToTop").on("click", function(e){
			e.preventDefault();
			$("html,body").animate({ scrollTop : 0 }, 500);
		});
		
		function goPage(id) { 
			var pj_id= id;
			alert('프로젝트에 지원되셨습니다!')
			location.href="/myproj?cmd=apply&pj_id="+pj_id;
		}
	</script>

</body>
</html>
