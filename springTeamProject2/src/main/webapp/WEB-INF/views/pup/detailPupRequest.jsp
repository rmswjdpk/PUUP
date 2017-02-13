<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/js/jq.js"></script>
<script type="text/javascript">
	$(function () {
		var p_no = $('#p_no').val();
		getList();
		function getList(){
			$.ajax({url:"listPupReply.do?p_no="+p_no,
				success:function(data){
					var arr = eval("("+data+")");
					var tb = $('#tb');
					$(tb).empty();
					var division = $('<tr><td colspan="3">샘플 파일입니다.</td></tr>')
					$(tb).append(division);
					var tb2 = $('#tb2');
					$(tb2).empty();
					var division2 = $('<tr><td colspan="3">완성 파일입니다.</td></tr>')
					$(tb2).append(division2);
					var category =$('#pu_category').val()
					if(category=="종료")
					{
						$('#pupGo').hide();
					}
					
					$(arr).each(function (idx,item) {
						if(item.CHOICE=="채택")
						{
							$('#pupGo').hide();
						}
						if($('#m_id').val()==item.M_ID || $('#m_id').val()==$('#m_id2').val())
						{
							if(item.CHOICE=='완료')
							{
								var tr1 = $('<tr></tr>');
								var tr2 = $('<tr></tr>');
								var tr3 = $('<tr></tr>');
								var td1 = $('<td></td>').html("댓글번호 :" +item.R_NO);
								var td2 = $('<td></td>').html("채택여부 : "+item.CHOICE);
								
								var td3 = $('<td></td>').html("아이디 : "+item.M_ID);
								var td4 = $('<td></td>').html("등록일 : " +item.R_REGDATE);
								var td5 = $('<td></td>').html("파일명 : ");
								var td6 = $('<td></td>').html("<a href='#' id='buy' key='"+item.R_NO+"'>결재완료</a>");
								var td7 = $('<td colspan="3"></td>').html("내용 : <br>");
								
								var a = $('<a href="downloadFile.do?f_name='+item.F_NAME+'">'+item.F_REALNAME+'</a>');
								var text = $('<textarea rows="3" cols="80"></textarea>').attr("readonly","readonly").html(item.R_CONTENT);
								$(tr1).append(td1, td2, td3);
								$(td5).append(a);
								$(tr2).append(td4,td5,td6)
								$(td7).append(text);
								$(tr3).append(td7);
								$(tb2).append(tr1,tr2,tr3);
								
							}
							else
								{
								var tr1 = $('<tr></tr>');
								var tr2 = $('<tr></tr>');
								var tr3 = $('<tr></tr>');
								var td1 = $('<td></td>').html("댓글번호 :" +item.R_NO);
								var td2 = $('<td></td>').html("채택여부 : "+item.CHOICE);
								if(item.CHOICE=="진행")
								{
									td2.html('채택여부 : '+'<font color="green">'+item.CHOICE+'</font>');
								}
								
								var td3 = $('<td></td>').html("아이디 : "+item.M_ID);
								var td4 = $('<td></td>').html("등록일 : " +item.R_REGDATE);
								var td5 = $('<td></td>').html("파일명 : ");
								var td6 = $('<td></td>');
								if($('#m_id2').val()==$('#m_id').val())
								{
									$(td6).html("<a href='#' id='a' key='"+item.R_NO+"'>채택하기</a>")
								}
								if(item.CHOICE=="종료")
								{
									td6.html("<font color='red'>채택되지 않았습니다.</font>");
								}
								if(item.CHOICE=="채택" )
								{
									td6.html("<font color='blue'>채택되었습니다.</font>");
								}
								if(item.CHOICE=="채택" && $('#m_id').val()==item.M_ID)
								{
									td6.html("<font color='blue'>채택되었습니다.</font><a href='pupProductReply.do?p_no="+p_no+"' >완성파일첨부하기</a>");
								}
								
								var td7 = $('<td colspan="3"></td>').html("내용 : <br>");
								
								var a = $('<a href="downloadFile.do?f_name='+item.F_NAME+'">'+item.F_REALNAME+'</a>');
								var text = $('<textarea rows="3" cols="80"></textarea>').attr("readonly","readonly").html(item.R_CONTENT);
								$(tr1).append(td1, td2, td3);
								$(td5).append(a);
								$(tr2).append(td4,td5,td6)
								$(td7).append(text);
								$(tr3).append(td7);
								$(tb).append(tr1,tr2,tr3);
								}
							}
						
					});
				}});
		}
			$('body').on('click','#a',function(){
				var r_no = $(this).attr('key');
				$.ajax({url:"updateChoice.do",
						data:{r_no:r_no, p_no:p_no},
						success:function(data){
							alert("채택하셨습니다.");
							getList();
						}
				})
				
			});
	});
</script>
</head>
<body>
<font color="green"></font>
	<h2>PUP 신청 상세</h2>
	<hr>
	<input type="hidden" id="p_no" value="${pup.p_no }">
	<input type="hidden" id="m_id" value="${lm.m_id }">
	<input type="hidden" id="m_id2" value="${pup.m_id }">
	<input type="hidden" id="pu_category" value="${pup.pu_category }">
	<table class="table table-bordered">
		<tr>
			<td>글번호 : ${pup.p_no }</td>
			<td colspan="2">제목 : ${pup.p_title }</td>
			<td>조회수 : ${pup.p_hit }</td>
		</tr>
		<tr>
			<td>작성자 : ${pup.m_id }</td>
			<td>샘플기한 : ${pup.sample_period }</td>
			<td>마감기한 : ${pup.product_period }</td>
			<td>진행상태 : ${pup.pu_category }</td>
		</tr>
		<tr>
			<td>파일 : <a href="downloadFile.do?f_name=${pup.f_name }">${pup.f_realname }</a></td>
			<td>예상가격 : ${pup.expection_price }</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="form-control" style="min-height: 300px">${pup.p_content }</div>
			</td>
		</tr>
	</table>
	<hr>
	<c:if test="${lm.g_num==30 }">
	<a href="pupReply.do?p_no=${pup.p_no }" id="pupGo" class="btn btn-default">PUP 해주기</a>
	</c:if>
	<br>
	<table id="tb2" class="table table-hover" width="40%" style="float: left">
	</table>
	<table id="tb" class="table" width="40%"  style="float: left" style="table-layout:fixed;">
	</table>
</body>
</html>