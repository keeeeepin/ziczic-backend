package com.ziczic.be.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public void memberRegister(String  memberName, String memberPassword)
	{
		Member member = Member.builder()
			.name(memberName)
			.password(memberPassword)
			.build();

		memberRepository.save(member);
	}

	public String memberLogin(String name, String password) {
		Member member = memberRepository.findByMemberName(name)
			.orElseThrow(() -> new EntityNotFoundException("Member Not Found.."));

		if(!member.getMemberPassword().equals(password)) {
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> claims = objectMapper.convertValue(member, Map.class);

		log.info("claims {}", claims);

		String token = jwtUtil.generateToken(claims);

		return token;
	}
}