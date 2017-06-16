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
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>DASHGUM - Bootstrap Admin Template</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/js/bootstrap-datepicker/css/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="assets/js/bootstrap-daterangepicker/daterangepicker.css" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    <link href="assets/css/custom.css" rel="stylesheet">
    <link href="assets/css/account.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <section id="container" >
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
          <section class="wrapper">
          	<div class="col-md-12 mt myAccount">
          		<div class="col-md-8 center">
          			<h3 class="accountTitle text-center">My Account</h3>
          			<br><br>
          			<form class="form-horizontal" method="post" action="#">
	                    <div class="form-group">
	                        <label class="col-md-4 control-label">E-Mail</label>
	                        <div class="col-md-8">
	                            <input type="text" class="form-control" id="userid" value="email@email.com">
	                        </div>
	                    </div>
                    	<div align="center">
	                  		<div align="center" class="form-group">
	                              <label class="col-md-4 control-label">Current Password</label>
	                              <div class="col-md-8">
	                                  <input type="password" class="form-control" id="userpw" onchange="fnPasswordConfirm()">
	                              </div>
	                        </div>
	                        <div id="pwconfirm"></div> 
	                  		<div align="center" class="form-group">
	                              <label class="col-md-4 control-label">New Password</label>
	                              <div class="col-md-8">
	                                  <input type="password"  class="form-control" id="newpw">
	                              </div>
	                        </div>
	                        <div align="center" class="form-group">
	                              <label class="col-md-4 control-label">Confirm New Password</label>
	                              <div class="col-md-8">
	                                  <input type="password"  class="form-control" id="newpwcf" onchange="fnNewPasswordConfirm()">
	                              </div>
	                        </div>
	                       	<div id="newpwconfirm"></div> 
	                        <div class="modifyBtn">
			               		<div align="center">
			                    	<button type="submit" class="btn">정보 변경</button>
			                	</div>
				            </div>
                	 	 </div>
                   </form>
          		</div>
          	</div>
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page-->
    <script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>

	<!--custom switch-->
	<script src="assets/js/bootstrap-switch.js"></script>
	
	<!--custom tagsinput-->
	<script src="assets/js/jquery.tagsinput.js"></script>
	
	<!--custom checkbox & radio-->
	
	<script type="text/javascript" src="assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	
	
	<script src="assets/js/form-component.js"></script>    
    
    
  <script>
      //custom select box
	
      function fnPasswordConfirm() {
        	var pw = document.getElementById("newpw").value;
        	var pwcf = document.getElementById("newpwcf").value;
        	var div = document.getElementById("pwconfirm");
        	
        	if(pw != pwcf){
        		div.classList.remove('greenLine');
        		div.classList.add('redLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;&nbsp;새로운 비밀번호가 일치하지 않습니다.";
        	}
        	else {
        		div.classList.remove('redLine');
        		div.classList.add('greenLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-ok'></span>&nbsp;&nbsp;새로운 비밀번호가 일치합니다.";
        	}
        }
      
      function fnNewPasswordConfirm() {
        	var pw = document.getElementById("userpw").value;
        	var pwcf = ""; // db 또는 세션에서 현재 비밀번호 가져오기
        	var div = document.getElementById("pwconfirm");
        	
        	if(pw != pwcf){
        		div.classList.remove('greenLine');
        		div.classList.add('redLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;&nbsp;새로운 비밀번호가 일치하지 않습니다.";
        	}
        	else {
        		div.classList.remove('redLine');
        		div.classList.add('greenLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-ok'></span>&nbsp;&nbsp;새로운 비밀번호가 일치합니다.";
        	}
        }

  </script>

  </body>
</html>
