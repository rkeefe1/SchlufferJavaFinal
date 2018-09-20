package com.example.demo.users;

import java.util.List;

import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping()
	public List<UserModel> getUser() {
		return userRepository.findAll();
	}

	@GetMapping("/username")
	public UserModel getUserByUsername(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}

	@GetMapping("/username/{userId}")
	public UserModel getUserByUserId(@PathVariable Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@GetMapping("/{email}")
	public UserModel getUserByEmail(@PathVariable String Email) {
		return userRepository.findByEmail(Email);
	}

	@PostMapping("/login")
	public UserModel loginUser(@RequestBody UserModel userModel) {
		for (UserModel u : userRepository.findAll()) {
			System.out.println(userModel.getUsername());
			System.out.println(userModel.getPassword());
			if (u.getUsername().equals(userModel.getUsername()) && u.getPassword().equals(userModel.getPassword())) {
				return u;
			}
		}
		return null;
	}

	@PostMapping("/logout")
	public void logoutUser(@RequestBody UserModel userModel) {
			HttpSession session = getUserId();
			if (session.getAttribute("userId") != null) {
				session.removeAttribute("userId");
				return;
			}
		}

	private HttpSession getUserId() {
		// TODO Auto-generated method stub
		return null;
	}


	@PostMapping("/signup")
	public UserModel addUser(@RequestBody UserModel userModel) {
		userRepository.save(userModel);
		return userModel;
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		userRepository.deleteById(userId);
	}
}