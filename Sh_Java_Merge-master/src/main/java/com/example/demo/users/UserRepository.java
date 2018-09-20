package com.example.demo.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    public UserModel findByEmail(String Email);
    public UserModel findByUsername(String username);
//	public UserModel findByUserId(Integer toUserId);
	public List<UserModel> getUsersByUserId(Integer toUserId);
}