<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>
	#adminMy {
		display: none;
	}
	</style>
    
<!-- 로그인 기능은 어느 화면에서든 작동해야 하므로 이곳에 있어야 한다 -->
	<%@ include file="/WEB-INF/views/member/login/loginModal.jsp"%>	

	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
		<div class="container">
<!-- 메뉴 좌상단의 '여기저기'를 클릭하면 초기화면으로 온다 -->
			<a class="navbar-brand" href="${root}/index.do">여기저기</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>
			
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="${root}/information/mvList.do">여행 정보<span class="caret"></span></a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/schedule/mvList.do">여행 일정<span class="caret"></span></a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/review/mvList.do">여행 후기<span class="caret"></span></a></li>

<!-- 세션에 userInfo가 있으면 로그인, 회원가입을 표시하지 않는다 -->
<c:if test="${userInfo == null}">
					<li class="nav-item cta" style="cursor: pointer"><a class="nav-link" data-toggle="modal" data-target="#myLoginModal"><span>로그인</span></a></li>
					<li class="nav-item cta"><a class="nav-link" href="${root}/member/join/mvRegister.do"><span>회원가입</span></a></li>
</c:if>

<c:if test="${userInfo != null}">
					<li class="nav-item cta dropdown">
						<a class="dropdown-toggle nav-link" href="" data-toggle="dropdown"><span>마이페이지</span></a>
						<ul class="dropdown-menu">
							<li><a href="${root}/member/myMenu/myInfo/view.do" class="dropdown-item">내 정보 관리</a></li>
							<li><a href="${root}/member/myMenu/mySchedule/mvList.do" class="dropdown-item">내가 작성한 일정</a></li>
							<li><a href="${root}/member/myMenu/myReview/mvList.do" class="dropdown-item">내가 작성한 후기</a></li>
						</ul>
					</li>
					
	<c:if test="${userInfo.adminCode == 1}">
					<li class="nav-item cta dropdown">
						<a class="dropdown-toggle nav-link" href="" data-toggle="dropdown"><span>관리자</span></a>
						<ul class="dropdown-menu">
							<li><a href="${root}/admin/member/mvList.do" class="dropdown-item">회원 관리</a></li>
						</ul>
					</li>
	</c:if>
					
					<li class="nav-item cta"><a class="nav-link" href="${root}/member/login/logout.do"><span>로그아웃</span></a></li>
</c:if>
				</ul>
			</div>
		</div>
	</nav>