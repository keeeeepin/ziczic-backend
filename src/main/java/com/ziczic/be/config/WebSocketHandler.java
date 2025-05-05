// package com.ziczic.be.config;
//
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;
//
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;
//
// import com.ziczic.be.chat.entity.Chat;
//
// import lombok.extern.slf4j.Slf4j;
//
//
// @Slf4j
// public class WebSocketHandler extends TextWebSocketHandler {
//
// 	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//
// 	// 웹소켓 연결
// 	@Override
// 	public void afterConnectionEstablished(WebSocketSession session){
// 		// 새로운 유저가 기존의 웹세션에 연결을 요청할 경우, 해당 세션에 있던 기존 유저들에게 접속을 전달해주는 로직
//
// 		var sessionId = session.getId();
// 		sessions.put(sessionId, session);
//
// 		Chat message = Chat.builder()
// 			.sender(sessionId)
// 			.receiver("all")
// 			.build();
// 		message.newConnect();
//
// 		sessions.values().forEach(session -> {
// 			try {
// 				if(!session.getId().equals(sessionId)) {
// 					session.sendMessage(new TextMessage(Utils.getString(message)));
// 				}
// 			}
// 			catch (Exception e) {
// 				// TODO Throw Exception
// 			}
// 		})
// 	}
//
// 	// 양방향 데이터 통신
// 	@Override
// 	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
// 	}
//
// 	// 소켓 연결 종료
// 	@Override
// 	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//
// 	}
//
// 	// 소켓 통신 에러
// 	@Override
// 	public void handleTransportError(WebSocketSession session, Throwable throwable) {
//
// 	}
//
//
//
//
//
// }
// package com.ziczic.be.config;
//
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;
//
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;
//
// import com.ziczic.be.chat.entity.Chat;
//
// import lombok.extern.slf4j.Slf4j;
//
//
// @Slf4j
// public class WebSocketHandler extends TextWebSocketHandler {
//
// 	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//
// 	// 웹소켓 연결
// 	@Override
// 	public void afterConnectionEstablished(WebSocketSession session){
// 		// 새로운 유저가 기존의 웹세션에 연결을 요청할 경우, 해당 세션에 있던 기존 유저들에게 접속을 전달해주는 로직
//
// 		var sessionId = session.getId();
// 		sessions.put(sessionId, session);
//
// 		Chat message = Chat.builder()
// 			.sender(sessionId)
// 			.receiver("all")
// 			.build();
// 		message.newConnect();
//
// 		sessions.values().forEach(session -> {
// 			try {
// 				if(!session.getId().equals(sessionId)) {
// 					session.sendMessage(new TextMessage(Utils.getString(message)));
// 				}
// 			}
// 			catch (Exception e) {
// 				// TODO Throw Exception
// 			}
// 		})
// 	}
//
// 	// 양방향 데이터 통신
// 	@Override
// 	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
// 	}
//
// 	// 소켓 연결 종료
// 	@Override
// 	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//
// 	}
//
// 	// 소켓 통신 에러
// 	@Override
// 	public void handleTransportError(WebSocketSession session, Throwable throwable) {
//
// 	}
//
//
//
//
//
// }
