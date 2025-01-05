package com.ziczic.be.workspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ziczic.be.workspace.entity.WorkspaceMember;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {
}
