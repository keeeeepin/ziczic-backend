package com.ziczic.be.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP 사용하기 위해서 선언
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
	private static final String DEVELOP_FE_ORIGINS = "http://localhost:[*]";
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		// addEndpoint : http://localhost:8000/ws/sub, /pub로 구독, 발행시에만 통신 가능
		registry
			.addEndpoint("/ws") // stomp 접속 url -> ws://localhost:8080/ws
			.setAllowedOriginPatterns("http://localhost:[*]");

		log.info("STOMP endpoints registered.");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		// "/sub" 접두사가 붙은 url을 구독하는 client에 한해서 브로커가 메시지를 전달함
		registry.enableSimpleBroker("/sub");

		// "/pub" 접두사가 붙은 url로 발행한 메시지만 핸들러로 라우팅시켜줌
		registry.setApplicationDestinationPrefixes("/pub");
	}



}
