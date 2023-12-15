package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 질문 + 답변 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfo {

	private int req_idx;
	private String user_email;
	private String req_user_nick;
	private String req_title;
	private String req_content;
	private String req_img;
	private String created_at;
	private int ans_idx;
	private String ans_user_email;
	private String ans_user_name;
	private String ans_content;
	private String ans_file;
	private String answered_at;

}
