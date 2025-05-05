package com.ziczic.be.chat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ziczic.be.channel.entity.Channel;
import com.ziczic.be.channel.repository.ChannelRepository;
import com.ziczic.be.chat.entity.Chat;
import com.ziczic.be.chat.repository.ChatRepository;
import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

	private final ChatRepository chatRepository;
	private final ChannelRepository channelRepository;
	private final MemberRepository memberRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public Chat createChat(Long workspaceId, Long channelId, Long memberId, String content) {

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("Not Found"));

		Channel channel = channelRepository.findById(channelId)
			.orElseThrow(() -> new EntityNotFoundException("Not Found"));

		Chat chat = Chat.builder()
			.channel(channel)
			.sender(member)
			.content(content)
			.build();

		log.info("Chat Entity 생성완료");

		chatRepository.save(chat);

		return chat;
	}

	public List<Chat> getChatHistory(Long workspaceId, Long channelId) {

		return chatRepository.findChatsByWorkspaceIdAndChannelId(workspaceId, channelId);
	}
}
