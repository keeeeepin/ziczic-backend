package com.ziczic.be.workspaceMember.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziczic.be.workspaceMember.entity.WorkspaceMember;



@Repository
public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {

	List<WorkspaceMember> findByMemberId(Long memberId);

}
