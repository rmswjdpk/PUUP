<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List QandABoard here</title>
<script type="text/javascript">
const ADMIN_GRADE_NUMBER = 40;
$(function() {
	$(".a-detailQA").click(function(e){
		e.preventDefault();
		var lm_id = $("#lm_id").val();
		var lm_grade = $("#lm_grade").val();
		var writer_id = $(this).parent().next().text();
		
		var address = $(this).attr("href");
		if(lm_grade != ADMIN_GRADE_NUMBER){
			var p_ref = $(this).parent().next().next().text();
			var p_no = address.split("=").pop();
			/* if( writer_id != lm_id ){
				alert("본인이 작성한 글이나 작성한 글에 달린 리플만 읽을 수 있습니다.")
				return;
			} */
			
			var writer = $("#"+p_ref).find("#writer").text();
			if(writer != lm_id){
				alert("본인이 작성한 글이나 작성한 글에 달린 리플만 읽을 수 있습니다.")
				return;
			}
		}
		location.href = address;
	});
});
</script>
</head>
<body>
<h2>문의게시판</h2>
<hr>
<table class="table table-bordered table-hover">
<tr>
	<th width="50%">제 목</th>
	<th width="100px">작성자</th>
	<th width="100px">등록일</th>
</tr>
<c:forEach var="p" items="${list }">
<tr id="${p.P_NO }">
	<td>
	<c:forEach begin="1" end="${p.P_DEEP }">&nbsp;&nbsp;&nbsp;</c:forEach>
	<c:if test="${p.P_DEEP != 0 }">└></c:if>
	<a href="detailQandA.do?p_no=${p.P_NO }" class="a-detailQA">${p.P_TITLE }</a>
	</td>
	<td id="writer">${p.M_ID }</td>
	<td style="display: none;">${p.P_REF }</td>
	<td>${p.P_REGDATE }</td>
</tr>
</c:forEach>
</table>
<input type="hidden" id="lm_id" value="${lm.m_id }">
<input type="hidden" id="lm_grade" value="${lm.g_num }">
<a href="insertQandA.do" class="btn btn-default">글쓰기</a>
${pageStr }
</body>
</html>