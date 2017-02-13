<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$(".dd").removeClass("active");
		if($('#check').val()==0)
		{
			$('#sended').addClass("active");
		}
		else
		{
			$('#receive').addClass("active");
		}
	});
</script>
</head>
<body>
	<form action="#"  class="form-horizontal">
		<fieldset>
	    <legend>
	    <c:if test="${check==0 }">
	    	Sent Message
	    </c:if>
	    <c:if test="${check==1 }">
	    	Received Message
	    </c:if>
	    <input type="hidden" value="${check }" id="check">
	    </legend>
	    </fieldset>
	 </form>
	<table class="table table-striped table-hover ">
		<thead>
			<tr>
				<td>title</td>
				<td>
					<c:if test="${check==0 }">
				    	toID
				    </c:if>
				    <c:if test="${check==1 }">
				    	fromID
				    </c:if>
				</td>
				<td>saved_at</td>
				<td>action</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${sendList.size()==0 }">
				<tr>
					<td colspan="4"><center><p>doesn't have Message.</p></center></td>
				</tr>
			</c:if>
			<c:forEach var="m" items="${sendList }">
				<tr>
					<td>${m.title }</td>
					<td>
						<c:if test="${check==0 }">
					    	${m.toID }
					    </c:if>
					    <c:if test="${check==1 }">
					    	${m.fromID }
					    </c:if>					
					</td>
					<td>${m.saved_at }</td>
					<td>
						<c:if test="${check==0 }">
					    	<a href="detailMessage.do?id=${m._id }&check=0">view</a>
							<a href="deleteMessage.do?id=${m._id }&check=0">delete</a>
					    </c:if>
					    <c:if test="${check==1 }">
					    	<a href="detailMessage.do?id=${m._id }&check=1">view</a>
							<a href="deleteMessage.do?id=${m._id }&check=1">delete</a>
					    </c:if>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<center>
	<ul class="pagination pagination-sm">
	  ${paging}
	</ul>
	</center>
</body>
</html>