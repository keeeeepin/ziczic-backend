package com.ziczic.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String DEVELOP_FE_ORIGINS = "http://localhost:*";
	private static final String DOCKER_FE_ORIGINS = "http://ziczic-fe-container:*";
	private static final String IP_FE_ORIGINS = "http://172.30.1.2:*";  // 추가



	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns(DEVELOP_FE_ORIGINS, DOCKER_FE_ORIGINS, IP_FE_ORIGINS)
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true)
			.exposedHeaders("Authorization")
			.maxAge(3600);
	}
}
