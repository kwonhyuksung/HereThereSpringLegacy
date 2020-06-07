$(document).ready(function() {	
	// 찜, 추천, 댓글 가져오기
	selectDibs();
	selectRecommend();
	selectCommentList();
	
});
function updateSchedulePost(){
	var result = confirm("수정하시겠습니까?");
	if(result) {
		document.location.href = _contextPath + "/schedule/mvRewrite.do?postSeq=" + _postSeq;
	}
}

function deleteSchedulePost(){
	var result = confirm("삭제하시겠습니까?");
	if(result) {
		document.location.href = _contextPath + "/schedule/delete.do?postSeq=" + _postSeq;
	}
}

//찜하기
function selectDibs(){
	var url = _contextPath + "/common/selectDibs.do";
	var sendData = {"postSeq" : _postSeq, "userId" : _loginUserId};
	sendData = JSON.stringify(sendData);
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			var myDibs = response.myDibs;
			var dibsCount = response.dibsCount;				  
			makeDibsView(myDibs, dibsCount);			  
		}
	});
}

function makeDibsView(myDibs, dibsCount){
	$("#divDibs").children("div").remove();
	var makeDibs = "";
	if(myDibs > 0){
		makeDibs += '<div id="myDibs" value="1"><h3 class="heading mb-4"><i class="icon-heart"></i> 찜 '+ dibsCount +'</h3></div>';
	}else{
		makeDibs += '<div id="myDibs" value="0"><h3 class="heading mb-4"><i class="icon-heart-o"></i> 찜 '+ dibsCount +'</h3></div>';
	}
	$("#divDibs").append(makeDibs);
}

$(document).on("click", "#myDibs", function() {
	var result;
	var url;
	
	if (_loginUserId == "") {
		alert("로그인 후 이용 가능합니다.");
		return;
	}
	
	if ($("#myDibs").attr("value") == 0) {
		result = confirm("이 게시물을 찜하시겠습니까?");
		url = _contextPath + "/common/insertDibs.do";
	} else if ($("#myDibs").attr("value") == 1) {
		result = confirm("이 게시물의 찜을 해제하시겠습니까?");
		url = _contextPath + "/common/deleteDibs.do";
	} else {
		return;
	}
	
	if (!result) {
		return;
	}
	
	var sendData = {"postSeq" : _postSeq, "userId" : _loginUserId};
	sendData = JSON.stringify(sendData);
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			var myDibs = response.myDibs;
			var dibsCount = response.dibsCount;
			makeDibsView(myDibs, dibsCount);			  
		}
	});
});


//추천
function selectRecommend(){
	var url = _contextPath + "/common/selectRecommend.do";
	var sendData = {"postSeq" : _postSeq, "userId" : _loginUserId};
	sendData = JSON.stringify(sendData);
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			var myRecommend = response.myRecommend;
			var recommendCount = response.recommendCount;				  
			makeRecommendView(myRecommend, recommendCount);			  
		}
	});
}

function makeRecommendView(myRecommend, recommendCount){
	$("#divRecommend").children("div").remove();
	var makeRecommend = "";
	if(myRecommend > 0){
		makeRecommend += '<div id="myRecommend" value="1"><h3 class="heading mb-4"><i class="icon-thumbs-up"></i> 추천 '+ recommendCount +'</h3></div>';
	}else{
		makeRecommend += '<div id="myRecommend" value="0"><h3 class="heading mb-4"><i class="icon-thumbs-o-up"></i> 추천 '+ recommendCount +'</h3></div>';
	}
	$("#divRecommend").append(makeRecommend);
}

$(document).on("click", "#myRecommend", function() {
	var result;
	var url;
	
	if (_loginUserId == "") {
		alert("로그인 후 이용 가능합니다.");
		return;
	}
	
	if ($("#myRecommend").attr("value") == 0) {
		result = confirm("이 게시물을 추천하시겠습니까?");
		url = _contextPath + "/common/insertRecommend.do";
	} else if ($("#myRecommend").attr("value") == 1) {
		result = confirm("이 게시물의 추천을 해제하시겠습니까?");
		url = _contextPath + "/common/deleteRecommend.do";
	} else {
		return;
	}
	
	if (!result) {
		return;
	}
	
	var sendData = {"postSeq" : _postSeq, "userId" : _loginUserId};
	sendData = JSON.stringify(sendData);
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			var myRecommend = response.myRecommend;
			var recommendCount = response.recommendCount;				  
			makeRecommendView(myRecommend, recommendCount);
		}
	});
});

// 댓글 가져오기
function selectCommentList(){
	var url = _contextPath + "/common/selectCommentList.do"
	var sendData = {"postTypeCode" : 3, "boardCode" : 1, "groupNo" : _postSeq};
	sendData = JSON.stringify(sendData);
	
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			makeCommentList(response);
		}
	});
}

function makeCommentList(response){
	$('#commentList').empty(); //댓글 리스트 목록 비우기
	var commentList = response.list; //댓글 리스트 가져오기
	var commentListView = "";
	
	for (var i=0; i<commentList.length; i++) {
		commentListView += '<li class="comment">';
		commentListView += '<div class="comment-body">';
		commentListView += '<div class="row">';
		commentListView += '<h3><i class="icon-person"></i> '+ commentList[i].userId + '&nbsp;</h3>';
		commentListView += '<div class="meta">' + commentList[i].logDate + '&nbsp;</div>';
		if (commentList[i].userId == _loginUserId) { // 댓글 작성 ID와 로그인 ID가 같은때 수정버튼
			commentListView += '<p><label class="reply updateComment"  data-toggle="modal" data-target="#viewRecommModal" commentSeq ="' + commentList[i].postSeq + '">수정</p>&nbsp;';
		}
		if ((commentList[i].userId == _loginUserId) || (_adminCode == 1)) {// 댓글 작성 ID와 로그인 ID가 같은때 , 관리자 삭제버튼
			commentListView += '<p><label class="reply deleteComment" commentSeq ="' + commentList[i].postSeq + '">삭제</p>';
		}
		commentListView += '</div>';
		commentListView += '<p>'+ commentList[i].postContent + '</p>';
		commentListView += '</div>';
		commentListView += '</li>';
	}	
	$('#commentList').append(commentListView);
}

//댓글 쓰기
$(document).on("click", "#btnInsertComment", function() { //댓글 등록 버튼 클릭시
	var result; // confirm 함수의 리턴값을 받는 변수
	var url = _contextPath + "/common/insertComment.do";
	var title = ""; // 제목은 없다
	var content = $("#txtComment").val(); // 댓글 내용 가져오기
	
	if (_loginUserId == "") {
		alert("로그인 후 이용 가능합니다.");
		return;
	}
	
	if (content.length == 0) {
		alert("내용을 입력하세요.")
		return;
	}
	
	result = confirm("댓글을 등록하겠습니까?");
	if (!result) {
		return;
	}
	
	$("#txtComment").val(''); // 댓글 내용 비우기
	
	var sendData = {"postSeq" : _postSeq, "postTypeCode" : 3, "boardCode" : 1, "userId" : _loginUserId, "postTitle" : title, "postContent" : content,
					"groupNo" : _postSeq, "depth" : 0, "parentSeq" : _postSeq};
	sendData = JSON.stringify(sendData);
	
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			// 댓글 입력에 성공했다면 댓글 목록을 가져온다
			selectCommentList();
		}
	});
	
});

//업데이트되는 댓글의 postSeq를 modal에서 사용할 수 있도록 한다
$(document).on("click", ".updateComment", function(){
	$("#btnUpdateComment").attr("commentSeq", $(this).attr("commentSeq"));
	$("#txtUpdateComment").val('');
});

// 댓글  수정
$(document).on("click", "#btnUpdateComment", function() {
	var result; // confirm 함수의 리턴값을 받는 변수
	var url = _contextPath + "/common/updateComment.do";
	var updateSeq = $(this).attr("commentSeq");
	var content = $("#txtUpdateComment").val();
	
	if (_loginUserId == "") {
		alert("로그인 후 이용 가능합니다.");
		return;
	}
	
	result = confirm("댓글을 수정하겠습니까?");
	if (!result) {
		return;
	}
	
	if (content.length == 0) {
		alert("내용을 입력하세요.")
		return;
	}
	
	$("#txtUpdateComment").val('');
	var sendData = {"postSeq" : updateSeq, "postContent" : content};
	sendData = JSON.stringify(sendData);
	
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			// 댓글 수정에 성공했다면 댓글 목록을 가져온다
			selectCommentList();
		}
	});
	
});

// 댓글 삭제
$(document).on("click", ".deleteComment", function() {
	var result; // confirm 함수의 리턴값을 받는 변수
	var url = _contextPath + "/common/deleteComment.do";
	var deleteSeq = $(this).attr("commentSeq");
	
	if (_loginUserId == "") {
		alert("로그인 후 이용 가능합니다.");
		return;
	}
	
	result = confirm("댓글을 삭제하겠습니까?");
	if (!result) {
		return;
	}
	
	var sendData = {"postSeq" : deleteSeq};
	sendData = JSON.stringify(sendData);
	
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType : 'application/json;charset=UTF-8',		  
		data: sendData,
		success: function(response){
			// 댓글 삭제에 성공했다면 댓글 목록을 가져온다
			selectCommentList();
		}
	});
});
