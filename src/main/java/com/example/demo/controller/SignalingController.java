package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SignalingController {
	// offer 정보를 주고 받기 위한 websocket
	// camKey : 각 요청하는 캠의 key , roomId : 룸 아이디 -> 이건 나같은 경우에는 토론방 Pk겠네.
	@MessageMapping("/peer/offer/{camKey}/{roomId}") // Spring WebSocket에서 메시지를 매핑하는 역할을 하는 어노테이션. 특정 메시지를 처리할 메서드를 지정. /app/peer/offer/{camKey}/{roomId}로 메시지를 보내면 해당 메서드가 호출되어 처리
	@SendTo("/topic/peer/offer/{camKey}/{roomId}") // 메서드가 처리한 결과를 지정된 경로로 다시 전송. PeerHandleOFfer 메서드에서 처리된 결과를 /topic/peer/offer/{camKey}/{roomId} 경로로 전송. 클라이언트에서 해당 주제를 구독하고 있는 경우 메시지 수신 가능.
	public String PeerHandleOffer(@Payload String offer, @DestinationVariable(value = "roomId") String roomId,
			//@Payload String offer : 이 어노테이션은 메서드의 매개변수 중에서 메시지의 페이로드를 가져오도록 지정. 클라이언트에서 보낸 메시지의 내용을 offer변수에 담아서 메서드에서 사용할수 있음.
			//@DestinationVariable : {roomId} 및 {camKey}와 같은 경로 변수를 메서드의 매개벼수로 가져올수 있도록 함.
			@DestinationVariable(value = "camKey") String camKey) {
		log.info("[OFFER] {} : {}", camKey, offer);// 확인하려고 찍은거.
		return offer;//offer: 클라이언트에서 보낸 메시지의 내용을 리턴 
	}

	// iceCandidate 정보를 주고 받기 위한 webSocket  // iceCandidate : WebRTC에서 네트워크 간 통신을 설정하기 위해 사용되는 중요한 메시지로, 브라우저 간의 연결을 수립하고 최적의 통신 경로를 찾아내는데 필요. 
	// camKey : 각 요청하는 캠의 key , roomId : 룸 아이디
	@MessageMapping("/peer/iceCandidate/{camKey}/{roomId}")// 메서드가 처리하려고 호출되는 주소
	@SendTo("/topic/peer/iceCandidate/{camKey}/{roomId}") // 메서드가 처리하고난 경로 보내는 주소
	public String PeerHandleIceCandidate(@Payload String candidate,
			@DestinationVariable(value = "roomId") String roomId,
			@DestinationVariable(value = "camKey") String camKey) {
		log.info("[ICECANDIDATE] {} : {}", camKey, candidate); // 확인하려고 찍은거
		return candidate; // 네트워크간 통신 경로 리턴
	}

	// answer 정보를 주고 받기 위한 websocket
	@MessageMapping("/peer/answer/{camKey}/{roomId}")// 메서드가 처리하려고 호출되는 주소
	@SendTo("/topic/peer/answer/{camKey}/{roomId}")// 메서드가 처리하고난 경로 보내는 주소
	public String PeerHandleAnswer(@Payload String answer, @DestinationVariable(value = "roomId") String roomId,
			@DestinationVariable(value = "camKey") String camKey) {
		log.info("[ANSWER] {} : {}", camKey, answer);
		return answer;
	}

	// camKey 를 받기위해 신호를 보내는 webSocket
	@MessageMapping("/call/key")// 메서드가 처리하려고 호출되는 주소
	@SendTo("/topic/call/key")// 메서드가 처리하고난 경로 보내는 주소
	public String callKey(@Payload String message) {
		log.info("[Key] : {}", message);
		return message;
	}

	// 자신의 camKey 를 모든 연결된 세션에 보내는 webSocket
	@MessageMapping("/send/key")// 메서드가 처리하려고 호출되는 주소
	@SendTo("/topic/send/key")// 메서드가 처리하고난 경로 보내는 주소
	public String sendKey(@Payload String message) {
		return message;
	}

}