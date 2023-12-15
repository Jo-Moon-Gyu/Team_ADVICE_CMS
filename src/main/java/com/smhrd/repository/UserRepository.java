package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.entity.Tbl_User;

public interface UserRepository extends JpaRepository<Tbl_User,String>{
	
	public Tbl_User findByUserEmail(String userEmail);

}
