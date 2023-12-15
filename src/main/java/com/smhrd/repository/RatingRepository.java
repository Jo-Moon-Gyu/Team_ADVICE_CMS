package com.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.entity.Tbl_Rating;

public interface RatingRepository extends JpaRepository<Tbl_Rating, Integer> {

	
}
