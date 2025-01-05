package com.ziczic.be.global.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(Map<String, Object> claims) {
		String token = Jwts.builder()
			.setClaims(claims)
			.setExpiration(getExpireDate())
			.signWith(getSigningKey())
			.compact();

		return token;
	}

	public Date getExpireDate() {
		long expireTimeMils = 1000 * 60 * 60;
		return new Date(System.currentTimeMillis() + expireTimeMils);
	}

	public Claims getClaimsFromToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
