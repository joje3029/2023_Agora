<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="네이버로그인" />

<%@ include file="../common/head2.jsp"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<ul>
	<li th:text="|인가코드 = ${code}|">code</li>
	<li th:text="|유효토큰 = ${access_token}|">code</li>
	<li th:text="|사용자정보 = ${userInfo}|">code</li>
	<li th:text="|동의정보 = ${agreementInfo}|">code</li>
</ul>
<a href="https://kauth.kakao.com/oauth/logout?client_id=2aad40910868e3c5fa9594f8de34a07b&logout_redirect_uri=http://localhost:8080/member/do">로그아웃</a>
  </body>
</html>