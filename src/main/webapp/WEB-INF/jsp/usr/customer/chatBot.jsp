<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<!-- 미친짓하기 : 시나리오봇 셀프로 만든다! APi 들이 내가 원하는데로 안해주니까. 시도 -->
<script src="/resource/chatBot.js"></script>

<script>
// 채널톡 씨
//  (function(){var w=window;if(w.ChannelIO){return w.console.error("ChannelIO script included twice.");}var ch=function(){ch.c(arguments);};ch.q=[];ch.c=function(args){ch.q.push(args);};w.ChannelIO=ch;function l(){if(w.ChannelIOInitialized){return;}w.ChannelIOInitialized=true;var s=document.createElement("script");s.type="text/javascript";s.async=true;s.src="https://cdn.channel.io/plugin/ch-plugin-web.js";var x=document.getElementsByTagName("script")[0];if(x.parentNode){x.parentNode.insertBefore(s,x);}}if(document.readyState==="complete"){l();}else{w.addEventListener("DOMContentLoaded",l);w.addEventListener("load",l);}})();

//  ChannelIO('boot', {
    //"pluginKey": "86d18a91-f7bf-4710-a116-191a99f17f48"
//  });

//깃플
//window.GitpleConfig = {
//  appCode: 'xjWIL0ZP9yz667q0TunQqeWWrs6693'
//};

//!function(){function e(){function e(){var e=t.contentDocument,a=e.createElement("script");a.type="text/javascript",a.async=!0,a.src=window[n]&&window[n].url?window[n].url+"/inapp-web/gitple-loader.js":"https://app.gitple.io/inapp-web/gitple-loader.js",a.charset="UTF-8",e.head&&e.head.appendChild(a)}var t=document.getElementById(a);t||((t=document.createElement("iframe")).id=a,t.style.display="none",t.style.width="0",t.style.height="0",t.addEventListener?t.addEventListener("load",e,!1):t.attachEvent?t.attachEvent("onload",e):t.onload=e,document.body.appendChild(t))}var t=window,n="GitpleConfig",a="gitple-loader-frame";if(!window.Gitple){document;var i=function(){i.ex&&i.ex(arguments)};i.q=[],i.ex=function(e){i.processApi?i.processApi.apply(void 0,e):i.q&&i.q.push(e)},window.Gitple=i,t.attachEvent?t.attachEvent("onload",e):t.addEventListener("load",e,!1)}}();

//Gitple('boot'); // 로그인 사용자라면 아래 추가 정보 부분을 확인

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
					<li class="question-item" data-question-id="1">계정 관리</li>
					<li class="question-item" data-question-id="2">칼럼</li>
					<li class="question-item" data-question-id="3">토론방</li>
					<li class="question-item" data-question-id="4">구독</li>
					<li class="question-item" data-question-id="5">탈퇴</li>
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