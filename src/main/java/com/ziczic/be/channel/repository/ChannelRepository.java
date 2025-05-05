package com.ziczic.be.channel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziczic.be.channel.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

	List<Channel> findAllByWorkspaceId(Long workspaceId);
}
