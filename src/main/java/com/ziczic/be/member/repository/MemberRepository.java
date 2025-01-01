package com.ziczic.be.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ziczic.be.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findById(Long id);
}