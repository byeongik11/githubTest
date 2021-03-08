<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/menu_top.jsp" %>   
<%
	String id = null;
	if(session.getAttribute("id")!=null) {
		id=((String)session.getAttribute("id")).trim();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 메인 페이지</title>
<style type="text/css">

</style>
</head>
<body>
	<% if(id!=null) {
	%>
	<h3><%=id %>로 로그인하셨습니다.</h3>
	<% if(id!=null && id.equals("admin")) {
	%>
	<a href="memberList.me" class="aCenter">관리자모드 접속(회원 목록 보기)</a>
<% 	}
	}
 %>
</body>
</html>