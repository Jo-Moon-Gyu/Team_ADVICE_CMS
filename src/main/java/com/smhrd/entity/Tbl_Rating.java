package com.smhrd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Tbl_Rating {

	@Id
	@Column(nullable = false, name = "RATING_IDX")
	private int ratingIdx;
	@Column(nullable = false, name = "ANS_IDX")
	private int ansIdx;
	@Column(nullable = false, name = "USER_EMAIL")
	private String userEmail;
	@Column(nullable = false, name = "RATING")
	private float rating;
	@Column(nullable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
	
    @PrePersist
    protected void onCreate() {
    	createdAt = new Date();
    }	
	
}
