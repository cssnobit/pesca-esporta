package com.pescaria.api_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pescaria.api_rest.infra.security.CustomUserDetailsService;
import com.pescaria.api_rest.infra.security.SecurityConfig;
import com.pescaria.api_rest.infra.security.SecurityFilter;
import com.pescaria.api_rest.infra.security.TokenService;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

}
