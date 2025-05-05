package com.ziczic.be.channel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ziczic.be.chat.entity.Chat;
import com.ziczic.be.workspace.entity.Workspace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;

	@Column
	private LocalDateTime createdAt;
	//
	// @OneToMany(mappedBy = "channel")
	// private List<Chat> chatList = new ArrayList<>();


	@Builder
	public Channel(String name, Workspace workspace){
		this.name = name;
		this.workspace = workspace;
		this.createdAt = LocalDateTime.now();
	}
}
