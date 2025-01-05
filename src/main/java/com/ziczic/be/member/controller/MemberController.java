package com.ziczic.be.member.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void memberRegister(@RequestBody MemberRegisterReq req) {
		memberService.memberRegister(req.memberName(), req.memberPassword());
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public String memberLogin(@RequestBody MemberLoginReq req) {
		log.info("member Req : {}", req);
		String token = memberService.memberLogin(req.memberName(), req.memberPassword());
		log.info("token {}", token);
		return token;
	}
}