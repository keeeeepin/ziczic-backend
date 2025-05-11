package com.ziczic.be.member.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziczic.be.global.jwt.JwtUtil;
import com.ziczic.be.member.dto.MemberLoginReq;
import com.ziczic.be.member.dto.MemberLoginResp;
import com.ziczic.be.member.dto.MemberRegisterReq;
import com.ziczic.be.member.entity.Member;
import com.ziczic.be.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final JwtUtil jwtUtil;
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@GetMapping("/test")
	@ResponseStatus(HttpStatus.OK)
	public String memberTest() {
		return "member Test Ok";
	}


	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void memberRegister(@RequestBody MemberRegisterReq req) {
		memberService.memberRegister(req.memberName(), req.memberPassword());
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> memberLogin(@RequestBody MemberLoginReq req) {
		Member member = memberService.memberLogin(req.memberName(), req.memberPassword());

		log.info("member : {}", member);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> claims = objectMapper.convertValue(member, Map.class);

		String token = jwtUtil.generateToken(claims);
		log.info("token : {}", token);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);

		return ResponseEntity
			.status(HttpStatus.OK)
			.headers(headers)
			.body(member);
	}
}