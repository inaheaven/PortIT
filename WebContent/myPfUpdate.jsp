<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="portit.model.dto.Portfolio"%>
<link href="assets/css/profpfproj.css" rel="stylesheet">
<%--sidenavbar start--%>
<jsp:include page="my.jsp"></jsp:include>
<%--sidenavbar end--%>
<%
	request.setCharacterEncoding("UTF-8");
	Portfolio portfolio = (Portfolio)request.getAttribute("portfolio");
%>
<script>
	$(document).ready(function() {

		$('#btnSave').click(function() {
			document.getElementById("final_result").value = prof_arr;
			document.getElementById("final_result_id").value = prof_id_arr;
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

<section id="main-content">
	<section class="wrapper site-min-height">
		<div class="col-md-12 col-sm-12 col-xs-12 mt pfreg">
			<!-- BASIC FORM ELELEMNTS -->
			<div class="pfregForm">
				<h3 class="formTitle text-center">포트폴리오 수정</h3>
				<form class="form-horizontal style-form" method="post"
					action="/edit?type=portfolio" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-md-3 control-label">포트폴리오 제목</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="pf_title" value=${portfolio.pf_title}""
								required="required" /> <span class="help-block">반드시
								입력하여야 합니다.</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">포트폴리오 제작 기간</label> <label
							class="col-md-1 control-label" for="date">시작일</label>
						<div class="col-md-3">
							<input class="form-control" id="start_date" name="pf_startdate" value=${portfolio.pf_startdate}"
								required="required" type="date" /> <span class="help-block">반드시
								입력하여야 합니다.</span>
						</div>
						<div class="col-md-1"></div>
						<label class="col-md-1 control-label" for="date">마감일</label>
						<div class="col-md-3">
							<input class="form-control" id="end_date" name="pf_enddate" value=${portfolio.pf_enddate}"
								required="required" type="date" /> <span class="help-block">반드시
								입력하여야 합니다.</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">포트폴리오 내용</label>
						<div class="col-md-9">
							<textarea class="form-control" name="pf_intro"
								placeholder="프로젝트 주제 , 목적 등 자세한 내용을 작성하세요.(2000byte 이내)"
								rows="10">${portfolio.pf_intro}"</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">개발 언어</label>
						<div class="col-md-9">
							<c:forEach items="${portfolio.pf_tags_language}" var="pf_tag_language" varStatus="status">
								<input class="form-control tagInput" id="pf_language${status.index}" value="${pf_tag_language.tag_name}" type="text" name="pf_tags_lang">&nbsp;&nbsp;
							</c:forEach>
								<span class="help-block">* 태그로 작성됩니다. (예시 : C, JAVA, Python 등 )</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">개발 도구</label>
						<div class="col-md-9">
							<c:forEach items="${portfolio.pf_tags_tool}" var="pf_tag_tool" varStatus="status">
								<input class="form-control tagInput" id="pf_tool${status.index}" value="${pf_tag_tool.tag_name}" type="text" name="pf_tags_tool">&nbsp;&nbsp;
							</c:forEach>
							<span class="help-block">* 태그로 작성됩니다. (예시 : Window7, OracleDB, Eclipse, Visual Studio2013 등)</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">수행 인원</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="pf_numofperson" value="${portfolio.pf_numofperson}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">담당 업무</label>
						<div class="col-md-9">
							<c:forEach items="${portfolio.pf_tags_field}" var="pf_tag_field" varStatus="status">
								<input class="form-control tagInput" id="pf_field${status.index}" value="${pf_tag_field.tag_name}" type="text" name="pf_tags_field">&nbsp;&nbsp;
							</c:forEach>
							<span class="help-block">* 태그로 작성됩니다. (예시 : 백엔드 개발, 프론트엔드 개발, 서버 개발, 디자이너, 기획 등 )</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">URL</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="pf_url" value="${portfolio.pf_url}"
								placeholder="ex) github URL">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">함께한 사람</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" readonly="readonly" id="final_result" value="<c:forEach items="${portfolio.pf_coworker}" var="coworker" varStatus="status"><c:if test="${status.last}">${coworker.prof_name}(${coworker.prof_nick})</c:if>${coworker.prof_name}(${coworker.prof_nick}),</c:forEach>">
							<input type="hidden" class="form-control" name="final_result_id" id="final_result_id" readonly="readonly" value="<c:forEach items="${portfolio.pf_coworker}" var="coworker" varStatus="status"><c:if test="${status.last}">${coworker.mem_id}</c:if>${coworker.mem_id},</c:forEach>">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default" data-toggle="modal"
								data-target="#searchModal">추가 및 수정</button>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">이미지</label>
						<div class="col-md-9">
							<input type="file" name="pf_image"
								accept="image/jpg,image/jpeg,image/png,inage/gif"
								multiple="multiple" class="form-control">
						</div>
					</div>
					<div class="form-group text-center buttonDiv">
						<input type="hidden" name="mem_id"
							value="<%=session.getAttribute("loginId")%>" />
						<button type="submit" class="btn common">등록하기</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn cancel"
							onclick="location.href='/page?page=myPfList'">취소하기</button>
					</div>
				</form>
			</div>
			<!-- /row -->


			<!-- Modal for search -->
			<div class="modal fade" id="searchModal" data-backdrop="static">
				<!--  fade효과 및 data-backdrop 으로 닫기버튼시만 닫히도록 적용 -->
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h3>&nbsp;&nbsp;&nbsp;함께한 사람</h3>
						</div>

						<!-- custom.css 를 이용하여 아이콘 내부에 입력하도록 설정함 -->
						<div class="modal-body">
							<div class="inner-addon left-addon col-sm-10">
								<span class="glyphicon glyphicon-search"></span> <input
									type="text" class="form-control" name="coworker"
									id="coworker_search" placeholder="프로젝트를 함께할 PortIT 사용자를 검색하세요." />
							</div>
							<button type="button" class="btn btn-default"
								onclick="coworker_Search();">검색</button>
							<p>&nbsp;&nbsp;&nbsp;</p>

							<div id="searchResult"></div>

							<div id="finalResult">
								<table class="table table-striped table-advance table-hover"
									id="final_table" style="text-align: center">
									<hr>
									<h4>
										<i class="fa fa-angle-right"></i> 함께한 사람
									</h4>
									<thead>
										<tr>
											<th style="text-align: center"><i class="fa fa-bullhorn"></i>
												이름</th>
											<th style="text-align: center" class="hidden-phone"><i
												class="fa fa-question-circle"></i> 닉네임</th>
											<th style="text-align: center"><i class="fa fa-bookmark"></i>
												사용자 삭제</th>
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
							<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- /modal -->

		</div>
	</section>
	<!-- /wrapper -->
</section>