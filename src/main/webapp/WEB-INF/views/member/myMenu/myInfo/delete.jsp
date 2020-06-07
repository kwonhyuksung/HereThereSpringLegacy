<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<title>여기저기 - 계정 삭제</title>
	
	<%@ include file="/WEB-INF/views/include/link.jsp"%>
	<%@ include file="/WEB-INF/views/include/loader.jsp"%>
	
	<script type="text/javascript">
	function deleteMember() {
		if(document.getElementById("deletePass").value.trim().length == 0){
			alert("비밀번호를 입력해주세요.")
			return;
		} else{
			 if (confirm("정말 삭제하시겠습니까??") == true){    //확인
				document.getElementById("formDeleteMember").setAttribute("action", "${root}/member/myMenu/myInfo/delete.do");
				document.getElementById("formDeleteMember").submit();
			 }else{   //취소
			     return false;
			 }
		}
	}
	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	
<c:if test="${userInfo == null}">
	<script>	
	alert('로그인 세션이 만료되었습니다.'); 
	document.location.href = "${root}/index.do";
	</script>
</c:if>

    <div class="hero-wrap js-fullheight3" style="background-image: url('${root}/resources/images/HereThere_Main_10.JPG')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
          	<h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">계정삭제</h1>           
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
							<li><a href="${root}/member/myMenu/myInfo/view.do" class="dropdown-item" style="color:red;">내 정보 관리</a></li>
							<li><a href="${root}/member/myMenu/mySchedule/mvList.do" class="dropdown-item">내가 작성한 일정</a></li>
							<li><a href="${root}/member/myMenu/myReview/mvList.do" class="dropdown-item">내가 작성한 후기</a></li>							
						</div>
					</div>
				</div>
<!-- 왼쪽 목록 END -->

<!-- 오른쪽 목록 -->
				<div class="col-lg-9" align="center">		
					<div class="wrap-login100" style="background: aliceblue;">
						<form class="login100-form validate-form" id="formDeleteMember" name="formDeleteMember" method="post">
							<span class="login100-form-title p-b-49">계정 삭제</span>
							<br><br>
							<div class="wrap-input100 validate-input m-b-23" data-validate = "Username is reauired" align="left">
								<span class="label-input100">아이디</span>
								<input class="input100" type="text" id="deleteId" name="userId" value="${userInfo.userId}" readonly="readonly">
								<span class="focus-input100" data-symbol="&#xf206;"></span>
							</div>				
							<br>
							<div class="wrap-input100 validate-input" data-validate="Password is required" align="left">
								<span class="label-input100">비밀번호</span>
								<input class="input100" type="password" id="deletePass" name="userPass" placeholder="비밀번호입력">
								<span class="focus-input100" data-symbol="&#xf190;"></span>
							</div>							
							<br><br>
							<div class="d-flex justify-content-center mb-3">
								<div class="col-lg-5">
									<input type="button" value="삭제" class="btn btn-primary" style="width: 70%;" onclick="javascript:deleteMember();">
								</div>
								<div class="col-lg-5">
									<input type="button" value="취소" class="btn btn-primary" style="width: 70%;" onclick="location.href='${root}/member/myMenu/myInfo/view.jsp'">
								</div>
							</div>			
						</form>
					</div>    	
				</div> <!-- 오른쪽 목록  END-->
			</div> <!-- 큰 row END -->
		</div> <!-- 큰 container END -->
	</section>
<!-- 내용끝 -->
	
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>  
	<%@ include file="/WEB-INF/views/include/arrowup.jsp"%>
</body>
</html>



