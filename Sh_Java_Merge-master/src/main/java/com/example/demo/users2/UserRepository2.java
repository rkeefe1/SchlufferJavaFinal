package com.example.demo.users2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository2 extends JpaRepository<UserModel, Integer> {
	public UserModel findByEmail(String Email);
	public UserModel findByUsername(String username);
}
