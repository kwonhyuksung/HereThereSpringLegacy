<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>여기저기 - 내가 작성한 일정</title>
	
	<%@ include file="/WEB-INF/views/include/link.jsp"%>
	<%@ include file="/WEB-INF/views/include/loader.jsp"%> 
	   
	<style>
	#lastPage,#firstPage,#nextPageGroup,#prevPageGroup,.naviNum {
		cursor: Pointer;
	}
	</style>
	
	<script type="text/javascript">
	var _contextPath = '${root}';
	var _userId = '${userInfo.userId}';
	</script>
	<script src="${root}/resources/js/member/myMenu/mySchedule/list.js"></script>
	
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	
<c:if test="${userInfo == null}">
	<script>	
	alert('로그인 세션이 만료되었습니다.'); 
	document.location.href = "${root}/index.jsp";
	</script>
</c:if>
	
    <div class="hero-wrap js-fullheight3" style="background-image: url('${root}/resources/images/HereThere_Main_10.JPG')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">  
            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">내가 작성한 일정</h1>
          </div>
        </div>
      </div>
    </div>

<!-- 내용시작 -->
	<section class="ftco-section ftco-degree-bg">
		<div class="container">
			<div class="row">
				<!-- 왼쪽 목록 -->	
				<div class="col-lg-3 sidebar">
					<div class="sidebar-wrap bg-light ftco-animate">
						<div class="categories">
							<li><a href="${root}/member/myMenu/myInfo/view.do" class="dropdown-item">내 정보 관리</a></li>
							<li><a href="${root}/member/myMenu/mySchedule/mvList.do" class="dropdown-item" style="color:red;">내가 작성한 일정</a></li>
							<li><a href="${root}/member/myMenu/myReview/mvList.do" class="dropdown-item">내가 작성한 후기</a></li>							
						</div>
					</div>
				</div>
<!-- 왼쪽 목록 END -->
<!-- 오른쪽 목록 -->
				<div class="col-lg-9">
<!-- 목록들 -->
			        <div id="scheduleList" class="row">
			        </div>
<!-- 목록들 END -->
<!-- page -->
	          		<div class="row mt-5">
			          <div class="col text-center">
			            <div class="block-27">
			              <ul id="navigator">
	
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