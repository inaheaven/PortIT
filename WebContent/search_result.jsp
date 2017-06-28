<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table class="table table-striped table-advance table-hover" style="text-align: center">
		<hr>
		<h4>
			<i class="fa fa-angle-right"></i> �˻� ���
		</h4>
		
		<thead>
			<tr>
				<th style="text-align: center"><i class="fa fa-bullhorn"></i> �̸�</th>
				<th style="text-align: center" class="hidden-phone"><i class="fa fa-question-circle"></i>�г���</th>
				<th style="text-align: center"><i class="fa fa-bookmark"></i> ����� �߰�</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty list}">
				<c:forEach items="${list}" var="result">
					<tr>
						<td style="text-align: center">${result.prof_name }</td>
						<td>${result.prof_nick }</td>
						<td>
						<button type="button" class="btn btn-primary btn-xs" id="append" onclick="append_result(this, '${result.prof_name }', '${result.prof_nick }', '${result.prof_id }')" >�߰� 
						</button> 
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<br>
