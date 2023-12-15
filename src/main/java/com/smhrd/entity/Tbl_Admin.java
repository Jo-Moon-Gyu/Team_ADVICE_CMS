package com.smhrd.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 관리자 계정
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Tbl_Admin {

	
	
	private String admin_id;
	private String admin_pw;
	private String admin_name;
	private String admin_email;
	private String admin_phone;
	private String admin_addr;
	private Date joined_at;
}
