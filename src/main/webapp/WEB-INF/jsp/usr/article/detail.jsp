<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="ARTICLE LIST" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/toastUiEditorLib.jsp"%>

<section class="listBody">
        <section class="title-section border">
            <div class="title">${article.title }</div>
            <div class="alarm-session border w-96">
            <!-- 수정이랑 삭제는 권한인놈만 보이게 수정해야함. -->
            	<div class="btn"><a href="/usr/article/modify?id=${article.id }">수정하기</a></div>
            	<div class="btn"><a class="" href="doDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제하기</a></div>
                <div class="btn">좋아요 버튼</div>
                <div class="btn">구독 버튼</div>
            </div>
        </section>
        <section class="article-read">
            <div class="toast-ui-viewer">
            <!-- 토스트 ui body를 안보여줌. -->
            <script type="text/x-template">${article.body }</script>
       </section>
        <!-- 댓글과 댓글 형태 -->
        <section class="comment-session">
            <div>댓글 갯수(게시글 갯수 세는거랑 동일로직)</div>
            <div class="border">
                <form action="../reply/doWrite" method="post" onsubmit="">
                    <input name="relTypeCode" type="hidden" value="article" />
                    <input name="relId" type="hidden" value="${article.id }" />
                    <div class="mt-4 border border-gray-400 rounded-lg p-4">
                        <div class="mb-2"><span>닉네임</span></div>
                        <textarea class="textarea textarea-bordered w-full" name="body" placeholder="댓글을 입력해보세요"></textarea>
                        <div class="flex justify-end"><button class="btn-text-color btn btn-outline btn-sm">작성</button></div>
                        <div class="border">
                            <form action="" method="post" onsubmit="">
                                <input name="relTypeCode" type="hidden" value="article" />
                                <input name="relId" type="hidden" value="${article.id }" />
                                <div class="mt-4 border border-gray-400 rounded-lg p-4">
                                    <div class="mb-2"><span>닉네임</span></div>
                                    <textarea class="textarea textarea-bordered w-full" name="body" placeholder="댓글을 입력해보세요"></textarea>
                                    <div class="flex justify-end"><button class="btn-text-color btn btn-outline btn-sm">작성</button></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </section>  

<%@ include file="../common/foot.jsp"%>