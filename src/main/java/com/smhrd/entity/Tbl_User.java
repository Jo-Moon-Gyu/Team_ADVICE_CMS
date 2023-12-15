package com.smhrd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


// 사용자 계정
@Data
@NoArgsConstructor
@Entity
public class Tbl_User {
	
	@Id
	@Column(nullable = false, name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_PW")
	private String userPw;
	@Column(nullable = false, name = "USER_NAME")
	private String userName;
	@Column(name = "USER_NICK")
	private String userNick;
	@Column(nullable = false, name = "USER_PHONE")
	private String userPhone;
	@Column(name = "USER_BIRTHYEAR")
	private String userBirthyear; 
	@Column(name = "USER_GENDER")
	private String userGender;
	@Column(nullable = false, name = "USER_LOGIN_TYPE")
	private String userLoginType;// 회원가입 방법
	@Column(nullable = false, name = "JOINED_AT")
	private Date joinedAt;
	@Column(name = "APPROVED_AT")
	private Date approvedAt;
	@Column(name = "USER_AUTH")
	private String userAuth; // 회원 증명서
	@Column(nullable = false, name = "USER_ROLE")
	private String userRole; // 회원 구분
	@Column( name = "USER_PROFIT")
	private int userProfit; // 회원 수익
	@Column(name = "USER_TOKEN")
	private int userToken;
	
	@PrePersist
	protected void onCreate() {
		joinedAt = new Date();
	}
	
}
