<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.*" %>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="dao" class="portit.model.dao.FollowDao"></jsp:useBean>
<% 

	String fw_id=request.getParameter("fw_id");
	dao.deleteFollow(Integer.parseInt(fw_id));
	response.sendRedirect("following.jsp");
	
%>