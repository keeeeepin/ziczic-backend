package com.ziczic.be.member.service;

import org.springframework.stereotype.Service;

import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void memberRegister(String  memberName, String memberPassword)
	{
		Member member = Member.builder()
			.name(memberName)
			.password(memberPassword)
			.build();

		memberRepository.save(member);
	}

	public String memberLogin(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("Member Not Found.."));

		return member.getMemberName();
	}
}