package com.ziczic.be.workspace.serivce;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ziczic.be.global.jwt.JwtUtil;
import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.repository.MemberRepository;
import com.ziczic.be.workspace.dto.WorkspaceResp;
import com.ziczic.be.workspace.entity.Workspace;
import com.ziczic.be.workspace.entity.WorkspaceMember;
import com.ziczic.be.workspace.repository.WorkspaceMemberRepository;
import com.ziczic.be.workspace.repository.WorkspaceRepository;

import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private final WorkspaceRepository workspaceRepository;
	private final WorkspaceMemberRepository workspaceMemberRepository;
	private final MemberRepository memberRepository;
	private final JwtUtil jwtUtil;

	public void createWorkspace(String accessToken, String name) {

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

		// workspace member에 등록
		addWorkspaceMember(workspace, member);
	}

	public void addWorkspaceMember(Workspace workspace, Member member) {
		WorkspaceMember workspaceMember = WorkspaceMember.builder()
			.workspace(workspace)
			.member(member)
			.build();

		workspaceMemberRepository.save(workspaceMember);
	}

	// accessToken ->
	public List<WorkspaceResp> getWorkspaceList(Long memberId) {

		List<Workspace> list = workspaceRepository.findAllByMemberId(memberId);
		log.info("List : {}", list.get(0).getWorkspaceName());

		return workspaceRepository.findAllByMemberId(memberId)
			.stream()
			.map(WorkspaceResp::new)
			.collect(Collectors.toList());

	}

}
