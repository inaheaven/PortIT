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
		<jsp:include page="header.jsp" />
      	<!--header end-->

		
		<!-- Profile header -->
		<section id="memHeader">
			<div class="headerContainer">
				<img src="http://loremflickr.com/640/640/cat" alt="사용자_이름" class="img-circle" />
				<h1>사용자_이름</h1>
				<h3 class="username">USERNAME</h3>
				<h4 class="tags">
					<span>#<a href="">태그</a>&nbsp;</span>
					<span>#<a href="">태그</a>&nbsp;</span>
					<span>#<a href="">태그</a>&nbsp;</span>					
				</h4>
				<h4>
					<i class="fa fa-user"></i>
					42
				</h4>
				<div class="actions">
					<button type="button" class="btn common" onclick="location.href='#'">+ Follow</button>					
				</div>
			</div>
		</section><!-- /Profile header -->
	
		<!-- Profile nav -->
		<nav class="navbar navbar-default" id="profileNavbar">
			<div class="container">
				<div class="text-center">
					<ul class="nav navbar-nav">
						<li><a href="#About">About</a></li>
						<li><a href="#Portfolios">Portfolios</a></li>
						<li><a href="#Projects">Projects</a></li>
						<li><a href="#Contact">Contact</a></li>
						<li><a href="#" id="BackToTop"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
					</ul>
				</div>
			</div>
		</nav>
		<!-- /Profile nav -->
		
		<!-- About -->
		<section id="memAbout">
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
					<div class="col-md-offset-2 col-md-8">
						<div class="skillset">
							<c:forEach>
							<c:if><c:set/></c:if>
							<div class="skill clearfix">
								<div class="col-xs-3 text-center">
									<span class="">태그</span>
								</div>
								<div class="col-xs-9">
									<div class="progress">
										<div class="progress-bar" style="width: 60%;"></div>
									</div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</section><!-- /About -->
		<hr />
		<!-- Portfolios -->
		<section id="Portfolios">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Portfolios</h2>
				</div>
				<div class="row">
					<ul class="sorting">
						<li><a href="#" class="active">전체</a></li>
						<li><a href="#">최신순</a></li>
						<li><a href="#">인기순</a></li>
					</ul>
				</div>
				<jsp:include page="pfGrid.jsp">
					<jsp:param name="sort" value="" />
				</jsp:include>
			</div>
		</section><!-- /Portfolios -->
		<hr />
		<!-- Projects -->
		<section id="Projects">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Projects</h2>
				</div>
				<div class="row">
					<ul class="sorting">
						<li><a href="#" class="active">전체</a></li>
						<li><a href="#">최신순</a></li>
						<li><a href="#">인기순</a></li>
					</ul>
				</div>
				<jsp:include page="projGrid.jsp">
					<jsp:param name="sort" value="" />
				</jsp:include>
			</div>
		</section><!-- /Projects -->
		<hr />
		<!-- Contact -->
		<section id="Contact">
			<div class="container">
				<div class="row">					
					<h2 class="text-center">Contact</h2>
				</div>
				<div class="row">
					<div class="col-md-8 center">
						<div class="actions col-md-12 text-center">
							<a href="#" title="Website"><span class="fa fa-globe"></span></a>
							<a href="#" title="GitHub"><span class="fa fa-github"></span></a>
							<a href="#" title="Facebook"><span class="fa fa-facebook-square"></span></a>
							<a href="#" title="Email"><span class="fa fa-envelope"></span></a>
							<a href="#" title="Message"><span class="fa fa-comment"></span></a>
							<a data-toggle="collapse" href="#Share" title="Share"><span class="fa fa-share-alt"></span></a>
							<div class="collapse pull-right" id="Share">
								<div class="actions">
									<a href="#" title="Copy URL"><span class="fa fa-clipboard"></span></a>
									<a href="#" title="Facebook"><span class="fa fa-facebook-square"></span></a>
									<a href="#" title="Email with this URL"><span class="fa fa-envelope"></span></a>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</section><!-- /Contact -->
		
		<!--footer start-->
		<jsp:include page="footer.jsp" />
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
		$(document).ready(function(){
			// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
			$("#profileNavbar").sticky({topSpacing:0, zIndex: '50'});
			
			// 스크롤 맨 위로
			$("#BackToTop").on("click", function(e){
				e.preventDefault();
				$("html,body").animate({ scrollTop : 0 }, 500);
			});
			
		});	
		
		
	</script>

</body>
</html>
