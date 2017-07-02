<%@page import="portit.model.dto.Member"%>
<%@page import="portit.model.dto.Timeline"%>
<%@page import="portit.model.dto.Project"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@page import="portit.model.dto.Developer"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<link href="assets/css/main.css" rel="stylesheet">
<body>
	<section class="container">
		<section class="wrapper site-min-height">
			<div class="col-md-12 col-sm-12 col-xs-12 mt" id="main">
				<div class="recommend">
					<b>추천</b>&nbsp;&nbsp;&nbsp; <a href="#pfRecommend"
						data-toggle="tab">포트폴리오</a> &nbsp;&nbsp; <a href="#memRecommend"
						data-toggle="tab">개발자</a> &nbsp;&nbsp; <a href="#projRecommend"
						data-toggle="tab">모집</a> &nbsp;&nbsp;
				</div>
				<div class="tabs-below tabbable col-md-12">
					<div class="tab-content">

						<!-- 포트폴리오 패널 -->
						<div class="tab-pane active" id="pfRecommend">
							<div class="row mt">

								<c:if test="${port_list.size() != 0 }">
									<c:forEach begin="0" end="3" var="i">
										<!-- 첫번째 포트폴리오 -->
										<div class="col-md-3 mb">
											<div class="portfolio-simple">
												<div class="pfImg"></div>
												<div class="pfInfo">
													<div class="simple-content">
														<div class="pfTag">
															<a href="">#${port_list[i].tag_name}&nbsp;</a>
														</div>
														<div class="pfTitle">
															<a href="">${port_list[i].pf_title} </a>
														</div>
														<div class="pfBottom">
															<span class="pfmemName"><a href="">${port_list[i].prof_name}</a></span>
															<span class="pfLikeCount"><span
																class="fa fa-heart"></span>&nbsp;&nbsp;${port_list[i].pf_like}</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
								<c:if test="${port_list.size() == 0 }">
									검색된 결과가 없습니다.
								</c:if>
							</div>
						</div>
		

						<!-- 개발자 패널 -->
						<div class="tab-pane" id="memRecommend">
							<div class="row mt">
								<c:if test="${mem_list.size() != 0 }">
									<c:forEach begin="0" end="3" var="i">
										<!-- 첫 번째 member-->
										<div class="col-md-3 mb">
											<div class="member-simple">
												<div class="simple-content text-center">
													<img class="memImg img-circle" alt="avatar"
														src="${mem_list[i].prof_img}" />
													<div>
														<div class="memName">
															<a href=""> ${mem_list[i].prof_name}</a>
														</div>
														<div class="memTag">
															<a href=""># ${mem_list[i].tag_name}&nbsp;</a>
														</div>
														<div class="memFollow">
															<span class="fa fa-user"></span>&nbsp;&nbsp; <span
																class="memFollowCount">${mem_list[i].prof_follower}</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
								<c:if test="${mem_list.size() == 0 }">
									검색된 결과가 없습니다.
								</c:if>
							</div>
						</div>

						<!-- 모집 패널 -->
						<div class="tab-pane" id="projRecommend">
							<div class="row mt">
								<div class="col-lg-12">
									<div class="row">
										<c:if test="${proj_list.size() != 0 }">
											<c:forEach begin="0" end="3" var="i">
												<!-- 첫번째 모집 -->
												<div class="col-md-3 mb">
													<div class="project-simple">
														<div class="simple-content text-center">
															<div class="pjTag">
																<a href=""> #${proj_list[i].tag_name}&nbsp;</a>
															</div>
															<div class="pjTitle">
																<a href="">${proj_list[i].proj_title} </a>
															</div>
															<div class="pjInfo">
																<span class="pjField"><a href="">#${proj_list[i].tag_name}</a></span>&nbsp;/&nbsp;
																<span class="pjTo">${proj_list[i].proj_to} 명</span>
															</div>
															<div class="pjRegiEndDate">
																<span>마감일까지</span>&nbsp;&nbsp; <span class="pjDday">D&nbsp;-&nbsp;<span>
																		(${proj_list[i].regdate}-${toDay})</span></span>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:if>
										<c:if test="${proj_list.size() == 0 }">
											검색된 결과가 없습니다.
										</c:if>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>

				<!--/END PORTPOLIO ROW OF PANELS -->
				<hr class="mainLine col-md-12" />

				<!-- Timeline page start -->
				<div class="timeline col-md-12">
					<h3 align="center">
						<b> Timeline </b>
					</h3>


					<c:if test="${time_list.size() != 0 && time_list.size() > 0}">
						<c:forEach begin="0" end="${time_list.size()-1}" var="i">
							<!-- 첫번째 타임라인 -->
							<div class="col-md-12 mt">
								<div class="portfolio-timeline">
									<div class="pfTlType">
										<span class="glyphicon glyphicon-heart"></span> <span
											class="pfTypeText">[멤버이름]님이 내 포트폴리오를 좋아합니다. (mem_id
											받기)</span>
									</div>
									<span class="pfInfo">
										<div>
											<span class="pfTitle"><a href="">${time_list[i].pf_title}</a></span>
											<span class="pfLike"> <span
												class="glyphicon glyphicon-heart"></span> <span
												class="pfLikeCount">234</span>
											</span>
										</div>
										<div class="pfmemName">
											<span class="fa fa-user">${time_list[i].prof_name}</span>&nbsp;&nbsp;
											<span><a href=""></a></span>
										</div>
										<div class="pfTag">
											<a href="">#${time_list[i].tag_name}&nbsp;</a>
										</div>
									</span> <span class="pfImage"> <span><img
											src="${time_list[i].ml_path}" /></span> <span><img
											src="assets/img/instagram.jpg" /></span> <span><img
											src="assets/img/instagram.jpg" /></span>
									</span>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${time_list.size() == 0 }">
						검색된 결과가 없습니다.
					</c:if>

				</div>
			</div>
		</section>
		<!-- Timeline page end -->
	</section>
