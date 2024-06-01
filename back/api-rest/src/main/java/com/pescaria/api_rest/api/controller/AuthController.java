package com.pescaria.api_rest.api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pescaria.api_rest.domain.entity.Customer;
import com.pescaria.api_rest.domain.repository.CustomerRepository;
import com.pescaria.api_rest.dto.LoginRequestDTO;
import com.pescaria.api_rest.dto.RegisterRequestDTO;
import com.pescaria.api_rest.dto.ResponseDTO;
import com.pescaria.api_rest.infra.security.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
		Customer user = this.customerRepository.findByEmail(body.email())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		if(passwordEncoder.matches(body.password(), user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getFullName(), token));
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
		Optional<Customer> user = this.customerRepository.findByEmail(body.email());
		
		if(user.isEmpty()) {
			Customer newUser = new Customer();
			newUser.setPassword(passwordEncoder.encode(body.password()));
			newUser.setEmail(body.email());
			newUser.setFullName(body.fullName());
			this.customerRepository.save(newUser);

			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new ResponseDTO(newUser.getFullName(), token));
		}
		return ResponseEntity.badRequest().build();
		
	}
}
