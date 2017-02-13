/**
 * 
 */

function deleteReply(r_no){
	$.ajax({
		url:"deleteReply.do",
		data:{r_no:r_no}
	}).done(data => {
		if(data == "true"){
			//getListReply($("#p_no").val());
			alert("삭제 성공");
			let currPage = document.location.href;
			location.href = currPage;
		}
		else
			alert("삭제 실패!")
	});
}

function insertReply(data) {
	$.ajax({
		url:"insertReply.do",
		type:"post",
		data:data
	}).done(data => {
		if(data == "true"){
			//getListReply($("#p_no").val());
			alert("등록 성공");
			let currPage = document.location.href;
			location.href = currPage;
		}
		else
			alert("등록 실패!")
	});
}

function getListReply(p_no) {
	$.ajax({
		url:"listReply.do",
		data:{p_no:p_no}
	}).done(data => {
		setReply(data);
	}) 
}

function setReply(data){
	let replyContainer = $("#div-replyContainer");
	$("#div-replyContainer").children().remove();
	data = eval("("+data+")");
	$(data).each( (index,r) => {
		let replyContent = $("<div><div>").attr("id",r.r_no).append(
			$("<p></p>").html(r.m_id+" / "+r.r_regdate),
			$("<div></div>").html(r.r_content)
		);
		$(replyContainer).append(replyContent);
	});
}
$(function() {
	$("#btn_insert").click(() => {
		let data = $("#form_reply").serializeArray();
		insertReply(data);
	});
	
	$(".btn_delete").click(e => {
		let isDelete = confirm("정말로 삭제하시겠습니까?");
		if(isDelete){
			let r_no = $(e.target).parent().attr("id");
			deleteReply(r_no);
		}
	});
	
	$("textarea").click(() => {
		let m_id = $("#m_id").val();
		if(m_id.length == 0){
			$("#div-modal-window").css("display","block");
			$(this).blur();
		}
	});
})