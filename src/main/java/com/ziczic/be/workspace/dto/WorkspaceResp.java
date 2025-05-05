package com.ziczic.be.workspace.dto;

import java.time.LocalDateTime;

import com.ziczic.be.workspace.entity.Workspace;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkspaceResp {

	private Long id;
	private String workspaceName;
	private LocalDateTime createdAt;
	private String ownerName;

	public WorkspaceResp(Workspace workspace) {
		this.id = workspace.getId();
		this.workspaceName = workspace.getWorkspaceName();
		this.createdAt = workspace.getCreatedAt();
		this.ownerName = workspace.getOwner().getMemberName();
	}


}
