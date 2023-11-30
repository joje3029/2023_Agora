<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<%@ include file="../common/toastUiEditorLib.jsp" %>

		<section class="login">
        <h1 class="text-4xl">비밀번호 찾기</h1>
	            <form action="doLogin" method="post"
	                    onsubmit="loginForm_onSubmit(this); return false;">
                        <table>
                            <tr>
                                <th><span class="text-red-700">*</span>이름</th>
                                <td><input type="text" name="name" placeholder="이름을 입력해주세요"
                                    class="input input-bordered w-full max-w-xs input-sm"></td>
                            </tr>
                            <tr>
                                <th><span class="text-red-700">*</span>아이디</th>
                                <td class="flex"><input type="text" name="loginId"
                                    placeholder="로그인 입력해주세요"
                                    class="input input-bordered w-full max-w-xs input-sm">
                                    <div class="btn btn-sm ml-1">중복확인</div></td>
                            </tr>
                            <tr>
                                <th><span class="text-red-700">*</span>이메일</th>
                                <td><input type="text" name="email" placeholder="이메일 입력해주세요"
                                    class="input input-bordered w-full max-w-xs input-sm">
                                    <div class="btn btn-sm ml-1">인증번호 받기</div></td>
                            </tr>
                            <tr>
                                <th><span class="text-red-700">*</span>본인인증</th>
                                <td class="flex"><input type="text" name="certification" placeholder="본인인증번호를 입력해주세요"
                                    class="input input-bordered w-full max-w-xs input-sm">
                                    <div class="btn btn-sm ml-1">인증번호 확인</div></td>
                            </tr>
                            <tr>
                                <th colspan="2">
                                    <button class="btn">비밀번호 찾기</button>
                                </th>
                            </tr>
                        </table>
	            </form>
    </section>
	</body>
</html>