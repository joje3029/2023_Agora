// index.jsp랑 연결된거 맞는지 확인. -> 연결 됨.
// 문서가 준비되면 실행
$(document).ready(function(){
  // 클릭 이벤트 핸들러 설정
  $("#myButton").click(function(){
    alert("버튼이 클릭되었습니다!");
  });
});

// let remoteStreamElement = document.querySelector('#remoteStream');
 let localStreamElement = document.querySelector('#localStream'); // 내 웹캠 화면 화면을 보여주는 video html
 const myKey = Math.random().toString(36).substring(2, 11); // 내 key를 만듦 : 길이가 9인 랜덤한 36진법 문자열 * 36진법 : 0-9와 a-z까지의 문자를 사용하는 진법 
 let pcListMap = new Map(); // 피어커넥션을 저장할 Map 객체 초기화  //Map 형태 : 키-값 쌍 * 자바스크립트의 MAP은 유연한 키 타입
 let roomId; // 방의 식별자를 저장할 변수 초기화
 let otherKeyList = []; // 다른 피어들의 키를 저장할 배열 초기화
 let localStream = undefined; // 사용자의 로컬 웹캠 및 마이크 스트림을 저장할 변수 초기화

// 웹캠 및 마이크 스트림을 가져와서 사용하는 코드 -> 웹캠 및 마이크 스트림을 가져와서 페이지에 표시하는 것.
// * 스트림 : 데이터의 연속적인 흐름을 나타내는 개념. 여기서는 미디어 스트림을 의미.
 const startCam = async () =>{ // 비동기 함수 사용. // async : 비동기 함수를 정의할 때 사용하는 키워드. 
     // 미디어 딩바이스에 액세스 가능한지 확인
     if(navigator.mediaDevices !== undefined){
		 // 오디오와 비디오 스트림 가져오기
         await navigator.mediaDevices.getUserMedia({ audio: true, video : true }) // 여기서 MdiaStream 객체를 사용해서 미디어 스트림을 가져오고 있음.
             .then(async (stream) => {
                 console.log('Stream found');
 				//웹캠, 마이크의 스트림 정보를 글로벌 변수로 저장한다. // 로컬 스트림 변수에 스트림 저장
                 localStream = stream;
                 // 마이크 기본적으로 활성화
                 stream.getAudioTracks()[0].enabled = true;
                 // 로컬 스트림을 웹페이지의 video 태그에 설정하여 보여줌.
                 localStreamElement.srcObject = localStream;
                 
    
             }).catch(error => {
				 // 오류 발생시 콘솔에 기록
                 console.error("Error accessing media devices:", error);
             });
     }
    
 }
    
 // 소켓 연결 -> 소켓이 연결되고 연결이 성공하면 특정 주제를 구독하고, 해당 주제로 메시지가 도착하면 수신한 후보(peer로부터 받은 ICE 후보) 정보를 처리하는 역할을 수행함.
 const connectSocket = async () =>{
     const socket = new SockJS('/signaling'); // WebSocketStomp 설정에 화상관련으로 endpoint 한거
     stompClient = Stomp.over(socket); // 주어진 socket을 사용하여 STOMP 클라이언트를 생성하는 메서드. socket은 Websocket연결
     stompClient.debug = null; // STOMP 클라이언트의 디버깅 로그를 비활성화 하는 역할.
    
     stompClient.connect({}, function () {
         console.log('Connected to WebRTC server'); // 연결 성공 시 실행되는 콜백 함수
            
 		//iceCandidate(수신한 후보) peer 교환을 위한 subscribe
         stompClient.subscribe(`/topic/peer/iceCandidate/${myKey}/${roomId}`, candidate => {
			 // 수신한 메시지에서 피어의 키(Key)와 메시지(body)를 추출
             const key = JSON.parse(candidate.body).key
             const message = JSON.parse(candidate.body).body;
    
 			 // 해당 key에 해당되는 peer 에 받은 정보를 addIceCandidate 해준다.
             pcListMap.get(key).addIceCandidate(new RTCIceCandidate({candidate:message.candidate,sdpMLineIndex:message.sdpMLineIndex,sdpMid:message.sdpMid}));
    		//pcListMap에서 해당 키에 해당하는 피어를 찾아, 'addIceCandidate'메서드를 사용하여 수신한 후보 정보를 해당 피어에 추가.
         });
    				
 		//offer peer 교환을 위한 subscribe
 		// WebRTC에서의 offer를 교환하기 위한 STOMP 메시징을 처리하는 부분. 
         stompClient.subscribe(`/topic/peer/offer/${myKey}/${roomId}`, offer => { // offer 는 WebRTC 피어 간에 세션을 설정하기 위한 SDP정보를 의미.
             // 수신한 메시지에서 피어의 키(key)와 메시지(body)를 추출
             const key = JSON.parse(offer.body).key;
             const message = JSON.parse(offer.body).body;
    						
 			// 해당 key에 새로운 peerConnection 를 생성해준후 pcListMap 에 저장해준다.
             pcListMap.set(key,createPeerConnection(key)); // 추출한 키를 사용하여 새로운 peerConnection을 생성하고, 이를 pcListMap에 저장. createPeerConnectino함수는 새로운 RTCPeerConnection 객체를 생성하는 함수.
 			// 생성한 peer 에 offer정보를 setRemoteDescription 메서드를 사용하여 설정 해준다. -> 피어로부터 수신한 SDP 정보를 사용하여 원격 설명을 설정하는 것을 의미.
             pcListMap.get(key).setRemoteDescription(new RTCSessionDescription({type:message.type,sdp:message.sdp}));
             // setRemoteDescrtption : WebRTC에서 사용되는 메서드 중 하나.
             //RTCSEssionDesctiption 객체를 사용하여 원격(peer)의 세션(회의) 설명을 설정하는 역할을 함.
             //WebRTC 연결에서 중요한 역할을 함. SDP 정보를 사용하여 원격 피어로부터 수신한 미디어 연결 및 설정 정보를 설정하는 데 사용도미.
             //setRemoteDescrtiption 메서드는 RTCPeerConnection 객체의 메서드로 사용되며, 보통 offer/answer 모델에서 원격 피어로부터 받은 RTCSessionDescription 객체를 전달하여 호출하게 됨.
             
             //sendAnswer 함수를 호출. 해당 피어에 응답을 보냄. 해당 피어에게 answer를 생성하고 보내는 역할.
 			 sendAnswer(pcListMap.get(key), key);
    
         });
    				
 		//answer peer 교환을 위한 subscribe
         stompClient.subscribe(`/topic/peer/answer/${myKey}/${roomId}`, answer =>{
             const key = JSON.parse(answer.body).key;
             const message = JSON.parse(answer.body).body;
    						
 			// 해당 key에 해당되는 Peer 에 받은 정보를 setRemoteDescription 해준다.
             pcListMap.get(key).setRemoteDescription(new RTCSessionDescription(message));
    
         });
    				
 		 //key를 보내라는 신호를 받은 subscribe
         stompClient.subscribe(`/topic/call/key`, message =>{
 			//자신의 key를 보내는 send
             stompClient.send(`/app/send/key`, {}, JSON.stringify(myKey));
    
         });
    				
 		 //상대방의 key를 받는 subscribe
         stompClient.subscribe(`/topic/send/key`, message => {
             const key = JSON.parse(message.body);
    						
 			//만약 중복되는 키가 ohterKeyList에 있는지 확인하고 없다면 추가해준다.
             if(myKey !== key && otherKeyList.find((mapKey) => mapKey === myKey) === undefined){
                 otherKeyList.push(key);
             }
         });
    
     });
 }

// WebRTC에서의 onTrack 이벤트 핸들러. 원격피어(remote peer)의 미디어 트랙을 처리하는 역할.
// onTrack 함수 == 원격 피어로부터 전송된 미디어 스트림을 받아와 비디오 요소를 동적으로 생성하고, 이를 문서에 추가하여 원격 피어의 화면을 표시하는 역할.
 let onTrack = (event, otherKey) => { // 매개변수 : event-RTCTrackEvent 객체. 이벤트가 발생한 시점에서의 미디어 스트림 정보 제공, otherkey- 해당 이벤트와 연관된 원격 피어의 식별자
    // 문서에 id가 otherKey라는 요소가 없으면 실행
     if(document.getElementById(`${otherKey}`) === null){  
         const video =  document.createElement('video'); // video 요소를 생성한다.
    
         video.autoplay = true; // 자동으로 속성을 설정함.
         video.controls = true; // 컨트롤러 표시
         video.id = otherKey; // otherKey를 사용하여 고유한 식별자 부여
         video.srcObject = event.streams[0]; // 이벤트에서 제공된 스트림을 비디오 요소의 소스로 설정
    		
    	// 생성한 비디오를 remoteStreamDiv에 추가
         document.getElementById('remoteStreamDiv').appendChild(video);
     }
    
     //
     // remoteStreamElement.srcObject = event.streams[0];
     // remoteStreamElement.play();
 };
 
 //peer연결 생성   
 const createPeerConnection = (otherKey) =>{ // 매개를 otherkey(해당 이벤트와 연관된 원격 피어의 식별자)로 받음
     const pc = new RTCPeerConnection(); // WebRTC에서 사용되는 객체. 피어 간의 연결을 나타냄. 이 객체는 웹 브라우저에서 WebRTC를 사용하여 미디어 스트림을 교환할 때 핵심적인 역할을 함. 피어간에 미디어 스트림을 안전하고 신뢰성 있게 교환할 수 있도록 도와줌.
     // RTCPeerConnection기능 :(1) 피어간의 연결 설정 (2)SDP(세션 설명 프로토콜) (3) ICE 프레임워크 지원 (4)미디어 스트림 관리 (5) 이벤트 처리 (6) DTLS 및 SRTP 기반의 보안
     try {
         pc.addEventListener('icecandidate', (event) =>{
             onIceCandidate(event, otherKey);
         });
         pc.addEventListener('track', (event) =>{
             onTrack(event, otherKey);
         });
         if(localStream !== undefined){
             localStream.getTracks().forEach(track => {
                 pc.addTrack(track, localStream);
             });
         }
    
         console.log('PeerConnection created');
     } catch (error) {
         console.error('PeerConnection failed: ', error);
     }
     return pc;
 }
    
 let onIceCandidate = (event, otherKey) => {
     if (event.candidate) {
         console.log('ICE candidate');
         stompClient.send(`/app/peer/iceCandidate/${otherKey}/${roomId}`,{}, JSON.stringify({
             key : myKey,
             body : event.candidate
         }));
     }
 };
    
 let sendOffer = (pc ,otherKey) => {
     pc.createOffer().then(offer =>{
         setLocalAndSendMessage(pc, offer);
         stompClient.send(`/app/peer/offer/${otherKey}/${roomId}`, {}, JSON.stringify({
             key : myKey,
             body : offer
         }));
         console.log('Send offer');
     });
 };
    
 let sendAnswer = (pc,otherKey) => {
     pc.createAnswer().then( answer => {
         setLocalAndSendMessage(pc ,answer);
         stompClient.send(`/app/peer/answer/${otherKey}/${roomId}`, {}, JSON.stringify({
             key : myKey,
             body : answer
         }));
         console.log('Send answer');
     });
 };
    
 const setLocalAndSendMessage = (pc ,sessionDescription) =>{
     pc.setLocalDescription(sessionDescription);
 }
    
 //룸 번호 입력 후 캠 + 웹소켓 실행
 document.querySelector('#enterRoomBtn').addEventListener('click', async () =>{
     await startCam();
    
     if(localStream !== undefined){
         document.querySelector('#localStream').style.display = 'block';
         document.querySelector('#startSteamBtn').style.display = '';
     }
     roomId = document.querySelector('#roomIdInput').value;
     document.querySelector('#roomIdInput').disabled = true;
     document.querySelector('#enterRoomBtn').disabled = true;
    
     await connectSocket();
 });
    
 // 스트림 버튼 클릭시 , 다른 웹 key들 웹소켓을 가져 온뒤에 offer -> answer -> iceCandidate 통신
 // peer 커넥션은 pcListMap 으로 저장
 document.querySelector('#startSteamBtn').addEventListener('click', async () =>{
     await stompClient.send(`/app/call/key`, {}, {});
    
     setTimeout(() =>{
    
         otherKeyList.map((key) =>{
             if(!pcListMap.has(key)){
                 pcListMap.set(key, createPeerConnection(key));
                 sendOffer(pcListMap.get(key),key);
             }
    
         });
    
     },1000);
 });