package com.ziczic.be.channel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ziczic.be.channel.dto.ChannelListGetResp;
import com.ziczic.be.channel.entity.Channel;
import com.ziczic.be.channel.repository.ChannelRepository;
import com.ziczic.be.workspace.entity.Workspace;
import com.ziczic.be.workspace.repository.WorkspaceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChannelService {

	private final ChannelRepository channelRepository;
	private final WorkspaceRepository workspaceRepository;

	public void createChannel(Long workspaceId, String name) {

		Workspace workspace = workspaceRepository.findById(workspaceId)
			.orElseThrow(() -> new EntityNotFoundException("Not Founded"));

		Channel channel = Channel.builder()
			.name(name)
			.workspace(workspace)
			.build();

		channelRepository.save(channel);
	}

	public List<ChannelListGetResp> getChannelList(Long workspaceId) {
		// workspaceId로 workspace 조회
		Workspace workspace = workspaceRepository.findById(workspaceId)
			.orElseThrow(() -> new EntityNotFoundException("Not founded"));

		// channelRepository로 해당 workspace_id를 가지는 channel entity모두 조회
		return channelRepository.findAllByWorkspaceId(workspaceId)
			.stream()
			.map(channel -> new ChannelListGetResp(
				channel.getId(),
				channel.getName(),
				channel.getWorkspace().getId(),
				channel.getCreatedAt()))
			.collect(Collectors.toList());
	}






}
