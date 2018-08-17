package com.calsoftlabs.assessment.dto;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class QuestionId {
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private int questionId;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}
