<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="portit.model.dto.*"%>
<link href="assets/css/profpfproj.css" rel="stylesheet">
<%--sidenavbar start--%>
<jsp:include page="my.jsp"></jsp:include>
<%--sidenavbar end--%>

<%
	request.setCharacterEncoding("UTF-8");
	Portfolio portfolio = (Portfolio) request.getAttribute("portfolio");
%>
<section id="main-content">
	<section class="wrapper site-min-height">
		<div class="col-md-12 col-sm-12 col-xs-12 mt pfreg">
			<!-- BASIC FORM ELELEMNTS -->
			<div class="pfregForm">
				<h3 class="formTitle text-center">포트폴리오 수정</h3>
				<form class="form-horizontal style-form" method="post"
					action="/post?type=portfolio" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-md-3 control-label">포트폴리오 제목</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="pf_title" value="${portfolio.pf_title}"
								required="required" /> <span class="help-block">반드시
								입력하여야 합니다.</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">포트폴리오 제작 기간</label> <label
							class="col-md-1 control-label" for="date">시작일</label>
						<div class="col-md-3">
							<input class="form-control" id="start_date" name="pf_start_date" value="${portfolio.pf_startdate}"
								type="date" />
						</div>
						<div class="col-md-1"></div>
						<label class="col-md-1 control-label" for="date">마감일</label>
						<div class="col-md-3">
							<input class="form-control" id="end_date" name="pf_end_date" value="${portfolio.pf_enddate}"
								type="date" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">포트폴리오 내용</label>
						<div class="col-md-9">
							<textarea class="form-control" name="intro"
								placeholder="프로젝트 주제 , 목적 등 자세한 내용을 작성하세요.(2000byte 이내)"
								rows="10">${portfolio.pf_intro}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">개발 언어</label>
						<div class="col-md-9">
							<c:choose>
							<c:when test="${!empty portfolio.pf_tags_language}">
							<input class="form-control tagInput" id="pf_language1" type="text" name="pf_tags_lang" value="${portfolio.pf_tags_language[0]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_language2" type="text" name="pf_tags_lang" value="${portfolio.pf_tags_language[1]}">&nbsp;,&nbsp; 
							<input class="form-control tagInput" id="pf_language3" type="text" name="pf_tags_lang" value="${portfolio.pf_tags_language[2]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_language4" type="text" name="pf_tags_lang" value="${portfolio.pf_tags_language[3]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_language5" type="text" name="pf_tags_lang" value="${portfolio.pf_tags_language[4]}">
							</c:when>
							<c:otherwise>
							<input class="form-control tagInput" id="pf_language1" type="text" name="pf_tags_lang">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_language2" type="text" name="pf_tags_lang">&nbsp;,&nbsp; 
							<input class="form-control tagInput" id="pf_language3" type="text" name="pf_tags_lang">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_language4" type="text" name="pf_tags_lang">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_language5" type="text" name="pf_tags_lang">
							</c:otherwise>
							</c:choose>
							<span class="help-block">* 태그로 작성됩니다. (예시 : C, JAVA, Python 등 )</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">개발 도구</label>
						<div class="col-md-9">
							<c:choose>
							<c:when test="${!empty portfolio.pf_tags_tool}">
							<input class="form-control tagInput" id="pf_tool1" type="text" name="pf_tags_tool" value="${portfolio.pf_tags_tool[0]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_tool2" type="text" name="pf_tags_tool" value="${portfolio.pf_tags_tool[1]}">&nbsp;,&nbsp; 
							<input class="form-control tagInput" id="pf_tool3" type="text" name="pf_tags_tool" value="${portfolio.pf_tags_tool[2]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_tool4" type="text" name="pf_tags_tool" value="${portfolio.pf_tags_tool[3]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_tool5" type="text" name="pf_tags_tool" value="${portfolio.pf_tags_tool[4]}">
							</c:when>
							<c:otherwise>
							<input class="form-control tagInput" id="pf_tool1" type="text" name="pf_tags_tool">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_tool2" type="text" name="pf_tags_tool">&nbsp;,&nbsp; 
							<input class="form-control tagInput" id="pf_tool3" type="text" name="pf_tags_tool">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_tool4" type="text" name="pf_tags_tool">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_tool5" type="text" name="pf_tags_tool">
							</c:otherwise>
							</c:choose>
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
							<c:choose>
							<c:when test="${!empty portfolio.pf_tags_field}">
							<input class="form-control tagInput" id="pf_field1" type="text" name="pf_tags_field" value="${portfolio.pf_tags_field[0]}">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_field2" type="text" name="pf_tags_field" value="${portfolio.pf_tags_field[1]}">&nbsp;,&nbsp; 
							<input class="form-control tagInput" id="pf_field3" type="text" name="pf_tags_field" value="${portfolio.pf_tags_field[2]}">
							</c:when>
							<c:otherwise>
							<input class="form-control tagInput" id="pf_field1" type="text" name="pf_tags_field">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="pf_field2" type="text" name="pf_tags_field">&nbsp;,&nbsp; 
							<input class="form-control tagInput" id="pf_field3" type="text" name="pf_tags_field">
							</c:otherwise>
							</c:choose>
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
						<div class="col-md-7">
							<input type="text" class="form-control" readonly="readonly">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default">검색</button>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">이미지 및 동영상</label>
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


		</div>
	</section>
	<!-- /wrapper -->
</section>