package com.calsoftlabs.assessment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "assessment_option")
public class AssessmentOption {
	
	@Id
	@GeneratedValue
	@Column(name="option_id")
	private int optionId;
	
	@Column(name = "option_name")
	private String optionName;
	
	
	
	/*@ManyToOne
	@JoinColumn(name="question_id", nullable=false)
	private AssessmentQuestion assessmentQuestion;
	*/
	/*@ManyToOne
	@JoinColumn(name="question_id", 
				insertable=false, updatable=false, 
				nullable=false)*/
	
	
	public AssessmentOption(String optionName) {
		this.optionName = optionName;
	
	//	this.assessmentQuestion = assessmentQuestion;
	}
	
	/*
	public Options(String optionName, String optionType, AssessmentQuestion assessmentQuestion) {
		this.optionName = optionName;
		this.optionType = optionType;
		this.assessmentQuestion = assessmentQuestion;
	}*/
	
	public AssessmentOption() {}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

/*
	public AssessmentQuestion getAssessmentQuestion() {
		return assessmentQuestion;
	}

	public void setAssessmentQuestion(AssessmentQuestion assessmentQuestion) {
		this.assessmentQuestion = assessmentQuestion;
	}*/

}
