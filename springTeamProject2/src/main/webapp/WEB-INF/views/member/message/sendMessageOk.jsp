<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<blockquote id="send_result">
		<small><cite title="Source Title">${saved_at }</cite></small>
		<c:if test="${id!=null }">
			<p>${toID }님에게 메세지 발송에 성공하였습니다.</p>
		</c:if>
		<c:if test="${id==null }">
			<p>${toID }님에게 메세지 발송에 실패하였습니다.</p>
		</c:if>
	</blockquote>
</body>
</html>