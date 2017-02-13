/**
 * 
 */
$(function() {
	fileUpload = $(".tr-uploadFiles").length;
	$(".btn_delete").click(function(){
		var r = confirm("정말로 삭제하시겠습니까?");
		if(r == true){
			var fname = $(this).val();
			$.ajax({
				url:"deleteFile.do",
				data:{f_name:fname}
			}).done(function(data) {
				var r = eval("("+data+")");
				if(r == true){
					var data = CKEDITOR.instances.p_content.getData();
					var reg = new RegExp("<.*?"+fname+".*?>","gi");
					data = data.replace(reg,"");
					CKEDITOR.instances.p_content.setData(data);
					$("#"+fname).remove();
					alert("삭제 성공");
				}
				else{
					alert("삭제 실패! 다시 시도해 주세요")
				}
			})
		}
	})
});