package com.calsoftlabs.assessment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "selected_option")
public class SelectedOption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/*@Column(name = "answer_id")
	private int answerId;*/
	
	/*@Column(name = "question_id")
	private int questionId;*/
	
	@Column(name = "option_id")
	private int optionId;
	
	public SelectedOption() {}
	
	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public SelectedOption( int optionId)//, int questionId) 
			{		
		this.optionId = optionId;
	//	this.questionId = questionId;
	}

	/*public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}*/
}
