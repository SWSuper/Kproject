<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>본격! 게시판 - 게시글 리스트</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
	 
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>게시글 조회</h1>
	<table class=table>
		<tr>
			<th>번호</th>
			<td>${article.board_seq}</td>
			<td>작성자</td>
			<td>${article.writer}</td>
			<th>ip</th>
            <td>${article.regIp}</td>
			<td>날짜</td>
			<td>${article.regDate}</td>
			<td>조회수</td>
			<td>${article.count}</td>
		</tr>
		<tr>
			<td colspan="2">제목</td>
			<td colspan="6">${article.title}</td>
		</tr>
		<tr>
			<td colspan="2">내용</td>
			<td colspan="6">${article.content}</td>
		</tr>
	</table>
	<a href="delete.do?board_seq=${article.board_seq}">게시글삭제</a>
	<a href="index.jsp">목록으로</a>
</body>
</html>