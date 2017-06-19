<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/profpfproj.css" rel="stylesheet">
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt projlist">
					<div class="projlist_top clearfix">
						<button type="button" class="btn common" onclick="location.href='/mypage?page=myProjRegister'">프로젝트 등록하기</button>
					</div>
					<div class="proj_my">
						<p>내가 등록한 프로젝트</p>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">
									<span class="col-sm-3"> 프로젝트 제목 #1 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">수정</button>
										<button type="button" class="btn common">삭제</button>
										<a class="updown collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne"
										aria-expanded="false" aria-controls="collapseOne"> 
											<i class="fa fa-chevron-down"></i>
										</a>
									</span>									
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
									<div class="col-lg-6">
										APPLICANTS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
									</div>
									<div class="col-lg-6">
										COWORKERS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingTwo">
								<h4 class="panel-title">
									<span class="col-sm-3"> 프로젝트 제목 #2 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">수정</button>
										<button type="button" class="btn common">삭제</button>
										<a class="updown collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo"> 
											<i class="fa fa-chevron-down"></i>
										</a>
									</span>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
									<div class="col-lg-6">
										APPLICANTS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
									</div>
									<div class="col-lg-6">
										COWORKERS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingThree">
								<h4 class="panel-title">
									<span class="col-sm-3"> 프로젝트 제목 #3 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">수정</button>
										<button type="button" class="btn common">삭제</button>
										<a class="updown collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseThree"
										aria-expanded="false" aria-controls="collapseThree"> 
											<i class="fa fa-chevron-down"></i>
										</a>
									</span>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
									<div class="col-lg-6">
										APPLICANTS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">수락</button>
										</div>
									</div>
									<div class="col-lg-6">
										COWORKERS
										<hr>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7">지원자 이름</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default">삭제</button>
										</div>
									</div>
								</div>
							</div>
						</div>						
					</div>
					<hr class="line" />
					<div class="proj_apply">
						<p>내가 지원한 프로젝트</p>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">								
									<span class="col-sm-3"> 프로젝트 제목 #4 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">지원 취소</button>
									</span>
								</h4>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">
									<span class="col-sm-3"> 프로젝트 제목 #4 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">지원 취소</button>
									</span>
								</h4>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">
									<span class="col-sm-3"> 프로젝트 제목 #4 </span> 
									<span class="col-sm-6"> 프로젝트 지원 마감일까지 D-3</span>									
									<span class="col-md-3 text-right">
										<button type="button" class="btn common">지원 취소</button>
									</span>
								</h4>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- /wrapper -->
		</section>

