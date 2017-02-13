<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-bordered">
<tr>
	<td>총 회원수 : ${countMember } 명</td>
	<td></td>
</tr>
<tr>
	<td>이전달 신규 회원수 : ${beforeMonthNewMember.CNT_MEMBER } 명</td>
	<td>이번달 신규 회원수 : ${todayMonthNewMember.CNT_MEMBER } 명</td>
</tr>
<tr>
	<td>총 게시글 수 : </td>
	<td></td>
</tr>
<tr>
	<td>이전달 게시글 수 : </td>
	<td>이번달 게시글 수 : </td>
</tr>
<tr>
	<td>답변이 안된 문의게시글 수 : </td>
	<td></td>
</tr>
<tr>
	<td><img alt="" src="resources/upload/img/${report }"></td>
</tr>
<tr>
	<td><img alt="데이터가 부족해서 보여줄수 없당!" src="resources/upload/img/${report1 }"></td>
</tr>
<tr>
	<td><img alt="데이터가 부족해서 보여줄수 없당!" src="resources/upload/img/${report2 }"></td>
</tr>
</table>
</body>
</html>