package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QNA {

	private int req_idx;
	private String user_email;
	private String req_title;
	private String req_user_nick;
	private String user_birthyear;
	private String user_gender;
	private String req_content;
	private String req_img;
	private String deep_img;
	private String deep_result;
	private String created_at;
	private int ans_idx;
	private String ans_user_email;
	private String ans_user_name;
	private String ans_content;
	private String ans_file;
	private String answered_at;
	private String req_img_url;
	private String deep_img_url;
	
	
	
	
	
}
