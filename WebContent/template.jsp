<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title></title>

<!-- Bootstrap core CSS -->
<link href="assets/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="assets/lib/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" />
<!-- <link rel="stylesheet" type="text/css" href="jquery/jquery.autocomplete.css" /> -->
<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>

	<section id="container">
		<%--header start--%>
		<jsp:include page="header.jsp"></jsp:include>
		<%--header end--%>
		
		<%
			String pageName = (String)request.getAttribute("pageName");
		%>
		<%-- 내용 --%>	
		<jsp:include page="<%= pageName%>"></jsp:include>
		
		<%--footer start--%>
		<jsp:include page="footer.jsp"></jsp:include>
		<%--footer end--%>
	</section>
	
	<!-- JavaScript dependencies -->
	<script type="text/javascript" src="assets/lib/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="assets/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<!-- <script type="text/javascript" src="jquery/lib/jquery.js"></script> -->
	<script src="assets/lib/bgiframe-3.0.1/jquery.bgiframe.js"></script>
	<script src="assets/lib/jquery.ajaxQueue/dist/jquery.ajaxQueue.min.js"></script>
	<!-- <script type='text/javascript' src='jquery/jquery.autocomplete.js'></script> -->
	<!-- <script src="assets/js/jquery.js"></script> -->
	<script src="assets/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<!-- <script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>  -->
	<script src="assets/lib/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="assets/lib/dcjqaccordion/js/jquery.dcjqaccordion.2.7.min.js"></script>
	<script src="assets/lib/jquery.scrollTo/jquery.scrollTo.min.js"></script>
	<script src="assets/lib/jquery.nicescroll/jquery.nicescroll.min.js"></script>
	
	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>
	<!-- <script>jQuery.noConflict();</script> -->
</body>
</html>