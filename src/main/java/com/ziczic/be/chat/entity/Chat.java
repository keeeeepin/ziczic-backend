package com.ziczic.be.chat.entity;

import java.time.LocalDateTime;

import com.ziczic.be.channel.entity.Channel;
import com.ziczic.be.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")
	private Channel channel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member sender;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column
	private LocalDateTime createdAt;

	// 답글을 위한 셀프 참조

	@Builder
	public Chat(Channel channel, Member sender, String content) {
		this.channel = channel;
		this.sender = sender;
		this.content = content;
		this.createdAt = LocalDateTime.now();
	}

}
