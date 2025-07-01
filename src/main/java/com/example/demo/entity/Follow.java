package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "follows")
public class Follow {

//	CREATE TABLE follows(
//			id SERIAL PRIMARY KEY,
//			following_id Integer NOT NULL,
//			followed_id Integer NOT NULL,
//			created_at timestamp NOT NULL DEFAULT current_timestamp,
//			updated_at timestamp NOT NULL DEFAULT current_timestamp
//		);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "following_id")
	private Integer follower;
	
	@Column(name = "followed_id")
	private Integer followee;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// デフォルトコンストラクタ
	public Follow() {
		super();
	}

	// 新規作成用
	public Follow(Integer follower, Integer followee) {
		super();
		this.follower = follower;
		this.followee = followee;
	}

	// ゲッターセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFollower() {
		return follower;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	public Integer getFollowee() {
		return followee;
	}

	public void setFollowee(Integer followee) {
		this.followee = followee;
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
