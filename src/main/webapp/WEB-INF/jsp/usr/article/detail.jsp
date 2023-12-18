<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="ARTICLE LIST" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>

<%@ include file="../common/floatingBanner.jsp"%>

<%@ include file="../common/toastUiEditorLib.jsp"%>

<script>
	//여기서 할꺼.
	$(document).ready(function() {
		// 좋아요 관련
		getRecommendPoint(); // 좋아요 카운트
		
		$('#recommendBtn').click(function(){
			
			let recommendBtn = $('#recommendBtn').hasClass('btn-active');
						
						$.ajax({
							url: "/usr/recommendPoint/doRecommendPoint",
							method: "get",
							data: {
									"coulumnId" : ${article.id },
									"recommendBtn" : recommendBtn
								},
							dataType: "text",
							success: function(data) {
								if (data === "좋아요 성공") {
				                    // 좋아요 성공 시의 처리
				                    $('#recommendBtn').removeClass('btn-active');
				                    $('#recommendBtn').prop('disabled', true); // 버튼 비활성화
				                } else if (data === "좋아요 취소") {
				                    // 좋아요 취소 시의 처리
				                    $('#recommendBtn').addClass('btn-active');
				                } else {
				                    console.error("예상치 못한 응답 : " + data);
				                }
								
							},
							error: function(xhr, status, error) {
								console.error("ERROR : " + status + " - " + error);
							}
						})
						
						location.reload();
			
			
		})
	})
	
	
	const getRecommendPoint = function(){
			$.ajax({
				url: "../recommendPoint/getRecommendPoint",
				method: "get",
				data: {
						"coulumnId" : ${article.id }
					},
				dataType: "json",
				success: function(data) {
					if (data.success) {
						$('#recommendBtn').addClass('btn-active');
					}
				},
				error: function(xhr, status, error) {
					console.error("ERROR : " + status + " - " + error);
				}
			})
		}
		
		//댓글 길이 제한 관련
		const replyInput = $('#reply'); // textarea 
		const textCount = $('#textCount'); // 숫자 올라갈 부분
		const maxLength = 500;

		replyInput.on('input', function () {
			const text = replyInput.val();
			const currentCharacterCount = text.length;

			if (currentCharacterCount > maxLength) {
				replyInput.val(text.slice(0, maxLength));
				textCount.text(maxLength);
			} else {
				textCount.text(currentCharacterCount);
			}
		});

		replyInput.on('input', function() {
			const inputValue = $(this).val();
			
			if (inputValue.length > maxLength) {
				$(this).val(inputValue.slice(0, maxLength));
				alert('댓글은 500자 이내로 작성 가능합니다.');
			}
	});

	const replyForm_onSubmit = function(form) {
		form.reply.value = form.reply.value.trim();
		
		if (form.reply.value.length === 0) {
			alert('댓글을 작성해주세요.');
			form.reply.focus();
			return;
		}
		
		form.submit();
	}	
</script>

<section class="listBody">
        <section class="title-section border p-3 font-semibold">
            <div class="title text-3xl">${article.title }</div>
	            <div class="alarm-session w-96">
	              <div class="btn">좋아요 갯수 : 3</div><!-- 좋아요 갯수 보일 곳. -->
            <c:if  test="${rq.getLoginedMemberId() != 0 }">
	            <!-- 수정이랑 삭제는 권한인놈만 보이게 수정함. -->
	             <c:if test="${rq.getLoginedMemberId() != null && rq.getLoginedMemberId() != article.colmnWrter }">
	                <div class="btn" id="recommendBtn">좋아요 버튼</div>
	                <div class="btn">구독 버튼</div>
	             </c:if>
	             <c:if test="${rq.getLoginedMemberId() != null && rq.getLoginedMemberId() == article.colmnWrter }">
	            	<div class="btn"><a href="/usr/article/modify?id=${article.id }">수정하기</a></div>
	            	<div class="btn"><a class="" href="doDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제하기</a></div>
	        	</c:if>
	        </c:if>
	            </div>
        </section>
        <section class="article-read">
            <div class="toast-ui-viewer">
            <script type="text/x-template">${article.getForPrintBody() }</script>
       </section>
       
        <!-- 댓글과 댓글 형태 -->
	    <section class="comment-session p-1 border">
        	<c:if  test="${rq.getLoginedMemberId() != 0 }">
	            <div>댓글 남기기 : [댓글 갯수](게시글 갯수 세는거랑 동일로직)</div>
	            
	            <div><!-- 댓글 입력 부분 -->
	                <form action="../reply/doWrite" method="post" onsubmit="replyForm_onSubmit(this); return false;">
	                    <input name="columnId" type="hidden" value="${article.id }" /><!-- 이 글 넘버 보냄. -->
	                    <div class="mt-4 border border-gray-400 rounded-lg p-4">
	                        <div class="mb-2"><span>닉네임: ${rq.getLoginedMemberId() } </span></div><!-- 여기 지금은 rq에 pk 번호만 저장되서 이거로 박음 . 수정해야함. 닉네임으로 -->
	                        <textarea id="reply" class="textarea textarea-bordered w-full resize-none" name="reply" placeholder="500자 이내로 댓글을 적어주세요."></textarea>
	                        <div class="flex justify-end ">
	                        	<!-- 글자수 보여주는 부분 -->
	                        	<div class="count_bar"><span id="textCount">0</span>/500</div>
	                        	<button class="btn btn-sm" onclick="">작성</button>
	                        </div>
	                </form>
	            </div>
            </c:if>
            <!-- 입력한 댓글 보여주는 부분 -->
            <div>
            	<c:forEach var="reply" items="${replies }">
				<div class="py-2 pl-16 border-bottom-line">
					<div class="font-semibold">${reply.writerName }</div>
					<div class="my-1 text-lg ml-2">${reply.getForPrintBody() }</div>
					<div class="text-xs text-gray-400">${reply.answerUpdtTime }</div>
            	</div>
            	</c:forEach>
            </div>
	</body>
</html>           
            

