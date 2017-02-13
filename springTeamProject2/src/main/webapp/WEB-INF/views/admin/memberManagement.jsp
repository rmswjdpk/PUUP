<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link  href="./resources/datepicker/datepicker.css" rel="stylesheet">
<script type="text/javascript" src="./resources/datepicker/datepicker.js"></script>
<script type="text/javascript">
// 날짜검색필드에 날짜 세팅
function setDate(){
	var now = new Date();
	var date = now.getFullYear()-1+"/"+(now.getMonth()+1)+"/"+now.getDate();
	$("#startDate").val(date);
	date = now.getFullYear()+"/"+(now.getMonth()+1)+"/"+now.getDate();
	$("#endDate").val(date);
}
//인풋 필드 바꾸는 함수
function changeInputType(option){
	$("#input-keyWord").css("display","none").appendTo("body");
	$("#span-date").css("display","none").appendTo("body");
	if(option == "m_regdate"){
		$("#span-date").css("display","inline-block").appendTo("#span-keyWord");
	}
	else{
		$("#input-keyWord").css("display","inline-block").appendTo("#span-keyWord");
	}
}
//끝
$(function(){
	if($("#startDate").val().length == 0){
		setDate();
	}
	var keyField = $("#hidden-keyField").val();
	if(keyField.length != 0 && keyField != "m.g_num"){
		$("#keyField").val(keyField).attr("selected","selected");
		changeInputType($("#keyField").val());
	}
	//검색 조건에 따라 인풋필드 바꿈
	$("#keyField").change(function () {
		changeInputType($(this).val());
	});
	//데이트 피커
	$("#startDate,#endDate").datepicker({
		format:"yyyy/mm/dd",
		autoHide:true,
		endDate:function(){
			return new Date();
		}
	});
});
</script>
</head>
<body>
<table class="table ">
<tr>
	<td colspan="4"><a href="memberManagement.do">총회원수</a> : ${totalMemberCount } 명</td>
</tr>
<tr>
<c:forEach var="l" items="${cntlist }">
	<td><a href="memberManagement.do?keyField=m.g_num&keyWord=${l.G_NUM }">${l.G_TITLE }</a> : ${l.CNT_MEMBER } 명 </td>
</c:forEach>
</tr>
<tr>
</tr>
</table>
<!-- 검색 필드 -->
<div align="center">
<form action="memberManagement.do">
	<select name="keyField" id="keyField">
		<option value="m_id">아이디</option>
		<option value="m_name">이름</option>
		<option value="m_regdate">가입일</option>
	</select>
	<span id="span-keyWord">
		<input type="text" name="keyWord" id="input-keyWord" value="${s.keyWord }">
	</span>
	<input type="submit" value="검색">
</form>
<input type="hidden" id="hidden-keyField" value="${s.keyField }">
<!-- 날짜 검색 필드 -->
<span id="span-date" style="display: none;">
	<input type="text" id="startDate" name="startDate" value="${s.startDate }">~<input type="text" id="endDate" name="endDate" value="${s.endDate }">
</span>
</div>
<!-- 날짜 검색 필드 끝 -->
<!-- 검색 필드 끝 -->
<table class="table table-bordered table-hover">
<tr>
	<th>ID</th>
	<th>이름</th>
	<th>등급</th>
</tr>
<c:forEach var="m" items="${list }">
<tr>
	<td><a href="detailMember.do?m_id=${m.M_ID }">${m.M_ID }</a></td>
	<td>${m.M_NAME }</td>
	<td>${m.G_TITLE }</td>
</tr>
</c:forEach>
</table>
</body>
</html>