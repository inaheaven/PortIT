<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<jsp:useBean id="search" class="portit.model.dao.SearchDao" />

<!-- 통합 검색시 검색되는 포트폴리오 -->
<% 
	String keyword = request.getParameter("search");
	List list = search.searchAll("keyword"); 
%>

<%
	for (int i = 0; i < list.size(); i++) {
		Portfolio portfolio = (Portfolio) list.get(i);
		
%>
		ml_path: <%= portfolio.getMl_path() %>	<br>
		tag_name: <%= portfolio.getTag_name() %> <br>	
		pf_title : <%= portfolio.getPf_title() %> &nbsp;&nbsp;&nbsp;
		pf_like : <%= portfolio.getPf_like() %>	<br>	
		prof_name : <%= portfolio.getProf_name() %>	<br>
<%
	}
%>

