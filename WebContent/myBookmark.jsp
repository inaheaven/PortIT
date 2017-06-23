<%@page import="portit.model.dto.Bookmark"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*" %>
<%@page import="javax.naming.*" %>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/bookmark.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

      <section id="main-content">
          <section class="wrapper site-min-height">
          <div class="col-md-12 col-sm-12 col-xs-12 mt bmlist">
          	<h3 class="formTitle text-center">북마크</h3>	
          	<div class="bmlist_box clearfix">
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
		</section><!--/wrapper -->
      </section><!-- /MAIN CONTENT -->
