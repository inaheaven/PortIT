<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/profpfproj.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt pfreg">
				<!-- BASIC FORM ELEMENTS -->
				<div class="pfregForm">		
					<h3 class="formTitle text-center">포트폴리오 등록</h3>
					<form class="form-horizontal style-form" autocomplete="off" method="post" action="/post?type=portfolio" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-md-3 control-label">제목</label>
							<div class="col-md-9">
								<input type="text" name="pf_title" class="form-control" required="required">
								<span class="help-block">반드시 입력하여야 합니다.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">작업 기간</label> 
							<label class="col-md-1 control-label" for="date">시작일</label>
							<div class="col-md-3">
								<input class="form-control datepicker" name="pf_startdate" type="date" /> 
							</div>
							<div class="col-md-1"></div>
							<label class="col-md-1 control-label" for="date">종료일</label>
							<div class="col-md-3">
								<input class="form-control datepicker" name="pf_enddate" type="date" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">내용</label>
							<div class="col-md-9">
								<textarea class="form-control" name="pf_intro" placeholder="프로젝트 주제, 목적 등 자세한 내용을 작성하세요.(2000byte 이내)" rows="10"></textarea>
								<span class="help-block">1000자 이내로 입력해 주세요.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">개발 언어</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_tags_language" placeholder="ex) C, Java, Python 등">
								<span class="help-block">콤마(,)로 구분됩니다.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">개발 도구</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_tags_tool" placeholder="ex) Eclipse, Git, Github 등">
								<span class="help-block">콤마(,)로 구분됩니다.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">수행 인원</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_numofperson">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">담당 업무</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_tags_field" placeholder="ex) 기획, 설계, 프론트, 백엔드 등">
								<span class="help-block">콤마(,)로 구분됩니다.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">URL</label>
							<div class="col-md-9">
								<input type="text" name="pf_url" class="form-control" placeholder="ex) github URL">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">함께한 사람</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" class="form-control" name="pf_coworker">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#coworkers">검색</button>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">이미지</label>
							<div class="col-md-9" id="mediaRows">
								<div id="mediaRow">
									<div class="form-inline">
										<label for="fileUpload" class="btn btn-default">파일 선택</label>
										<input type="file" accept="image/jpeg,image/gif,image/png" id="fileUpload" name="image[]" multiple="multiple" />
										<span class="help-block">최대 9개의 GIF, JPG/JPEG, PNG 파일을 선택할 수 있습니다.</span>
									</div>
									<ul id="fileList"></ul>
								</div>
							</div>
						</div>
						<div class="form-group text-center buttonDiv" >
							<input type="hidden" name="mem_id" value="<%= session.getAttribute("loginId") %>" />
							<input type="hidden" name="type" value="portfolio" />
							<button type="submit" class="btn common">등록하기</button>&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn cancel" onclick="javascript:history.back()">취소하기</button>
						</div>
					</form>
				</div>
				<!-- /row -->
				
				<!-- modal #coworkers -->
				<div class="modal fade" id="coworkers" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">함께한 사람 찾기</h4>
						</div>
						<div class="modal-body">
							<form id="searchUser">
								<div class="input-group">
									<input type="text" class="form-control" name="username">
									<span class="input-group-btn">
										<button type="button" class="btn common">검색</button>
									</span>
								</div>
							</form>
							<hr />
							<ul id="userList"></ul>
							<hr />
							<div id="selectedUser"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn cancel" data-dismiss="modal">취소</button>
							<button type="button" class="btn common" id="confirmUser">확인</button>
						</div>
					</div>
				</div>
			</div><!-- /modal -->

				</div>
			</section>
			<!-- /wrapper -->
		</section>
