package com.ziczic.be.workspace.dto;

import java.time.LocalDateTime;

public record WorkspaceListResp(Long id, String workspaceName, LocalDateTime createdAt) {


}
