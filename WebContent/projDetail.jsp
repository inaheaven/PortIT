<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
		<section id="projHeader">
			<div class="container">
				<h4 class="tags">
					<span>#<a href="">태그</a>&nbsp;</span>
					<span>#<a href="">태그</a>&nbsp;</span>
					<span>#<a href="">태그</a>&nbsp;</span>
				</h4>
				<h1>프로젝트_이름</h1>
				<h3 class="username"><a href="">USERNAME</a></h3>
				<h4 class="field">모집 분야 / ?명</h4>
				<h3 class="dday">D-12</h3>
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
							<p>생의 무엇을 창공에 이 광야에서 무엇을 그리하였는가? 얼음 그들의 발휘하기 얼마나 이상 있으랴? 살
								평화스러운 넣는 부패뿐이다. 방지하는 동력은 그들의 긴지라 옷을 있을 끓는 지혜는 아름다우냐? 사랑의 없는 이는
								때문이다. 곳으로 만천하의 돋고, 보라. 인간의 우리 끓는 사막이다.</p>
		
							<p>꽃 역사를 눈이 이상의 뭇 속에 있다. 붙잡아 풀이 군영과 피고, 듣는다. 넣는 아니한 얼음 오직
								황금시대다. 있으며, 힘차게 뼈 위하여 것은 주는 이상을 것이다. 이상을 인생을 목숨이 너의 웅대한 가치를 맺어,
								부패뿐이다. 우리의 위하여, 사는가 튼튼하며, 예수는 못하다 위하여 설산에서 듣는다.</p>
		
							<p>이상 곳으로 힘차게 따뜻한 열락의 바로 이것을 이성은 것이다. 물방아 지혜는 끓는 트고, 봄바람을 충분히
								수 것이다. 창공에 이것을 없으면, 바이며, 방황하였으며, 사막이다. 있으며, 할지니, 청춘의 약동하다. 예수는
								풀밭에 주는 것은 행복스럽고 그들은 약동하다. 두손을 새가 풍부하게 하였으며, 가치를 어디 그러므로 가슴에 것이다.
								날카로우나 심장의 꽃 피부가 길을 목숨이 칼이다.</p>
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
									<img src="http://loremflickr.com/1280/1024/cat" alt="...">
									<!-- <div class="carousel-caption">...</div> -->
								</div>
								<div class="item">
									<img src="http://loremflickr.com/1280/1024/pug" alt="...">
								</div>
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
								<td>사용자_이름</td>
							</tr>
							<tr>
								<th>등록일</th>
								<td>
									2017-06-07 오후 9:13<br />
									마감일까지 D-12
								</td>
							</tr>
							<tr>
								<th>모집분야</th>
								<td>
									<span><a href="">백엔드 개발자</a>,&nbsp;</span>
								</td>
							</tr>
							<tr>
								<th>모집인원</th>
								<td>6명</td>
							</tr>
							<tr>
								<th>요구기술</th>
								<td>
									<span><a href="">Java</a>,&nbsp;</span>
									<span><a href="">Oracle</a>,&nbsp;</span>
									<span><a href="">Git</a>,&nbsp;</span>
									<span><a href="">Eclipse</a>,&nbsp;</span>
								</td>
							</tr>							
							<tr>
								<th>예상작업기간</th>
								<td>14일</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="actions">
						<button type="button" class="btn common" onclick="location.href=''">개설자에게 연락하기</button>&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn common" onclick="location.href=''">프로젝트 지원하기</button>
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
					<div class="col-md-3 mb">
          				<div class="member-simple">
	          				<div class="simple-content text-center">	      
		          				<img class="memImg img-circle" alt="avatar" src="assets/img/friends/fr-06.jpg"/>   
		         				<div>
		         					<div class="memName"><a href="">멤버 이름</a></div>
		         					<div class="memTag"><a href="">#태그&nbsp;</a></div>
		         					<div class="memFollow">
		         						<span class="fa fa-user"></span>&nbsp;&nbsp;
		         						<span class="memFollowCount">135</span>
		         					</div>
		         				</div>
	          				</div>          				
          				</div>
          			</div> <!-- member-simple end -->
          			<div class="col-md-3 mb">
          				<div class="member-simple">
	          				<div class="simple-content text-center">	      
		          				<img class="memImg img-circle" alt="avatar" src="assets/img/friends/fr-06.jpg"/>   
		         				<div>
		         					<div class="memName"><a href="">멤버 이름</a></div>
		         					<div class="memTag"><a href="">#태그&nbsp;</a></div>
		         					<div class="memFollow">
		         						<span class="fa fa-user"></span>&nbsp;&nbsp;
		         						<span class="memFollowCount">135</span>
		         					</div>
		         				</div>
	          				</div>          				
          				</div>
          			</div> <!-- member-simple end -->
          			<div class="col-md-3 mb">
          				<div class="member-simple">
	          				<div class="simple-content text-center">	      
		          				<img class="memImg img-circle" alt="avatar" src="assets/img/friends/fr-06.jpg"/>   
		         				<div>
		         					<div class="memName"><a href="">멤버 이름</a></div>
		         					<div class="memTag"><a href="">#태그&nbsp;</a></div>
		         					<div class="memFollow">
		         						<span class="fa fa-user"></span>&nbsp;&nbsp;
		         						<span class="memFollowCount">135</span>
		         					</div>
		         				</div>
	          				</div>          				
          				</div>
          			</div> <!-- member-simple end -->          			
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
	</script>

</body>
</html>
