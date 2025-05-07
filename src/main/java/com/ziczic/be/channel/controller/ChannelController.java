package com.ziczic.be.channel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ziczic.be.channel.dto.ChannelCreateReq;
import com.ziczic.be.channel.dto.ChannelListGetResp;
import com.ziczic.be.channel.service.ChannelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {

	private final ChannelService channelService;
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createChannel(@RequestBody ChannelCreateReq req) {
		log.info("req :", req.name());
		channelService.createChannel(req.workspaceId(), req.name());
	}

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ChannelListGetResp>> getChannelList(@RequestParam Long workspaceId) {
		List<ChannelListGetResp> channels = channelService.getChannelList(workspaceId);

		log.info("channels : {}", channels);
		return ResponseEntity.ok(channels);
	}
}
