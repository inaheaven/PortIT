<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="portit.model.dao.*"%>
<%@ page import="portit.model.dto.*"%>
<link href="assets/css/profpfproj.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

<jsp:useBean id="portfolioDao" class="portit.model.dao.PortfolioDao" />
<jsp:useBean id="portfolio" class="portit.model.dto.Portfolio" />
<%
	request.setCharacterEncoding("UTF-8");
	portfolio = portfolioDao.selectOne(Integer.parseInt(request.getParameter("id")));
%>
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt pfreg">
				<!-- BASIC FORM ELEMENTS -->
				<div class="pfregForm">		
					<h3 class="formTitle text-center">포트폴리오 수정</h3>
					<form class="form-horizontal style-form" autocomplete="off" method="post" action="/modify" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-md-3 control-label">제목</label>
							<div class="col-md-9">
								<input type="text" name="pf_title" class="form-control" required="required" value="${portfolio.pf_title}">
								<span class="help-block">반드시 입력하여야 합니다.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">작업 기간</label> 
							<label class="col-md-1 control-label" for="date">시작일</label>
							<div class="col-md-3">
								<input class="form-control datepicker" name="pf_startdate" type="date" value="${portfolio.pf_startdate}" /> 
							</div>
							<div class="col-md-1"></div>
							<label class="col-md-1 control-label" for="date">종료일</label>
							<div class="col-md-3">
								<input class="form-control datepicker" name="pf_enddate" type="date" value="${portfolio.pf_enddate}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">내용</label>
							<div class="col-md-9">
								<textarea class="form-control" name="pf_intro" placeholder="프로젝트 주제, 목적 등 자세한 내용을 작성하세요.(2000byte 이내)" rows="10">${portfolio.pf_intro}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">개발 환경</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_tags_env" placeholder="ex) C, JAVA, Python 등" value="${portfolio.pf_tags_env}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">개발 언어</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_tags_language" placeholder="ex) C, JAVA, Python 등" value="${portfolio.pf_tags_language}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">개발 도구</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="pf_tags_tool" placeholder="ex) Eclipse, Visual Studio2013 등" value="${portfolio.pf_tags_tool}">
								<span class="help-block">작업에 사용된 개발 환경들과 도구들을 적어주세요.</span>
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
								<input type="text" class="form-control" name="pf_tags_field" placeholder="ex) 기획, 설계, 프론트, 백엔드 등" value="${portfolio.pf_tags_field}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">URL</label>
							<div class="col-md-9">
								<input type="text" name="pf_url" class="form-control" placeholder="ex) github URL" value="${portfolio.pf_url}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">함께한 사람</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" class="form-control" name="pf_coworker" value="${portfolio.pf_coworkers}">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">검색</button>
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
										<input type="file" id="fileUpload" name="media[]" multiple="multiple" />
										<span class="help-block">최대 9개의 파일을 선택할 수 있습니다.</span>
									</div>
									<ul id="fileList">
										<%
										for(int i = 0; i < portfolio.getPf_mediae().size(); i++) {
											Media media = portfolio.getPf_mediae().get(i);
											String[] fn = media.getMl_path().split(File.separator);
											String filename = fn[fn.length-1];
										%>
										<li>${filename} <a id="mediaRemove" style="cursor:pointer"><i class="fa fa-times"></i></a></li>
										<%
										}
										%>
									</ul>
								</div>
							</div>
						</div>
						<div class="form-group text-center buttonDiv" >
							<input type="hidden" name="mem_id" value="<%= session.getAttribute("mem_id") %>" />
							<input type="hidden" name="type" value="portfolio" />
							<button type="submit" class="btn common">수정하기</button>&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn cancel" onclick="javascript:history.back()">취소하기</button>
						</div>
					</form>
				</div>
				<!-- /row -->

				</div>
			</section>
			<!-- /wrapper -->
		</section>
