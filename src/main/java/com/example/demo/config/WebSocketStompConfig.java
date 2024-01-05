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

@Configuration 
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
		

	// 여기서부터 채팅관련
	private WebSocketSessionManager webSocketSessionManager;
    
    @Autowired
    public WebSocketStompConfig(WebSocketSessionManager webSocketSessionManager) {
        this.webSocketSessionManager = webSocketSessionManager;
    }
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
	    //	stomp 접속 주소 url => /ws-stomp : 채팅관련
	    registry.addEndpoint("/ws-stomp") //  연결될 엔드포인트
	            .withSockJS(); //  SocketJS 를 연결한다는 설정
	    
	    // 화상 관련
	    registry.addEndpoint("/signaling")// webSokcet 접속시 endpoint 설정
        .setAllowedOriginPatterns("*") // cors 에 따른 설정 ( * 는 모두 허용 )
        .withSockJS(); // 브라우저에서 WebSocket 을 지원하지 않는 경우에 대안으로 어플리케이션의 코드를 변경할 필요 없이 런타임에 필요할 때 대체하기 위해 설정
	
	}
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
		
        // 채팅	메시지를 구독하는 요청 url => 즉 메시지 받을 때
        registry.enableSimpleBroker("/sub");

        // 채팅	메시지를 발행하는 요청 url => 즉 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
        
        // 화상 관련
        registry.enableSimpleBroker("/topic"); // broker url 설정
        registry.setApplicationDestinationPrefixes("/app"); // send url 설정
        
        
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