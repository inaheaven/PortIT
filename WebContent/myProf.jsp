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
<link href="assets/css/profpfproj.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script src="assets/js/search.js"></script>
<script language="javascript">
//skill 추가
function addSkill() {
	var skill = document.getElementById('skill').value;
	var score = document.getElementById('score').value;
	
	if(Number(score)>Number("5")){
		alert("숫자의 범위를 확인해주세요.");
		document.getElementById('score').focus();
		return;
	}
	
	addSkillIndex++;
	
	var newSpan = document.createElement("span"); 
	
	newSpan.setAttribute("id","item_" + addSkillIndex);
	
	//상태바 길이 조정.
	var barSize =0;
	if(Number(score)==Number("1")){
		barSize=20;
	}else if(Number(score)==Number("2")){
		barSize=40;
	}else if(Number(score)==Number("3")){
		barSize=60;
	}else if(Number(score)==Number("4")){
		barSize=80;
	}else if(Number(score)==Number("5")){
		barSize=100;
	}
	
	//상태바 추가
	var idx ="";
		
	idx+="<div class='col-md-3 skill'>"+skill+"</div>";
	idx+="<div class='col-md-7'>";
		idx+="<div class='progress'>";
			idx+="<div class='progress-bar' role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='100' style='width:"+ barSize+"%''>";
				idx+="<span class='sr-only'>45% Complete</span>";
				idx+="<span class='sr-only'>45% Complete</span>";
			idx+="</div>";			
		idx+="</div>";
	idx+="</div>";
	idx+="<div class='col-md-2'>";
		idx+="<button class='btn common' type='button' onclick='fnDeleteItem("+ addSkillIndex +")'>삭제</button";
	idx+="</div>";
	idx+="</br>";
	
	newSpan.innerHTML = idx;
	
	var itemList = document.getElementById("skillList");
	itemList.appendChild(newSpan);			
}
//상태바 삭제 
function fnDeleteItem(idx){
	var item = document.getElementById("item_" + idx);
	
	if(item != null){
		item.parentNode.removeChild(item);
	}
	
}
function frmCheck(){
  var frm = document.form;
  
  for( var i = 0; i <= frm.elements.length - 1; i++ ){
     if( frm.elements[i].name == "addText" )
     {
         if( !frm.elements[i].value ){
             alert("텍스트박스에 값을 입력하세요!");
                 frm.elements[i].focus();
	 return;
          }
      }
   }
 }
 
// sns url 붙이기
function fnSelectSnsInfo(snsName){
	document.getElementById("snsText").value=snsName;
	document.getElementById("snsText").focus();	
}

</script>

<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script>
	
	$(document).ready(function() {
				
		$("#snsAdd").click(function(){
			var snsUrl = $("#snsText").val();
			var snsHtml = "<tr>"+
							"<td class='col-md-3'></td>"+
							"<td class='col-md-7' style='padding-left: 30px'>"+snsUrl+"</td>"+
							"<td class='col-md-2'><button type='button' class='btn common'>삭제</button></td>"+
						"</tr>";
			$("#snsList table").append(snsHtml);
		});
	
		
		$("#snsList table").on("click", "button", function(){
			$(this).closest("tr").remove();
		});
		
	});
</script>
<body>
	<section id="container">
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
				<div class="col-md-12 mt profreg">					
					<div class="profregForm">
						<h3 class="formTitle text-center">프로필 등록</h3>	
						<form action="#" method="post" class="form-horizontal style-form">
							<div class="form-group">
								<label class="col-md-3 control-label">프로필 사진</label>
								<div class="col-md-9">
									<input type="file" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">배경 사진</label>
								<div class="col-md-9">
									<input type="file" class="form-control">
								</div>
					
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">이름</label>
								<div class="col-md-9">
									<input class="form-control" id="name" type="text"
										placeholder="한글로 입력하세요.">						
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">닉네임</label>
								<div class="col-md-9">
									<input class="form-control" id="nickname" type="text"
										placeholder="영문으로 입력하세요. 이 닉네임은 url로도 쓰입니다.(중복 불가)">									
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">소개</label>
								<div class="col-md-9">
									<textarea class="form-control" rows="5" name="contents"
										placeholder="2000byte 이내로 입력하세요."></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">관심 언어</label>
								<div class="col-md-9">
									<input class="form-control" id="disabledInput" type="text"
										placeholder="ex) C, JAVA, Python 등">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">관심 툴</label>
								<div class="col-md-9">
									<input class="form-control" id="disabledInput" type="text"
										placeholder="ex) Eclipse, Visual Studio2013, Bootstrap 등">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">관심 분야</label>
								<div class="col-md-9">
									<input class="form-control" id="disabledInput" type="text"
										placeholder="ex) 백엔드 개발, 프론트엔드 개발, 서버 개발, 디자이너, 기획 등">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">Link</label>
								<div class="col-md-9">									
									<div class="col-md-3">
										<select class="form-control" name="SNS" id="SNS" onchange="fnSelectSnsInfo(this.value)">
											<option value="">SNS선택</option>
											<option value="www.facebook.com/">FaceBook</option>
											<option value="WWW.github.com/">Github</option>
											<option value="http://www.">Blog</option>
										</select>
									</div>
									<div class="col-md-7">										
										<input type="text" class="form-control" id ="snsText" name="snsText"/>
									</div>
									<div class="col-md-2">
										<button type="button" class="btn common" id="snsAdd">추가</button>
									</div>
									<br><br>
									<div id="snsList">
										<table class="col-md-12">
										
										</table>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">Skill</label>
								<div class="col-md-9">
									<div class="col-md-3"><input class="form-control" id="skill" type="text" placeholder="기술명"></div>
									<div class="col-md-7"><input class="form-control" id="score" type="text" placeholder="숫자 1~5까지 입력하세요"></div>
									<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill()">추가</button></div>
									<br><br>
									<div id="skillList">
									</div>
								</div>
							</div>
							
							<div class="form-group text-center buttonDiv">
								<button type="submit" class="btn common">등록하기</button>&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn cancel" onclick="">수정하기</button>
							</div>							
						</form>
					</div>
				</div>
			</section><!--/wrapper -->
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


 	</script>

</body>
</html>
