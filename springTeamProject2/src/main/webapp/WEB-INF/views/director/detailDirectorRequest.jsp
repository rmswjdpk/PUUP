<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<td>글번호 : ${dr.P_NO}</td>
			<td width="60%" colspan="2">글제목 : ${dr.P_TITLE}</td>
			<td>조회수 : ${dr.P_HIT}</td>			
		</tr>
		<tr>
			<td>등록일 : ${dr.P_REGDATE}</td>
			<td>마감일 : ${dr.DUE_DATE}</td>
			<td>진행상태 : ${dr.DIR_CATEGORY }</td>
			<td>투표자수 : ${dr.PROS+dr.CONS}</td>
		</tr>
		<tr>
			<td colspan="4"><div class="form-control" style="min-height: 300px">${dr.P_CONTENT }</div></td>
		</tr>
		<c:forEach items="${flist }" var="f">
			<tr>
				<td colspan="4">파일명: <a href="downloadFile.do?f_name=${f.f_name }">${f.f_realname }</a></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<c:if test="${lm.g_num==30 }">
	<a href="updateVote.do?p_no=${dr.P_NO}&vote=1" class="btn btn-default">찬성</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="updateVote.do?p_no=${dr.P_NO}&vote=-1" class="btn btn-default">반대</a>
	</c:if>
	<hr>
	<c:if test="${dr.DIR_CATEGORY=='평가완료' }">
		<c:if test="${restrict>(dr.PROS+dr.CONS) }">
			<font color="red">투표인원이 부족하여 투표가 무효처리 되었습니다.</font>
		</c:if>
		<c:if test="${restrict<=(dr.PROS+dr.CONS) }">
			<c:choose>
				<c:when test="${dr.PROS/(dr.PROS+dr.CONS)>0.7 }">
				<font color="green">찬성 : ${dr.PROS }, 반대 : ${dr.CONS }로 찬성률 : ${dr.PROS/(dr.PROS+dr.CONS)}로 디렉터로 오르셧습니다.</font> 
				</c:when>
				<c:otherwise>
				<font color="red">찬성 : ${dr.PROS }, 반대 : ${dr.CONS }로 찬성률 : ${dr.PROS/(dr.PROS+dr.CONS)}로 디렉터로 오르지 못하셧습니다.</font>
				</c:otherwise>
			 </c:choose>
		</c:if>
	</c:if>
</body>
</html>