<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<%@ include file="../common/toastUiEditorLib.jsp" %>

		<section class="login">
        <h1 class="text-4xl">칼럼 작성</h1>
        <form action="doWrite" method="post" onsubmit="submitForm(this); return false;">
				<input name="body" type="hidden" />
            <input name="body" type="hidden" />
            <div class="table-box-type">
                <table class="table table-lg">
                    <tr>
                        <td class="text-center" colspan="2">
                            <p><span class="text-red-700">*</span>컬럼 분류 설정은 필수입니다</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center" colspan="2">
                            <!-- 이거 alert common->toastUiEditorLib.jsp의 script에 추가해야함. -->
                            <select class="select select-bordered max-w-xs select-sm">
                                <option disabled selected>컬럼 분류</option>
                                <option>철학</option>
                                <option>종교</option>
                                <option>사회과학</option>
                                <option>자연과학</option>
                                <option>기술과학</option>
                                <option>예술</option>
                                <option>언어</option>
                                <option>문학</option>
                                <option>역사</option>
                              </select>
                            <!-- 임시저장은 안되면 빼야해서 일단 div. submit꼬이는거 싫어 -->
                            <div class="btn btn-sm ml-1">임시저장</div>
                            <button class="btn btn-sm ml-1">작성</button>
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td><input class="input input-bordered w-full max-w-xs input-sm" name="title" type="text" placeholder="제목을 입력해주세요" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            <div class="toast-ui-editor"></div>
                        </td>
                    </tr>
                </table>
        </form>
    </section>
	</body>
</html>