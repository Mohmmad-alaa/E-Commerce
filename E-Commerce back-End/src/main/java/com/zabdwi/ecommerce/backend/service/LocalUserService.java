package com.zabdwi.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabdwi.ecommerce.backend.api.model.LoginBody;
import com.zabdwi.ecommerce.backend.api.model.RegistertionBody;
import com.zabdwi.ecommerce.backend.exception.UserAlreadyExistsExcption;
import com.zabdwi.ecommerce.backend.model.LocalUser;
import com.zabdwi.ecommerce.backend.model.dao.LocalUserDao;

@Service
public class LocalUserService {

	@Autowired
	private LocalUserDao dao;
	
	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private JWTService jwtService;
	
	public LocalUser save(RegistertionBody body) throws UserAlreadyExistsExcption {

		if (dao.findByUserNameIgnoreCase(body.getUserName()).isPresent()
				|| dao.findByEmailIgnoreCase(body.getEmail()).isPresent()) {
			throw new UserAlreadyExistsExcption();
		}
		
		LocalUser localUser = new LocalUser();
		localUser.setUserName(body.getUserName());
		localUser.setPassword(encryptionService.encryptPassword(body.getPassword()));
		localUser.setEmail(body.getEmail());
		localUser.setFirstName(body.getFirstName());
		localUser.setLastName(body.getLastName());
		return dao.save(localUser);
	}
	
	public List<LocalUser> getUsers(){
		return dao.findAll();
	}
	
	public String loginUser(LoginBody loginBody) {
		 Optional<LocalUser> opUser = dao.findByUserNameIgnoreCase(loginBody.getUserName());
		 if (opUser.isPresent()) {
			LocalUser user = opUser.get();
			if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
				return jwtService.generateJWT(user);
			}
		 }
		 return null;
		 
	}

	
	
	
	
	
}
