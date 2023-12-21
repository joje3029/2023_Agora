package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ChatService;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Chat;
import com.example.demo.vo.ChatRoom;
import com.example.demo.vo.Member;


@Controller
public class ChatController {
	
	private final SimpMessageSendingOperations template;
	private ChatService chatService;
	private MemberService memberService;
	
	@Autowired
	public ChatController(SimpMessageSendingOperations template, ChatService chatService, MemberService memberService) {
		this.template = template;
		this.chatService = chatService;
		this.memberService = memberService;
	}
	
	//	MessageMapping 을 통해 webSocket 로 들어오는 메시지를 발신 처리한다.
	//	이때 클라이언트에서는 /pub/usr/chat/sendMessage 로 요청하게 되고 이것을 controller 가 받아서 처리한다.
	//	처리가 완료되면 /sub/usr/chat/joinChatRoom/discussionRoomId 로 메시지가 전송된다.
	@MessageMapping("/usr/chat/enterMember") // 이놈이구나!! enterMember로 해서 누구씨가 입장했습니다 하는 놈이
	public void enterMember(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) {
		// @Payload : 메소드의 특정 매개변수가 메시지의 페이로드를 나타냄. 
		// 여기서 chat 타입의 chat 매개변수가 메시지의 페이로드를 나타냄. 이렇게 선언된 매개변수는 Spring이 WebSocket 메시지의 페이로드를 해당 ㄱ개체로 매핑하는데 사용됨
		//SimpMessageHeaderAccessor : 매개변수는 Spring의 메시징 인프라에서 사용되는 헤더 정보에 접근하는데 사용됨. 
		// 예 : 메시지의 헤더에서 추가적인 정보를 추출하거나 설정하는데 활용될 수 있음.
		// 즉, webSocket을 통해 전달되는 메시지의 페이로드를 처리. chat 객체로 매핑하여 사용, 동시에 메시지의 헤더 정보에 접근할 수 있음. 
		// 이러한 방식으로 메시지의 내용과 부가적인 정보를 처리할 수 있음.
		
		LocalDateTime now = LocalDateTime.now(); // 지금 시간
		chat.setRegDate(now); //chat 객체에 지금시간 세팅
		chat.setFormatRegDate(Util.formatRegDateVer1(chat.getRegDate())); //chat 에서 이전 시간 데꼬와서 세팅
		
		//chatroom에 들어감		  chat에서 방id			chat에서 유저 id, 	메시징 인프라에서 사용하는 헤더 정보에서 세션 id를 가져옴
		chatService.joinChatRoom(chat.getDiscussionId(), chat.getMemberId(), headerAccessor.getSessionId());	// 챗에서 받는 수신자 	 챗에서 강퇴 당한놈 id		챗에서 메세지 타입
		chatService.saveChat(chat.getRegDate(), chat.getDiscussionId(), chat.getMemberId(), chat.getMessage(), chat.getRecipientId(), chat.getBanMemberId(), chat.getMessageType());
		// chat 내용 세이브	  chat에서 날짜 가져옴 chat에서 방 번호 가져옴 caht에서 멤버 아이디 가져옴 chat에서 메시지 가져오기 
		
		headerAccessor.getSessionAttributes().put("memberId", chat.getMemberId()); // 메시지 헤더에 글쓰는놈 id를 key이름 memberId로 해서 세팅
		headerAccessor.getSessionAttributes().put("discussionRoomId", chat.getDiscussionId()); //메시지 헤더에 chatRoom id를 key이름 discussionRoomId로 해서 세팅
		//암튼 그 템플릿에 template.convertAndSend 는 Spring 프레임워크에서 메시징을 처리하기 위한 메소드 중 하나. 
		// 주로 Spring의 메시징 기능을 사용하여 메시지를 특정 주제에 발행하거나 메시지를 소비하는데 사용. 이 메소드는 주로 Spring의 메시징 모듈에서 WebSocket이나 메시지 큐와 같은 통신 메커니즘에 사용됨.
		template.convertAndSend("/sub/usr/chat/joinChatRoom/" + chat.getDiscussionId(), chat);
		//여기서는 특정 주제에 메시지를 발행함. 
		// * 여서 sub씨는 메세지를 구독하는 요청 url -> WebSocketStompconfig를 참고
		// 구체적으로는 /sub/usr/chat/joinChatRoom/ 다음에 채팅방 id가 추가된 주제로 메시지 발행. 
	}
    
    @MessageMapping("/usr/chat/sendMessage") // socket.js에서 json형태의 메시지를 전달하면 오는 url
    public void sendMessage(@Payload Chat chat) {  //@Payload : 메소드의 특정 매개변수가 메시지의 페이로드를 나타냄. 
    	
    	String recipientNickname = chat.getRecipientNickname(); //chat에서 수신자 닉네임을 recipientNickname에 담음.
    	
    	System.out.println("recipientNickname : "+ recipientNickname );
    
    	if (recipientNickname != null) { //만약에 수신자에 뭐가 들었으면(null이 아니면)
    		Member member = memberService.getMemberByNickname(recipientNickname); //memberService에서 수신자놈 닉네임을 꺼내서 담음
    		chat.setRecipientId(member.getId()); //chat에 수신자놈 id 세팅
    		chat.setMessage(chat.getMessage().substring(chat.getMessage().indexOf(chat.getMessage().split(" ")[2])));// chat에 메시지를 세팅
							//chat의 메시지에서 끊어 메시지의 인덱스가 공백으로 끊었을때 인덱스 2번째부터 
    	}
    	// 수신자에 뭐가 든게 아니면 (null)이면
    	LocalDateTime now = LocalDateTime.now(); //지금 시간
		chat.setRegDate(now); // 지금 시간세팅
		chat.setFormatRegDate(Util.formatRegDateVer1(chat.getRegDate())); //chat의 시간을 chatdml 형태 시간에 세팅
    	// 챗을 저장			chat 날짜			chat의 채팅 방 id	chatdml 멤버 id		chat의 메시지		chat의 수신자id			chat의 강퇴당한놈 	chat의 메시지 타입
    	// 여기서 chat.getDiscussionRoomId()가 0으로 들어감. chat에 세팅을 지금 언노밍 하고 있는가. 
		chatService.saveChat(chat.getRegDate(), chat.getDiscussionId(), chat.getMemberId(), chat.getMessage(), chat.getRecipientId(), chat.getBanMemberId(), chat.getMessageType());

		System.out.println("chat.getDiscussionId() : "+chat.getDiscussionId());
    	System.out.println("chat : "+chat);
    	
    	//응 그래서 DB에 저장은 겁나 잘되. /usr/discussion/chat/         /usr/discussion/chat?discussionId=7
    	
        template.convertAndSend("/sub/usr/chat/joinChatRoom/" + chat.getDiscussionId(), chat);
        //여기서는 특정 주제에 메시지를 발행함. 
		// * 여서 sub씨는 메세지를 구독하는 요청 url -> WebSocketStompconfig를 참고
		// 구체적으로는 /sub/usr/chat/joinChatRoom/ 다음에 채팅방 id가 추가된 주제로 메시지 발행. 
    }
    
    @MessageMapping("/usr/chat/exitMember") // 나갈끼다! url
    public void exitMember(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) {
		//	stomp 세션에 있던 memberId 와 discussionRoomId 를 확인해서 채팅방 멤버 리스트와 채팅방에서 해당 멤버를 삭제
		
    	int memberId = (int) headerAccessor.getSessionAttributes().get("memberId");
    	
		int discussionRoomId = (int) headerAccessor.getSessionAttributes().get("discussionRoomId");
		
		
		//chatService에 어느놈이 나간다 고 방 id랑 나가는놈 id를 보냄
		chatService.exitChatRoom(discussionRoomId, memberId);
		
		ChatRoom chatRoom = chatService.getChatRoomById(discussionRoomId); //방 id를 찾아서 변수에 담음
		
		
		//	퇴장한 멤버가 방장일 때 입장해 있는 멤버 중 가장 빨리 들어온 멤버가 자동으로 방장이 되게 함
		
		
		// 나갈끼다 했는데 주인도 아니고 방이 없는것도 아니면
    	LocalDateTime now = LocalDateTime.now(); //지금시간
    	chat.setRegDate(now); //chat에 지금 시간 세팅
    	chat.setFormatRegDate(Util.formatRegDateVer1(chat.getRegDate())); //양식 시간 세팅
    	
    	System.out.println(8);
    	
    	System.out.println("saveChat 들어가기전 Test");
    	
    	System.out.println(9);
    	
		//chat 저장
    	chatService.saveChat(chat.getRegDate(), chat.getDiscussionId(), chat.getMemberId(), chat.getMessage(), chat.getRecipientId(), chat.getBanMemberId(), chat.getMessageType());
    	
    	System.out.println(9);
    	
    	template.convertAndSend("/sub/usr/chat/joinChatRoom/" + chat.getDiscussionId(), chat);
    	//여기서는 특정 주제에 메시지를 발행함. 
		// * 여서 sub씨는 메세지를 구독하는 요청 url -> WebSocketStompconfig를 참고
		// 구체적으로는 /sub/usr/chat/joinChatRoom/ 다음에 채팅방 id가 추가된 주제로 메시지 발행. 
    }
    
    @MessageMapping("/usr/chat/banMember") // 강퇴한다!
    public void banMember(@Payload Chat chat) {
    	
    	String sessionId = chat.getSessionId(); //cat에서 sessionid를 세팅함
    	
    	Member member = chatService.getMemberBySessionId(sessionId); //세션 찾아서 세팅
    	
    	chat.setBanMemberId(member.getId());//chat의 강퇴당한 시키를 BanMemberId에 세팅
    	
    	chatService.exitChatRoom(chat.getDiscussionId(), chat.getBanMemberId()); // 강퇴하는 놈이니께 나가게 해야지
    	
    	LocalDateTime now = LocalDateTime.now(); //시간
    	chat.setRegDate(now); // 현시간 세팅
    	chat.setFormatRegDate(Util.formatRegDateVer1(chat.getRegDate())); //세팅 form
    	
    	chat.setMessage(member.getNickname() + " 님이 강제 퇴장되었습니다."); //메시지 세팅
    	chat.setBanMemberNickname(member.getNickname());//강퇴 당한놈 닉네임 세팅
    	
		// chat에 저장
    	chatService.saveChat(chat.getRegDate(), chat.getDiscussionId(), chat.getMemberId(), chat.getMessage(), chat.getRecipientId(), chat.getBanMemberId(), chat.getMessageType());
    	
    	System.out.println("chat.getDiscussionRoomId() : "+ chat.getDiscussionId());
    	System.out.println("chat : "+ chat);
    	
    	template.convertAndSend("/usr/discussion/chat?discussionId=" + chat.getDiscussionId(), chat);
    	//여기서는 특정 주제에 메시지를 발행함. 
		// * 여서 sub씨는 메세지를 구독하는 요청 url -> WebSocketStompconfig를 참고
		// 구체적으로는 /sub/usr/chat/joinChatRoom/ 다음에 채팅방 id가 추가된 주제로 메시지 발행. 

    }
    
    //	채팅방에 참여한 멤버 리스트 반환 -> 임마가 없어서 쟈가 안되는거였어!!
    @RequestMapping("/usr/chat/memberList")
    @ResponseBody
    public List<Member> memberList(int discussionId) {
        return chatService.getMemberList(discussionId);
    }
    
    //	퇴장한 멤버가 방장이면 입장해 있는 멤버 중 가장 빨리 들어온 멤버가 자동으로 방장이 됨
    //	이때 채팅방에서 방장 닉네임이 바뀌어야 하므로 채팅방 정보를 넘김
    @RequestMapping("/usr/chat/getChatRoom")
    @ResponseBody
    public ChatRoom getChatRoom(int discussionRoomId) {
    	return chatService.getChatRoomById(discussionRoomId); //이아 뭘 return하는지 찾아가기
    }
    
    //	서버와의 연결이 끊기면 DB에서 데이터 삭제 
    @RequestMapping("/usr/chat/exitChatRoom") // 방 나간다!
    public void exitChatRoom(int discussionRoomId, int memberId) { //나갈 방과 지금 유저 id 
    	
    	chatService.exitChatRoom(discussionRoomId, memberId); //chat 서비스에서 나가는거 일함 -> 나가면 되서 따로 받지 않음.
    	ChatRoom chatRoom = chatService.getChatRoomById(discussionRoomId); //
    	
		if (chatRoom.getCurrentMemberCount() == 0) { // 방 삭제됨을 말함.
			chatService.deleteChatRoom(discussionRoomId);
			chatService.deleteChat(discussionRoomId);
			return;
		}
		
		if (chatRoom.getMemberId() == memberId) { //방장인지 아닌지 방장이면 위임 해야하니까.
			chatService.modifyChatRoom(discussionRoomId);
		}
		
    }
}
	
