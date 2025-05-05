package com.ziczic.be.workspace.serivce;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ziczic.be.global.jwt.JwtUtil;
import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.repository.MemberRepository;
import com.ziczic.be.workspace.dto.WorkspaceListResp;
import com.ziczic.be.workspace.dto.WorkspaceResp;
import com.ziczic.be.workspace.entity.Workspace;
import com.ziczic.be.workspace.repository.WorkspaceRepository;
import com.ziczic.be.workspaceMember.service.WorkspaceMemberService;

import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private final WorkspaceRepository workspaceRepository;
	private final MemberRepository memberRepository;
	private final JwtUtil jwtUtil;

	private final WorkspaceMemberService workspaceMemberService;

	public Workspace createWorkspace(String accessToken, String name) {

		Claims claims = jwtUtil.getClaimsFromToken(accessToken);
		Long ownerId = claims.get("id", Long.class);

		log.info("owner id {}", ownerId);

		Member member = memberRepository.findById(ownerId)
			.orElseThrow(() -> new EntityNotFoundException("Member Not Found.."));

		Workspace workspace = Workspace.builder()
			.name(name)
			.owner(member)
			.build();

		workspaceRepository.save(workspace);

		workspaceMemberService.joinWorkspace(workspace.getId(), member.getId());

		// // workspace member에 등록
		// addWorkspaceMember(workspace, member);

		return workspace;
	}

	// public void addWorkspaceMember(Workspace workspace, Member member) {
	// 	WorkspaceMember workspaceMember = WorkspaceMember.builder()
	// 		.workspace(workspace)
	// 		.member(member)
	// 		.build();
	//
	// 	workspaceMemberRepository.save(workspaceMember);
	// }


	public List<WorkspaceListResp> getWorkspaceList() {

		List<Workspace> list = workspaceRepository.findAll();

		log.info("List : {}", list.get(0).getWorkspaceName());

		List<WorkspaceListResp> workspaceListResp = workspaceRepository.findAll()
			.stream()
			.map(ws -> new WorkspaceListResp(ws.getId(), ws.getWorkspaceName(), ws.getCreatedAt()))
			.collect(Collectors.toList());

		log.info("resp :", workspaceListResp.get(0).workspaceName());

		return workspaceListResp;
	}

}
