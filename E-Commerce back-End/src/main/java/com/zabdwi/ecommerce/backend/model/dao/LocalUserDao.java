package com.zabdwi.ecommerce.backend.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabdwi.ecommerce.backend.model.LocalUser;

@Repository
public interface LocalUserDao extends JpaRepository<LocalUser, Long>{
	Optional<LocalUser> findByUserNameIgnoreCase(String userName);
	Optional<LocalUser> findByEmailIgnoreCase(String Email);
}
