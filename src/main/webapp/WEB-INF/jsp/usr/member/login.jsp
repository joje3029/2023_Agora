<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="USER LOGIN" />

<%@ include file="../common/head2.jsp"%>
<!-- 네이버 로그인을 위한 cdn-->
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>

<!-- 구글 로그인을 위해 -->
<meta name ="google-signin-client_id" content="156368883589-0hdsi099embd282knv5uhadn8t93sem9.apps.googleusercontent.com">

<script>
		// 로그인이랑 비밀번호 글자 제한
		$(document).ready(function() {
		    const loginIdInput = $('#loginId');
		    const loginPwInput = $('#loginPw');

		    loginIdInput.on('input', function() {
		        const inputValue = $(this).val();
		        
		        if (inputValue.length > 30) {
		        	 $(this).val('');// 내용 비움.
		            alert('아이디는 영문 대소문자와 숫자로 1~30자 이내여야 합니다.');
		        }
		    });
		    
		    loginPwInput.on('input', function() {
		    	const inputValue = $(this).val();
				        
				if (inputValue.length > 50) {
					$(this).val('');// 내용 비움.
				    alert('비밀번호는 영문 대문자, 소문자, 숫자, 특수문자를 각각 1개 이상 포함하여 10~50자 이내여야 합니다.');
					}
				});
		});
		
		// 아무것도 입력 안했을 때
			const loginForm_onSubmit = function(form) {
				form.loginId.value = form.loginId.value.trim();
				form.loginPw.value = form.loginPw.value.trim();
				
				if (form.loginId.value.length == 0) {
					alert('아이디를 입력해주세요');
					form.loginId.focus();
					return;
				}
				
				if (form.loginPw.value.length == 0) {
					alert('비밀번호를 입력해주세요');
					form.loginPw.focus();
					return;
				}
				
				form.submit();
			}
		</script>

<section class="login boder">
	<h1 class="text-4xl">로그인</h1>
	<div class="container">
		<form action="doLogin" method="post"
			onsubmit="loginForm_onSubmit(this); return false;">
			<div class="table-box-type">
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="loginId" placeholder="로그인 입력해주세요" class="input input-bordered w-full max-w-xs input-sm"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="loginPw" placeholder="비밀번호 입력해주세요" class="input input-bordered w-full max-w-xs input-sm"></td>
					</tr>
					<tr>
						<td class="text-center" colspan="2"><button
								class="btn">로그인</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="account_row">
	                <div class="account_left"><a href="/usr/member/join">회원가입</a></div>
	                <div class="account_right">
	                    <div class="loginId"><a href="/usr/member/findId">아이디찾기</a></div>
	                    <div class="passWord"><a href="/usr/member/findPw">비밀번호</a></div>
	                </div>
	            </div>
	            <hr>
	            <div class="external_login">
	            	<div>
	            	<!-- 카카오 로그인 이미지를 누르면 href의 주소로 날라감.  -->
		            	<a href="https://kauth.kakao.com/oauth/authorize?client_id=b17693f42efb99ea5feb3e636ab6eb1b&redirect_uri=http://localhost:8081/usr/member/kakaoLogin&response_type=code">
			                <img src="/resource/images/kakao_login.png" alt="카카오로그인">
			            </a>
	            	</div>
	            	<div>
	            		<a href="/usr/member/naverLogin">
		                	<img src="/resource/images/naver_login.png" alt="네이버로그인">
		                </a>
	            	</div>
	            	<div>
	            		<a href="/usr/member/googleLogin">
			                <img src="/resource/images/google_login.png" alt="구글로그인">
			            </a>  
	            	</div>
	                <div>QR코드들어올자리</div>
	            </div>
	            
	            <!-- 네이버 로그인 테스트 html -->
				<sesction>
				<ul>
					<li class="border">
						<!-- 아래와같이 아이디를 꼭 써준다. --> <a id="naverIdLogin_loginButton"
						href="javascript:void(0)"> <span>네이버 로그인</span>
					</a>
					</li>
					<li class="border" onclick="naverLogout(); return false;"><a
						href="javascript:void(0)"> <span>네이버 로그아웃</span>
					</a></li>
				</ul>
				</sesction>
				
				<!-- 네이버 로그인 테스트 스크립트 -->
				<script>
					var naverLogin = new naver.LoginWithNaverId({
						clientId : "sre3apwylaef28oEMZxP", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
						callbackUrl : "http://localhost:8081/usr/member/login", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
						isPopup : false,
						callbackHandle : true
					});
					
					naverLogin.init();
					//로그인 하는부분
					window.addEventListener('load', function() {
						naverLogin.getLoginStatus(function(status) {
							if (status) {
								var email = naverLogin.user.getEmail(); // 필수로 설정할것을 받아와 아래처럼 조건문을 줍니다.
			
								console.log(naverLogin.user);
			
								if (email == undefined || email == null) {
									alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
									naverLogin.reprompt();
									return;
								}
							} else {
								console.log("callback 처리에 실패하였습니다.");
							}
						});
					});
			
					var testPopUp;
					function openPopUp() {
						testPopUp = window
								.open("https://nid.naver.com/nidlogin.logout", "_blank",
										"toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
					}
					function closePopUp() {
						testPopUp.close();
					}
					// 로그아웃
					function naverLogout() {
						openPopUp();
						setTimeout(function() {
							closePopUp();
						}, 1000);
			
					}
				</script>
				
				<!-- 구글 로그인 테스트 html -->
				<sesction>
				<ul>
					<li id="GgCustomLogin"><a href="javascript:void(0)"> <span>Login
								with Google</span>
					</a></li>
				</ul>
				</sesction>
				<!-- 구글 로그인 스크립트 -->
				<script>
				//처음 실행하는 함수
				function init() {
					gapi.load('auth2', function() {
						gapi.auth2.init();
						options = new gapi.auth2.SigninOptionsBuilder();
						options.setPrompt('select_account');
				        // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
						options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
				        // 인스턴스의 함수 호출 - element에 로그인 기능 추가
				        // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
						gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
					})
				}
				
				function onSignIn(googleUser) {
					var access_token = googleUser.getAuthResponse().access_token
					$.ajax({
				    	// people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
						url: 'https://people.googleapis.com/v1/people/me'
				        // key에 자신의 API 키를 넣습니다.
						, data: {personFields:'birthdays', key:'AIzaSyBOdmeC4SOSzXmPGLEM2vZueqiBSWKg3wk', 'access_token': access_token}
						, method:'GET'
					})
					.done(function(e){
				        //프로필을 가져온다.
						var profile = googleUser.getBasicProfile();
						console.log(profile)
					})
					.fail(function(e){
						console.log(e);
					})
				}
				function onSignInFailure(t){		
					console.log(t);
				}
				</script>
				//구글 api 사용을 위한 스크립트
				<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>

</section>
</body>
</html>