package com.zabdwi.ecommerce.backend.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginBody {
	@NotNull
	@NotBlank
	private String userName;
	@NotNull
	@NotBlank
	@Size(min = 6, max = 32)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
