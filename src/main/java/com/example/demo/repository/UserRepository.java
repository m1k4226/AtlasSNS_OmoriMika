package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "SELECT * "
			+"FROM users us "
			+"WHERE (us.email = ?1) "
			+"AND (us.password = ?2)",nativeQuery = true)
	Optional<User> findByEmailAndPass(String email,String password);
	
	@Query(value = "SELECT "
			+"CASE "
			+"WHEN COUNT(*) > 0 "
			+"THEN true "
			+"ELSE false "
			+"END "
			+"FROM users us "
			+"WHERE (us.email = ?1)",nativeQuery = true)
	boolean existsByEmail(String email);
	
	
}
