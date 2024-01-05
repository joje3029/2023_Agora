package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import com.example.demo.sessionmanager.WebSocketSessionManager;

@Configuration // 이 클래스가 설정 클래스임을 명시
@EnableWebSocketMessageBroker // WebSocket 기능을 활성화하는 어노테이션. 메시지 브로커를 구성하고 WebSocket엔드포인트를 등록하는데 필요한 설정 제공.
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer { // WebSocketMessageBrokerConfigurer : WebSocket 구성을 위한 인터페이스
		
	private WebSocketSessionManager webSocketSessionManager;
    
    @Autowired
    public WebSocketStompConfig(WebSocketSessionManager webSocketSessionManager) {
        this.webSocketSessionManager = webSocketSessionManager;
    }
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { //Stomp 엔드포인트를 등록하는데 사용.
		
	    //	stomp 접속 주소 url => /ws-stomp : 채팅관련
	    registry.addEndpoint("/ws-stomp") //  연결될 엔드포인트
	            .withSockJS(); //  SocketJS 를 연결한다는 설정
	    
	    // 화상 관련 : "/signaling"경로를 통해 Stomp 프로토콜을 지원하는 WebSocket 연결을 활성화. setAllowedOriginPatterns("*")는 모든 원천(Origin)에 대해 연결을 허용하도록 설정. withSockJS()는 브라우저에서 WebSocket을 지원하지 않을때 SockJS를 사용하여 대체할 수 있도록 설정.
	    registry.addEndpoint("/signaling")// webSokcet 접속시 endpoint 설정
        .setAllowedOriginPatterns("*") // cors 에 따른 설정 ( * 는 모두 허용 )
        .withSockJS(); // 브라우저에서 WebSocket 을 지원하지 않는 경우에 대안으로 어플리케이션의 코드를 변경할 필요 없이 런타임에 필요할 때 대체하기 위해 설정
	
	}
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // 메시지 브로커를 구성하는데 사용. 
		
        // 채팅	메시지를 구독하는 요청 url => 즉 메시지 받을 때
        registry.enableSimpleBroker("/sub");

        // 채팅	메시지를 발행하는 요청 url => 즉 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
        
        // 화상 관련
        registry.enableSimpleBroker("/topic"); // broker url 설정. "/topic"으로 시작하는 대상에 대한 간단한 메모리 기반 메시지 브로커 활성화
        registry.setApplicationDestinationPrefixes("/app"); // send url 설정. "/app" 클라이언트에서 메시지를 송신할 때 사용할 URL의 접두사 설정.
        // /app으로 시작하는 URL을 통해 메시지를 송신하고, /topic으로 시작하는 대상에 메시지를 발행할 수 있게 함. Stomp 프로토콜과 SockJS를 이용하여 브라우저에서도 WebSocket을 사용할수 있도록 설정됨.
        
        
    }
	
	@Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            
        	@Override
            public WebSocketHandler decorate(WebSocketHandler handler) {
        		
                return new WebSocketHandlerDecorator(handler) {
                	
                    @Override
                    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                        webSocketSessionManager.addSession(session.getId(), session);
                        super.afterConnectionEstablished(session);
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        webSocketSessionManager.removeSession(session.getId());
                        super.afterConnectionClosed(session, closeStatus);
                    }
                    
                };
                
            }
        	
        });
        
    }
	
}