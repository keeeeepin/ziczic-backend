package com.ziczic.be.channel.dto;

import java.time.LocalDateTime;

public record ChannelListGetResp(Long id, String name, Long workspaceId, LocalDateTime createdAt) {
}
