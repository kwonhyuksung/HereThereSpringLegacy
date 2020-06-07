// TourAPI 3.0 service key
var SERVICE_KEY = "0zAzf%2BGAdBrV1fO%2FpVDQLGfnTgpOC9OKxvQpqS7NtWBLDf06y1iIFk70Qg%2Bf5pBWhggl2%2F7lQTxABewTAmXPcw%3D%3D";
// 지역코드
var areaCode = "";
// 시군구코드
var sigunguCode = "";
// 관광 정보 목록의 현재 페이지
var infoListCurrPageNum = 1;
// 관광 정보의 총 갯수
var infoTotalCount = 0;
// 관광 정보 페이지의 정렬(A=제목순, B=조회순, C=수정일순, D=생성일순)
// 대표이미지가 반드시 있는 정렬(O=제목순, P=조회순, Q=수정일순, R=생성일순)
var infoListArrange = "R";
// 한 화면에 보여지는 관광 목록의 페이지 갯수
var navigation_size = 10;
// 관광 목록 한 페이지의 결과 수
var infoListNumOfRows = 12;
// 현재 관광 정보
var infoTypeId = "infoArea";

// 일정, 후기 목록 갯수
var LIST_COUNT = 4;
// 게시물 유형 코드(1=일정, 2=후기, 3=답변)
var postTypeCode;
// 게시판 코드(1=여행 일정, 2=여행 후기)
var boardCode;
// 목록의 순서(1=인기순, 2=최신순)
var listOrder;
//테마
var thema;
var themaValue = 0;
//여행기간
var minTerm;
var maxTerm;
//검색어
var searchWord;

$(document).ready(function() {
	
	// Information List
	makeInfoFestivalList();
	
	// Schedule List
	makeScheduleList();
	
	// Review List
	makeReviewList();
	
});

// Search Button Click Event
function searchKeyWord() {
	var mainSearchKey = $("#mainSearchKey").val();
	var mainSearchWord = $("#mainSearchWord").val();
	
	if(mainSearchWord == "") {
		alert("검색할 글자가 없습니다.");
		return;
	} else {
		$("#searchWord").val(mainSearchWord);
		if (mainSearchKey == 1) {
			$("#formSearchKeyword").attr("action", contextPath + "/information/mvList.do").submit();
		} else if (mainSearchKey == 2) {				
			$("#formSearchKeyword").attr("action", contextPath + "/schedule/mvList.do").submit();
		} else if (mainSearchKey == 3) {
			$("#formSearchKeyword").attr("action", contextPath + "/review/mvList.do").submit();
		}
	}
}

function makeInfoFestivalList() {
	var url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
					"ServiceKey=" + SERVICE_KEY +
					"&eventStartDate=" +
					"&eventEndDate=" +
					"&areaCode=" +
					"&sigunguCode=" +
					"&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=KokKok" +
					"&arrange=" + infoListArrange + 
					"&numOfRows=" + infoListNumOfRows + 
					"&pageNo=1";		
	$.ajax({
	    url : url,
	    type : "GET",
	    success : function(xml){		    
	    	makeInfoListToHtml(xml);	        
	    },
		error : function() {
			//alert("fail");
		}	    
	});
}	

function makeInfoListToHtml(xml){
	var xmlData = $(xml).find("item");
    var listLength = xmlData.length;
    var contentStr = "";
    if (listLength > 0) {
    	$(xmlData).each(function(index,item){   
    		var siLocation = $(this).find("addr1").text().split(' ');
    		var eventStartDate = $(this).find("eventstartdate").text() + "";
    		var eventEndDate = $(this).find("eventenddate").text() + "";
    		var formatEventStartDate = eventStartDate.slice(0,4) + "/" + eventStartDate.slice(4, 6) + "/" + eventStartDate.slice(6);
    		var formatEventEndDate = eventEndDate.slice(0,4) + "/" + eventEndDate.slice(4, 6) + "/" + eventEndDate.slice(6);
    		contentStr += "<div class='destination blog-entry'>";
    		contentStr += "<a href='" + contextPath + "/information/mvView.do";
    		contentStr += "?contentTypeId=" + $(this).find("contenttypeid").text(); 
    		contentStr += "&contentId=" + $(this).find("contentid").text();
    		contentStr += "' class='img d-flex justify-content-center align-items-center' style='background-image: url(" + $(this).find("firstimage").text() + ");'>";
    		contentStr += "<div class='icon d-flex justify-content-center align-items-center'>";
    		contentStr += "<span class='icon-search2'></span></div></a>";
    		contentStr += "<div class='text p-3'>";
    		contentStr += "<span class='tag'>"+ siLocation[0] +"</span>";
    		contentStr += "<h3><a href='" + contextPath + "/information/mvView.do"
    		contentStr += "?contentTypeId=" + $(this).find("contenttypeid").text(); 
    		contentStr += "&contentId=" + $(this).find("contentid").text();
    		contentStr += "'>" + $(this).find("title").text() + "</a></h3>";
    		contentStr += "<div align='right'>";
    		contentStr += "<span class='listing'>"+ formatEventStartDate +" ~ "+ formatEventEndDate +"</span>";	    		
    		contentStr += "</div></div></div>";    		
    		$(".informationItemList"+index).append(contentStr);	    		
    		contentStr = "";
    	});
    }
}

function makeScheduleList() {
	var urlStr = contextPath + '/schedule/list.do';
	var offset = 1;
	// 게시물 유형 코드(1=일정, 2=후기, 3=답변)
	postTypeCode = 1;
	// 페이지의 정렬(1=인기순, 2=최신순)
	listOrder = 2;
	// 테마
	thema = "";
	// 여행 기간
	minTerm = 1;
	maxTerm = 60;
	// 검색어
	searchWord = "";
	
	var param = {"offset": offset, "limit": LIST_COUNT, "postTypeCode": postTypeCode,
				 "order": listOrder, "thema": thema, "minTerm": minTerm, "maxTerm": maxTerm, "searchWord": searchWord};
	param = JSON.stringify(param);
	
	$.ajax({		
		url : urlStr,
		type : 'post',
		contentType : 'application/json;charset=UTF-8',
		dataType : 'json',
		data: param,
		success : function(response) {
//			alert("success : function(response)");
			makeScheduleListHtml(response);
		},
		error : function() {
			alert("error : function()");			
		}
		
	});
}

function makeScheduleListHtml(json) {
	var listCnt = json.list.length;
	var contentStr = "";
	for (var i = 0; i < listCnt; i++) {
		var schedule = json.list[i];
		contentStr += "<div class='col-md-3 ftco-animate  fadeInUp ftco-animated destination'>";		
		contentStr += "<a href='" + contextPath + "/schedule/view.do?postSeq=" + schedule.postSeq + "' class='img img-2 d-flex justify-content-center align-items-center' ";		
		contentStr += "style='background-image: url(" + contextPath + "/resources/" + schedule.savePath + "/"+ schedule.saveName + ");'>";		
		contentStr += "<div class='icon d-flex justify-content-center align-items-center'>";		
		contentStr += "<span class='icon-search2'></span>";		
		contentStr += "</div>";		
		contentStr += "</a>";		
		contentStr += "<div class='text p-3'>";		
		contentStr += "<div class='d-flex'>";		
		contentStr += "<h3><a href='" + contextPath + "/schedule/view.do?postSeq=" + schedule.postSeq + "'>" + schedule.postTitle + "</a></h3>";		
		contentStr += "</div>";
		contentStr += "<p>#" + schedule.thema + "</p>";
		contentStr += "<p class='bottom-area d-flex'>";		
		contentStr += "<span class='days'>" + schedule.startDate + " ~ " + schedule.endDate + " (" + schedule.term + "일)</span>";
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
}

function makeReviewList() {
	var urlStr = contextPath + "/review/list.do";
	var offset = 1;
	// 게시물 유형 코드(1=일정, 2=후기, 3=답변)
	postTypeCode = 2;
	// 페이지의 정렬(1=인기순, 2=최신순)
	listOrder = 2;
	// 테마
	thema = "";
	// 검색어
	searchWord = "";
	
	var param = {"offset": offset, "limit": LIST_COUNT, "postTypeCode": postTypeCode,
				 "order": listOrder, "thema": thema, "searchWord": searchWord};
	param = JSON.stringify(param);
	
	$.ajax({		
		url : urlStr,
		type : 'POST',
		contentType : 'application/json;charset=UTF-8',
		dataType : 'json',
		data: param,
		success : function(response) {
//			alert("success : function(makeList)");
			makeReviewListHtml(response);
		},
		error : function() {
			alert("error : function()");			
		}
		
	});
}

function makeReviewListHtml(json) {
	var listCnt = json.list.length;
	var contentStr = "";
	for (var i = 0; i < listCnt; i++) {
		var review = json.list[i];
		contentStr += "<div class='col-md-3 ftco-animate  fadeInUp ftco-animated destination'>";		
		contentStr += "<a href='" + contextPath + "/review/view.do?postSeq=" + review.postSeq + "' class='img img-2 d-flex justify-content-center align-items-center' ";		
		contentStr += "style='background-image: url(" + contextPath + "/resources/" + review.savePath + "/" + review.saveName + ");'>";		
		contentStr += "<div class='icon d-flex justify-content-center align-items-center'>";		
		contentStr += "<span class='icon-search2'></span>";		
		contentStr += "</div>";		
		contentStr += "</a>";		
		contentStr += "<div class='text p-3'>";
		contentStr += "<div class='d-flex'>";
		contentStr += "<h3><a href='" + contextPath + "/review/view.do?postSeq=" + review.postSeq + "'>" + review.postTitle + "</a></h3>";		
		contentStr += "</div>";
		contentStr += "<p>#" + review.thema + "</p>";
		contentStr += "<hr>";
		contentStr += "<p class='bottom-area d-flex'>";		
		contentStr += "<span><i class='icon-person'></i>" + review.userId + "</span>";
		contentStr += "<span class='list-cnt'>";
		contentStr += "<i class='icon-thumbs-o-up'></i> " + review.dibsCount + " &nbsp;";
		contentStr += "<i class='icon-heart-o'></i> " + review.recommendCount ;
		contentStr += "</span>";
		contentStr += "</p>";
		contentStr += "</div>";		
		contentStr += "</div>";		
	}
	
	// 일단 싹 지우고 리스트 추가
	$("#reviewList").children("div").remove();
	$("#reviewList").append(contentStr);
}
