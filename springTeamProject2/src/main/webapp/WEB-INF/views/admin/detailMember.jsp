<%@page import="com.hanb.enums.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$("#select-grade").change(function(){
		if($(this).val() == $("#hidden-grade").val()){
			$("#btn-change").attr("disabled","disabled");
		}
		else{
			$("#btn-change").removeAttr("disabled");
		}
	});
	
	$("#btn-delete").click(function(){
		var r = confirm("정말로 강제탈퇴 시키겠습니까? 돌이킬수 없습니다.");
		if(r == true){
			var id = $("#td-id").text();
			$.ajax({url:"deleteMember.do?m_id="+id})
			.done(function(data){
				var r = eval("("+data+")");
				if(r == true){
					alert("탈퇴 성공");
					location.replace("memberManagement.do");
				}
				else{
					alert("탈퇴 실패!!!!!! \n다시 시도해 주세요.");
				}
			})
		}
	});
	
	$("#btn-change").click(function(){
		var r = confirm($("#select-grade").find(":selected").text() + "등급으로 변경합니다. 계속하시겠습니까?");
		if(r == true){
			var id = $("#td-id").text();
			var grade = $("#select-grade").val();
			$.ajax({
				url:"updateMemberGrade.do",
				data:{m_id:id,g_num:grade},
				success:function(data){
					var r = eval("("+data+")");
					if(r == true){
						alert("수정 성공");
						$("#hidden-grade").val(grade);
						$("#btn-change").attr("disabled","disabled");
					}
					else{
						alert("수정 실패 \n다시 시도해 주세요.");
					}
				}
			});
		}
	});
});
</script>
</head>
<body>
<h2>회원 정보</h2>
<hr>
<table class="table">
<tr>
	<td>아이디  </td>
	<td id="td-id">${m.m_id }</td>
	<c:if test="${m.g_num == dirCode}">
	<td>계약 성공률</td>
	</c:if>
</tr>
<tr>
	<td>성함  </td>
	<td>${m.m_name }</td>
	<c:if test="${m.g_num == dirCode}">
	<td rowspan="6"><img alt="" src="resources/upload/img/${pieImg }" width="80%" height="80%"></td>
	</c:if>
</tr>
<tr>
	<td>전화번호  </td>
	<td>${m.m_tel }</td>
</tr>
<tr>
	<td>이메일 주소  </td>
	<td>${m.m_email }</td>
</tr>
<tr>
	<td>주소  </td>
	<td>${m.m_addr }</td>
</tr>
<tr>
	<td>회원가입일  </td>
	<td>${m.m_regdate }</td>
</tr>
<tr>
	<td>회원 등급  </td>
	<td id="td-grade">
	<select id="select-grade">
	<c:forEach var="g" items="${list }">
		<option value="${g.G_NUM }" <c:if test="${g.G_NUM == m.g_num }">selected="selected"</c:if> >${g.G_TITLE }</option>
	</c:forEach>
	</select>
	<input type="hidden" value="${m.g_num }" id="hidden-grade">
	<button type="button" id="btn-change" disabled="disabled" class="btn btn-default">등급 변경</button>
	</td>
</tr>
<tr>
	<td><button type="button" id="btn-delete" class="btn btn-default">강제 탈퇴</button></td>
	<td></td>
</tr>
</table>

</body>
</html>