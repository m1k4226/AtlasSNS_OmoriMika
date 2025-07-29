package com.example.demo.controller.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;



@Controller
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Account account;
	
	@GetMapping({"/","/login"})
	public String index() {
		return "auth/login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {
		
		if(email.isEmpty() || email == null || password.isEmpty() || password == null) {
			model.addAttribute("error","メールアドレスとパスワードを入力してください");
			return "auth/login";
		}
		
		Optional<User> optionalUser = userRepository.findByEmailAndPass(email, password);
		
		if(optionalUser.isEmpty() || optionalUser == null) {
			model.addAttribute("error","メールアドレスまたはパスワードが正しくありません");
			model.addAttribute(email, email);
			return "auth/login";
		}
		
		User user = optionalUser.get();
		
	    account.setId(user.getId());
	    account.setName(user.getName());
	    account.setIconImage(user.getIconImage());
	    
		return "redirect:/posts/index";
	}
	
}
