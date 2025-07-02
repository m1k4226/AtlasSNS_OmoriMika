package com.example.demo.controller.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;



@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping({"/","/login"})
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {
		
		if(email.isEmpty() || email == null || password.isEmpty() || password == null) {
			model.addAttribute("error","メールアドレスとパスワードを入力してください");
			return "login";
		}
		
		Optional<User> user = userRepository.findByEmailAndPass(email, password);
		
		if(user.isEmpty() || user == null) {
			model.addAttribute("error","メールアドレスまたはパスワードが正しくありません");
			model.addAttribute(email, email);
			return "login";
		}
		
		return "index";
	}
	
}
