package com.ziczic.be.workspaceMember.dto;

import java.time.LocalDateTime;

import com.ziczic.be.member.entity.Member;

public record WorkspaceDto(Long id, String workspaceName, LocalDateTime createdAt) {
}
