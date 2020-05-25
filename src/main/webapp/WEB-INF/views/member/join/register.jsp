<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>여기저기 - 회원 가입</title>
	
	<%@ include file="/WEB-INF/views/include/link.jsp"%>
	<%@ include file="/WEB-INF/views/include/loader.jsp"%>
	
	<link rel="stylesheet" href="${root}/resources/css/login.css">
	<link rel="stylesheet" href="${root}/resources/fonts/iconic/css/material-design-iconic-font.min.css">

	<style>
	.ftco-navbar-light {
		z-index: 1;
	}
	.label-input100{
		font-weight: bold;
	}
	</style>

	<script type="text/javascript">	
	function register() {
		//아이디 정규식(a~z,0~9로 시작하는 4~16자리 아이디)
		var idJ = /^[a-z0-9]{4,16}$/;
			
		// 비밀번호 정규식(A~Z,a~z,0~9로 시작하는 4~16자리 비밀번호를 설정)
		var pwJ = /^[A-Za-z0-9]{4,16}$/; 
		
		// 이름 정규식(가~힣, 한글로 이루어진 2~6자리 이름을 적어야한다.)
		var nameJ = /^[가-힣]{2,6}$/;
		
		// 이메일 검사 정규식
		var mailJ = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		var nameerrorview = document.getElementById("nameBlank");
		var pwerrorview = document.getElementById("pwBlank");
		var emailerrorview = document.getElementById("emailBlank");
		
		var nameCheck = $("#userName").val();
		var passCheck = $("#userPass").val();
		var emailCheck = $("#userEmail").val();

		if (document.getElementById("userId").value.trim().length == 0) {
			alert("아이디를 입력해주세요.")
			return;
		} else if (document.getElementById("userName").value.trim().length == 0) {
			alert("이름을 입력해주세요.")
			return;
		} else if (document.getElementById("userPass").value.trim().length == 0) {
			alert("비밀번호를 입력해주세요.")
			return;
		} else if (document.getElementById("userPass").value != document.getElementById("passCheck").value) {
			alert("비밀번호를 확인해주세요.")
			return;
		} else if (document.getElementById("userEmail").value.trim().length == 0) {
			alert("이메일을 입력해주세요.")

			return;
		} else if(nameJ.test(nameCheck) == false){
			nameerrorview.innerHTML = "<font color='red'>이름은 2~6글자의 한글로 입력해주세요.</font>";
			nameerrorview.style = "display:";
			pwerrorview.style = "display: none";
			emailerrorview.style = "display: none";
			return;
		}else if(pwJ.test(passCheck) == false){
			pwerrorview.innerHTML = "<font color='red'>4~16자리의 영문 대소문자 또는 숫자로 입력해주세요.</font>";
			pwerrorview.style = "display:";
			nameerrorview.style = "display: none";
			emailerrorview.style = "display: none";
			return;
		}else if(mailJ.test(emailCheck) == false){
			emailerrorview.innerHTML = "<font color='red'>이메일 형식을 확인해주세요.</font>";
			emailerrorview.style = "display:";
			nameerrorview.style = "display: none";
			pwerrorview.style = "display: none";
			return;
		}
		else {
			document.getElementById("formRegister").setAttribute("action", "${root}/member/join/register.do");
			document.getElementById("formRegister").submit();
		}
	}

	function checkId() {
		window.open("${root}/member/join/checkId.do","checkId","top=200, left=100, width=500, height=350, menubar=no, status=no, toolbar=no, location=no, scrollbars=no");
	}

	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	</script>	
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>

	<div class="hero-wrap js-fullheight2" style="padding-top: 100px; background-image: url('${root}/resources/images/HereThere_Main_05.JPG');">
		<div class="container" style="overflow: auto; max-width: 2000px; max-height: 1000px;">
			<div class="row no-gutters slider-text js-fullheight2 align-items-center justify-content-center" data-scrollax-parent="true" style="height: 300px;">
				<div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
					<div class="container-login100"	style="padding-top: 0px; padding-bottom: 100px;">
						<div class="wrap-login100" style="width:500px; height:780px;">
							<form class="login100-form" name="formRegister" id="formRegister" method="post" action="" style="padding: 50px">
								<input type="hidden" name="act" value="register">
								<span class="login100-form-title p-b-49">회원가입</span> 
								<br><br>									
								<div class="row">
									<div class="wrap-input100 validate-input m-b-23" data-validate="Username is reauired" align="left" style="width: 70%">
										<span class="label-input100">아이디</span>
										<input class="input100" type="text" id="userId" name="userId" readonly="readonly" placeholder="중복체크를 통해 입력해주세요."/>
										<span class="focus-input100" data-symbol="&#xf206;"></span>
									</div>
									<div class="idcheckDiv">
										<input type="button" value="중복체크" class="btn btn-primary" id="btnCheckId" onclick="javascript:checkId();">
									</div>
								</div>
								<br>
								<div class="row">
									<div class="wrap-input100 validate-input m-b-23" data-validate="Username is reauired" align="left">
										<span class="label-input100">이름 (2~6글자의 한글로 입력해주세요.)</span>
										<input class="input100" type="text" id="userName" name="userName" placeholder="이름입력">
										<span class="focus-input100" data-symbol="&#xf203;"></span>
									</div>
								</div>
								<div id="nameBlank" style="display: none;" align="left"></div>
								<br>
								<div class="row">
									<div class="wrap-input100 validate-input" data-validate="Password is required" align="left">
										<span class="label-input100">비밀번호 (4~16자리의 영문 대소문자 또는 숫자로 입력해주세요.)</span>
										<input class="input100" type="password" id="userPass" name="userPass" placeholder="비밀번호입력">
										<span class="focus-input100" data-symbol="&#xf190;"></span>
									</div>
								</div>
								<div id="pwBlank" style="display: none;" align="left"></div>								
								<br>
								<div class="row">
									<div class="wrap-input100 validate-input" data-validate="Password is required" align="left">
										<span class="label-input100">비밀번호확인</span>
										<input class="input100" type="password" id="passCheck" name="passCheck" placeholder="비밀번호 재입력">
										<span class="focus-input100" data-symbol="&#xf191;"></span>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="wrap-input100 validate-input" data-validate="Password is required" align="left">
										<span class="label-input100">이메일 (비밀번호 찾기에 사용됩니다. 정확히입력해주세요.)</span>
										<input class="input100" type="text" id="userEmail" name="userEmail"	placeholder="이메일입력">
										<span class="focus-input100" data-symbol="&#xf15a;"></span>
									</div>
								</div>
								<div id="emailBlank" style="display: none;" align="left"></div>
								<br><br>
								<div class="d-flex justify-content-center mb-3">
									<div class="col-lg-5">
										<input type="button" value="회원가입" class="btn btn-primary" style="width: 70%;"	onclick="javascript:register();">
									</div>
									<div class="col-lg-5">
										<input type="button" value="취소" class="btn btn-primary" style="width: 70%;" onclick="location.href='${root}/index.jsp'">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<%@ include file="/WEB-INF/views/include/arrowup.jsp"%>
</body>
</html>


