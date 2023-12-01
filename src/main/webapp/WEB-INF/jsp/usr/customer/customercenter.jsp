<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

		<section class="listBody">
			<div class="flex justify-between">
				<h1 class="backcground2 my-6">1:1 고객 상담</h1>
			</div>
			<section class="table-section flex justify-center ">
				<form action="doLogin" method="post"
					onsubmit="loginForm_onSubmit(this); return false;">
					<table>
						<tr>
							<th><span class="text-red-700">*</span>제목</th>
							<td><input type="text" name="title" placeholder="제목을 입력해주세요"
								class="input input-bordered w-full max-w-xs input-sm"></td>
						</tr>
						<tr>
							<th><span class="text-red-700">*</span>내용</th>
							<td class="flex"><textarea class="textarea textarea-bordered"
									name="body" cols="50" rows="10" placeholder="내용 입력해주세요"></textarea>
						</tr>
						<tr>
							<th><span class="text-red-700">*</span>첨부사진</th>
							<td><input type="file" name="file" placeholder="첨부사진 입력해주세요"
								class="input input-bordered w-full max-w-xs input-sm">
								<!--  파일선택씨는 수정해야함. input 타입을 file로 해서 생긴 저거랑 내가만든 저놈이랑 붙여야함. -->
								<div class="btn btn-sm ml-1">파일선택</div>
								<div class="btn btn-sm ml-1">파일삭제</div></td>
						</tr>
						<tr>
							<th colspan="2">
								<div class="btn">
									<a href="/">취소</a>
								</div>
								<button class="btn">확인</button>
							</th>
						</tr>
					</table>
			</section>
		</section>

	<%@ include file="../common/foot.jsp"%>