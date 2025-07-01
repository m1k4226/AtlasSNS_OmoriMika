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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	private String name;
	
	private String email;
	private String password;
	private String bio;
	
	@Column(name = "icon_image")
	private String image;
	
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
