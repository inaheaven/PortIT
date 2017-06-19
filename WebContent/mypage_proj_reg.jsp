<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>PORTIT - MYPAGE</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!--  jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />


<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>
<link href="assets/css/custom.css" rel="stylesheet">

<script>
	var i = 1;

	$(document).ready(function() {
		$("#insert").click(function() {
			if (i == 1) {
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-2").append($cloneRow);
				i++;
			} else if (i == 2) {
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-3").append($cloneRow);
				i++;
			} else if (i == 3) {
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-4").append($cloneRow);
				i++;
			} else if (i == 4) {
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-5").append($cloneRow);
				i++;
			} else {
				alert("모집 분야수를 5개 이내로 제한합니다.");
			}
		});

		$("#delete").click(function() {
			if (i > 1) {
				$(this).parent().parent().remove();
				i--;
			} else {
				alert("모집 분야수를 1개 이상으로 제한합니다.")
			}
		});
	});
</script>
</head>

<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg"></header>
		<!--header end-->
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">

				<!-- BASIC FORM ELELEMNTS -->
				<div class="row mt">
					<div class="col-lg-12">
						<div class="form-panel">
							<form action="mypage_proj_reg" class="form-horizontal style-form"
								method="post">
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로젝트 제목</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="proj_title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로젝트 설명</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="proj_intro"
											placeholder="프로젝트 주제 , 목적등 자세한 설명을 작성하세요" rows="10"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로젝트 개발
										환경</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="proj_env"
											placeholder="ex) windows7, oracle DB 같은 실행 환경과 서버 환경 ">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로젝트 개발
										언어</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="proj_language"
											placeholder="ex) C, JAVA, Python 등">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로젝트 개발
										도구</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="proj_tool"
											placeholder="ex) Eclipse, Visual Studio2013 등">
									</div>
								</div>
								<div class="form-group" id="add-team">
									<label class="col-sm-2 col-sm-2 control-label">모집 분야</label>
									<div class="col-sm-5" id="add-team-input">
										<input type="text" class="form-control" name="proj_field"
											placeholder="ex) 기획, 설계, 프론트, 백엔드 등">
									</div>
									<div class="col-sm-1">
										<label class="control-label">필요 인원</label>
									</div>
									<div class="col-sm-1">
										<input type="text" class="form-control"
											name="proj_numofperson">
									</div>
									<div class="col-sm-1">
										<label class="control-label">명</label>
									</div>
									<div class="col-sm-2">
										<button type="button" class="btn btn-default" id="insert">추가</button>
										<button type='button' class='btn btn-default' id='delete'>삭제</button>
									</div>
								</div>

								<div id="add-team-2"></div>
								<div id="add-team-3"></div>
								<div id="add-team-4"></div>
								<div id="add-team-5"></div>



								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label" for="date">프로젝트
										모집 마감일</label>
									<div class="col-sm-3">
										<input class="form-control" id="proj_regenddate"
											name="proj_regenddate" placeholder="MM/DD/YYYY" type="text" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로젝트 운영
										기간</label> <label class="col-sm-2 control-label" for="date">프로젝트
										예정 시작일</label>
									<div class="col-sm-3">
										<input class="form-control" id="proj_startdate"
											name="proj_startdate" placeholder="MM/DD/YYYY" type="text" />
									</div>
									<label class="col-sm-1 col-sm-1 control-label" for="date">예상
										기간</label>
									<div class="col-sm-2">
										<input class="form-control" name="proj_period" type="text" />
									</div>
									<div class="col-sm-1 col-sm-1 control-label">
										<label>일</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">함께할 사람</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" readonly="readonly">
									</div>
									<div class="col-sm-2">
										<button type="button" class="btn btn-default"
											data-toggle="modal" data-target="#searchModal">검색</button>
									</div>
									<!-- Modal for search -->
									<div class="modal fade" id="searchModal" data-backdrop="static">
										<!--  fade효과 및 data-backdrop 으로 닫기버튼시만 닫히도록 적용 -->
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h3>&nbsp;&nbsp;&nbsp;함께할 사람</h3>
												</div>
												
												<!-- custom.css 를 이용하여 아이콘 내부에 입력하도록 설정함 -->
												<div class="modal-body">
														<div class="inner-addon left-addon">
															<h5 class="col-sm-4"> 이름 또는 닉네임 검색</h5>
															<i class="glyphicon glyphicon-search"></i>
															<input class="col-sm-8" type="text" name="coworker" placeholder="프로젝트를 함께할 PortIT 사용자를 검색하세요." aria-describedby="inner-addon">
														</div>
														<p>&nbsp;</p>
														<h4>검색 결과</h4>
												</div>
												
												<div class="modal-footer">
													<button class="btn btn-primary" data-dismiss="modal">닫기</button>
												</div>

										</div>
									</div>
								</div>
						</div>
 
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label">소개 이미지 및
								동영상</label>
							<div class="col-sm-10">
								<span class="col-sm-12 btn btn-default btn-file"><input
									type="file"></span>
							</div>
						</div>
						<div class="form-group" style="text-align: center;">
							<button type="submit" class="btn btn-success">등록하기</button>
							<button type="button" class="btn btn-danger">취소하기</button>
						</div>
						</form>
					</div>
				</div>
				<!-- col-lg-12-->
				</div>
				<!-- /row -->


			</section>
			<!-- /wrapper -->
		</section>
		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2017 - PORTIT<a href="mypage_proj_reg.html" class="go-top"> <i
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
	<!-- datepicker script -->
	<script>
		$(document)
				.ready(
						function() {
							var proj_regenddate_input = $('input[name="proj_regenddate"]'); //our date input has the name "date"
							var proj_startdate_input = $('input[name="proj_startdate"]'); //our date input has the name "date"
							var container = $('.bootstrap-iso form').length > 0 ? $(
									'.bootstrap-iso form').parent()
									: "body";
							var options = {
								format : 'mm/dd/yyyy',
								container : container,
								todayHighlight : true,
								autoclose : true,
							};
							proj_regenddate_input.datepicker(options);
							proj_startdate_input.datepicker(options);
						})
	</script>
</body>
</html>
