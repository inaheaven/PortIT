<%@ page  contentType="text/html; charset=EUC-KR"%>




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
    
    
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script>


//View->Controller  -MessageController
// ��Ʈ�ѷ� : /Project2/msg

/*�������.
 1.J_Query�� ������ ����.
	�����Ϳ� ��� reqeust�� �����ؾ��ϳ�?
*/

/*  	
 $(document).ready(function() {
	$("#btn-send").click(function() {
		//alert("aa");
		
		var msg_content =$("#msg_content");
		var mem_id_sender =$("#mem_id_sender");
		//�� ������ ��� request�� ��ƾ��ϳ��� �Ф�?
		
		location.href="/Project2/msg";
	});
});  
*/


/*	�ϴ� form������� ����غ���.
 	dto�� ������� id���� �����ؾ��Ѵ�.
 */



</script>

</head>





<body>

<%
//�α��� : ����
//Session�� ����Ǵ°� : mem_ID (DB���� ��ȸ�ؾ���.)
session.setAttribute("longin_id","7729");
%>

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
				<div class="col-md-12 mt msg msgSend">
					<div class="msgSendContent">
						<form method="post" action="/Project2/msg?cmd=send" class="msgForm clearfix">
									<div class="form-group col-md-8 center" >
								<label class="control-label" for="msgReceiver">�޴� ��� </label><br>
								<div class="">
									<input class="form-control" type="text" id="msgReceiver" name="msgReceiver" value=""/>
								</div>
							</div><br>
							<div class="form-group col-md-8 center">
								<label class="control-label" for="msgText">���� </label><br>
								<div>
									<input class="form-control" type="text" id="msgText" name="msgText" value=""/>
								</div>
							</div>
							<div class="msgBtn col-md-3 center">
								<button type="submit" class="btn">������</button>&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn" onclick="location.href='#'">���ư���</button>
							</div>
						</form>
					</div>
				</div>



			</section>
			<!--/wrapper -->
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

     /*  $(function(){
          $('select.styled').customSelect();
      }); */
  </script>

</body>
</html>
