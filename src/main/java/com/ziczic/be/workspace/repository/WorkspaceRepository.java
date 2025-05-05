package com.ziczic.be.workspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziczic.be.workspace.entity.Workspace;


@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
	@Query("SELECT w FROM Workspace w JOIN WorkspaceMember wm ON w.id = wm.workspace.id WHERE wm.member.id = :memberId")
	List<Workspace> findAllByMemberId(@Param("memberId") Long memberId);

	Optional<Workspace> findById(Long id);

}
