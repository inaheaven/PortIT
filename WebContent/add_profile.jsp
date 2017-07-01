<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="portit.model.dao.ProfileDao" %>
<%@ page import="portit.model.dto.Profile" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="dao" class="portit.model.dao.ProfileDao" ></jsp:useBean>
<jsp:useBean id="dto" class="portit.model.dto.Profile" ></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%
	dao.addprofile(dto);
%>