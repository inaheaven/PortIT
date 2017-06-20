<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="container">
<section class="wrapper site-min-height">
	<div class="col-md-12 col-sm-12 col-xs-12 mt" id="main">
		<div class="recommend">
			<b>추천</b>&nbsp;&nbsp;&nbsp; <a href="#pfRecommend" data-toggle="tab">포트폴리오</a>
			&nbsp;&nbsp; <a href="#memRecommend" data-toggle="tab">개발자</a>
			&nbsp;&nbsp; <a href="#projRecommend" data-toggle="tab">모집</a>
			&nbsp;&nbsp;
		</div>
		<div class="tabs-below tabbable col-md-12">
			<div class="tab-content">
				<!-- 포트폴리오 패널 -->
				<div class="tab-pane active" id="pfRecommend">
					<div class="row mt">
						<!-- 첫번째 포트폴리오 -->
						<div class="col-md-3 mb">
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
							</div>
						</div>
						<!-- 두번째 포트폴리오 -->
						<div class="col-md-3 mb">
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
							</div>
						</div>

						<!-- 세번째 포트폴리오 -->
						<div class="col-md-3 mb">
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
							</div>
						</div>

						<!-- 네번째 포트폴리오 -->
						<div class="col-md-3 mb">
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
							</div>
						</div>
					</div>
				</div>

				<!-- 개발자 패널 -->
				<div class="tab-pane" id="memRecommend">
					<div class="row mt">
						<!-- 첫 번째 member-->
						<div class="col-md-3 mb">
							<div class="member-simple">
								<div class="simple-content text-center">
									<img class="memImg img-circle" alt="avatar"
										src="assets/img/friends/fr-06.jpg" />
									<div>
										<div class="memName">
											<a href="">멤버 이름</a>
										</div>
										<div class="memTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="memFollow">
											<span class="fa fa-user"></span>&nbsp;&nbsp; <span
												class="memFollowCount">135</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 두 번째 member-->
						<div class="col-md-3 mb">
							<div class="member-simple">
								<div class="simple-content text-center">
									<img class="memImg img-circle" alt="avatar"
										src="assets/img/friends/fr-06.jpg" />
									<div>
										<div class="memName">
											<a href="">멤버 이름</a>
										</div>
										<div class="memTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="memFollow">
											<span class="fa fa-user"></span>&nbsp;&nbsp; <span
												class="memFollowCount">135</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 세 번째 member-->
						<div class="col-md-3 mb">
							<div class="member-simple">
								<div class="simple-content text-center">
									<img class="memImg img-circle" alt="avatar"
										src="assets/img/friends/fr-06.jpg" />
									<div>
										<div class="memName">
											<a href="">멤버 이름</a>
										</div>
										<div class="memTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="memFollow">
											<span class="fa fa-user"></span>&nbsp;&nbsp; <span
												class="memFollowCount">135</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 네 번째 member-->
						<div class="col-md-3 mb">
							<div class="member-simple">
								<div class="simple-content text-center">
									<img class="memImg img-circle" alt="avatar"
										src="assets/img/friends/fr-06.jpg" />
									<div>
										<div class="memName">
											<a href="">멤버 이름</a>
										</div>
										<div class="memTag">
											<a href="">#태그&nbsp;</a>
										</div>
										<div class="memFollow">
											<span class="fa fa-user"></span>&nbsp;&nbsp; <span
												class="memFollowCount">135</span>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

				<!-- 모집 패널 -->
				<div class="tab-pane" id="projRecommend">
					<div class="row mt">
						<div class="col-lg-12">
							<div class="row">

								<!-- 첫번째 모집 -->
								<div class="col-md-3 mb">
									<div class="project-simple">
										<div class="simple-content text-center">
											<div class="pjTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pjTitle">
												<a href="">프로젝트 제목입니다...</a>
											</div>
											<div class="pjInfo">
												<span class="pjField"><a href="">모집 분야</a></span>&nbsp;/&nbsp;
												<span class="pjTo">2 명</span>
											</div>
											<div class="pjRegiEndDate">
												<span>마감일까지</span>&nbsp;&nbsp; <span class="pjDday">D&nbsp;-&nbsp;<span>3</span></span>
											</div>
										</div>
									</div>
								</div>

								<!-- 두번째 모집 -->
								<div class="col-md-3 mb">
									<div class="project-simple">
										<div class="simple-content text-center">
											<div class="pjTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pjTitle">
												<a href="">프로젝트 제목입니다...</a>
											</div>
											<div class="pjInfo">
												<span class="pjField"><a href="">모집 분야</a></span>&nbsp;/&nbsp;
												<span class="pjTo">2 명</span>
											</div>
											<div class="pjRegiEndDate">
												<span>마감일까지</span>&nbsp;&nbsp; <span class="pjDday">D&nbsp;-&nbsp;<span>3</span></span>
											</div>
										</div>
									</div>
								</div>

								<!-- 세번째 모집 -->
								<div class="col-md-3 mb">
									<div class="project-simple">
										<div class="simple-content text-center">
											<div class="pjTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pjTitle">
												<a href="">프로젝트 제목입니다...</a>
											</div>
											<div class="pjInfo">
												<span class="pjField"><a href="">모집 분야</a></span>&nbsp;/&nbsp;
												<span class="pjTo">2 명</span>
											</div>
											<div class="pjRegiEndDate">
												<span>마감일까지</span>&nbsp;&nbsp; <span class="pjDday">D&nbsp;-&nbsp;<span>3</span></span>
											</div>
										</div>
									</div>
								</div>

								<!-- 네번째 모집 -->
								<div class="col-md-3 mb">
									<div class="project-simple">
										<div class="simple-content text-center">
											<div class="pjTag">
												<a href="">#태그&nbsp;</a>
											</div>
											<div class="pjTitle">
												<a href="">프로젝트 제목입니다...</a>
											</div>
											<div class="pjInfo">
												<span class="pjField"><a href="">모집 분야</a></span>&nbsp;/&nbsp;
												<span class="pjTo">2 명</span>
											</div>
											<div class="pjRegiEndDate">
												<span>마감일까지</span>&nbsp;&nbsp; <span class="pjDday">D&nbsp;-&nbsp;<span>3</span></span>
											</div>
										</div>
									</div>
								</div>

							</div>
							<!--/END PORTPOLIO ROW OF PANELS -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<hr class="mainLine col-md-12" />

		<!-- Timeline page start -->
		<div class="timeline col-md-12">
			<h3 align="center">
				<b>Timeline</b>
			</h3>
			<!-- 첫번째 타임라인 -->
			<div class="col-md-12 mt">
				<div class="portfolio-timeline">
					<div class="pfTlType">
						<span class="glyphicon glyphicon-heart"></span> <span
							class="pfTypeText">[멤버이름]님이 내 포트폴리오를 좋아합니다.</span>
					</div>
					<span class="pfInfo">
						<div>
							<span class="pfTitle"><a href="">포트폴리오 제목</a></span> <span
								class="pfLike"> <span class="glyphicon glyphicon-heart"></span>
								<span class="pfLikeCount">234</span>
							</span>
						</div>
						<div class="pfmemName">
							<span class="fa fa-user"></span>&nbsp;&nbsp; <span><a
								href="">게시자이름</a></span>
						</div>
						<div class="pfTag">
							<a href="">#태그&nbsp;</a>
						</div>
					</span> <span class="pfImage"> <span><img
							src="assets/img/instagram.jpg" /></span> <span><img
							src="assets/img/instagram.jpg" /></span> <span><img
							src="assets/img/instagram.jpg" /></span>
					</span>
				</div>
			</div>
			<!-- 두번째 타임라인 -->
			<div class="col-md-12 mt">
				<div class="portfolio-timeline">
					<div class="pfTlType">
						<span class="glyphicon glyphicon-heart"></span> <span
							class="pfTypeText">[멤버이름]님이 내 포트폴리오를 좋아합니다.</span>
					</div>
					<span class="pfInfo">
						<div>
							<span class="pfTitle"><a href="">포트폴리오 제목</a></span> <span
								class="pfLike"> <span class="glyphicon glyphicon-heart"></span>
								<span class="pfLikeCount">234</span>
							</span>
						</div>
						<div class="pfmemName">
							<span class="fa fa-user"></span>&nbsp;&nbsp; <span><a
								href="">게시자이름</a></span>
						</div>
						<div class="pfTag">
							<a href="">#태그&nbsp;</a>
						</div>
					</span> <span class="pfImage"> <span><img
							src="assets/img/instagram.jpg" /></span> <span><img
							src="assets/img/instagram.jpg" /></span> <span><img
							src="assets/img/instagram.jpg" /></span>
					</span>
				</div>
			</div>
		</div>

	</div>
</section>
</section>
<!-- Timeline page end -->
