<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/profpfproj.css" rel="stylesheet">
<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>

<script>
	var i=1;
	
	 $(document).ready(function() {
		$("#insert").click(function(){
			if(i==1){
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-2").append("<label class='col-md-3 control-label'>모집 분야</label>");
				$("#add-team-2").append($cloneRow);
				i++;
			}else if(i==2){
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-3").append("<label class='col-md-3 control-label'>모집 분야</label>");
				$("#add-team-3").append($cloneRow);
				i++;
			}else if(i==3){
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-4").append("<label class='col-md-3 control-label'>모집 분야</label>");
				$("#add-team-4").append($cloneRow);
				i++;
			}else if(i==4){
				var $cloneRow = $(this).parent().parent().clone(true);
				$("#add-team-5").append("<label class='col-md-3 control-label'>모집 분야</label>");
				$("#add-team-5").append($cloneRow);
				i++;
			}else{
				alert("모집 분야수를 5개 이내로 제한합니다.");
			}
		});

		$("#delete").click(function(){
			if(i>1){
				$(this).parent().parent().prev().remove();
				$(this).parent().parent().remove();
				i--;
			}else{
				alert("모집 분야수를 1개 이상으로 제한합니다.")
			}
		});
	});

	
</script>
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>


		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt projreg">
					<!-- BASIC FORM ELELEMNTS -->
					<div class="projregForm">
						<h3 class="formTitle text-center">프로젝트 등록</h3>
						<form class="form-horizontal style-form" method="post" action="">
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 제목</label>
								<div class="col-md-9">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 설명</label>
								<div class="col-md-9">
									<textarea class="form-control"
										placeholder="프로젝트 주제 , 목적등 자세한 설명을 작성하세요" rows="10"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 개발 환경</label>
								<div class="col-md-9">
									<input type="text" class="form-control"
										placeholder="ex) windows7, oracle DB 같은 실행 환경과 서버 환경 ">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 개발 언어</label>
								<div class="col-md-9">
									<input type="text" class="form-control"
										placeholder="ex) C, JAVA, Python 등">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 개발 도구</label>
								<div class="col-md-9">
									<input type="text" class="form-control"
										placeholder="ex) Eclipse, Visual Studio2013 등">
								</div>
							</div>



							<div class="form-group" id="add-team">
								<label class="col-md-3 control-label">모집 분야</label>
								<div class="col-md-9">
									<div class="col-md-5">
										<input type="text" class="form-control"
											placeholder="ex) 기획, 설계, 프론트, 백엔드 등">
									</div>
									<div class="col-md-2">
										<label class="control-label">인원 수</label>
									</div>
									<div class="col-md-2">
										<input type="text" class="form-control"/>
									</div>
									<div class="col-md-3">
										<button type="button" class="btn btn-default" id="insert">추가</button>
										<button type='button' class='btn btn-default' id='delete'>삭제</button>
									</div>
								</div>
								
								<div id="add-team-2"></div>
								<div id="add-team-3"></div>
								<div id="add-team-4"></div>
								<div id="add-team-5"></div>
							</div>
							
							



							<div class="form-group">
								<label class="col-md-3 control-label" for="date">프로젝트 모집 마감일</label>
								<div class="col-md-9">
									<input class="form-control" id="reg_date" name="reg_date"
										placeholder="MM/DD/YYY" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">프로젝트 운영 기간</label> 
								<div class="col-md-9">
									<label class="col-md-3 control-label" for="date">프로젝트 예정 시작일</label>
									<div class="col-md-3">
										<input class="form-control" id="start_date" name="start_date"
											placeholder="MM/DD/YYY" type="text" />
									</div>
									<label class="col-md-3 control-label" for="date">예상 	기간</label>
									<div class="col-md-2">
										<input class="form-control" id="end_date" name="end_date"
											type="text" />
									</div>
									<div class="col-md-1 control-label">
										<label>일</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">함께할 사람</label>
								<div class="col-sm-7">
									<input type="text" class="form-control">
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-default">검색</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">이미지 및 동영상</label>
								<div class="col-md-9">
									<input type="file" class="form-control">
								</div>
							</div>
							<div class="form-group text-center buttonDiv" >
								<button type="submit" class="btn common">등록하기</button>&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn cancel" onclick="location.href='/page?page=myProjList'">취소하기</button>
							</div>
						</form>
					</div>
				</div>

			</section>
			<!-- /wrapper -->
		</section>
