package com.ziczic.be.workspaceMember.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ziczic.be.global.jwt.JwtUtil;
import com.ziczic.be.workspace.entity.Workspace;
import com.ziczic.be.workspaceMember.dto.MemberJoinRequest;
import com.ziczic.be.workspaceMember.dto.WorkspaceDto;
import com.ziczic.be.workspaceMember.service.WorkspaceMemberService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceMemberController {

	private final JwtUtil jwtUtil;
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private final WorkspaceMemberService workspaceMemberService;


	//  workspace에 member 가입 or 초대 / workspaceId, memberId
	// 1. Method
	// 2. Mapping URL
	// 3. Query Parameter : workspaceID
	// 4. Request Body : memberId
	@PostMapping("/{workspaceId}/member")
	@ResponseStatus(HttpStatus.OK)
	public void joinWorkspace(@PathVariable Long workspaceId,@RequestHeader("Authorization") String token) {

		String jwtToken = token.substring(7);

		Claims claims = jwtUtil.getClaimsFromToken(jwtToken);

		Long memberId = claims.get("id", Long.class);

		workspaceMemberService.joinWorkspace(workspaceId, memberId);
	}


	// member가 가입한 모든 workspace 조회
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<WorkspaceDto>> getWorkspaceList(@RequestHeader("Authorization") String token) {
		String jwtToken = token.substring(7);

		log.info("jwtToken = {}", jwtToken);

		Claims claims = jwtUtil.getClaimsFromToken(jwtToken);
		log.info("claims : {}", claims);

		Long memberId = claims.get("id", Long.class);
		log.info("memberId = {}", memberId);

		List<WorkspaceDto> workspaceDtos = workspaceMemberService.getWorkspaceList(memberId);

		log.info("dtos : ", workspaceDtos.get(0));

		return ResponseEntity.ok(workspaceDtos);
	}


	//



}
