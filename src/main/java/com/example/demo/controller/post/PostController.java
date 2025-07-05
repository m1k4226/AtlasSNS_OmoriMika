package com.example.demo.controller.post;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Post;
import com.example.demo.model.Account;
import com.example.demo.repository.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private Account account;
	
	@GetMapping("/top")
	public String index(Model model) {
		
		model.addAttribute("post", new Post());
		
		// 投稿一覧の取得
		Integer userId = account.getId();
		List<Post> postList = postRepository.findByFolloweePosts(userId);
		model.addAttribute("postList", postList);
		
		return "top";
	}
	
	@PostMapping("/top")
	public String createPost(
			@ModelAttribute @Valid Post post,
			BindingResult result,
			Model model) {
		
		// Entityのパリデーション確認
		if (result.hasErrors()) {
			 model.addAttribute("post", post);
		        return "top"; 
		    }

		// 投稿を保存
		postRepository.save(post);
		
		// 投稿一覧の取得
		Integer userId = account.getId();
		List<Post> postList = postRepository.findByFolloweePosts(userId);
		model.addAttribute("postList", postList);
		
		return "redirect:/posts/index";
	}
	
}
