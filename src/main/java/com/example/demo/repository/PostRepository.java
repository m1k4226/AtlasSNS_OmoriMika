package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	@Query(value = "SELECT "
			+ "pt.* "
			+ "FROM posts pt "
			+ "WHERE pt.user_id = ?1 "
			+ "OR pt.user_id IN ( "
				+ "SELECT f.followed_id "
				+ "FROM follows f "
				+ "WHERE f.following_id = ?1) "
			+ "ORDER BY pt.updated_at DESC",nativeQuery = true)
	List<Post> findByFolloweePosts(Integer userId);

}
