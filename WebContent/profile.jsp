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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<script src="assets/js/search.js"></script>
<script language="javascript">
	//변수선언.
	var oTbl;
	var index = 0;
	var addSkillIndex = 0;

	//Row 추가 (sns주소 입력할 때)
	function insRow() {
		oTbl = document.getElementById("addTable");
		var oRow = oTbl.insertRow();
		oRow.onmouseover = function() {
			oTbl.clickedRowIndex = this.rowIndex
		}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
		var oCell1 = oRow.insertCell(0);
		var oCell2 = oRow.insertCell(1);
		index++;

		//셀렉트박스 값 가져오기
		var select = document.getElementById("SNS");
		var option_value = document.getElementById("addText").value;

		//삽입될 Form Tag
		var frmTag1 = "";
		frmTag1 += "<input type=text name=addText id=addText"+index+" style=width:350px; height:20px;>";
		oCell1.innerHTML = frmTag1;
		//x버튼(삭제) 삽입
		var frmTag2 = "";
		frmTag2 += "<input type=button value='x' onClick='removeRow()' style='cursor:hand'>";
		oCell2.innerHTML = frmTag2;
		var temp = "addText" + index;
		document.getElementById(temp).value = option_value;
	}

	//Row 삭제
	function removeRow() {
		oTbl.deleteRow(oTbl.clickedRowIndex);

	}
	
	
	//skill 추가
	function addSkill(skill, score) {

		if (Number(score) > Number("5")) {
			alert("숫자의 범위를 확인해주세요.");
			document.getElementById('score').focus();
			return;
		}

		addSkillIndex++;

		var newSpan = document.createElement("span");
		newSpan.setAttribute("id", "item_" + addSkillIndex);

		//상태바 길이 조정.
		var barSize = 0;
		if (Number(score) == Number("1")) {
			barSize = 5;
		} else if (Number(score) == Number("2")) {
			barSize = 10;
		} else if (Number(score) == Number("3")) {
			barSize = 15;
		} else if (Number(score) == Number("4")) {
			barSize = 20;
		} else if (Number(score) == Number("5")) {
			barSize = 25;
		}

		//상태바 추가
		var idx = "";

		idx += "<div class='col-sm-1'>" + skill + "</div>";

		idx += "<div class='col-sm-11'>";
		idx += "<div class='progress progress-striped active'>";
		idx += "<div class='progress-bar' role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='50' style='width:"
				+ barSize + "%''>";
		idx += "<span class='sr-only'>45% Complete</span>";
		idx += "<span class='sr-only'>45% Complete</span>";
		idx += "</div>";
		idx += "<input type='button' value='x' onclick='fnDeleteItem("
				+ addSkillIndex + ")'/>";
		idx += "</div>";
		idx += "</div>";
		idx += "</br>";

		newSpan.innerHTML = idx;

		var itemList = document.getElementById("itemList");
		itemList.appendChild(newSpan);
	}
	//상태바 삭제 
	function fnDeleteItem(idx) {
		var item = document.getElementById("item_" + idx);

		if (item != null) {
			item.parentNode.removeChild(item);
		}

	}
	function frmCheck() {
		var frm = document.form;

		for (var i = 0; i <= frm.elements.length - 1; i++) {
			if (frm.elements[i].name == "addText") {
				if (!frm.elements[i].value) {
					alert("텍스트박스에 값을 입력하세요!");
					frm.elements[i].focus();
					return;
				}
			}
		}
	}

	//상태바 삭제 
	function fnSelectSnsInfo(snsName) {
		document.getElementById("addText").value = snsName;
		document.getElementById("addText").focus();
	}
//-->
</script>
<body>
	<section id="container" action="addprofile.jsp">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<!--logo start-->
		</header>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->

				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="row mt">
					<div class="col-md-12 col-sm-12 col-xs-12 mt">
						<div style="border: 1px solid black">
							<form action="#" method="get" class="form-horizontal style-form">
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">프로필 사진</label>
									<div class="col-sm-10">
										<input type="text" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">배경 사진</label>
									<div class="col-sm-10">
										<input type="text" class="form-control"> <span
											class="help-block"></span>
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">이름</label>
									<div class="col-sm-10">
										<input class="form-control" id="name" type="text"
											value="한글로 입력"
											onclick="if(this.value=='한글로 입력'){this.value=''}">

									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">닉네임</label>
									<div class="col-sm-10">
										<input class="form-control" id="nickname" type="text"
											value="영문으로 입력"
											onclick="if(this.value=='영문으로 입력'){this.value=''}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">소개</label>
									<div class="col-sm-10">
										<textarea rows="4" cols="100" name="contents"
											onclick="if(this.value=='xxxxbyte이내로 입력 바랍니다.'){this.value=''}">xxxxbyte이내로 입력 바랍니다.</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">관심분야</label>
									<div class="col-sm-10">
										<input class="form-control" id="disabledInput" type="text">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">Link</label>
									<div class="col-sm-10">
										<form name="form" method="post">
											<table width="400" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="2" align="left">
														<table width="100%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td height="25">
																	<table id="addTable" width="400" cellspacing="0"
																		cellpadding="0" border="0">
																		<tr>
																			<select name="SNS" id="SNS"
																				onchange="fnSelectSnsInfo(this.value)">
																				<option value="">SNS선택</option>
																				<option value="www.facebook.com/">FaceBook</option>
																				<option value="WWW.github.com/">Github</option>
																				<option value="http://www.">Blog</option>
																			</select>
																			<td><input type="text" id="addText"
																				name="addText" style="width: 350px; height: 20px;" />
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>


																			<td>
																				<button type="button" class="btn btn-theme"
																					style="cursor: hand" onClick="insRow()" value="추가">추가</button>
																			</td>
																		</tr>
																	</table>

																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<table width="400" border="0" cellspacing="0" cellpadding="0">
												<br>
												<tr>
													<td height="10"></td>
												</tr>

											</table>
										</form>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-sm-2 control-label">Skill</label>
									<div class="col-lg-10">
										<input id="skill" type="text" value="Skill을 입력하세요."
											onclick="if(this.value=='Skill을 입력하세요.'){this.value=''}">&nbsp;&nbsp;
										<input id="score" type="text" value="1-5까지 입력하세요."
											onclick="if(this.value=='1-5까지 입력하세요.'){this.value=''}">&nbsp;&nbsp;
										<button type="submit" class="btn btn-theme"
											onclick="addSkill(document.getElementById('skill').value,document.getElementById('score').value)">추가</button>
										<br />
										<div id="itemList" class="row"></div>
									</div>
								</div>

								<div class="form-group" align="center">
									<button type="submit" class="btn btn-theme">등록</button>
									<button type="submit" class="btn btn-theme">수정</button>
								</div>

							</form>
						</div>
					</div>
				</div>

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

		$(function() {
			$('select.styled').customSelect();
		});
	</script>

</body>
</html>
