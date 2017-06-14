<%@page import="portit.model.dto.Media"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<jsp:useBean id="pf_view" class="portit.model.dao.Portfolio_ViewDao" />

<!-- 
<jsp:useBean id="media1" class="portit.model.dto.Media" />
<jsp:useBean id="tag1" class="portit.model.dto.Tag" />
<jsp:useBean id="portfolio1" class="portit.model.dto.Portfolio" />
<jsp:useBean id="profile1" class="portit.model.dto.Profile" />
 -->
 
<%
	List list = pf_view.getEx();
%>


<%
	for (int i = 0; i < list.size(); i++) {
		Portfolio portfolio = (Portfolio) list.get(i);
		
%>
		pf_id : <%= portfolio.getPf_id() %><br>
		pf_title : <%=portfolio.getPf_title()%><br>	
		pf_intro : <%=portfolio.getPf_intro()%><br>
		pf_regdate : <%=portfolio.getPf_regdate() %><br>
		pf_like : <%=portfolio.getPf_like()%><hr>
			
<%
	}
%>


