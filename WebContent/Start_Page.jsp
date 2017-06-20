<%@ page  contentType="text/html; charset=EUC-KR"%>
<%@page import="java.util.ArrayList"%>
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
    




<!-- JSP:useBean -->
<jsp:useBean id="dao" class="portit.model.dao.MassageDao"/>


<%
//�α��� : ����
//Session�� ����Ǵ°� : mem_ID (DB���� ��ȸ�ؾ���.)
session.setAttribute("longin_id","2");
%>

<script>
$(document).ready(function(){
	
			 $("#push").click(function(){
					alert("��ư����");
				});
		});

</script>


</head>

<body>
	<section id="container">
		<!--header start-->
		<header class="header black-bg">
			<!--logo start-->
			<a href="index.html" class="logo"><b>Start!</b></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  menu start -->
				<ul class="nav top-menu">
					<!-- settings start -->
					<li><a href="">Member</a></li>
					<li><a href="">Portfolio</a></li>
					<li><a href="">Project</a>
					<li>
					<li><a href="">Community</a></li>
				</ul>
				<!--  menu end -->
			</div>
			<div class="top-menu-right">
				<ul class="nav pull-right top-menu">
					<li>
						<form class="form-inline top-menu-search" method="post" action="">
							<div class="input-group">
								<input type="text" class="form-control round-form" name="search"
									size="20" placeholder="���� �˻�" /> <span class="input-group-btn">
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
							<li><a href=""> <span>����</span>&nbsp;/&nbsp; <span
									class="time">Just now</span> <span class="message">[��������]����
										�� ��Ʈ�������� �����մϴ�.</span>
							</a></li>
							<li><a href="">������</a></li>
						</ul></li>
					<li id="header_inbox_bar" class="dropdown mypage"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
							<img alt="avatar" src="assets/img/ui-danro.jpg"
							class="img-circle">
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-yellow"></div>
							<li>
								<p class="yellow">������ ��</p>
							</li>
							<li><a href="">�� ������</a></li>
							<li><a href="">�ȷ����� ���</a> <a href="">�ϸ�ũ�� ��Ʈ������</a></li>
							<li><a href="">�������� ����</a></li>
							<li><a class="logout" href="login.html">�α׾ƿ�</a></li>
						</ul></li>
				</ul>
			</div>
		</header>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
			<div id="sidebar">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu">
					<div class="myImg">
						<p class="centered">
							<a href="profile.html"><img src="assets/img/ui-sam.jpg"
								class="img-circle" width="60"></a>
						</p>
						<h5 class="centered">������ ��</h5>
					</div>
					<hr class="line" />
					<li class="sub-menu"><a href="index.html"> <span>My
								Profile</span>
					</a></li>
					<li class="sub-menu"><a href=""> <span>My Portfolio</span>
					</a></li>
					<li class="sub-menu"><a href=""> <span>My Project</span>
					</a></li>
					<li class="sub-menu"><a href=""> <span>Bookmarks</span>
					</a></li>
					<li class="sub-menu"><a href=""> <span>Following</span>
					</a></li>
					<li class="sub-menu"><a href=""> <span>Notification</span>
					</a></li>
					<li class="sub-menu"><a href="/Project2/msg?cmd=list"> <span>Message</span>
					</a></li>
					<hr class="line" />
					<li class="sub-menu"><a href=""> <span>My Account</span>
					</a></li>
					<li class="sub-menu"><a href=""> <span>Delete
								Account</span>
					</a></li>
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		
		
		<!--main content start-->
		
		
		<!-- /MAIN CONTENT -->

		<!--main content end-->
		<!--footer start-->
		<footer class="footer site-footer">
			<div class="col-md-3 footer-logo">(c) Port IT 2017 / All right
				reserved.</div>
			<div class="col-md-3 footer-menu">
				<a href="">����Ʈ �Ұ�</a> <a href="">����</a> <a href="">�����ϱ�</a> <a
					href="main.html#" class="go-top"> <i class="fa fa-angle-up"></i>
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