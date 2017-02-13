<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#leftBar{
		width:120px; height:200px; left:0x; top:0x; line-height: 50px; position: fixed;
	}
	#viewPage{ 
		 left:130px; top:0x; width: 700px; height: 300px; position: absolute; overflow: scroll; padding:0x; magin:0x;
		 
	}
</style>
<script type="text/javascript" src="./resources/js/jq.js"></script>
<script type="text/javascript">
	$(function() {
		$('.dd').mouseover(function () {
			$(".dd").removeClass("active");
			$(this).addClass("active");
		})
	});  
</script>
</head>
<body>
	<aside id="leftBar">
	<ul class="nav nav-pills nav-stacked">
  <li id="main" class="dd actived"><a href="frontMessage.do">Main</a></li>
  <li id="receive" class="dd"><a href="receiveMessageList.do">Received</a></li>
  <li id="sended" class="dd"><a href="sendMessageList.do">Sent</a></li>
  <li id="send" class="dd"><a href="sendMessage.do">Send</a></li>  
	</ul>
</aside>
<div id="viewPage">
	<jsp:include page="${viewPage}" ></jsp:include>
</div>
</body>
</html>