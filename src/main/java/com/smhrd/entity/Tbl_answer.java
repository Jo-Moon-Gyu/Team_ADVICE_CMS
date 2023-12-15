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
public class Tbl_answer {

	@Id
	@Column(nullable = false, name = "ANS_IDX")
	private int ansIdx;
	@Column(nullable = false, name = "REQ_IDX")
	private int reqIdx;
	@Column(name = "ANS_CONTENT")
	private String ansContent;
	@Column(name = "ANS_FILE")
	private String ansFile;
	@Column(nullable = false, name = "ANSWERED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date answeredAt;
	@Column(nullable = false, name = "ANS_USER_EMAIL")
	private String ansUserEmail;
	@Column(name = "ANS_USER_NAME")
	private String ansUserName;
	
	


    @PrePersist
    protected void onCreate() {
        answeredAt = new Date();
    }	
	
	
}


