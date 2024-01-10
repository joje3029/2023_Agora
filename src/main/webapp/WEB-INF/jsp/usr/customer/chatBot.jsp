<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<script>
// 누른거에 따라 ajax로 해야겠네.
  (function(){var w=window;if(w.ChannelIO){return w.console.error("ChannelIO script included twice.");}var ch=function(){ch.c(arguments);};ch.q=[];ch.c=function(args){ch.q.push(args);};w.ChannelIO=ch;function l(){if(w.ChannelIOInitialized){return;}w.ChannelIOInitialized=true;var s=document.createElement("script");s.type="text/javascript";s.async=true;s.src="https://cdn.channel.io/plugin/ch-plugin-web.js";var x=document.getElementsByTagName("script")[0];if(x.parentNode){x.parentNode.insertBefore(s,x);}}if(document.readyState==="complete"){l();}else{w.addEventListener("DOMContentLoaded",l);w.addEventListener("load",l);}})();

  ChannelIO('boot', {
    "pluginKey": "86d18a91-f7bf-4710-a116-191a99f17f48"
  });
</script>

		<section class="listBody">
			<div class="flex justify-between">
				<h1 class="text-4xl">고객상담 챗봇</h1>
				<button class="btn"
					onclick="history.back();">뒤로가기</button>
			</div>
			<section class="chat-contents flex backcground2 h-screen">
				<nav class="chat-question mr-4 ">
					<ui>
					<li class="question-item"><a href="#">계정 관리</a></li>
					<li class="question-item"><a href="#">칼럼</a></li>
					<li class="question-item"><a href="#">토론방</a></li>
					<li class="question-item"><a href="#">구독</a></li>
					<li class="question-item"><a href="#">탈퇴</a></li>
					<c:if  test="${rq.getLoginedMemberId() != 0 }">
						<li class="question-item"><a href="customercenter">1:1 고객상담센터</a></li>
					</c:if>
					</ui>
				</nav>
				<div class="chatbot-bubble border border-2 border-green-700 w-full p-6">
					
				</div>
			</section>
		</section>

<%@ include file="../common/foot.jsp"%>