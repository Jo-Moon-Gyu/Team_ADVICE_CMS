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
@Data
@NoArgsConstructor
@Entity
public class Tbl_Payment {

	
	// 질문 순번
		@Id
		@Column(nullable = false, name = "PAY_IDX")
		private int payIdx;
		
		@Column(nullable = false, name = "REQ_IDX")
		private int reqIdx;
		
		@Column(nullable = false, name = "PAID_AMOUNT")
		private int paidAmount;
		
		@Column(nullable = false, name = "SHARE_AMOUNT")
		private int shareAmount;
		
		@Column(nullable = false, name = "PAID_AT")
		@Temporal(TemporalType.TIMESTAMP)
		// 결제 날짜
		private Date paidAt;
		
		@Column(nullable = false, name = "USER_EMAIL")
		private String userEmail;
		
		@Column(name = "ANS_USER_EMAIL")
		private String ansUserEmail;
		
		@PrePersist
		protected void onCreate() {
			paidAt = new Date();
		}
}
