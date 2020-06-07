<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<style>
	.modal {
		text-align: center;
	}
	
	@media screen and (min-width: 768px) {
		.modal:before {
			display: inline-block;
			vertical-align: middle;
			content: " ";
			height: 100%;
		}
	}
	
	.modal-dialog {
		display: inline-block;
		text-align: left;
		vertical-align: middle;
		width: 350px;
	}
	</style>
	
	
	<link rel="stylesheet" href="${root}/resources/css/login.css">
	<link rel="stylesheet" href="${root}/resources/fonts/iconic/css/material-design-iconic-font.min.css">
	
	<script type="text/javascript">	
	function openfindpass() {
		window.open("${root}/member/findpass.kok","idcheck","top=200, left=100, width=480, height=400, menubar=no, status=no, toolbar=no, location=no, scrollbars=no");
	}

	function login() {
		var iderrorview = document.getElementById("idBlank");
		var passerrorview = document.getElementById("passBlank");

		if (document.getElementById("userId").value.trim().length == 0) {
			iderrorview.innerHTML = "<font color='red'>아이디를 입력하세요.</font>";
			iderrorview.style = "display:";
			passerrorview.style = "display: none";
			return;
		} else if (document.getElementById("userPass").value.trim().length == 0) {
			passerrorview.innerHTML = "<font color='red'>비밀번호를 입력하세요.</font>";
			passerrorview.style = "display:";
			iderrorview.style = "display: none";
			return;
		} else {
			document.getElementById("formLogin").setAttribute("action", "${root}/member/login/login.do");
			document.getElementById("formLogin").submit();
		}
	}
	
    $("body").on("hidden.bs.modal", ".modal", function () {
    	document.getElementById("userId").value = null;
    	document.getElementById("userPass").value = null;
    });
	</script>
	<!-- Modal -->
	<div class="modal fade" id="myLoginModal" role="dialog">
		<div class="modal-dialog">
	
			<!-- Modal content-->
			<div class="modal-content">
				<div>
					<form class="login100-form validate-form" id="formLogin" method="post" action="">
						<input type="hidden" name="act" value="login">
						<div class="loginModalHead" align="center">
							<br>
							<h2>
								<span class="glyphicon glyphicon-lock">로그인</span>
							</h2>
						</div>
						<br> <br>
						<div class="wrap-input100 validate-input m-b-23" data-validate="Username is reauired" align="left">
							<span class="label-input100">아이디</span>
							<input class="input100"	type="text" name="userId" id="userId"	placeholder="아이디를 입력해주세요.">
							<span class="focus-input100" data-symbol="&#xf206;"></span>
						</div>
						<div id="idBlank" style="display: none;"></div>
						<br>
						<div class="wrap-input100 validate-input" data-validate="Password is required" align="left">
							<span class="label-input100">비밀번호</span>
							<input class="input100"	type="password" name="userPass" id="userPass"	placeholder="비밀번호를 입력해주세요."	onkeypress="if(event.keyCode == 13){login(); return;}">
							<span class="focus-input100" data-symbol="&#xf190;"></span>
						</div>
						<div id="passBlank" style="display: none;"></div>
						<!-- <div class="text-right">
							<a href="javascript:openfindpass();"> 비밀번호를 잊으셨나요??!</a>
						</div> -->
						<br>
						<div class="d-flex justify-content-center mb-3">
							<div class="p-2">
								<input type="button" value="로그인" class="btn btn-primary py-3 px-4" onclick="javascript:login();"> &nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="회원가입"	class="btn btn-primary py-3 px-4" onclick="location.href='${root}/member/join/mvRegister.do'">
							</div>
						</div>
					</form>
				</div>
			</div>
	
		</div>
	</div>
