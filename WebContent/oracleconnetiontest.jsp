<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>����Ŭ ���� �׽�Ʈ</h1>
<%
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Connection con = DriverManager.getConnection(url,"scott","1111");
		
		if(con !=null){
			out.println("���� ����");
		}
	}
		catch(Exception err){
			System.out.println("���� ���� : " + err);
		}

%>
</body>
</html>