<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${loginUser == null}">
	 <c:redirect url="/login" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="/css/common.css">
</head>
<body>
	<div id="container">
		<header>
			<div><a href="list">Home</a></div>
			<div id="menus">
				<div><a href="write">글쓰기</a></div>
				<div><a href="logout">로그아웃</a></div>
			</div>
		</header>
		<section>
			<jsp:include page="${target}.jsp" />
		</section>
		<footer>
			footer
		</footer>
	</div>
</body>
</html>