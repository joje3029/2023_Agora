// 내가 원하는데로 수정한 웹 소켓.js

'use strict'; // 스크립트를 엄격한 모드로 실행하도록 지시하는 역할

let stompClient = null; // 밑에서 쓰니까 전역으로 미리 뺀거네
let memberId = null; //
let memberNickname = null; // 
let hostMemberId = null; //
let connectingElement = document.querySelector('#connecting'); // 연결중 div
let messageArea = document.querySelector('#message-area'); // 채팅되는 부분ul
let messageForm = document.querySelector('#message-form'); // 내가 쓴 메세지 보내기 부분 form
let messageInput = document.querySelector('#message-input'); // 내가 쓴 메세지 보내기 안에 있는 input 부분
let exitButton = document.querySelector('#exit-button'); // 나가기

//	URL 에서 discussionId 파라미터 값 가져오기 -> 나는 discussionId 파라미터값을 가져와야겠네.  
const url = new URL(location.href).searchParams; // searchParams : URL 쿼리 문자열을 나타내는 URLSearchParams 객체에 대한 참조 
// const url = new URL(location.href); 은 현재 문서의 URL을 나타내는 객체를 생성함. 
// URL.searchparams는 해당 URL의 쿼리 문자열 부분을 나타내는 URLSearchparams 객체를 반환
// => 즉, 현재 문서의 URL에서 쿼리 문자열을 추출하여 URLSearchparams 객체로 얻게 됨.
const discussionId = url.get('discussionId'); // 쿼리 문자열에서 discussionId라는 이름의 매개변수의 값을 가져오고 있음.

console.log("url"+url); //discussionId=8
console.log("discussionId"+discussionId); //discussionId8


// 웹페이지의 모든 리소스(이미지, 스타일시트, 스크립트 등)가 완전히 로드되고 나면 실행되는 이벤트.
// 브라우저가 웹 페이지의 모든 내용을 성공적으로 불러온 후에 이 이벤트 발생
//그지 대화창이 떠야하는데 다 가져오기도 전에 이게 뜨면 곤란해.
window.onload = function connect(event) {
	
	//	주소창 누르고 새로고침하면 퇴장, 입장 반복되던 부분 안되게

//	기능 만드는 동안 주석 - 불편
//	if (performance.navigation.type == 1) {
//		alert("정상적인 접근이 아닙니다.");
//		location.href = '/usr/chat/chatRoomList';
//		return;
//	}

	// 이 if 코드는 웹 페이지가 새로고침될 때의 처리를 하는 부분
	// performance.navigation.type : 사용자의 페이지 네비게이션 유형을 나타내는 속성 중 하나.
	// performance.navigation.type == 1 : 페이지가 새로 고침되었음을 의미. 새로고침이나 다른 페이지로의 이동 등이 일어날 때마다 performance.navigation.type은 특정 값으로 설정됨. 그중에서 1은 새로고침.
	// 이렇게 해서 사용자가 새로고침을 통해 반복적으로 입장, 퇴장하는 상황을 방지하려는 것으로 보임.

	
	memberId = document.querySelector('#member-id').value.trim(); // 내가 궁금해 하는 member.id input
	memberNickname = document.querySelector('#member-nickname').value.trim(); // 내가 궁금해 하는 member.nickname input
	hostMemberId = document.querySelector('#host-member-id').value.trim(); // 내가 궁금해 하는 chatRoom.memberId input
	
	console.log("memberId"+memberId);
	console.log("memberNickname"+memberNickname);
	console.log("hostMemberId"+hostMemberId);
	
	
	//	연결하고자 하는 Socket 의 endPoint (WebSocketStompConfig에서 정한 endPoint)
	let socket = new SockJS('/ws-stomp'); // SockJS를 통해 WebSocket 연결을 수립하려는 것. WebsocketStompConfig에 가면 있으셔
    stompClient = Stomp.over(socket); // Stomp.js 라이브러리의 over 메서드를 사용하여 WebSocket을 Stomp 클라이언트로 래핑함. 이렇게 하면 Stomp 프로토콜을 사용하여 서버와의 통신이 가능해짐.
	
	stompClient.connect({}, onConnected, onError); // Stomp 클라이언트를 서버에 연결함. 
	// {}는 헤더 정보를 나타내며, 일반적으로 빈 객체로 전달되는 경우가 많음.
	// onConnected : 연결이 성공한 경우 호출될 콜백 함수. 연결이 확립되면 실행되는 로직을 정의함.
	// onError : 연결이 실패한 경우 호출될 콜백함수. 연결 실패 시 실행될 로직을 정의함.
	
	event.preventDefault(); // 이벤트의 기본 동작을 취소하는 역할을 함. <form> 요소에서 submit 이벤트가 발생할 때 페이지 전체를 새로고침하는 기본 동작을 막아주기 위해 사용.
	//여기의 event는 submit 이벤트 객체를 나타냄.

}

// 연결하는 함수
function onConnected() {
	
	console.log("WebSocket 연결 성공!"); // 여기까지 오심

    //	sub 할 url => /sub/usr/chat/joinChatRoom/discussionId 로 구독한다
    stompClient.subscribe('/sub/usr/chat/' + discussionId, onMessageReceived);
    //여기까지도 출력됨 id : sub-0 destination 나오잖아
    // 나는 /joinChatRoom/discussionId -> chat/discussionId 로 변경


    //	서버에 memberNickname 을 가진 멤버가 들어왔다는 것을 알림
    //	/pub/usr/chat/enterMember 로 메시지를 보냄
	//  stompClient.send(destination, headers, body) : STOMP 메시지를 서버로 전송하는데 사용 됨.
	// destination : 메시지가 전송될 대상 주소
	// headers : 메시지에 추가할 헤더 정보. 메세지에 추가할 헤더 정보
	// body : 메시지의 본문. 문자열/ 객체 등이 될수 있음. 
    stompClient.send('/pub/usr/chat/enterMember', // /pub/usr/chat/enterMember 는 WebsocketStompConfig 의 configureMessageBroker의 메시지 보낼때 를 보면 됨
        {},
        JSON.stringify({ // JSON.stringify를 사용하여 js 객체를 JSON 문자열로 변환한 것을 전송함.
            'discussionId' : discussionId,
            'memberId' : memberId,
            'message' : memberNickname + ' 님이 입장하셨습니다.',
            'memberNickname' : memberNickname,
            'messageType' : 'ENTER' // 들어왔다.
        })
    )// 여까지도 잘 됨. 그래서 이게 만들어졌음을 표시됨. 
    
	//connectingElement.classList.add('hidden'); // 연결중 div의 요소 클래스에 접근. hidden -> 즉, 잘 연결되면 연결중을 안보이게 함.
	//일단 이걸 주석 처리하면 classList를 못찾아서 나는 에러는 없음.
	//문제는 화면에 sen내용이 안떠. 씨댕.
}

// 에러 함수
function onError(error) {
	
	alert(error); //에러 내용 알림 내용은 아래의 영어로 websocket연결안되었다를 빨간색으로
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
    //나갈래? 함수 호출
    exitChatRoom();
    
}
//나갈래? 함수
function exitChatRoom() {
	//ajax 로 이방에서 내 나갈끼다를 함.
	$.ajax({
        type: 'GET',
        url: '/usr/chat/exitChatRoom', //
        data: {
            'discussionId': discussionId,
            'memberId': memberId
        },
        complete: function () { //Ajax 요청이 완료된 후에 실행되는 콜백 함수를 지정하는 옵션
			if (confirm('서버와의 연결이 끊겼습니다. 채팅방에서 나가시겠습니까?')) {
				location.href = '/usr/chat/chatRoomList'; //나갈끼다 하면 채팅목록으로 보냄
			}
        }
    })
    
}
// async : 해당함수가 비동기 함수임을 나타냄. 이거 쓰면 함수내에서 await 키워드 사용가능. 
// await : 프로미스가 처리 될 때까지 함수의 실행을 일시 중단하고, 프로미스가 처리되면 해당 값을 반환함.
// 누구 나갔다 함수
async function disconnect(event) {
	//여서 await 썼네. 
	//  stompClient.send(destination, headers, body) : STOMP 메시지를 서버로 전송하는데 사용 됨.
	// destination : 메시지가 전송될 대상 주소
	// headers : 메시지에 추가할 헤더 정보. 메세지에 추가할 헤더 정보
	// body : 메시지의 본문. 문자열/ 객체 등이 될수 있음.
	await stompClient.send('/pub/usr/chat/exitMember',
	    {},
	    JSON.stringify({
	        'discussionId' : discussionId,
	        'memberId' : memberId,
	        'message' : memberNickname + ' 님이 퇴장하셨습니다.',
	        'memberNickname' : memberNickname,
	        'messageType' : 'LEAVE' // 나갔다.
	    })
	)
    
    await stompClient.disconnect(); // 연결했던 stompClient를 끔.
    
    event.preventDefault(); // 주어진 이벤트의 기본 동작을 중단시키는 메서드. 
    
	location.href = '/usr/discussion/list'; // 나가서 돌아갈 곳.
	
}

//	채팅방에 입장한 멤버 리스트 받기
//	비동기로 멤버 리스트를 받으며 '입장/퇴장/강퇴/위임 문구'가 나올 때마다 실행된다.
function getMemberList() {
	
    let memberList = $('#member-list'); // 멤버 목록이 나올 부분
    
    $.ajax({
        type: 'GET',
        url: '/usr/chat/memberList', // chatControoler에 채팅방 참가한 맴버 리스트 반환하는 url이 있음
        data: {
            'discussionId': discussionId
        },
        success: function (data) { // 성공하면 ajax로 받은 data로 
			let members = ''; // 해당인간들 (들 즉, 복수잖아 그래서 s)
			for (let i = 0; i < data.length; i++) { //
				if (data[i].id == memberId) { // 현재 사용자의 ID와 일치하는 경우, 해당 멤버의 닉네임을 그대로 표시
                    //소민이는 현재 사용자는 자기가 초록색으로 조금 굵게 보이게 했지용
					members += `<li class="p-1">
									<span>
										<span class="text-green-500 font-semibold">${data[i].nickname}</span> 
									</span>
								</li>`;//해당 i의 nickname
				} else { // 해당인간이 아니면 맴버의 닉네임을 표시하고, 몇 가지 조건에 따라 추가적인 기능을 제공하는 메뉴 생성
					members += `<li class="p-1">
									<span>														
										<span class="onclick="showCommandList('${data[i].sessionId}');">${data[i].nickname}</span>
										<ul id="${data[i].sessionId}" class="hidden">`;
					if (memberId == hostMemberId) { // memberid랑 방장ID가 일치하면 즉, 방장이면 강퇴 기능 추가
						members += `			<li>
													<span class="cursor-pointer hover:underline" onclick="if(confirm('${data[i].nickname} 님을 강퇴하시겠습니까?')) {banMember('${data[i].sessionId}');}">강퇴</span>
												</li>
											</ul>
										</span>
									</li>`;
					}
				}
			}
			memberList.empty(); // 멤버 목록을 표시하는 엘리머트의 내용을 초기화 // 안그러면 이전에 남아있을지도 모르는 아가 있것지
			memberList.html(members); // 멤버 목록을 동적으로 생성한 HTML 코드로 채움. 이를 통해 화면에 멤버 목록이 표시됨.
        }
    })
    
}

//	멤버 리스트에 있는 멤버를 클릭할 때 강퇴, 귓속말 보내기 등의 명령어 목록 보여주기(자기 자신은 제외)
//	클릭한 상태에서 동일한 멤버를 한번 더 클릭하면 명령어 목록 사라지게 함
//	클릭한 상태에서 다른 멤버를 클릭하면 기존 명령어 목록 사라지고 다른 명령어 목록 보여주게 함
let originalCommandListElement = null;
// 귓속말 강퇴등 기능
function showCommandList(sessionId) { //써야함.
	
	if (originalCommandListElement != null) {
		originalCommandListElement.classList.add('hidden');
		if (originalCommandListElement == document.getElementById(sessionId)) {
			originalCommandListElement = null;
			return;
		}
	}
	
	let commandListElement = document.getElementById(sessionId);
	commandListElement.classList.remove('hidden');
	
	originalCommandListElement = commandListElement;
	
}
//강퇴, 위에서 쓰고 있음.
function banMember(sessionId) {
	
	stompClient.send('/pub/usr/chat/banMember',
	    {},
	    JSON.stringify({
	        'discussionId' : discussionId,
	        'memberId' : memberId,
	        'memberNickname' : memberNickname,
	        'sessionId' : sessionId,
	        'messageType' : 'BAN'
	    })
	)
	
}

//	비동기로 채팅방 정보를 받으며 '퇴장/위임 했다는 문구'가 나올 때마다 실행된다.
//	퇴장한 멤버가 방장이면 입장해 있는 멤버 중 가장 빨리 들어온 멤버가 자동으로 방장이 됨
//	이때 채팅방에서 방장 닉네임이 바뀌어야 하므로 받아온 채팅방 정보로 채팅방의 방장 닉네임을 새로운 방장 닉네임으로 변경

//	메시지 전송때는 JSON 형식의 메시지를 전달한다.
function sendMessage(event) {
	
    let messageContent = messageInput.value.trim();

    if (messageContent && stompClient) { // 귓속말일 때만 -> 필요없음.
		
	     // 그냥 대화 -> 내가 필요한 부분.
			
	        let chatMessage = {
	            'discussionId' : discussionId, // 방 di
	            'memberId' : memberId, // 누가 말한긴가
	            'message' : messageContent, // 메세지 내용
	            'memberNickname' : memberNickname, //말한놈 닉네임
	            'messageType' : 'TALK' // 메세지 타입
	        };
	
	        stompClient.send('/pub/usr/chat/sendMessage', {}, JSON.stringify(chatMessage)); //stompClient에 보냄
			// 보낼 주소, 헤드, 내용(json으로 위에 변수에 담은거 보냄.)
	        
	        messageInput.value = ''; // 채팅의 메세지 적는곳 비움.
	        
    }
    
    event.preventDefault();  // 이벤트의 기본 동작을 취소하는 역할. 
}

//	채팅방에 파라미터로 받은 닉네임을 가진 멤버가 참여 중인지 판단
//	참여 중이면 true 리턴, 참여 중이지 않으면 false 리턴
function getMember(nickname) {
	
	let exist;
	
	$.ajax({
	    type: 'GET',
	    url: '/usr/chat/getMember',
	    async: false,
	    data: {
    		'discussionId': discussionId,
    		'nickname': nickname
	    },
	    success: function (data) {
			if (data.fail) {
				exist = false;
			} else {
				exist = true;
			}
	    }
	})
			
	return exist;
	
}

//	메시지를 받을 때도 마찬가지로 JSON 타입으로 받으며, -> 위의꺼는 메시지를 보낸거. 그럼 딴놈들은 받아야지.
//	넘어온 JSON 형식의 메시지를 parse 해서 사용한다.
function onMessageReceived(payload) { // 웹 소켓을 통해 메시지를 수신할 떄 호출되는 함수. 
	
    let chat = JSON.parse(payload.body); // 받은 메시지의 본문을 json형식으로 파싱하여 chat 변수에 저장.
    // 실제로 온거
    // {"discussionId":"10","memberId":"2","message":"뱁새씨 님이 입장하셨습니다.","memberNickname":"뱁새씨","messageType":"ENTER"}
    
    let messageElement = document.createElement('li'); // 문서에 li 만드는거 -> 이게 메시지임 노랑 배경놈.
    
    let chatFormatRegDateElement = document.createElement('span'); //문서에 span 만드는거 : 시간 표시를 위한 span 생성
	let chatFormatRegDateText = document.createTextNode(' [ ' + chat.formatRegDate + ' ] '); // 메시지의 채팅 시간 뜨는 그거
	
	chatFormatRegDateElement.classList.add('text-sm');// 채팅 시간에 css
	
	chatFormatRegDateElement.appendChild(chatFormatRegDateText);// 시간 엘리먼트에 텍스트 노드를 추가
	
	// 메시지 상태에 따라 함.
	// 현재 사용자의 메시지와 다른 사용자의 메시지를 구분하고 각각을 다르게 스타일링하여 표시하는 부분
    if (chat.messageType == 'ENTER') { // 누가 들어오면 실행됨.
        messageElement.classList.add('event-message');  // 이벤트 메시지ㅔㅇ 대한 스타일 추가
		messageElement.appendChild(chatFormatRegDateElement); //누가 들어왔다고 말해줌.
		getMemberList(); // 누가 들어왔으니까 유저 이름이 추가되야지
    } else if (chat.messageType == 'LEAVE' || chat.messageType == 'CHANGE') { //나갔다 / 방장위임.
        messageElement.classList.add('event-message'); //나갔다 메시지 / 방장위임했다 메시지
		messageElement.appendChild(chatFormatRegDateElement); // 말해줌.
		getChatRoom(getMemberList); // 유저 리스트 새로 불러움
    } else if (chat.messageType == 'BAN') { // 금지됨
		if (chat.banMemberId == memberId) { //만약에 밴된 시킨 아이디랑 같다.
			stompClient.disconnect(); //stompClient.에서 밴된놈 지움.
			alert(chat.banMemberNickname + ' 님은 방장에 의해 강제 퇴장되었습니다. 더 이상 채팅에 참여할 수 없습니다.');
			return;
		}
		messageElement.classList.add('event-message');
		messageElement.appendChild(chatFormatRegDateElement);
		getMemberList(); // 중도 밴 된거니까 
	} else if (chat.messageType == 'DELETE') { // 삭제
		messageElement.classList.add('event-message'); //방장시키가 삭제했다는 메시지
		messageElement.appendChild(chatFormatRegDateElement); 
	} else { // 전부(Enter, Leave, change, ban, delete, whisper) 아니면
		
		if (memberId == chat.memberId) { // 메시지가 현재 사용자의 것인지 아닌지에 따라 다른 스타일을 적용함. 만약 현재 사용자의 메시지라면 me 클래스 추가/ 아니면 other 클래스 추가 -> 색상 구분하려고 한거구나
			messageElement.classList.add('me');
		} else {
			messageElement.classList.add('other');
		}
		
		let memberNicknameElement = document.createElement('span'); //누구님의 말을 만들기 위해 span 추가
		let memberNicknameText = document.createTextNode(chat.memberNickname + ' 님의 말'); //span안 내용
		
		memberNicknameElement.classList.add('font-semibold'); // 사용자 닉네임 굵게 표시
		
		memberNicknameElement.appendChild(memberNicknameText); // 생성한 닉네임 텍스트를 span 엘리먼트에 추가함.
		messageElement.appendChild(memberNicknameElement); //사용자의 닉네임을 표시하는 span 엘리먼트를 메시지 엘리먼트에 추가함.
		
		messageElement.appendChild(chatFormatRegDateElement); //메시지의 작성시간을 표시하는 엘리먼트를 메시지 엘리먼트에 추가함.
		
    }
    
    let contentElement = document.createElement('p'); // 채팅 내용을 담을 새로운 p 엘리먼트를 생성함.
    let messageText = document.createTextNode(chat.message); //채팅 메시지의 내용을 담는 텍스트 노드를 생성
    
    if (chat.messageType == 'DELETE') {  // 삭제된 메시지일 경우.
		contentElement.classList.add('text-red-500'); // 삭제된 메시지임을 나타내기 위해 텍스트 색상을 빨간색으로 변경함
	}
    contentElement.appendChild(messageText); //채팅 메시지 내용을 p 엘리먼트에 추가함
    
	messageElement.appendChild(contentElement); // 채팅 메시지 내용을 표시하는 p 엘리먼트를 메시지 엘리먼트에 추가함.
	
    messageArea.appendChild(messageElement); // 새로운 메시지 엘리먼트를 채팅창에 추가
    
    //	스크롤바 하단으로 이동
    messageArea.scrollTop = messageArea.scrollHeight; // 스크롤바를 채팅창의 맨 아래로 이동시켜서 최신 메시지가 보이도록 함.
    
    if (chat.messageType == 'DELETE') { // 메시지의 종류가 delete인 경우 웹소켓 연결을 종료함
		stompClient.disconnect();
	}
    
}

//	채팅방에서 새로고침하면 퇴장, 입장 반복되던 부분 안되게
window.onkeydown = function (event) { // 키 이벤트를 감지하여 F5키를 눌렀을때, 새로고침을 방지하고 사용자에게 확인 메시지를 띄워 채팅방을 나가게 함.
	//	F5, Ctrl + F5, Ctrl + R (새로고침)
	if ((event.keyCode == 116) || (event.ctrlKey == true && event.keyCode == 82)) {
		event.stopPropagation();
		if (confirm('채팅방에서 나가시겠습니까?')) {
			location.href = '/usr/chat/chatRoomList';
		}
		return false;
	}
}

//보내기의 form id가 messageForm임
messageForm.addEventListener('submit', sendMessage, true);// 메시지 전송 폼의 제출 이벤트를 ㄱ마지하여 sendmessage 실행
exitButton.addEventListener('click', disconnect, true); //나가기 버튼(id = exit-button) 클릭 이벤트를 감지하여 disconnect 함수를 실행
window.addEventListener('beforeunload', disconnect, true); // 페이지를 나가기 전에 disconnect 함수를 실행하여 정리작업 수행