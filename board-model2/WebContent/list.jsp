<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>본격! 게시판 - 게시글 리스트</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>게시글 리스트</h1>
	<table class="table table-striped">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${articleList}" var="article">
			<tr>
				<td>${article.board_seq}</td>
				<td><a href='count.do?board_seq=${article.board_seq}'><a href='content.do?board_seq=${article.board_seq}'>${article.title}</a></a></td>
				<td>${article.writer}</td>
				<td>${article.regDate}</td>
				<td>${article.count}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="row">
		<div class="col-md-3 col-md-offset-3">
			<c:if test="${page > 0}"> 
				<a href="list.do?page=${page-10}">이전페이지</a> 
			</c:if>
			<c:if test="${page == 0}"> 
				<a href="#">이전페이지</a> 
			</c:if>
			
			<fmt:parseNumber value="${page/10+1}" type="number"  integerOnly="True" /> page
			
			<c:if test="${fn:length( articleList ) < 10}"> 
				<a href="#">다음페이지</a>
			</c:if>
			<c:if test="${fn:length( articleList ) == 10}"> 
				<a href="list.do?page=${page+10}">다음페이지</a>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<a href="write.jsp">글 쓰기</a>
		</div>
	</div>


</body>
</html>