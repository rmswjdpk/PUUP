<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>   
	  <form class="form-horizontal">
		<fieldset>
	    <legend>detail Message</legend>
	    <div class="form-group">
	      <label for="inputEmail" class="col-lg-2 control-label">Title</label>
	      <div class="col-lg-10"> 
	        <input type="text" class="form-control" id="inputEmail" placeholder="To..(ID)" name="toID" readonly="readonly" value="${m.title }">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="inputEmail" class="col-lg-2 control-label">date</label>
	      <div class="col-lg-10"> 
	        <input type="text" class="form-control" id="inputEmail" placeholder="To..(ID)" name="toID" readonly="readonly" value="${m.saved_at }">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="inputEmail" class="col-lg-2 control-label">fromID</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="inputPassword" placeholder="Title" name="title" readonly="readonly" value="${m.fromID }">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="inputEmail" class="col-lg-2 control-label">toID</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="inputPassword" placeholder="Title" name="title" readonly="readonly" value="${m.toID }">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="textArea" class="col-lg-2 control-label">Textarea</label>
	      <div class="col-lg-10">
	        <textarea class="form-control" rows="3" id="textArea" name="content" readonly="readonly">${m.content }</textarea>
	      </div>
	    </div>
    	</fieldset>
	</form>
</body>
</html>