package com.example.demo.users2;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController2 {
	
	@Autowired
	UserRepository2 userRepository;
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserModel>> getUser() {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserModel> getUserByUsername(@PathVariable String username){
		return ResponseEntity.ok(userRepository.findByUsername(username));
	}

	@GetMapping("/{Email}")
	public ResponseEntity<UserModel> getUserByEmail(@PathVariable String Email) {
		return ResponseEntity.ok(userRepository.findByEmail(Email));
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel) {
			userRepository.save(userModel);
			return ResponseEntity.ok(userModel);
		

	}
	
	@PostMapping("/login")
	public boolean loginUser(@RequestBody UserModel userModel) {
		for(UserModel u: userRepository.findAll()) {
			if (u.getEmail().equals(userModel.getEmail())&& u.getPassword().equals(userModel.getPassword())) {
				return true;
			}
		}
		return false;
		
	}

	
	
	
	@DeleteMapping("/{UserId}")
	public void deleteUser(@PathVariable int UserId) {
		userRepository.deleteById(UserId);
	}
}
