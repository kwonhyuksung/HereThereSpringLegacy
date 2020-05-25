<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>여기저기 - 여행 일정</title>
    
  	<%@ include file="/WEB-INF/views/include/link.jsp"%>
  	<%@ include file="/WEB-INF/views/include/loader.jsp"%>
  	
  	<link rel="stylesheet" href="${root}/resources/css/schedule.css">
  	
  	<style>
	#lastPage,#firstPage,#nextPageGroup,#prevPageGroup,.naviNum {
		cursor: Pointer;
	}	
	</style>
  	
  	<script type="text/javascript">
	var _contextPath = '${root}';
	</script>
	<script src="${root}/resources/js/schedule/list.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
<!-- 이미지 -->
    <div class="hero-wrap js-fullheight" style="background-image: url('${root}/resources/images/HereThere_Main_03.JPG');">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행 일정</h1>
          </div>
        </div>
      </div>
    </div>
<!-- 내용시작 -->
	<section class="ftco-section ftco-degree-bg">
	<div class="container">
	<div class="row">
<!-- 왼쪽 검색창 시작 -->	
		<div class="col-lg-3 sidebar">
	        <div class="sidebar-wrap bg-light ftco-animate">
<c:if test="${userInfo == null }">
	        	<div class="form-group" id="btnCreateSchedule" style="display: none;">
</c:if>
<c:if test="${userInfo != null }">
	        	<div class="form-group" id="btnCreateSchedule" style="display: inline;">
</c:if>
	        		<a href="${root}/schedule/mvWrite.do">
						<input type="button" value="일정 만들기" class="btn btn-primary py-3 px-5">
					</a>
			    </div>
			    <br>
				<h3 class="heading mb-4">상세 검색</h3>
	        	<form action="#">
	        	<div class="fields">
<!-- 정렬 순서 -->		         
			         <div class="row">
			         	<div class="col-md-12">
		        			<div class="form-group">
				            	<div class="select-wrap one-third">
				                	<div class="icon"><span class="ion-ios-arrow-down"></span></div>
				                    <select name="" id="listOrder" class="form-control">
				                    	<option value="1">인기순</option>
				                    	<option value="2">최신순</option>
				                    </select>
			                  	</div>
		        			</div>
	        			</div>
			         </div>
<!-- 테마 -->
					<div class="row">
			         	<div class="col-md-12">		            
				            <div class="form-group">
				                <div class="select-wrap one-third">
				                    <div class="icon"><span class="ion-ios-arrow-down"></span></div>
				                    <select name="" id="searchThema" class="form-control">
				                    	<option value="0">테마 전체</option>
				                    	<option value="1">나홀로 여행</option>
				                    	<option value="2">친구와 함께</option>
				                      	<option value="3">커플 여행</option>
				                    	<option value="4">가족 여행</option>
				                      	<option value="5">단체 여행</option>
				                      	<option value="6">패키지 여행</option>
				                    </select>
			                  	</div>
				            </div>
				         </div>
				     </div>
<!-- 여행기간 -->
		        	<div class="form-group">
		            	<div class="range-slider">
		              		<label for="name">여행기간</label>
		              		<span>
								<input type="number" id="term1_number" value="1" min="1" max="60"/>일  -
								<input type="number" id="term2_number" value="60" min="1" max="60"/>일
							</span>
							<p>
								<input type="range" id="term1_range" value="1" min="1" max="60" step="1"/>
								<input type="range" id="term2_range" value="60" min="1" max="60" step="1"/>
							</p>
						</div>
		        	</div>
<!-- 검색어 입력 -->
		        	<div class="form-group">
		                <input type="text" id="searchWord" class="form-control" placeholder="검색어를 입력하세요">
		        	</div>
<!-- 검색 버튼 -->
		        	<div class="form-group">
		            	<input type="button" id="btnList" value="검색" class="btn btn-primary py-3 px-5">
		        	</div>
			    </div>
				</form>
	        </div>
		</div>
<!-- 왼쪽 검색창 끝 -->

<!-- 오른쪽 목록 -->
		<div class="col-lg-9">
<!-- 이 곳에 여행 일정 목록 실제 코드 추가 -->
        	<div id="scheduleList" class="row">

				<div class='col-md-4 ftco-animate  fadeInUp ftco-animated destination'>		
					<a href='${root}/schedule/view.jsp' class='img img-2 d-flex justify-content-center align-items-center' style='background-image: url(${root}/resources/images/Image_07.jpg);'>		
						<div class='icon d-flex justify-content-center align-items-center'>
							<span class='icon-search2'></span>
						</div>		
					</a>
					<div class='text p-3'>
						<div class='d-flex'>
							<h3><a href='${root}/schedule/view.jsp'>여행 일정 제목</a></h3>		
						</div>
						<p>#여행 테마</p>
						<p class='bottom-area d-flex'>		
							<span class='days'>20.03.10 ~ 20.03.12 (3일)</span>
							<span class='ml-auto'>일정</span>
						</p>
						<hr>
						<p class='bottom-area d-flex'>		
							<span><i class='icon-person'></i>작성자 아이디</span>
							<span class='list-cnt'>
								<i class='icon-thumbs-o-up'></i> 3 &nbsp;
								<i class='icon-heart-o'></i> 2
							</span>
						</p>
					</div>
				</div>
				<div class='col-md-4 ftco-animate  fadeInUp ftco-animated destination'>		
					<a href='${root}/schedule/view.jsp' class='img img-2 d-flex justify-content-center align-items-center' style='background-image: url(${root}/resources/images/Image_08.jpg);'>		
						<div class='icon d-flex justify-content-center align-items-center'>
							<span class='icon-search2'></span>
						</div>		
					</a>
					<div class='text p-3'>
						<div class='d-flex'>
							<h3><a href='${root}/schedule/view.jsp'>여행 일정 제목</a></h3>		
						</div>
						<p>#여행 테마</p>
						<p class='bottom-area d-flex'>		
							<span class='days'>20.02.10 ~ 20.02.12 (3일)</span>
							<span class='ml-auto'>일정</span>
						</p>
						<hr>
						<p class='bottom-area d-flex'>		
							<span><i class='icon-person'></i>작성자 아이디</span>
							<span class='list-cnt'>
								<i class='icon-thumbs-o-up'></i> 4 &nbsp;
								<i class='icon-heart-o'></i> 5
							</span>
						</p>
					</div>
				</div>
        		
          	</div>
<!-- 목록들 END -->

<!-- page -->
          	<div class="row mt-5">
		          <div class="col text-center">
		            <div class="block-27">
		              <ul id="navigator">
		                <li><a href="#">&lt;</a></li>
		                <li class="active"><span>1</span></li>
		                <li><a href="#">2</a></li>
		                <li><a href="#">3</a></li>
		                <li><a href="#">4</a></li>
		                <li><a href="#">5</a></li>
		                <li><a href="#">&gt;</a></li>
		              </ul>
		            </div>
		          </div>
          	</div>
<!-- page END -->          	
		</div> 
<!-- 오른쪽 목록  END-->
	</div> <!-- 큰 row END -->
	</div> <!-- 큰 container END -->
	</section>
<!-- 내용끝 -->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>   
	<%@ include file="/WEB-INF/views/include/arrowup.jsp"%>
</body>
</html>