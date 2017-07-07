<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
      <jsp:include page="header.jsp"></jsp:include>
      <!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!-- Profile header -->
		<section id="pfHeader">
			<div class="container">
				<h1>포트폴리오_이름</h1>
				<h4 class="tags">
					<span>#<a href="">태그</a>&nbsp;</span>
					<span>#<a href="">태그</a>&nbsp;</span>
					<span>#<a href="">태그</a>&nbsp;</span>					
				</h4>
				<h4>
					<i class="fa fa-heart"></i>
					42
				</h4>
				<div class="actions">
					<button type="button" class="btn common" onclick="location.href='#'"><i class="fa fa-heart"></i></button>
					<button type="button" class="btn common" onclick="location.href='#'"><i class="fa fa-bookmark"></i></button>
					<button type="button" class="btn common" onclick="location.href='#'"><i class="fa fa-share-alt"></i></button>				
				</div>
			</div>
		</section><!-- /Profile header -->
		
		<!-- Portfolio nav -->
		<nav class="navbar navbar-default" id="portfolioNavbar">
			<div class="container">
				<div class="text-center">
					<ul class="nav navbar-nav">
						<li><a href="#pfAbout">About</a></li>
						<li><a href="#Collaborators">Collaborators</a></li>
						<li><a href="#Recommendations">Recommendations</a></li>
						<li><a href="#" id="BackToTop"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
					</ul>
				</div>
			</div>
		</nav>
		<!-- /Portfolio nav -->
		
		<!-- About -->
		<section id="pfAbout">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-center">About</h2>
					</div>
					<div class="col-md-offset-2 col-md-8 pfMem">						
						<div class="col-xs-2">
							<a href=""><img src="http://loremflickr.com/640/640/cat" alt="사용자_이름" class="img-circle uploaderImage" /></a>
						</div>
						<div class="col-xs-6">
							<h3 class="uploaderName"><a href="">사용자_이름</a></h3>
							<div class="tags">
								<span>#<a href="">태그</a>&nbsp;</span>
								<span>#<a href="">태그</a>&nbsp;</span>
								<span>#<a href="">태그</a>&nbsp;</span>								
								<span>#<a href="">태그</a>&nbsp;</span>	
							</div>
						</div>
						<div class="col-xs-4 text-right">
							<div class="actions">
								<button type="button" class="btn common" onclick="location.href='#'"><i class="fa fa-envelope"></i></button>
								<button type="button" class="btn common" onclick="location.href='#'"><i class="fa fa-comment"></i></button>
							</div>
						</div>
						</div>
					</div>
					<div class="col-md-offset-2 col-md-4">
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
					<div class="col-md-4">
						<div class="intro">
							<table class="table">
								<tr>
									<th>기간</th>
									<td>2017. 4. 24. ~ 2017. 6. 23.</td>
								</tr>
								<tr>
									<th>개발환경</th>
									<td>
										<span><a href="">Tomcat</a>,&nbsp;</span>
										<span><a href="">Oracle</a>,&nbsp;</span>
									</td>
								</tr>
								<tr>
									<th>사용언어</th>
									<td>
										<span><a href="">Java</a>,&nbsp;</span>
										<span><a href="">JavaScript</a>,&nbsp;</span>
										<span><a href="">HTML5</a>,&nbsp;</span>
										<span><a href="">CSS3</a>,&nbsp;</span>
									</td>
								</tr>
								<tr>
									<th>사용도구</th>
									<td>
										<span><a href="">Eclipse</a>,&nbsp;</span>
										<span><a href="">Git</a>,&nbsp;</span>
										<span><a href="">Bootstrap</a>,&nbsp;</span>
									</td>
								</tr>
								<tr>
									<th>인원</th>
									<td>6</td>
								</tr>
								<tr>
									<th>담당분야</th>
									<td>프론트엔드 개발</td>
								</tr>
								<tr>
									<th>저장소</th>
									<td><a href="#">http://www.GitHub url.com</a></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-offset-2 col-md-8 pfMedia">
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
								class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control" href="#Screenshots"
								data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</section><!-- /About -->
		<hr />
		<!-- Collaborators -->
		<section id="pfCollaborators">
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
		<hr />
		<!-- Recommendations -->
		<section id="Recommendations">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Recommendations</h2>
				</div>
				<div class="row recoList">
					<div class="col-md-3 mb">
          				<div class="portfolio-simple">
            				<div class="pfImg"></div>
	          				<div class="pfInfo">
	          					<div class="simple-content">
		          					<div class="pfTag"><a href="">#태그&nbsp;</a></div>
		          					<div class="pfTitle"><a href="">포트폴리오 제목</a></div>
		          					<div class="pfBottom">
		          						<span class="pfmemName"><a href="">멤버 이름</a></span>
		          						<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
		          					</div>
	          					</div>          					
	          				</div>        				
          				</div>
          			</div> <!-- portfolio-simple end -->
          			<div class="col-md-3 mb">
          				<div class="portfolio-simple">
            				<div class="pfImg"></div>
	          				<div class="pfInfo">
	          					<div class="simple-content">
		          					<div class="pfTag"><a href="">#태그&nbsp;</a></div>
		          					<div class="pfTitle"><a href="">포트폴리오 제목</a></div>
		          					<div class="pfBottom">
		          						<span class="pfmemName"><a href="">멤버 이름</a></span>
		          						<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
		          					</div>
	          					</div>          					
	          				</div>        				
          				</div>
          			</div> <!-- portfolio-simple end -->
          			<div class="col-md-3 mb">
          				<div class="portfolio-simple">
            				<div class="pfImg"></div>
	          				<div class="pfInfo">
	          					<div class="simple-content">
		          					<div class="pfTag"><a href="">#태그&nbsp;</a></div>
		          					<div class="pfTitle"><a href="">포트폴리오 제목</a></div>
		          					<div class="pfBottom">
		          						<span class="pfmemName"><a href="">멤버 이름</a></span>
		          						<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
		          					</div>
	          					</div>          					
	          				</div>        				
          				</div>
          			</div> <!-- portfolio-simple end -->
          			<div class="col-md-3 mb">
          				<div class="portfolio-simple">
            				<div class="pfImg"></div>
	          				<div class="pfInfo">
	          					<div class="simple-content">
		          					<div class="pfTag"><a href="">#태그&nbsp;</a></div>
		          					<div class="pfTitle"><a href="">포트폴리오 제목</a></div>
		          					<div class="pfBottom">
		          						<span class="pfmemName"><a href="">멤버 이름</a></span>
		          						<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
		          					</div>
	          					</div>          					
	          				</div>        				
          				</div>
          			</div> <!-- portfolio-simple end -->
				</div>
			</div>
		</section><!-- /Recommendations -->
		
		<!--footer start-->
		<jsp:include page="footer.jsp"></jsp:include>
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
		$("#portfolioNavbar").sticky({topSpacing:0, zIndex: '50'});
		
		// 스크롤 맨 위로
		$("#BackToTop").on("click", function(e){
			e.preventDefault();
			$("html,body").animate({ scrollTop : 0 }, 500);
		});
	</script>

</body>
</html>

