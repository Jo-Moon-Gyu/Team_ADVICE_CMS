package com.smhrd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Tbl_Deep {

	@Id
	@Column(nullable = false, name = "DEEP_IDX")
	private int deepIdx;
	@Column(nullable = false, name = "REQ_IDX")
	private int reqIdx;
	@Column(nullable = false, name = "DEEP_IMG")
	private String deepImg;
	@Column(nullable = false, name = "DEEP_RESULT")
	private String deepResult;
	@Column(nullable = false, name = "USER_EMAIL")
	private String userEmail;

}
