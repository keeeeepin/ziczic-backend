package com.ziczic.be.member.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziczic.be.global.jwt.JwtUtil;
import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final JwtUtil jwtUtil;
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

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> claims = objectMapper.convertValue(member, Map.class);

		String token = jwtUtil.generateToken(claims);

		return token;
	}
}