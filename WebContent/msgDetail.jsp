<%@ page  contentType="text/html; charset=EUC-KR"%>
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
<link href="assets/css/message.css" rel="stylesheet">

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
			<div class="sidebar-toggle-box">
			</div>
			
		</header>
		
		
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		
		
		<!--sidebar end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt msg msgDetail">
					<div class="panel-group msgDetailContent">
						<div class="panel panel-default msgBox">
							<div class="panel-heading">
								<span class="pull-left">
									<a href="#">
										<img src="assets/img/you.png" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender">발신자 이름</span>
									</a>
								</span>
								<span class="pull-right"> 
									<a href="msgSend.html">
										<button type="button" class="btn">메세지 보내기</button>
									</a>&nbsp;&nbsp;&nbsp;
									<a href="msgList.html">
										<button type="button" class="btn">목록</button>
									</a>
								</span>								
							</div>
							<div class="panel-body">
							
								<c:forEach var="msg" items="${msgList}" varStatus="staus">
							
								<div class="msgContentBox mb clearfix"> <!-- 반복 -->
									<span class="pull-left col-md-10 msgContent">
									
											${msg.msg_content}
									
									</span>
									<span class="pull-right col-md-2">
										<span style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i>17.05.12 16:34</span>
										<span style="position:absolute; top: 20px; right: 0;"><button type="button" class="btn">삭제</button></span>											
									</span>										
								</div>	
								
								</c:forEach>
								
								
								
								
								
								
								
								<!-- 페이지네이션 -->
								<div class="center"> 
									1 2 3 4 5
								</div>					
							</div>			
								
						</div> <!-- END - msgBoxOpen -->
					</div> <!-- END - msgBox -->			
						
							
					</div>
				</div>



				<!--  chat box end -->



			</section>
			<! --/wrapper -->
		</section>





		<!-- /MAIN CONTENT -->

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


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!--script for this page-->

	<script>
      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

</body>
</html>
