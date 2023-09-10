package com.zabdwi.ecommerce.backend.api.controller.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabdwi.ecommerce.backend.model.LocalUser;
import com.zabdwi.ecommerce.backend.service.LocalUserService;

@RestController
@RequestMapping("/getUsers")
public class TestGetUsers {

	@Autowired
	private LocalUserService service;

	@GetMapping("/getU")
	public List<LocalUser> getUsers() {
		return service.getUsers();

	}

}
