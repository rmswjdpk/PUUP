<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/js/jq.js"></script>
<script type="text/javascript">
	$(function() {
		$(".dd").removeClass("active");
		$('#send').addClass("active");
	});
</script>
</head>
<body>
	
	<form action="sendMessage.do" method="post" class="form-horizontal">
		<fieldset>
	    <legend>Send Message</legend>
	    <div class="form-group">
	      <label for="inputEmail" class="col-lg-2 control-label">To..(ID)</label>
	      <div class="col-lg-10"> 
	        <input type="text" class="form-control" id="inputEmail" placeholder="To..(ID)" name="toID" required="required" >
	        <input type="hidden"  value="${messageID }" name="fromID" id="fromId">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="inputEmail" class="col-lg-2 control-label">Title</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="inputPassword" placeholder="Title" name="title" required="required">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="textArea" class="col-lg-2 control-label">Textarea</label>
	      <div class="col-lg-10">
	        <textarea class="form-control" rows="3" id="textArea" name="content" required="required" placeholder="WTF" ></textarea>
	      </div>
	    </div>
	    <div class="form-group">
	      <div class="col-lg-10 col-lg-offset-2">
	        <button type="reset" class="btn btn-default">Cancel</button>
	        <button type="submit" class="btn btn-primary">Submit</button>
	      </div>
    	</div>
    	</fieldset>
	</form>
</body>
</html>