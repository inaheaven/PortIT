<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.*" %>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html; charset=EUC-KR" %>
<jsp:useBean id="dao" class="portit.model.dao.BookmarkDao"></jsp:useBean>
<% 

	String bm_id=request.getParameter("bm_id");
	dao.deleteBookmark(Integer.parseInt(bm_id));
	response.sendRedirect("Bookmark.jsp");
	
%>