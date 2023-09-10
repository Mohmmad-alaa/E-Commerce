package com.zabdwi.ecommerce.backend.api.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabdwi.ecommerce.backend.api.model.LoginBody;
import com.zabdwi.ecommerce.backend.api.model.LoginResponse;
import com.zabdwi.ecommerce.backend.api.model.RegistertionBody;
import com.zabdwi.ecommerce.backend.exception.UserAlreadyExistsExcption;
import com.zabdwi.ecommerce.backend.service.LocalUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private LocalUserService service;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistertionBody body) {
		System.out.println(body.getUserName());
		try {
			service.save(body);
			return ResponseEntity.ok().build();
		} catch (UserAlreadyExistsExcption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginBody body) {
		String jwt = service.loginUser(body);
		if (jwt == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			LoginResponse response = new LoginResponse();
			response.setJwt(jwt);
			return ResponseEntity.ok(response);
		}
	}
}
