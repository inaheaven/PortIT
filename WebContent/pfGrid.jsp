<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--<c:forEach var="i" items="${}">--%>
	<c:set var="i" value="0" />
	<c:if test="${i==0 || (i+1)%4 == 0}">
	<div class="row">
	</c:if>
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
	<c:if test="${i==0 || (i+1)%4 == 0}">
	</div>
	</c:if>
	<!-- member-simple end -->
<%--</c:forEach>--%>
<div class="row">
	<c:if></c:if>
	<c:forEach></c:forEach>
	<c:if></c:if>
</div>