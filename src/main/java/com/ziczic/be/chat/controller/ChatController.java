package com.ziczic.be.chat.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziczic.be.chat.dto.ChatCreateResp;
import com.ziczic.be.chat.dto.GetChatHistoryReq;
import com.ziczic.be.chat.dto.GetChatHistoryResp;
import com.ziczic.be.chat.entity.Chat;
import com.ziczic.be.chat.service.ChatService;
import com.ziczic.be.global.jwt.JwtUtil;
import com.ziczic.be.member.entity.Member;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
// @RequestMapping
@RequiredArgsConstructor
public class ChatController {

	private final ChatService chatService;
	private final JwtUtil jwtUtil;

	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	// 채팅 발행
	@MessageMapping("/workspaces.{workspaceId}.channels.{channelId}") // endpoint, prefix가 포함된 url로 발행된 메시지를 구독
	@SendTo("/sub/workspaces.{workspaceId}.channels.{channelId}") // 메소드의  return값을 해당 url을 구독하는 client들에게 발행
	public GetChatHistoryResp createChat(@DestinationVariable Long workspaceId, @DestinationVariable Long channelId, @Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) {

		// 헤더에서 토큰 추출 (STOMP 클라이언트에서 설정한 헤더)
		String token = headerAccessor.getFirstNativeHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			log.error("Invalid or missing token");
			return null; // 또는 예외 처리
		}

		String jwtToken = token.substring(7);
		Claims claims = jwtUtil.getClaimsFromToken(jwtToken);
		Long memberId = claims.get("id", Long.class);

		log.info("chat :", chat);

		// ChatCreateResp chatCreateResp = new ChatCreateResp("ddd@naver.com", message.getContent());
		Chat createdChat = chatService.createChat(workspaceId, channelId, memberId, chat.getContent());

		GetChatHistoryResp resp = GetChatHistoryResp.fromEntity(createdChat);



		return resp; // return dㅏㄴ하니까 안가지윙 ㅣㅣ
	}


	// 채팅 리스트 반환
	@GetMapping("/{workspaceId}/{channelId}/chatHistory")
	public ResponseEntity<List<GetChatHistoryResp>> getChatMessages(@PathVariable Long workspaceId, @PathVariable Long channelId) {
		List<Chat> chats = chatService.getChatHistory(workspaceId, channelId);

		List<GetChatHistoryResp> chatResp = chats.stream()
				.map(GetChatHistoryResp::fromEntity)
				.collect(Collectors.toList());


		return ResponseEntity.ok(chatResp);

	}
}
