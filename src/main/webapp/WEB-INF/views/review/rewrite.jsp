<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>여기저기 - 여행 후기 수정</title>
	<%@ include file="/WEB-INF/views/include/link.jsp"%>
  	<%@ include file="/WEB-INF/views/include/loader.jsp"%>
  	<link rel="stylesheet" href="${root}/resources/css/review.css">
  	<link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
  	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.js"></script>
 	
	<style type="text/css">
	#uploadFile{display: none;}
	</style>
	<script type="text/javascript">
	var _contextPath = '${root}';
	var _postSeq = '${reviewVo.postSeq}';
	var _postContent = '${reviewVo.postContent}';
	var _thema = '${reviewVo.thema}';
	</script>
	<script src="${root}/resources/js/review/rewrite.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
   
<!-- 이미지 -->
	<div class="hero-wrap js-fullheight3" style="background-image: url('${root}/resources/images/HereThere_Main_06.JPG');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행 후기 수정</h1>
				</div>
			</div>
		</div>
	</div>

<!-- 내용 시작 -->
	<section class="ftco-section ftco-degree-bg">
		<form action="" id="formWriteReview" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="postSeq" id="postSeq" value="${reviewVo.postSeq}">
			<input type="hidden" name="postContent" id="postContent" value="">
			<input type="hidden" name="uploadImage" id="uploadImage" value="0">
		
			<div class="container">
				<div class="row">
<!-- 왼쪽 설정창 시작 -->	
					<div class="col-lg-3 sidebar">
						<div class="sidebar-wrap bg-light ftco-animate">
							<h3 class="heading mb-4">대표 사진</h3>
							<div class="ftco-animate destination">
					    		<a href="javascript:uploadFile();" id="mainImg" class="img img-2 d-flex justify-content-center align-items-center" style="background-image: url('${root}/resources/${reviewVo.savePath}/${reviewVo.saveName}');">		    		
						    		<div class="icon d-flex justify-content-center align-items-center">
				    					<span class="icon-plus"></span>
				    					<input type="file" id="uploadFile" name="uploadFile"/>
				    				</div>
					    		</a>
							</div>
						</div>
								
				        <div class="sidebar-wrap bg-light ftco-animate">
							<h3 class="heading mb-4">후기 정보</h3>
				        	<div class="fields">
					         	<div class="col-md-12">
									<!-- 일정(계획/후기) -->
				        			<div class="form-group">
						            	<div class="select-wrap one-third">
						                	<div class="icon"><span class="ion-ios-arrow-down"></span></div>
						                    <select name="thema" id="thema" class="form-control">
						                    	<option value="no">후기 테마</option>
						                    	<option value="장소 후기">장소 후기</option>
						                    	<option value="숙박 후기">숙박 후기</option>
						                    	<option value="맛집 후기">맛집 후기</option>
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
				                    	<input type="text" name="postTitle" id="postTitle" class="form-control" placeholder="제목을 입력하세요" value="${reviewVo.postTitle}"><br>
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