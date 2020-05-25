<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
	<title>여기저기</title>
<!-- meta, css 파일 등을 가져옴 -->
	<%@ include file="/WEB-INF/views/include/link.jsp"%>
<!-- loader?, js 파일 등을 가져옴 -->
	<%@ include file="/WEB-INF/views/include/loader.jsp"%>
	
	<link rel="stylesheet" href="${root}/resources/css/index.css">
	
	<script type="text/javascript">
	// JSTL에서 정의했던 root변수를 JS에서 사용하기 위해 변수를 정의
	var contextPath = '${root}';
	</script>
	<script src="${root}/resources/js/index.js"></script>
</head>
<body>
<!-- 메인메뉴를 가져옴 -->
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	    
<!-- 대문(검색 기능 포함) 시작 -->
<!-- 페이지 제목과 이미지. 이미지 높이는 js-fullheight로 조정 -->
	<div class="hero-wrap js-fullheight" style="background-image: url('${root}/resources/images/HereThere_Main_01.JPG');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate" data-scrollax=" properties: { translateY: '70%' }"> 
<!-- 대문 내용 -->        
					<h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><strong><br></strong>대한민국 여기저기!</h1>
					<p data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행정보를 공유해 보세요!</p>
					<div class="block-17 my-4">
<!-- 검색 위치 form태그-->
						<form action="" id="formSearchKeyword" method="get" class="d-block d-flex">
							<input type="hidden" name="searchWord" id="searchWord" value="">
							<div class="fields d-block d-flex">
<!-- 검색 카테고리 -->
								<div class="select-wrap one-two">
									<div class="icon"><span class="ion-ios-arrow-down"></span></div>
									<select name="" id="mainSearchKey" class="form-control">
										<option value="1">여행 정보</option>
										<option value="2">여행 일정</option>
										<option value="3">여행 후기</option>
									</select>
								</div>
<!-- 검색 TEXT -->
								<div class="textfield-search one-third">
									<input type="text" id="mainSearchWord" class="form-control" placeholder="예: 강릉, 축제, 맛집">
								</div>                  
							</div>
<!-- 검색 버튼 -->
							<input type="button" id="btnMainSearch" class="search-submit btn btn-primary" value="검색">  
						</form>
					</div>          
				</div>
			</div>
		</div>
	</div>
<!-- 대문 종료 -->

<!-- 여행 정보 섹션 시작 -->
    <section class="ftco-section ftco-destination bg-light">
    	<div class="container">
    		<div class="row justify-content-start mb-1 pb-3">
        		<div class="col-md-7 heading-section ftco-animate">
<!-- 여행 정보 제목 -->     
					<span class="subheading">여행 정보</span>
					<h2 class="mb-4"><strong>행사  &amp; 축제</strong></h2>
				</div>
        	</div>
<!-- 여행 정보 목록 시작 -->
    		<div class="row">
    			<div class="col-md-12">
<!-- 부트스트랩의 carousel은 회전목마처럼 요소들을 순환시키는 슬라이드 쇼 -->
	    			<div class="destination-slider owl-carousel ftco-animate">
<!-- 12개의 목록을 반복해서 보여줌 -->
<!-- 이 곳에 여행 정보 목록 실제 코드 추가 -->
		    			<c:forEach begin="0" end="11" varStatus="status"> 
		    				<div class='item informationItemList${status.index}'></div>
		    			</c:forEach>
		    			<%-- <div class="item informationItemList0">
			    			<div class='destination blog-entry'>
				    			<a href="${root}/information/view.jsp" class="img d-flex justify-content-center align-items-center" style="background-image: url(${root}/resources/images/Image_01.jpg);">
					    			<div class='icon d-flex justify-content-center align-items-center'>
					    				<span class='icon-search2'></span>
					    			</div>
				    			</a>
					    		<div class='text p-3'>
					    			<span class='tag'>제주시</span>
					    			<h3><a href='${root}/information/view.jsp'>행사 제목</a></h3>
						    		<div align='right'>
						    			<span class='listing'>2020/03/10 ~ 2020/03/30</span>	    		
						    		</div>
					    		</div>
				    		</div>
				    	</div>
				    	<div class="item informationItemList2">
			    			<div class='destination blog-entry'>
				    			<a href="${root}/information/view.jsp" class="img d-flex justify-content-center align-items-center" style="background-image: url(${root}/resources/images/Image_02.jpg);">
					    			<div class='icon d-flex justify-content-center align-items-center'>
					    				<span class='icon-search2'></span>
					    			</div>
				    			</a>
					    		<div class='text p-3'>
					    			<span class='tag'>서울시</span>
					    			<h3><a href='${root}/information/view.jsp'>행사 제목</a></h3>
						    		<div align='right'>
						    			<span class='listing'>2020/03/10 ~ 2020/03/30</span>	    		
						    		</div>
					    		</div>
				    		</div>
				    	</div> --%>
				    	
	    			</div>
    			</div>
    		</div>
    	</div>
    </section>
<!-- 여행 정보 섹션 종료 -->

<!-- 여행 일정 섹션 시작 -->
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-start mb-1 pb-3">
				<div class="col-md-7 heading-section ftco-animate">
					<span class="subheading">여행 일정</span>
					<h2><strong>여행 일정</strong></h2>
				</div>
			</div>
<!-- 이 곳에 여행 일정 목록 실제 코드 추가 -->
			<div id="scheduleList" class="row">
				
				<div class='col-md-3 ftco-animate  fadeInUp ftco-animated destination'>
					<a href='${root}/schedule/view.jsp' class='img img-2 d-flex justify-content-center align-items-center' style='background-image: url(${root}/resources/images/Image_03.jpg);'>
						<div class='icon d-flex justify-content-center align-items-center'>
							<span class='icon-search2'></span>
						</div>
					</a>		
					<div class='text p-3'>		
						<div class='d-flex'>		
							<h3><a href='${root}/schedule/view.jsp'>일정 제목</a></h3>		
						</div>
						<p>#나 홀로 여행</p>
						<p class='bottom-area d-flex'>		
							<span class='days'>2020-03-10 ~ 2020-03-12(3일)</span>
							<span class='ml-auto'>일정</span>
						</p>
						<hr>		
						<p class='bottom-area d-flex'>		
							<span><i class='icon-person'></i>작성자 아이디</span>
							<span class='list-cnt'>
								<i class='icon-thumbs-o-up'></i> 1 &nbsp;
								<i class='icon-heart-o'></i> 2
							</span>
						</p>
					</div>		
				</div>
				<div class='col-md-3 ftco-animate  fadeInUp ftco-animated destination'>
					<a href='${root}/schedule/view.jsp' class='img img-2 d-flex justify-content-center align-items-center' style='background-image: url(${root}/resources/images/Image_04.jpg);'>
						<div class='icon d-flex justify-content-center align-items-center'>
							<span class='icon-search2'></span>
						</div>
					</a>		
					<div class='text p-3'>		
						<div class='d-flex'>		
							<h3><a href='${root}/schedule/view.jsp'>일정 제목</a></h3>		
						</div>
						<p><br>#커플 여행</p>
						<p class='bottom-area d-flex'>		
							<span class='days'>2020-02-10 ~ 2020-02-12(3일)</span>
							<span class='ml-auto'>일정</span>
						</p>
						<hr>		
						<p class='bottom-area d-flex'>		
							<span><i class='icon-person'></i>작성자 아이디</span>
							<span class='list-cnt'>
								<i class='icon-thumbs-o-up'></i> 3 &nbsp;
								<i class='icon-heart-o'></i> 1
							</span>
						</p>
					</div>		
				</div>
				
			</div>
		</div>
	</section>
<!-- 여행 일정 섹션 종료 -->
	
<!-- 여행 후기 섹션 시작 -->
	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row justify-content-start mb-1 pb-3">
				<div class="col-md-7 heading-section ftco-animate">
					<span class="subheading">여행 후기</span>
					<h2><strong>여행 후기</strong></h2>
				</div>
			</div>
<!-- 이 곳에 여행 후기 목록 실제 코드 추가 -->
			<div id="reviewList" class="row">
			
				<div class='col-md-3 ftco-animate  fadeInUp ftco-animated destination'>
					<a href='${root}/review/view.jsp' class='img img-2 d-flex justify-content-center align-items-center' style='background-image: url(${root}/resources/images/Image_05.jpg);'>
						<div class='icon d-flex justify-content-center align-items-center'>
							<span class='icon-search2'></span>
						</div>
					</a>		
					<div class='text p-3'>		
						<div class='d-flex'>		
							<h3><a href='${root}/review/view.jsp'>후기 제목</a></h3>		
						</div>
						<p><br>#맛집</p>
						<hr>		
						<p class='bottom-area d-flex'>		
							<span><i class='icon-person'></i>작성자 아이디</span>
							<span class='list-cnt'>
								<i class='icon-thumbs-o-up'></i> 1 &nbsp;
								<i class='icon-heart-o'></i> 2
							</span>
						</p>
					</div>		
				</div>
				<div class='col-md-3 ftco-animate  fadeInUp ftco-animated destination'>
					<a href='${root}/review/view.jsp' class='img img-2 d-flex justify-content-center align-items-center' style='background-image: url(${root}/resources/images/Image_06.jpg);'>
						<div class='icon d-flex justify-content-center align-items-center'>
							<span class='icon-search2'></span>
						</div>
					</a>		
					<div class='text p-3'>		
						<div class='d-flex'>		
							<h3><a href='${root}/review/view.jsp'>후기 제목</a></h3>		
						</div>
						<p><br>#맛집</p>
						<hr>		
						<p class='bottom-area d-flex'>		
							<span><i class='icon-person'></i>작성자 아이디</span>
							<span class='list-cnt'>
								<i class='icon-thumbs-o-up'></i> 4 &nbsp;
								<i class='icon-heart-o'></i> 1
							</span>
						</p>
					</div>		
				</div>
				
			</div>
		</div>
	</section>
<!-- 여행 후기 섹션 종료 -->

<!-- 페이지 하단의 정보들. main.js 포함 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<!-- 위로 가기 버튼 -->	
	<%@ include file="/WEB-INF/views/include/arrowup.jsp"%>

<!-- 이벤트 등록 시작 -->
	<script>
	document.getElementById("btnMainSearch").addEventListener("click", searchKeyWord, false);
	</script>
<!-- 이벤트 등록 종료 -->

</body>
</html>