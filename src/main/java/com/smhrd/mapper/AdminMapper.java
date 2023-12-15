package com.smhrd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smhrd.entity.QNA;
import com.smhrd.entity.QuestionInfo;
import com.smhrd.entity.Rating_View;
import com.smhrd.entity.Tbl_Admin;
import com.smhrd.entity.Tbl_Deep;
import com.smhrd.entity.Tbl_Payment;
import com.smhrd.entity.Tbl_Request;
import com.smhrd.entity.Tbl_User;

@Mapper
public interface AdminMapper {

	// 로그인
	public Tbl_Admin login(Tbl_Admin admin);

	// 비밀번호 변경
	public int changePw(Tbl_Admin admin);

	// 질문 상세 조회
	public QuestionInfo Q_detail(int req_idx);

	// 사용자 계정 수정
	public int accountChange(Tbl_User user);

	public List<Tbl_User> search(String userRole);

	public List<Rating_View> high_rating();

	public List<QNA> qnaNull(String user_email);

	public List<QNA> qnaNotNull(String user_email);

	public List<QNA> MedicQnaNull();

	public List<QNA> MedocQnaNotNull(String user_email);

	public List<QNA> UserQnaNotNull(String user_email);

	public int WriteDelete(String user_email);

	public void req_insert(Tbl_Request tblrequest);

	public void deep_insert(Tbl_Deep tblDeep);

	public void pay_insert(Tbl_Payment tblPayment);

	public List<QNA> qna_detail(String user_email);

	public int minusToken(String user_email);

	public void answerPay(String user_email, String req_idx);

	public void updateUserShare(String user_email);

	public int ratingSel(Map<String, Object> paramMap);
}
