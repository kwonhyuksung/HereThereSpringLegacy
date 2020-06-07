// 대표 사진을 업로드하는가?(1:업로드 수행)
var _uploadImage = 0;

$(document).ready(function() {
	// set summermote
	$('#summernote').summernote({
		placeholder: '내용을 적어주세요...',
		dialogsInBody: true,
		tabsize: 2,
		height: 400,
		lang: 'ko-KR',
		toolbar : [
			['Font Style', ['fontname']],
			['style', ['bold', 'italic', 'underline']],
			['font', ['strikethrough']],
			['fontsize', ['fontsize']],
			['color', ['color']],
			['para', ['paragraph']],
			['Insert', ['picture','link','hr']]
		],
		// 사용되는 이미지를 content에 통째로 포함시키지 않고, 이미지 파일로 서버에 저장하기 위해 summernote에서 마련한 callback 함수를 사용
		callbacks: {
			// onImageUpload: function(files, editor, welEditable) {
	    	onImageUpload: function(files) {
	    		for (var i = files.length - 1; i >= 0; i--) {
	    			uploadImage(files[i], this);
	    		}
	    	}
	    }
	});
	
	// JSP 파일 초기화
	initJspFile();
	
	$("#btnRegister").click(function() {
		if($("#postTitle").val() == "") {
			alert("여행 제목을 입력해주세요");
			return;
		} else if($("#startDate").val() == "") {
			alert("출발일을 입력해주세요");
			return;
		} else if($("#endDate").val() == "") {
			alert("도착일을 입력해주세요");
			return;
		} else if($("#thema").val() == "no") {
			alert("여행 테마를 입력해주세요");
			return;
		} else if(getDateTerm() < 1) {
			alert("도착일이 출발일 보다 빠를 수 없습니다");
			return;
		} else if($('#summernote').summernote('isEmpty')) {
			alert('내용을 입력해 주세요.');
			return;
		} else {
			var result = confirm("수정 하시겠습니까?");
			if (result){
				var markup = $("#summernote").summernote("code"); // 내용 가져오는거
				// summernote html은 개행을 거지같이 하기 때문에 아예 개행문자를 없애버리고 저장
				markup = markup.replace(/\r\n/g, '');
				
				$('#postContent').val(markup);
				$("#term").val(getDateTerm());
				$("#uploadImage").val(_uploadImage);
				if (_uploadImage == 0) {
					$("#formWriteSchedule").attr("method", "GET");
					$("#formWriteSchedule").attr("enctype", "application/x-www-form-urlencoded");
				}
				$("#formWriteSchedule").attr("action", _contextPath + "/schedule/rewrite.do").submit();
//				document.location.href = contextPath + "/schedule/list.jsp";
			}
		}
	});
	
	$("#btnCancel").click(function() {
		var result = confirm("등록을 취소 하시겠습니까?");
		if(result){
			history.back();
		}
	});
	
	// Set Date Format
	$(".datepicker").datepicker({
		format: "yyyy-mm-dd",
		autoclose: true
	});
	
});

function initJspFile() {
	// 테마를 설정
	$("#thema").val(_thema);
	
	// summernote에 게시물 내용을 넣는다
	$("#summernote").summernote('code', _postContent);
}

//summernote의 이미지 파일의 업로드를 위해 콜백 함수 onImageUpload의 구체적인 내용을 정의하는  함수
function uploadImage(file, el) {
	var formData = new FormData();	
	formData.append("file", file);
	$.ajax({
		data: formData,
		type: "POST",
		url: _contextPath + "/schedule/uploadImageSummernote.do",
		cache: false,
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: function(data) {
			// summernote에 이미지의 url을 삽입
			$(el).summernote('editor.insertImage', data.url);
		}
		
	});
}

// 여행 기간을 계산
function getDateTerm() {
	var term = 0;
	var startDate = new Date($("#startDate").val());
	var endDate = new Date($("#endDate").val());
	var diff = endDate - startDate;
	var day = 1000 * 60 * 60 * 24; // 밀리세컨초 * 초 * 분 * 시간
	
	term = parseInt(diff/day) + 1;
	
	return term;
}

/*-------- 대표사진 업로드 --------*/
//대표사진을 클릭하면 input type="file"을 클릭하는 메소드
function uploadFile(){  		  		
	  	document.getElementById("uploadFile").click();
}

//파일을 업로드가 됐는지 on change로 알아냄
$(function() {
    $("#uploadFile").on('change', function(){
        readURL(this);
    });
})
	
//파일을 읽어서 대표사진태그에 이미지 URL 바꿔주는 메소드
function readURL(input) {  		 
    if (input.files && input.files[0]) {
        var reader = new FileReader();  	 
        reader.onload = function (e) {
            $('#mainImg').attr('style',"background-image: url('"+e.target.result+"');");
            _uploadImage = 1;
        }  	 
        reader.readAsDataURL(input.files[0]);
    } else {
    	// 브라우저에서 아무것도 선택하지 않으면 file을 제대로 업로드 할 수 없다.
    	$('#mainImg').attr('style',"background-image: url('" + _contextPath + "/resources/images/noimg.gif');");
    	_uploadImage = 0;
    }
}  	
/*--------------------*/
