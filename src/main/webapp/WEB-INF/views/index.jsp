<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />

<title>Insert title here</title>
</head>
<body>
	<%-- <nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">www.NEXTIT.or.kr</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a
				href="${pageContext.request.contextPath}/login/loginForm">로그인</a></li>
			<li><a
				href="${pageContext.request.contextPath}/member/memberList">회원관리</a></li>
			<li><a
				href="${pageContext.request.contextPath}/board/boardList">게시판</a></li>
		</ul>

		<c:if test="${not empty LOGIN_USER}">

			<div
				style="display: inline; float: right; padding-top: 15px; padding-bottom: 15px;">
				${LOGIN_USER.mem_name}회원님.(${LOGIN_USER.mem_id}) <a
					href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a>
			</div>

		</c:if>


	</div>
	</nav> --%>

	<div class="container">
		<h3>Basic Navbar Example</h3>
		<p>A navigation bar is a navigation header that is placed at the
			top of the page.</p>
	</div>
</body>
</html>