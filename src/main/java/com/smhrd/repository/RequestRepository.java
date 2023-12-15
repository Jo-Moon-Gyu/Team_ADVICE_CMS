package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.entity.Tbl_Request;

public interface RequestRepository extends JpaRepository<Tbl_Request, Integer>{

}
