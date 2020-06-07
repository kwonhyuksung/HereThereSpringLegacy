// 목록의 현재 페이지
var currPageNum = 1;
// 목록의 총 갯수
var listTotalCount = 0;
// 한 화면에 보여지는 네비개이션의 페이지 갯수
var navigation_size = 3;
// 목록 한 페이지의 결과 수
var listNumOfRows = 3;
//게시물 유형 코드(1=일정, 2=후기, 3=답변)
var postTypeCode;

$(document).ready(function() {

	// 처음은 1페이지
	currPageNum = 1;
	makeList();
		
});

function makeList() {
	var urlStr = _contextPath + '/member/myMenu/mySchedule/list.do';
	// 게시물 유형 코드(1=일정, 2=후기, 3=답변)
	postTypeCode = 1;
	var offset = (currPageNum - 1) * listNumOfRows;
	var param = {"offset": offset, "limit": listNumOfRows, "postTypeCode": postTypeCode, "userId": _userId}
	param = JSON.stringify(param);
	
	$.ajax({		
		url : urlStr,
		type : 'POST',
		contentType : 'application/json;charset=UTF-8',
		dataType : 'json',
		data: param,
		success : function(response) {
			//alert("success : function(response)");
			makeListHtml(response);
		},
		error : function() {
			alert("error : function()");			
		}
		
	});
}

function makeListHtml(json) {
	var listCnt = json.list.length;
	var contentStr = "";
	for (var i = 0; i < listCnt; i++) {
		var schedule = json.list[i];
		contentStr += "<div class='col-md-4 ftco-animate  fadeInUp ftco-animated destination'>";		
		contentStr += "<a href='" + _contextPath + "/schedule/view.do?postSeq=" + schedule.postSeq + "' class='img img-2 d-flex justify-content-center align-items-center' ";		
		contentStr += "style='background-image: url(" + _contextPath + "/resources/" + schedule.savePath + "/"+ schedule.saveName + ");'>";		
		contentStr += "<div class='icon d-flex justify-content-center align-items-center'>";		
		contentStr += "<span class='icon-search2'></span>";		
		contentStr += "</div>";		
		contentStr += "</a>";		
		contentStr += "<div class='text p-3'>";		
		contentStr += "<div class='d-flex'>";		
		contentStr += "<h3><a href='" + _contextPath + "/schedule/view.do?postSeq=" + schedule.postSeq + "'>" + schedule.postTitle + "</a></h3>";		
		contentStr += "</div>";
		contentStr += "<p>#" + schedule.thema + "</p>";
		contentStr += "<p class='bottom-area d-flex'>";		
		contentStr += "<span class='days'>" + schedule.startDate + " - " + schedule.endDate + " (" + schedule.term + "일)</span>";
//		contentStr += "<span class='ml-auto'>일정</span>";
		contentStr += "</p>";
		contentStr += "<hr>";		
		contentStr += "<p class='bottom-area d-flex'>";		
		contentStr += "<span><i class='icon-person'></i>" + schedule.userId + "</span>";
		contentStr += "<span class='list-cnt'>";
		contentStr += "<i class='icon-thumbs-o-up'></i> " + schedule.recommendCount + " &nbsp;";
		contentStr += "<i class='icon-heart-o'></i> " + schedule.dibsCount ;
		contentStr += "</span>";
		contentStr += "</p>";
		contentStr += "</div>";
		contentStr += "</div>";
	}
	
	// 일단 싹 지우고 리스트 추가
	$("#scheduleList").children("div").remove();
	$("#scheduleList").append(contentStr);
	listTotalCount = json.totalCount;
    // 네비게이터를 만듬
    makeNavigator();
}


function makeNavigator() {
//	alert("makeNav Start!");
	// 전체 페이지 갯수
	var totalPageCount = parseInt((listTotalCount - 1) / listNumOfRows + 1);
	// 목록을 몇 페이지 단위로 보게 할 것인가
	var naviSize = navigation_size;
	// 현재 페이지
	var pageNum = currPageNum;
	// 이전 페이지 그룹의 마지막 페이지
	var preLastPage = parseInt((pageNum - 1) / naviSize)  * naviSize;	
	// 시작 페이지
	var startPage = preLastPage + 1;	
	// 마지막 페이지
	var endPage = preLastPage + naviSize;
	// 다음 페이지 그룹의 시작 페이지
	var nextStartPage = startPage + naviSize;
	// html 코드
	var contentStr = "";
	
	if (listTotalCount <= 0) {
		// 네비게이터를 지우자
		$("#navigator").children("li").remove();
		// 웹브라우저의 뒤로가기 버튼을 위한 해쉬태그를 만듬
	    //createListHash();
	    return;
	}
	
	// 마지막 페이지가 전체 페이지보다 클 수는 없음
	if (endPage > totalPageCount) {
		endPage = totalPageCount;
	}
	
	// 현재 페이지가 1이 아닌 경우
	if (pageNum != 1) {
		contentStr+= "<li id='firstPage' class=''><span>&lt;&lt;</span></li>";
	} else {
		contentStr+= "<li id=''><span>&lt;&lt;</span></li>";
	}
	
	// 다음 페이지 그룹의 마지막 페이지는 0보다 커야 함
	if (preLastPage > 0) {
		contentStr+= "<li id='prevPageGroup' class=''><span>&lt;</span></li>";
	} else {
		contentStr+= "<li id=''><span>&lt;</span></li>";
	}	
	
	// 반복문을 돌며 네비게이터 코드를 작성
	for (var i = startPage; i <= endPage; i ++) {
		if (i != currPageNum) {
			contentStr+= "<li id='' class='naviNum'><span>" + i + "</span></li>";
		} else {
			contentStr+= "<li id='' class='active'><span>" + i + "</span></li>";
		}
				
	}
	
	// 다음 페이지 그룹의 시작 페이지는 전체 페이지 수보다 작거나 같아야 함
	if (nextStartPage <= totalPageCount) {
		contentStr+= "<li id='nextPageGroup' class=''><span>&gt;</span></li>";
	} else {
		contentStr+= "<li id=''><span>&gt;</span></li>";
	}
	
	// 현재 페이지가 마지막 페이지가 아닌 경우에만 마지막 페이지로
	if (pageNum != totalPageCount) {
		contentStr+= "<li id='lastPage' class=''><span>&gt;&gt;</span></li>";
	} else {
		contentStr+= "<li id=''><span>&gt;&gt;</span></li>";
	}	
	
	$("#navigator").children("li").remove();
    $("#navigator").append(contentStr);
    
    // 웹브라우저의 뒤로가기 버튼을 위한 해쉬태그를 만듬
//    createListHash();
    
}

$(document).on("click", "#lastPage", function() {
	// 전체 페이지 갯수
	var totalPageCount = parseInt((listTotalCount - 1) / listNumOfRows + 1);	
	
	// 현재 페이지가 마지막 페이지가 아닌 경우에만 마지막 페이지로
	if (currPageNum != totalPageCount) {
		currPageNum = totalPageCount;		
		makeList();
		makeNavigator();
	}	
});

$(document).on("click", "#firstPage", function() {
	// 현재 페이지가 1이 아닌 경우에만 첫 페이지로
	if (currPageNum != 1) {
		currPageNum = 1;
		makeList();
		makeNavigator();
	}	
});

$(document).on("click", "#nextPageGroup", function() {
	// 전체 페이지 갯수
	var totalPageCount = parseInt((listTotalCount - 1) / listNumOfRows + 1);
	// 목록을 몇 페이지 단위로 보게 할 것인가
	var naviSize = navigation_size;
	// 이전 페이지 그룹의 마지막 페이지
	var preLastPage = parseInt((currPageNum - 1) / naviSize)  * naviSize;
	// 시작 페이지
	var startPage = preLastPage + 1;
	// 다음 페이지 그룹의 시작 페이지
	var nextStartPage = startPage + naviSize;
		
	// 다음 페이지 그룹의 시작 페이지는 전체 페이지 수보다 작거나 같아야 함
	if (nextStartPage <= totalPageCount) {
		currPageNum = nextStartPage;
		makeList();
		makeNavigator();
	}	
});

$(document).on("click", "#prevPageGroup", function() {
	// 전체 페이지 갯수
	var totalPageCount = parseInt((listTotalCount - 1) / listNumOfRows + 1);
	// 목록을 몇 페이지 단위로 보게 할 것인가
	var naviSize = navigation_size;
	// 이전 페이지 그룹의 마지막 페이지
	var preLastPage = parseInt((currPageNum - 1) / naviSize)  * naviSize;	
		
	// 다음 페이지 그룹의 마지막 페이지는 0보다 커야 함
	if (preLastPage > 0) {
		currPageNum = preLastPage;
		makeList();
		makeNavigator();
	}	
});

$(document).on("click", ".naviNum", function() {	
	currPageNum = $(this).text();
	makeList();
	makeNavigator();
});