<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>여기저기 - 여행 일정 쓰기</title>
  	<%@ include file="/WEB-INF/views/include/link.jsp"%>
  	<%@ include file="/WEB-INF/views/include/loader.jsp"%> 
  	<link rel="stylesheet" href="${root}/resources/css/schedule.css">
  	<link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
  	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.js"></script>
 	
	<style type="text/css">
	#uploadFile{display: none;}
	</style>
	<script type="text/javascript">
	var _contextPath = '${root}';
	</script>
	<script src="${root}/resources/js/schedule/write.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
   
<!-- 이미지 -->
	<div class="hero-wrap js-fullheight3" style="background-image: url('${root}/resources/images/HereThere_Main_04.JPG');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행 일정 쓰기</h1>
				</div>
			</div>
		</div>
	</div>

<!-- 내용 시작 -->
	<section class="ftco-section ftco-degree-bg">
		<form action="" id="formWriteSchedule" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="postTypeCode" id="postTypeCode" value="1">
			<input type="hidden" name="boardCode" id="boardCode" value="1">
			<input type="hidden" name="postContent" id="postContent" value="">
			<input type="hidden" name="term" id="term" value="">
			<input type="hidden" name="uploadImage" id="uploadImage" value="0">
		
			<div class="container">
				<div class="row">
<!-- 왼쪽 설정창 시작 -->	
					<div class="col-lg-3 sidebar">
						<div class="sidebar-wrap bg-light ftco-animate">
							<h3 class="heading mb-4">대표 사진</h3>
							<div class="ftco-animate destination">
					    		<a href="javascript:uploadFile();" id="mainImg" class="img img-2 d-flex justify-content-center align-items-center" style="background-image: url('${root}/resources/images/noimg.gif');">		    		
						    		<div class="icon d-flex justify-content-center align-items-center">
				    					<span class="icon-plus"></span>
				    					<input type="file" id="uploadFile" name="uploadFile"/>
				    				</div>	    				
					    		</a>		    		
							</div>
						</div>
								
				        <div class="sidebar-wrap bg-light ftco-animate">
							<h3 class="heading mb-4">일정 정보</h3>
				        	<div class="fields">
					         	<div class="col-md-12">
									<!-- 달력1 -->
				        			<div class="form-group">
					                	<input type="text" name="startDate" id="startDate" class="form-control datepicker" placeholder="출발일" readonly="readonly">
					              	</div>
					         	</div>
					         	<div class="col-md-12">
					             	 <div class="form-group">
					                	<input type="text" name="endDate" id="endDate" class="form-control datepicker" placeholder="도착일" readonly="readonly">
					             	 </div>
				       			</div>
					         	<div class="col-md-12">
									<!-- 일정(계획/후기) -->
				        			<div class="form-group">
						            	<div class="select-wrap one-third">
						                	<div class="icon"><span class="ion-ios-arrow-down"></span></div>
						                    <select name="thema" id="thema" class="form-control">
						                    	<option value="no">여행 테마</option>
						                    	<option value="친구랑 여행">친구랑 여행</option>
						                    	<option value="나홀로 여행">나홀로 여행</option>
						                    	<option value="커플 여행">커플 여행</option>
						                    	<option value="가족 여행">가족 여행</option>
						                    	<option value="단체 여행">단체 여행</option>
						                    	<option value="패키지 여행">패키지 여행</option>
						                    </select>
					                  	</div>
				        			</div>
				       			</div>
						    </div>
				        </div>
					</div>
<!-- 왼쪽 설정창 종료 -->
			
<!-- 게시물 내용 시작 -->    
					<div class="col-md-8 ftco-animate destination">
						<div class="text p-3">
							<div class="comment-form-wrap">
				                <div class="p-4 bg-light">
				                	<div class="form-group">
				                    	<input type="text" name="postTitle" id="postTitle" class="form-control" placeholder="여행 제목을 입력하세요" ><br>
									</div>
									<hr>
									<div id="summernote"></div>
				                </div>
							</div>
						</div>
					</div>
<!-- 게시물 내용 종료 -->
				</div>
			
				<div class="writeEnd" align="center">		
					<input type="button" value="등록" class="btn btn-primary py-3 px-5" id="btnRegister">
					<input type="button" value="취소" class="btn btn-primary py-3 px-5" id="btnCancel">
			  	</div>
			
			</div>
		</form>
	</section>
<!-- 내용 종료 -->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%> 
	<%@ include file="/WEB-INF/views/include/arrowup.jsp"%>
</body>
</html>