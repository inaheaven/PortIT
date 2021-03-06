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
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="assets/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" />
<link href="jquery/jquery.autocomplete.css" rel="stylesheet" />
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

	<!--  JavaScript dependencies -->
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js"></script>
	<script src="jquery/lib/jquery.ajaxQueue.js"></script>
	<script src="jquery/lib/jquery.bgiframe.min.js"></script>
	<script src="jquery/jquery.autocomplete.js"></script>
	
	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>
	<!-- <script>jQuery.noConflict();</script> -->
</body>
</html>