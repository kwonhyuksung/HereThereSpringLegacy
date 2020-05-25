<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>여기저기 - 여행 후기 보기</title>
	
	<%@ include file="/WEB-INF/views/include/link.jsp"%>
	<%@ include file="/WEB-INF/views/include/loader.jsp"%>
	
	<link rel="stylesheet" href="${root}/resources/css/schedule.css">
	<style>
	.icon-heart { color: #f85e5e;}
	.icon-thumbs-up { color: #6887f1;}
	</style>
	
	<script type="text/javascript">
	var _contextPath = '${root}';
	var _postSeq = '${reviewVo.postSeq}';
	var _postUserId = '${reviewVo.userId}';
	var _loginUserId = '${userInfo.userId}';
	var _adminCode = '${userInfo.adminCode}';
	</script>
	<script src="${root}/resources/js/review/view.js"></script>
	
</head>
<body>
<!-- 메인메뉴를 가져옴 -->
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>

<!-- 페이지 제목과 이미지. 이미지 높이는 js-fullheight로 조정 -->
	<div class="hero-wrap js-fullheight3" style="background-image: url('${root}/resources/images/HereThere_Main_07.JPG');">
		<div class="overlay"></div>
		<div class="container slcontainer">
			<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행 후기 보기</h1>
				</div>
			</div>
		</div>
	</div>

<!-- 내용시작 -->
	<section class="ftco-section ftco-degree-bg">
		<div class="container">
			<div class="row">
		
<!-- 여행 후기 게시글 입력 시작 -->	
				<div class="col-md-12 ftco-animate destination">
					<div class="text p-3">
						<div class="bg-light sidebar-wrap row">
							<div class="col-md-4">
								<img src="${root}/resources/${reviewVo.savePath}/${reviewVo.saveName}" alt="" class="img-fluid">
							</div>
							<div class="col-md-8">
								<span class="ml-auto">여행 후기</span>
								<h3 class="mb-3">${reviewVo.postTitle}</h3>
								<hr>
								<p class="days">
									<span>
										#${reviewVo.thema}<br>
										<i class="icon-person"></i> ${reviewVo.userId} <br> 
										<i class="icon-pencil"></i> 게시일 : ${reviewVo.logDate} &nbsp;|&nbsp; <i class="icon-pencil"></i> 최종 수정일 : ${reviewVo.renewalDate}<br>
									</span>
								</p>
							</div>
						</div>
						<div>
							${reviewVo.postContent}
						</div>	
					</div>
					
				</div>
<!-- 여행 일정 게시글 입력 종료 -->
			
<!--  찜, 추천, 조회수와 수정/삭제 버튼을 보여준다 -->
				<div class="col-md-12 ftco-animate">
					<div class="sidebar-wrap bg-light ftco-animate viewWrap">
						<div class="cnt">
							<div id="divDibs"><div><h3 class="heading mb-4"><i class='icon-heart-o'></i> 찜 1</h3></div></div>
							<div id="divRecommend"><div><h3 class="heading mb-4"><i class='icon-thumbs-o-up'></i> 추천 2</h3></div></div>
							<div id="hitDiv"><div class="scheduleHit"><h3 class="heading mb-4"><i class="icon-eye"></i> 조회수 ${reviewVo.hitCount}</h3></div></div>
						</div>
		              	<hr>
<!-- 작성자인 경우 수정/삭제 버튼을 보여준다 -->
<c:if test="${userInfo.userId == reviewVo.userId}">
		              	<div align="center">
		              		<input type="button" value="후기 수정" class="btn btn-secondary modiDelBtn" onclick="updateReviewPost();">
		              		<input type="button" value="후기 삭제" class="btn btn-secondary modiDelBtn" onclick="deleteReviewPost();">
		              	</div>
</c:if>
		            </div>
				</div>
<!-- 작성자 옵션 종료 -->
		      
<!-- 댓글 시작 -->
				<div class="col-md-12 ftco-animate destination">
		            <!-- 댓글달기  -->           
		            <div class="pt-5a">
			        	<div class="comment-form-wrap pt-5">
							<div class="bg-light commForm">
								<h5 class="mb-4"><i class="icon-comment"></i> 댓글 </h5>
<!-- 로그인을 했을 때만 댓글을 작성할 수 있다 -->
<c:if test="${userInfo != null}">
								<div class="row commDiv">
									<textarea name="" id="txtComment" cols="30" rows="1" class="form-control commText" placeholder="내용과 무관한 댓글, 악플은 삭제될 수 있습니다."></textarea>
									<div class="center commBtnDiv">
										<input type="button" value="등록" class="btn btn-primary commBtn" id="btnInsertComment">
									</div>
								</div>
</c:if>
							</div>
						</div>
						<div class="comment-form-wrap pt-3">
<!-- 실제 댓글 목록 코드 삽입 시작 -->				            
							<div class="comment-list" id="commentList">
							
			            		<li class="comment">
									<div class="comment-body">
										<div class="row">
											<h3><i class="icon-person"></i>작성자 아이디1&nbsp;</h3>
											<div class="meta">20.03.10&nbsp;</div>
											<p><label class="reply"  data-toggle="modal" data-target="#viewRecommModal" commentSeq ="">수정</a></p>&nbsp;
											<p><label class="reply" commentSeq ="">삭제</a></p>
										</div>
										<p>댓글 내용1</p>
									</div>
								</li>	
			            		<li class="comment">
									<div class="comment-body" >
										<div class="row">
											<h3><i class="icon-person"></i>작성자 아이디2&nbsp;</h3>
											<div class="meta">20.03.10&nbsp;</div>
											<p><label class="reply"  data-toggle="modal" data-target="#viewRecommModal" commentSeq ="">수정</a></p>&nbsp;
											<p><label class="reply" commentSeq ="">삭제</a></p>
										</div>
										<p>댓글 내용2</p>
									</div>
								</li>	
			            		<li class="comment">
									<div class="comment-body">
										<div class="row">
											<h3><i class="icon-person"></i>작성자 아이디3&nbsp;</h3>
											<div class="meta">20.03.10&nbsp;</div>
											<p><a href="#" class="reply"  data-toggle="modal" data-target="#viewRecommModal" commentCseq ="">수정</a></p>&nbsp;
											<p><a href="#" class="reply" commentCseq ="">삭제</a></p>
										</div>
										<p>댓글 내용3</p>
									</div>
								</li>
									
				            </div>
<!-- 실제 댓글 목록 코드 삽입 종료 -->
				         </div>
		            </div>		
				</div>
<!-- 댓글 끝 -->
		
			</div>
		</div>
	</section>
<!-- 내용끝 -->

<!-- 댓글 수정 모달 시작 -->
	<div class="modal fade" id="viewRecommModal" role="dialog" >
		<div class="modal-dialog">
<!-- Modal content-->
			<div class="modal-content">
				<div class="pt-5a">	                      
	            	<div class="comment-form-wrap">
	              		<div class="bg-light commForm">
	                		<h5 class="mb-4"><i class="icon-comment"></i> 댓글 수정</h5>
		               		<div class="row commDiv">
		                    	<textarea name="" id="txtUpdateComment" cols="30" rows="1" class="form-control commText" placeholder="내용과 무관한 댓글, 악플은 삭제될 수 있습니다."></textarea>
		                   		<div class="center commBtnDiv">
			                   		<input type="button" value="등록" class="btn btn-primary commBtn" id="btnUpdateComment" data-dismiss="modal" commentSeq="">
		                   		</div>
		                	</div>
	              		</div>
	              	</div>
				</div>
			</div>
		</div>
	</div>
<!-- 댓글 수정 모달 종료 -->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<%@ include file="/WEB-INF/views/include/arrowup.jsp"%>

</body>
</html>