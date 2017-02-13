/**
 * 
 */


//올린 파일 개수
var fileUpload = 0;
//최대 업로드 파일 개수
var maxFileUpload = 0;
$(function() {
	if($("#btn-uploadFile").val() != null)
		maxFileUpload = $("#btn-uploadFile").val();
	else
		maxFileUpload = $("#btn-uploadImgFile").val();
	
	$("body").on("click","#btn-delete",function(){
		var check = confirm("정말로 삭제하시겠습니까?");
		if(check == true){
			var parent = $(this).parent();
			var f_name = $(this).val();
			$.ajax({
				url:"deleteFile.do",
				data:{f_name:f_name}
			}).done(function(data){
				var r = eval("("+data+")");
				if(r == true){
					fileUpload--;
					$(parent).remove();
				}
				else{
					alert("삭제 실패! 잠시후 다시 시도해 주세요.")
				}
			});
		}
	});
	
	var uploadimg = false;
	$("#btn-uploadImgFile").click(function() {
		if(fileUpload >= maxFileUpload){
			alert("최대 " + maxFileUpload + "개까지 업로드 하실 수 있습니다.");
			return;
		}
		uploadimg = true;
		$("#input-file").click();
	});
	
	$("#btn-uploadFile").click(function() {
		uploadimg = false;
		if(fileUpload >= maxFileUpload){
			alert("최대 " + maxFileUpload + "개까지 업로드 하실 수 있습니다.");
			return;
		}
		$("#input-file").click();
	});
	
	$("#input-file").change(function(){
		if(uploadimg == true){
			var exts = ["jpg","png","jpeg","bmp","gif"];
			var ext = $("#input-file").val().split(".").pop();
			if($.inArray(ext,exts) == -1){
				alert("이미지 파일만 등록하실 수 있습니다.");
				uploadimg = false;
				$("#input-file").val("");
				return;
			}
		}
		
		var formData = new FormData();
		formData.append("uploadFile",$("#input-file")[0].files[0]);
		$("#input-file").val("");
		$.ajax({
			url:"uploadFile.do?ajax="+new Date(),
			type:"post",
			processData:false,
			contentType:false,
			data:formData,
			success:function(data){
				try{
					var r = eval("("+data+")");
					fileUpload++;
					var uploadFile = $("<div></div>").attr("id","div-uploadFile").append("업로드된 파일 명 : " + r.f_realname);
					if(uploadimg == true){
						var filepath = "resources/upload/tmp/"+r.f_name;
						$(uploadFile).append($("<br />"),$("<img>").attr({"alt":"이미지파일 불러오기 실패","src":filepath,"width":"20%","heigth":"20%"}));
					}
					$(uploadFile).append(
							$("<input/>").attr({"type":"hidden","name":"f_name","value":r.f_name}),
							$("<br />"),
							$("<button></button>").attr({"display":"inline-block","type":"button","id":"btn-delete","class":"btn btn-default","value":r.f_name}).html("삭제"),
							$("<br />")
					);
					$("#div-uploadFiles").append(uploadFile);
				}catch(err){
					alert(data);
				}
			},
			error:function(data){
				alert(data);
			}
		});
	});
	
	$("#div-uploadFiles").on("click","img",function(){
		var filepath = $(this).attr("src");
		var img = "<img src='"+filepath+"' width=50% height=auto />"
		CKEDITOR.instances.p_content.insertHtml(img);
	});
});