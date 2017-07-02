<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/profpfproj.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp">
		<jsp:param name="active" value="myPfList" />
	</jsp:include>
	<%--sidenavbar end--%>
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt pflist">
					<h3 class="formTitle text-center">내 포트폴리오</h3>	
					<!-- BASIC FORM ELELEMNTS -->
					<div class="pflist_top col-md-12 center clearfix">						
						<span class="pull-left sorting">
							<b>정렬</b>&nbsp; &nbsp;&nbsp;&nbsp;
							<a href="">등록순</a>&nbsp;&nbsp;&nbsp;
							<a href="">인기순</a>
						</span>
						<span class="pull-right">
							<button type="button" class="btn common" onclick="location.href='/page?page=myPfRegister'">포트폴리오 등록하기</button>
						</span>
					</div>
					<div class="pflist_box clearfix">
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg"></div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pfTitle">
												<a href="">포트폴리오 제목</a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href="">멤버 이름</a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover">		
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- portfolio-simple end -->
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg"></div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pfTitle">
												<a href="">포트폴리오 제목</a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href="">멤버 이름</a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover">		
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- portfolio-simple end -->
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg"></div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pfTitle">
												<a href="">포트폴리오 제목</a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href="">멤버 이름</a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover">		
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- portfolio-simple end -->
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg"></div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pfTitle">
												<a href="">포트폴리오 제목</a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href="">멤버 이름</a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover">		
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- portfolio-simple end -->
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg"></div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pfTitle">
												<a href="">포트폴리오 제목</a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href="">멤버 이름</a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover">		
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- portfolio-simple end -->
						<div class="col-md-4 mb"> <!-- 반복 -->
							<div class="image-hover">
								<div class="portfolio-simple">
									<div class="pfImg"></div>
									<div class="pfInfo">
										<div class="simple-content">
											<div class="pfTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pfTitle">
												<a href="">포트폴리오 제목</a>
											</div>
											<div class="pfBottom">
												<span class="pfmemName"><a href="">멤버 이름</a></span> <span
													class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;534</span>
											</div>
										</div>
									</div>
									<div class="top-hover-right">
										<div class="after-hover" >
											<button type="button" class="btn btn-hover">		
												<span class="glyphicon glyphicon-edit"></span>
											</button>
											<button type="button" class="btn btn-hover">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- portfolio-simple end -->
					</div>
				</div>
				<!-- /row -->


			</section>
			<!-- /wrapper -->
		</section>
