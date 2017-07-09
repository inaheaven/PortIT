<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>PortIT</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<jsp:include page="signIn.jsp"></jsp:include>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!--BACKSTRETCH-->
	<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
	<script type="text/javascript"
		src="assets/js/jquery.backstretch.min.js"></script>
	<script>
        $.backstretch("assets/img/log	in-bg.jpg", {speed: 500});
        
        function fnPasswordConfirm() {
        	var pw = document.getElementById("userpw").value;
        	var pwcf = document.getElementById("userpwcf").value;
        	var div = document.getElementById("pwconfirm");
        	
        	if(pw != pwcf){
        		div.classList.remove('greenLine');
        		div.classList.add('redLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;&nbsp;비밀번호가 일치하지 않습니다.";
        	}
        	else {
        		div.classList.remove('redLine');
        		div.classList.add('greenLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-ok'></span>&nbsp;&nbsp;비밀번호가 일치합니다.";
        		
        	}
        }
        
        function fnSubmit() {
        	
        	//[0708]이메일 유효성추가 by Cater
        	//알림창 사라졌을때 해당 폼에 pocus하도록 수정할것.
        	
        	
        	//비밀번호 유효성
        	var pw = document.getElementById("userpw").value;
        	var pwcf = document.getElementById("userpwcf").value;
        	var $pw = $('#userpw');
        	
        	//이메일 유효성
        	var $email = $('#userid');
        	var exptext = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        	
        	if(pw != pwcf) {
        		confirm("비밀번호를 다시 확인하세요!");
        		$pw.focus();
        	}
        	else if(!exptext.test($email.val())) {
        		alert("이메일 형식이 올바르지 않습니다!!!!!!!!!!!");
        		$email.focus();
        		return false;
        	}
        	else {
        		document.getElementById("join").submit();
        	}
        }
        
        
        
        
    </script>


    
    

</body>
</html>
