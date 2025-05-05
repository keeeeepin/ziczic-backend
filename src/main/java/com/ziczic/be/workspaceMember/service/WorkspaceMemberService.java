package com.ziczic.be.workspaceMember.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.repository.MemberRepository;
import com.ziczic.be.workspace.entity.Workspace;
import com.ziczic.be.workspace.repository.WorkspaceRepository;
import com.ziczic.be.workspaceMember.dto.WorkspaceDto;
import com.ziczic.be.workspaceMember.entity.WorkspaceMember;
import com.ziczic.be.workspaceMember.repository.WorkspaceMemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceMemberService {

	private final WorkspaceRepository workspaceRepository;
	private final MemberRepository memberRepository;
	private final WorkspaceMemberRepository workspaceMemberRepository;


	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	// Member 가입
	public void joinWorkspace(Long workspaceId, Long memberId) {

		// workspace 객체
		Workspace workspace = workspaceRepository.findById(workspaceId)
			.orElseThrow(() -> new EntityNotFoundException("Not Founded"));

		// member 객체
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("Not Founded"));

		// entity 생성
		WorkspaceMember workspaceMember = WorkspaceMember.builder()
			.workspace(workspace)
			.member(member)
			.build();

		// save
		workspaceMemberRepository.save(workspaceMember);
	}

	// Member가 속한 ws 조회
	public List<WorkspaceDto> getWorkspaceList(Long memberId) {

		// memberId == memberId column -> 모든 ws id 목록 조회
		List<WorkspaceMember> workspaceMembers = workspaceMemberRepository.findByMemberId(memberId);
		// ws id 목록 순회화면서 workspace List 생성
		// List<Workspace> workspaces = workspaceMembers.stream()
		// 	.map(WorkspaceMember::getWorkspace) // @Getter 을 통해 자동생성
		// 	.collect(Collectors.toList()); // stream을 List로 변환
		log.info("wmes :", workspaceMembers.get(0).getWorkspace().getWorkspaceName());

		List<WorkspaceDto> workspaceDtos = workspaceMembers.stream()
			.map(wm -> {
				Workspace workspace = wm.getWorkspace();
				return new WorkspaceDto(workspace.getId(), workspace.getWorkspaceName(), workspace.getCreatedAt());
			}).collect(Collectors.toList());

		log.info("service ws ist : ", workspaceDtos);

		// 반환
		return workspaceDtos;
	}
}
