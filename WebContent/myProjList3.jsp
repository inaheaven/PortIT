<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>PORTIT - MYPAGE</title>


<link href="assets/css/profpfproj.css" rel="stylesheet">
<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>

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
    
<script>
$(document).ready(

    function() {
    	var httpRequest=null;
         $("#delete_my_reg").click(function() {
        	 alert("1");
        		httpRequest = new XMLHttpRequest();
        		var name = "7"
        		alert(name);
        		var param = "/project_delete?proj_id="+name;
        		
        		httpRequest.open("GET", param, true);
        		httpRequest.onreadystatechange = callback;
        		httpRequest.send(null);
        		
        		  $(this).parent().parent().parent().parent().remove();
                  $(this).next().remove();
        	});
         
});
     	function callback(){
    		if(httpRequest.readyState == 4){
    			if(httpRequest.status == 200){
    				alert("1");
    			}
    		}
    	}
</script>    
    
</head>
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt projlist">
					<h3 class="formTitle text-center">내 프로젝트</h3>	
					<div class="projlist_top clearfix">
						<button type="button" class="btn common" onclick="location.href='/page?page=myProjRegister'">프로젝트 등록하기</button>
					</div>
					<div class="proj_my">
						<p>내가 등록한 프로젝트</p>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">
									<span class="col-sm-3"> 프로젝트 제목 #1 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>
									<form action="/project_update">									
									<span class="col-md-3 text-right">
										<button type="submit" class="btn common">수정</button>
 										<button type="button" class="btn common" id="delete_my_reg">삭제</button>										<a class="updown collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne"
										aria-expanded="false" aria-controls="collapseOne"> 
											<i class="fa fa-chevron-down"></i>
										</a>
										<input type="hidden" name="proj_id" value="50">
									</span>							
									</form>		
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
									<div class="col-lg-6">
										APPLICANTS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
									</div>
									<div class="col-lg-6">
										COWORKERS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						
											
					</div>
					<hr class="line" />
					<div class="proj_apply">
						<p>내가 지원한 프로젝트</p>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">								
									<span class="col-sm-3"> 프로젝트 제목 #4 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">지원 취소</button>
									</span>
								</h4>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- /wrapper -->
		</section>


	<!--footer start-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--footer end-->

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
