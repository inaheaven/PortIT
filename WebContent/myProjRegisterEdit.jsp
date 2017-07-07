<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>PORTIT - MYPAGE</title>


<link href="assets/css/profpfproj.css" rel="stylesheet">
<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/detailpage.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script>
	var i = 1;
	$(document)
			.ready(
					function() {

						$("#insert")
								.click(
										function() {
											if (i < 5) {
												var $cloneRow = $(this)
														.parent().parent()
														.clone(true);

												$("#add-team-2")
														.append(
																"<label class='col-md-3 control-label'>모집 분야</label>");
												$("#add-team-2").append(
														$cloneRow);
												i++;
											} else {
												alert("모집 분야수를 5개 이내로 제한합니다.");
											}
										});

						$("#delete").click(function() {
							if (i > 1) {
								$(this).parent().parent().prev().remove();
								$(this).parent().parent().remove();
								i--;
							} else {
								alert("모집 분야수를 1개 이상으로 제한합니다.")
							}
						});

						$('#btnSave')
								.click(
										function() {
											document
													.getElementById("final_result").value = prof_arr;
											document
													.getElementById("final_result_id").value = prof_id_arr;

										})

					});
	var prof_arr = [];
	var prof_id_arr = [];
	function append_result(btn, name, nick, id) {
		prof_name = name;
		prof_nick = nick;
		prof_id = id;

		var table_row = btn.parentNode.parentNode;
		var final_table = document.getElementById("final_table");
		var row = final_table.insertRow(1);
		row.setAttribute("align", "center");
		row.setAttribute("id", "under" + prof_id);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);

		cell1.append(prof_name);
		cell2.append(prof_nick);

		var delete_button = document.createElement("button");
		delete_button.setAttribute("class", "btn btn-danger btn-xs");
		delete_button.setAttribute("id", "delete_search");
		delete_button.setAttribute("onclick",
				"delete_result(this, this.prof_id, this.prof_name)");
		delete_button.innerHTML = "삭제";

		var new_table_cell3 = delete_button.cloneNode(true);
		cell3.appendChild(new_table_cell3);

		prof_arr.push(this.prof_name + "(" + this.prof_nick + ")");
		prof_id_arr.push(this.prof_id);

		table_row.parentNode.removeChild(table_row);
	}

	function delete_result(btn, prof_id, prof_name) {
		var row = btn.parentNode.parentNode;
		row.parentNode.removeChild(row);
		prof_arr.pop(prof_name + "(" + prof_nick + ")");
		prof_id_arr.pop(prof_id);
	}

	var httpRequest = null;

	function coworker_Search() {
		httpRequest = new XMLHttpRequest();
		var name = document.getElementById("coworker_search").value;
		var url = "coworker_search";
		var param = "name=" + name;

		httpRequest.open("POST", url, true);
		httpRequest.onreadystatechange = callback; //null값 일단 제외시켜놓음
		httpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=utf-8");
		httpRequest.send(param);
	}

	function callback() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status = 200) {
				var div = document.getElementById("searchResult");
				div.innerHTML = httpRequest.responseText;
			} else {
				alert(httpRequest.status);
			}
		}
	}
</script>
</head>
<body>
	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<jsp:include page="header.jsp"></jsp:include>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

		<%--sidenavbar start--%>
		<jsp:include page="my.jsp"></jsp:include>
		<%--sidenavbar end--%>



<%
	String login_id=request.getParameter("mem_id");
%>

		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt projreg">
					<!-- BASIC FORM ELELEMNTS -->
					<div class="projregForm">

						<h3 class="formTitle text-center">프로젝트 등록<%=login_id%></h3>
						<form class="form-horizontal style-form" method="post"
							action="project_update_save">
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 제목</label>
								<div class="col-md-9">
									<c:forEach var="result" items="${list}">
										<input type="text" class="form-control" name="proj_title"
											value="${result.proj_title}" required="true">
									</c:forEach>
								</div>
							</div>
							<div class="form-group">

								<label class="col-md-3 control-label">프로젝트 설명</label>
								<div class="col-md-9">
									<c:forEach var="result" items="${list}">
										<textarea class="form-control" name="proj_intro"
											placeholder="프로젝트 주제 , 목적등 자세한 설명을 작성하세요" rows="10"
											required="true">${result.proj_intro}</textarea>
									</c:forEach>
								</div>

							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 개발 환경</label>

								<div class="col-md-9">
									<c:forEach begin="0" end="${env_list.size()-1}" var="i">
										<input type="text" class="form-control" name="proj_env"
											value="${env_list[i].proj_env_list[i]}"
											placeholder="ex) windows7, oracle DB 같은 실행 환경과 서버 환경 "
											required="true">
									</c:forEach>
								</div>

							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 개발 언어</label>
								<div class="col-md-9">
									<c:forEach begin="0" end="${language_list.size()-1}" var="i">
										<input type="text" class="form-control" name="proj_language"
											value="${language_list[i].proj_lang_list[i]}"
											placeholder="ex) C, JAVA, Python 등" required="true">
									</c:forEach>
								</div>
							</div>
							<div class="form-group">

								<label class="col-md-3 control-label">프로젝트 개발 도구</label>
								<div class="col-md-9">
									<c:forEach begin="0" end="${tool_list.size()-1}" var="i">
										<input type="text" class="form-control" name="proj_tool"
											value="${tool_list[i].proj_tool_list[i]}"
											placeholder="ex) Eclipse, Visual Studio2013 등"
											required="true">
									</c:forEach>
								</div>
							</div>

							<div class="form-group" id="add-team">
								<label class="col-md-3 control-label">모집 분야</label>
								<div class="col-md-9">
									<div class="col-md-5" id="add-team-input">
										<c:forEach begin="0" end="${field_list.size()-1}" var="i">
											<input type="text" class="form-control" name="proj_field"
												value="${field_list[i].proj_field_list[i]}"
												placeholder="ex) 기획, 설계, 프론트, 백엔드 등" required="true">
										</c:forEach>
									</div>
									<div class="col-md-2">
										<label class="control-label">인원 수</label>
									</div>
									<div class="col-md-2">
										<c:forEach begin="0" end="${field_list.size()-1}" var="i">

											<input type="text" class="form-control"
												value="${list[0].proj_numofperson[i]}"
												name="proj_numofperson" required="true">
										</c:forEach>

									</div>
									<div class="col-md-3">
										<button type="button" class="btn btn-default" id="insert">추가</button>
										<button type='button' class='btn btn-default' id='delete'>삭제</button>
									</div>
								</div>
								<div id="add-team-2"></div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label" for="date">프로젝트 모집
									마감일</label>
								<div class="col-md-9">
									<c:forEach var="result" items="${list}">

										<input class="form-control" id="proj_regenddate"
											value="${result.proj_regenddate}" name="proj_regenddate"
											placeholder="MM/DD/YYYY" type="date" required="true" />
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 운영 기간</label>
								<div class="col-md-9">
									<label class="col-md-2 control-label" for="date"> 예정
										시작일</label>
									<div class="col-md-4">
										<c:forEach var="result" items="${list}">
											<input class="form-control" id="proj_startdate"
												value="${result.proj_startdate}" name="proj_startdate"
												placeholder="MM/DD/YYYY" type="date" required="true" />
										</c:forEach>
									</div>
									<label class="col-md-3 control-label" for="date">예상 기간</label>
									<div class="col-md-2">
										<c:forEach var="result" items="${list}">

											<input class="form-control" name="proj_period" type="text"
												value="${result.proj_period}" required="true" />
										</c:forEach>
									</div>
									<div class="col-md-1 control-label">
										<label>일</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">함께할 사람</label>
								<div class="col-sm-7">
									<c:forEach begin="0" end="${prof_list.size()-1}" var="i">
										<input type="text" class="form-control" readonly="readonly"
											value="${prof_list[0].prof_name_list[i]}(${prof_list[0].prof_nick_list[i]})"
											required="true" id="final_result">
									</c:forEach>
									<c:forEach begin="0" end="${prof_list.size()-1}" var="i">
										<input type="text" class="form-control" name="final_result_id"
											value="${prof_list[0].prof_id_list[i]}" id="final_result_id">
									</c:forEach>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-default" id="hello"
										data-toggle="modal" data-target="#searchModal">추가 및
										수정</button>
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
												<div class="inner-addon left-addon col-sm-10">
													<span class="glyphicon glyphicon-search"></span> <input
														type="text" class="form-control" name="coworker"
														id="coworker_search"
														placeholder="프로젝트를 함께할 PortIT 사용자를 검색하세요." />
												</div>
												<button type="button" class="btn btn-default"
													onclick="coworker_Search();">검색</button>
												<p>&nbsp;&nbsp;&nbsp;</p>

												<div id="searchResult"></div>

												<div id="finalResult">
													<table
														class="table table-striped table-advance table-hover"
														id="final_table" style="text-align: center">
														<hr>
														<h4>
															<i class="fa fa-angle-right"></i> 함께할 사람
														</h4>
														<thead>
															<tr>
																<th style="text-align: center"><i
																	class="fa fa-bullhorn"></i> 이름</th>
																<th style="text-align: center" class="hidden-phone"><i
																	class="fa fa-question-circle"></i> 닉네임</th>
																<th style="text-align: center"><i
																	class="fa fa-bookmark"></i> 사용자 삭제</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-success" id="btnSave"
													data-dismiss="modal">저장</button>
												<button type="button" class="btn btn-danger"
													data-dismiss="modal">취소</button>
											</div>

										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">이미지 및 동영상</label>
								<div class="col-md-9">
									<span class="col-sm-12 btn btn-default btn-file"><input
										type="file"></span>
								</div>

							</div>
							<div></div>
							<div class="form-group text-center buttonDiv">
								<button type="submit" class="btn common">수정하기</button>
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn cancel"
									onclick="location.href='/page?page=myProjList'">취소하기</button>
							</div>
						</form>
					</div>
				</div>


			</section>
			<!-- /wrapper -->
		</section>
		<!--footer start-->
		<jsp:include page="footer.jsp"></jsp:include>
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

	<!-- JS Libraries -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.sticky/1.0.4/jquery.sticky.min.js"></script>
	<!-- Custom JS -->
	<script type="text/javascript">
		// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
		$("#portfolioNavbar").sticky({
			topSpacing : 0,
			zIndex : '50'
		});

		// 스크롤 맨 위로
		$("#BackToTop").on("click", function(e) {
			e.preventDefault();
			$("html,body").animate({
				scrollTop : 0
			}, 500);
		});
	</script>
</body>
</html>