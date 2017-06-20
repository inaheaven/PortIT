<%@page import="portit.model.dto.Bookmark"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*" %>
<%@page import="javax.naming.*" %>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html; charset=EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

		String keyword=request.getParameter("keyword");
		String keytext=request.getParameter("keytext");
		
		List list = dao.getList(keyword, keytext); 
		for(int i =0; i<list.size(); i++){
			Bookmark dto = (Bookmark)list.get(i);
	%>
			
		<tr>
				<td><%=dto.getE_no() %></td>
				<td><%=dto.getE_id() %></td>
				<td><%=dto.getE_name()%></td>
				<td><%=dto.getE_pass()%></td>
				<td><%=dto.getE_age()%></td>
				<td><%=dto.getE_addr()%></td>
				<td><%=dto.getE_dept()%></td>
				<td><a href="updateEmp.jsp?e_no=<%=dto.getE_no()%>">수정</a></td>
				<td><a href="javascript:isDelete(<%=dto.getE_no()%>)">삭제</a></td>
		</tr>
	<%	
		
	
		}
%>
</table>
</body>
</html>