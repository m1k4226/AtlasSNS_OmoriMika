package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.validation.group.NotBlankGroup;
import com.example.demo.validation.group.SizeGroup;

@Entity
@Table(name = "posts")
public class Post {
	
//	CREATE TABLE posts(
//			id SERIAL PRIMARY KEY,
//			user_id Integer NOT NULL,CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
//			post varchar(400) NOT NULL,
//			created_at timestamp NOT NULL DEFAULT current_timestamp,
//			updated_at timestamp NOT NULL DEFAULT current_timestamp
//		);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "content")
	@NotBlank(message = "内容を入力してください", groups = NotBlankGroup.class)
	@Size(min = 1, max = 150, message = "150文字以内で入力してください", groups = SizeGroup.class)
	private String content;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
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
	// updatedAt のみ更新
//	@PreUpdate
//	public void onUpdate() {
//		this.updatedAt = LocalDateTime.now();
//	}
	
	// デフォルトコンストラクタ
	public Post() {
		super();
	}

	// 新規投稿
	public Post(User user, String content) {
		super();
		this.user = user;
		this.content = content;
	}

	// ゲッターセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User userId) {
		this.user = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
