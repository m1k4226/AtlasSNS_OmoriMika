package com.example.demo.controller.post;

import java.util.List;

//import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.validation.ValidationOrder;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Account account;
	
	// トップ表示
	@GetMapping("/index")
	public String index(Model model) {
		
		// ログインしていない場合はログイン画面へ
		if (account.getId() == null) {
			return "redirect:/auth/login";
		}
		
		// ログインユーザー取得
		Integer userId = account.getId();
		User user = userRepository.findById(userId).orElse(null); // null チェック
		
		
		// 投稿オブジェクト（エンティティバリデーションで確認するため）
		Post post = new Post(); 
		post.setUser(user);
		model.addAttribute("post", post);
		
		// セッション本人か他かの比較のため
		model.addAttribute("account", account);
		
		// 投稿一覧の取得
		List<Post> postList = postRepository.findByFolloweePosts(userId);
		model.addAttribute("postList", postList);
		
		return "/posts/index";
	}
	
	
	@PostMapping("/index")
	public String createPost(
			@ModelAttribute @Validated(ValidationOrder.class) Post post,
			BindingResult result,
			Model model) {
		
		// ログインユーザー取得
		Integer userId = account.getId();
		User user = userRepository.findById(userId).orElse(null); // null チェック

		// ログインしていない場合はログイン画面へ
		if (account.getId() == null) {
			return "redirect:/auth/login"; // ← redirectのスペルミス修正も含めてます
		}
		
		// Entityのパリデーション確認
		if (result.hasErrors()) {
			model.addAttribute("post", post);
			
			// セッション本人か他かの比較のため
			model.addAttribute("account", account);
			
			// 投稿一覧の取得
			List<Post> postList = postRepository.findByFolloweePosts(userId);
			model.addAttribute("postList", postList);
			
			return "/posts/index"; 
			}
		
		// 投稿にユーザーをセット
		post.setUser(user);
		
		// 投稿を保存
		postRepository.save(post);
		
		
//		// 投稿一覧の取得
//		Integer userId = account.getId();
//		List<Post> postList = postRepository.findByFolloweePosts(userId);
//		model.addAttribute("postList", postList);
		
		return "redirect:/posts/index";
	}
	
}
