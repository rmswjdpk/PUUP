<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./resources/css/main.css">
<link rel="stylesheet" type="text/css" href="./resources/css/modal.css">
<script type="text/javascript" src="./resources/js/jq.js"></script>
<script type="text/javascript" src="./resources/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="./resources/js/rsa/jsbn.js"></script>
<script type="text/javascript" src="./resources/js/rsa/rsa.js"></script>
<script type="text/javascript" src="./resources/js/rsa/prng4.js"></script>
<script type="text/javascript" src="./resources/js/rsa/rng.js"></script>

<script type="text/javascript" src="./resources/js/member/checkMember.js"></script>
<script type="text/javascript" src="./resources/js/member/rsa.js"></script>
<script type="text/javascript" src="./resources/js/main.js"></script>
<script type="text/javascript">
	$(function(){
		$('#kuenjung').click(function () {
			var m_id = $('#myID').val();
			window.open("http://localhost:8087/controller/frontMessage.do?m_id="+m_id,'_blank','width=870px, height=350px','toolbar=no','channelmode=no');
		});
	});
</script>
</head>
<body>
<input type="hidden" id="myID" value="${lm.m_id }">
<div id="wrapper">
	<header id="header">
		<a href="front.do"><img alt="PPT" src="resources/css/imageFile/logo3.png" id="img-logo"></a>
	</header>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div id="div-nav">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">PUP</a>
						<ul class="dropdown-menu">
							<li><a href="#">PUP????</a></li>
							<li><a href="insertPupRequest.do">PUP 신청하기</a></li>
							<c:if test="${lm.g_num == 20 }">
							<li><a href="directorRequest.do">디렉터 신청하기</a></li>
							</c:if>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">BOARD</a>
						<ul class="dropdown-menu">
							<li><a href="listFree.do">자유게시판</a></li>
							<li><a href="#">템플릿 공유 게시판</a></li>
						</ul>
					</li>
					<c:if test="${lm.g_num >= 30 }">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">D:BOARD</a>
						<ul class="dropdown-menu">
							<li><a href="listDirectorRequest.do">디렉터 신청 목록</a></li>
			    			<li><a href="listPupRequest.do">PUP신청 목록</a></li>
						</ul>
					</li>
					</c:if>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">고객센터</a>
						<ul class="dropdown-menu">
							<li><a href="listQandA.do">문의게시판</a></li>
							<li><a href="listNotice.do">공지사항</a></li>
						</ul>
					</li>
					<c:if test="${lm.g_num == 40 }">
					<li><a href="adminMain.do">관리자 페이지</a></li>
					</c:if>
				</ul>
				<ul class="nav navbar-nav" id="memberNav">
					<c:choose>
						<c:when test="${lm == null }">
						<li><a href="#" id="a-login" class="btn btn-sm">로그인</a></li>
						<li><a href="insertMember.do" class="btn btn-sm">회원가입</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="#" id="kuenjung" class="btn btn-sm">쪽지함</a></li>
						<li><a href="logoutMember.do" class="btn btn-sm">로그아웃</a></li>
						<li><a href="myInfo.do" class="btn btn-sm">나의 정보</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<section>
	<div class="center-block" style="width: 60%; z-index: 0;" id="viewPage">
		<jsp:include page="${viewPage }"></jsp:include>
	</div>
	</section>
</div>
<!-- 로그인 모달 -->
<input type="hidden" id="modalOpen" value="${loginModalOpen }">
<div id="div-modal-window">
	<div id="div-modal-content">
		<h2>로그인</h2>
		<hr>
		<input type="text" id="input-loginid" placeholder="아이디를 입력하세요" class="form-control"><br>
		<input type="password" id="input-loginpwd" placeholder="비밀번호를 입력하세요" class="form-control"><br>
		<a href="findMemberId.do" class="btn btn-link">아이디 찾기</a><a href="findMemberPwd.do" class="btn btn-link">비밀번호 찾기</a><br>
		<button type="button" id="btn-login" class="btn btn-default">로그인</button>
	</div>
</div>
</body>
</html>