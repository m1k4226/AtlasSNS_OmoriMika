package com.example.demo.controller.auth;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;



@Controller
public class RegisterController {
	
	@Autowired
	private UserRepository userRepository;
	
	// 新規登録画面の表示
	// userオブジェクト(th:object="user")を使用するための準備
	@GetMapping("/register")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	// 入力内容の確認、完了画面に遷移
	// @ModelAttribute：フォームから送信されたパラメータを User クラスのフィールドに自動でマッピング(省略可)
	// @Valid：User クラスに書かれたバリデーションアノテーション（@NotBlank など）を実行
	// User user：マッピングされたデータを格納するオブジェクト（登録対象）
	// BindingResult result：@Valid のバリデーション実行結果を保持するオブジェクトのため、必ず@Validの直後に明記
	// 　→エラーがあれば、result.hasErrors() で判定
	@PostMapping("/register")
	public String register(
			@ModelAttribute @Valid User user,
			BindingResult result,
			@RequestParam String passconfirm,
			Model model) {
		
		
		// メールアドレスの重複確認
		// result.rejectValue()は、BindingResult resultにエラーを追加するメソッド
		// duplicateは、第二引数で国際化対応やテンプレート切り替えのための"エラーコード"
		// たとえば messages.propertiesに定義すれば再利用できる
		// 省略する場合はnullの記述
		if(userRepository.existsByEmail(user.getEmail())) {
			 result.rejectValue("email","duplicate","登録済みメールアドレスのため使用できません");
		 }
		 
		// パスワードとパスワード確認の一致確認
		if(!user.getPassword().equals(passconfirm)) {
			result.rejectValue("password","duplicate","パスワードが一致しません");
		 }
		 
		
		// Entityのパリデーション確認
		if (result.hasErrors()) {
			 model.addAttribute("user", user);
		        return "register"; 
		    }
		 
		// DBに保存
		userRepository.save(user);
		model.addAttribute("user", user);
		
		return "redirect:/register/complete";
	}
	
	// 登録完了画面の表示
	@GetMapping("/register/complete")
	public String complete(Model model) {
		return "registerComplete";
	}

}
