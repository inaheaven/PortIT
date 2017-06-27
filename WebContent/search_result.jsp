<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script>
$(document).ready(function() {
	$("#append_search").click(function() {
		alert("11");
	});
});

</script>
<body>

	<table class="table table-striped table-advance table-hover" style="text-align: center">
		<hr>
		<h4>
			<i class="fa fa-angle-right"></i> �˻� ���
		</h4>
		<thead>
			<tr>
				<th style="text-align: center"><i class="fa fa-bullhorn"></i> �����ʻ���</th>
				<th style="text-align: center"><i class="fa fa-bullhorn"></i> �̸�</th>
				<th style="text-align: center" class="hidden-phone"><i class="fa fa-question-circle"></i>
					�г���</th>
				<th style="text-align: center"><i class="fa fa-bookmark"></i> ����� �߰�</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty list}">
				<c:forEach items="${list}" var="record">
					<tr>
						<td></td>
						<td>${record.prof_name }</td>
						<td>${record.prof_nick }</td>
						<td>
						<button type="button" class="btn btn-primary btn-xs" id="append_search">�߰� 
						</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<br>

</body>
</html>