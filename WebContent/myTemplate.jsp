<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>My Page</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />


<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<!-- 
<link href="assets/css/profpfproj.css" rel="stylesheet">
<link href="assets/css/notification.css" rel="stylesheet">
<link href="assets/css/message.css" rel="stylesheet">
<link href="assets/css/bookmark.css" rel="stylesheet">
<link href="assets/css/following.css" rel="stylesheet">
<link href="assets/css/account.css" rel="stylesheet">
 -->
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
		
		<%--sidenavbar start--%>
		<jsp:include page="my.jsp"></jsp:include>
		<%--sidenavbar end--%>
	
		<%-- 내용 --%>		
		<c:if test="${pageName eq null}">
			<jsp:include page="myProf.jsp"></jsp:include>
		</c:if>		
		<jsp:include page="${pageName}"></jsp:include>

		<%--footer start--%>
		<jsp:include page="footer.jsp"></jsp:include>
		<%--footer end--%>
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
	<script src="assets/js/jquery-3.2.1.min.js"></script>

	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

</body>
</html>