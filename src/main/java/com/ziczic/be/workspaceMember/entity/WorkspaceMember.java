package com.ziczic.be.workspaceMember.entity;

import java.time.LocalDateTime;

import com.ziczic.be.member.entity.Member;
import com.ziczic.be.workspace.entity.Workspace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;

	@ManyToOne //
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "joined_at")
	private LocalDateTime joinedAt;

	@Builder
	public WorkspaceMember(Workspace workspace, Member member) {
		this.workspace = workspace;
		this.member	= member;
		this.joinedAt =  LocalDateTime.now();
	}
}
