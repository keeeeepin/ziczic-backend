package com.ziczic.be.chat.dto;

import java.time.LocalDateTime;

import com.ziczic.be.chat.entity.Chat;

public record GetChatHistoryResp(Long id, String content, String memberName, LocalDateTime createdAt) {
	public static GetChatHistoryResp fromEntity(Chat chat) {
		return new GetChatHistoryResp(
			chat.getId(),
			chat.getContent(),
			chat.getSender().getMemberName(),
			chat.getCreatedAt()
		);
	}
}
