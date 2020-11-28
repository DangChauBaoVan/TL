package com.ManagePhoto.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}


/*
	@Configuration
	public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}
	
	}

*/
