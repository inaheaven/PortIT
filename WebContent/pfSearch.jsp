<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<jsp:include page="header.jsp"/>
		<!--header end-->

		
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section class="container">
			<section class="wrapper site-min-height">			
				<div class="col-md-12 mt search" id="searchPf">
					<!-- 검색어 검색 폼 -->
					<div class="col-md-12 mt mb">
						<form class="col-md-10 searchKeyword" method="post" action="">
							<div class="form-group col-md-11">
								<input type="text" class="form-control" />
							</div>
							<button type="submit" class="col-md-1 btn common"><i class="fa fa-search"></i></button>
						</form>
						<div class="col-md-2 collapsed sortingBtn text-right" data-toggle="collapse" data-parent="#searchPf" href="#searchSorting">
							<button type="button" class="btn common updown">
								조건 검색&nbsp;&nbsp;<i class="fa fa-chevron-down"></i>
							</button>
						</div>	
					</div>
					<!-- 조건 검색 box -->					
					<div class="searchSorting col-md-12 collapse" id="searchSorting">	
						<form class="" method="post" action="">									
							<div class="">
								<div class="sortKey col-md-1"><b>정렬</b></div>
								<div class="col-md-11">
									<a href="#">최신순</a>
									<a href="#">인기순</a> 
									<a href="#">랜덤</a>					
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
					
					
					<!-- 포트폴리오 결과 -->
					<div class="pfResult mt mb" id="pfResult">
						<!-- 첫번째 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="">포트폴리오 제목</a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">멤버 이름</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
										</div>
									</div>
								</div>
							</div>
						</div>
	
						<!-- 두번째 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="">포트폴리오 제목</a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">멤버 이름</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<!-- 세번째 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="">포트폴리오 제목</a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">멤버 이름</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<!-- 네번째 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="">포트폴리오 제목</a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">멤버 이름</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 다섯번째 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="">포트폴리오 제목</a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">멤버 이름</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
										</div>
									</div>
								</div>
							</div>
						</div>
	          			<!-- 여섯번째 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="">포트폴리오 제목</a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">멤버 이름</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					
					<!-- 페이지네이션 -->
					<div class="col-md-12 text-center mt mb">
						&lt; 1 2 3 4 5 &gt;
					</div>
			</section>
			<! --/wrapper -->
		</section>

		<!--main content end-->
		<!--footer start-->
		<jsp:include page="footer.jsp"/>
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
	
	<!-- detail search bar -->
	<script src="assets/js/search.js"></script>

	<!--script for this page-->
	<script>
		$(document).ready(function(){
			$('.updown').on('click',function(){
				var icon = $(this).find('i');
				
		        if (icon.hasClass("fa-chevron-down")){
		        	icon.addClass("fa-chevron-up").removeClass("fa-chevron-down");
		    	} 
		        else {
		        	 icon.addClass("fa-chevron-down").removeClass("fa-chevron-up");
		        }
		    });
		});
		

	    
	</script>

</body>
</html>