package com.smhrd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

// 질문
@Data
@NoArgsConstructor
@Entity
public class Tbl_Request {

	// 질문 순번
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_REQUEST_SEQ")
	@SequenceGenerator(name = "TBL_REQUEST_SEQ", sequenceName = "TBL_REQUEST_SEQ", allocationSize = 1)
	@Column(insertable = false, updatable = false, name = "REQ_IDX")
	private int reqIdx;
	@Column(nullable = false, name = "USER_EMAIL")
	// 회원 아이디
	private String userEmail;
	@Column(nullable = false, name = "REQ_TITLE")
	// 질문 제목
	private String reqTitle;
	@Column(nullable = false, name = "REQ_CONTENT")
	// 질문 내용
	private String reqContent;
	@Column(name = "REQ_IMG")
	// 상처 이미지
	private String reqImg;
	@Column(nullable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	// 문의 날짜
	private Date createdAt;
	@Column(name = "REQ_USER_NICK")
	// 질문 제목
	private String reqUserNick;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
}