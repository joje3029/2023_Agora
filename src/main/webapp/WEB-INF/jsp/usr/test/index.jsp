<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="${board.name }" />

<!-- head2로 할지 head로 할지 정해야함. -->
<%@ include file="../common/head2.jsp"%>

<!-- 웹소캣 연결에 필요한 라이브러리 선언 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<div>
			<!-- 룸 아이디 번호를 입력하는 input -->
	    <input type="number" id="roomIdInput" /><!--  타입이 넘버여서 위아래버튼이 나는거 -->
			<!-- 룸 아이디를 입력후 클릭하는 button => 이걸 누르면 간다! --> 
	    <button type="button" id="enterRoomBtn">enter Room</button>
			<!-- enterRoomBtn 클릭시 나타남, Streams 정보를 담은 Peer 를 웹소켓 ( 시그널링 )   -->
	    <button type="button" id="startSteamBtn" style="display: none;">start Streams</button>
	</div>
			<!-- 내 웹캠 화면을 보여주는 video html -->
	    <video id="localStream" autoplay playsinline controls style="display: none;"></video>
	    <!-- autoplay : 비디오가 자동으로 재생되도록 지시.  -->
	    <!-- playsinline : ios 기기에서 비디오가 전체화면으로 이동하지 않고 인라인으로 재생되도록 지시  -->
	    <!-- controls : 비디오 플레이어에 컨트롤(재생, 일시정지, 음소거 등)을 표시하도록 지시함.  -->
			
			<!-- WebRTC에 연결된 웹캠들이 추가되는 Div  -->
	    <div id="remoteStreamDiv">
	    </div>
			<!-- webRTC 연결을 위한 js  -->
	    <script src="peerConfig.js"></script>

	</body>
</html>
