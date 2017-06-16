<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--<c:forEach var="i" items="${}">--%>
	<c:set var="i" value="0" />
	<c:if test="${i==0 || (i+1)%4 == 0}">
	<div class="row">
	</c:if>
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