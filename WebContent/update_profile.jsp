<%@ page contentType="text/html; charset=EUC-KR"%>
<%
request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="dao" class=portit.model.dao.ProfileDao"></jsp:useBean>
<jsp:useBean id="dto" class="portit.model.dto.Profile"></jsp:useBean>
<jsp:setProperty name="dto" property="*" />

<%
	dao.updateProfile(dto);
%>