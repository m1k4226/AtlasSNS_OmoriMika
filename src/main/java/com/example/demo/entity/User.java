package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.validation.group.EmailGroup;
import com.example.demo.validation.group.NotBlankGroup;
import com.example.demo.validation.group.PatternGroup;
import com.example.demo.validation.group.SizeGroup;

@Entity
@Table(name = "users")
public class User {
//	CREATE TABLE users(
//			id SERIAL PRIMARY KEY,
//			username varchar(255) NOT NULL,
//			email varchar(255) NOT NULL CONSTRAINT unique_email UNIQUE,
//			password varchar(255) NOT NULL,
//			bio varchar(400),
//			icon_image varchar(255) NOT NULL DEFAULT 'icon1.png',
//			created_at timestamp NOT NULL DEFAULT current_timestamp,
//			updated_at timestamp NOT NULL DEFAULT current_timestamp
//		);


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	@NotBlank(message = "ユーザー名は必須です", groups = NotBlankGroup.class)
	@Size(min = 2, max = 12, message = "2文字以上,12文字以内で入力してください", groups = SizeGroup.class)
	private String name;
	
	@NotBlank(message = "メールアドレスは必須です", groups = NotBlankGroup.class)
	@Size(min = 5,max = 40,message = "5文字以上,40文字以内で入力してください", groups = SizeGroup.class)
	@Email(message = "メールアドレスの形式が有効ではありません", groups = EmailGroup.class)
	private String email;
	
	@NotBlank(message = "パスワードは必須です", groups = NotBlankGroup.class)
	@Size(min = 8, max = 20, message = "8文字以上,20文字以内で入力してください", groups = SizeGroup.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "英数字のみで入力してください", groups = PatternGroup.class)
	private String password;
	
//	@Transient
//	@NotBlank(message = "パスワード確認は必須です")
//	@Size(min = 8, max = 20, message = "8文字以上,20文字以内で入力してください")
//	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "英数字のみで入力してください")
//	private String passconfirm;
	
	@Column(nullable = true)
	private String bio;
	
	@Column(name = "icon_image")
	private String iconImage = "/images/icon1.png";
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	// Userからpostを辿るリレーション
	// Postのuserフィールドにマッピングされている
	// 双方向リレーションの場合、主導権(外部キーの管理役、外部キー未所持、親)を明示する必要あり、また、ループ対策
	// → (mappedBy = "user") 
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.REMOVE)
	private List<Post> posts;
	
	// 新規データ（INSERT）される直前に自動で呼ばれる
	// ※userRepository.save(user)実行時にJPAにより実行
	// createdAt と updatedAt の両方に現在時刻をセット
//	@PrePersist
//	public void onCreate() {
//		LocalDateTime now = LocalDateTime.now();
//		this.createdAt = now;
//		this.updatedAt = now;
//	}
	
	// 新規データ（INSERT）される直前に自動で呼ばれる
	// ※userRepository.save(user)実行時にJPAにより実行
	// updatedAt だけが更新
//	@PreUpdate
//	public void onUpdate() {
//		this.updatedAt = LocalDateTime.now();
//	}

	// デフォルトコンストラクタ
	public User() {
		super();
	}

	// 新規登録用
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// ゲッターセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String image) {
		this.iconImage = image;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
}
