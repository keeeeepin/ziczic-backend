package com.ziczic.be.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ziczic.be.chat.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	@Query("SELECT c FROM Chat c JOIN c.channel ch WHERE ch.id = :channelId AND ch.workspace.id = :workspaceId ORDER BY c.createdAt ASC")
	List<Chat> findChatsByWorkspaceIdAndChannelId(@Param("workspaceId") Long workspaceId, @Param("channelId") Long channelId);
}
