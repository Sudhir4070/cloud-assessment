package com.calsoftlabs.assessment.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity(name = "assessment_question")
public class AssessmentQuestion {
	
	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private int questionId;
	
	@Column(name = "question_name")
	private String questionName;
	
	@Column(name = "option_type")
	private String optionType;
	
/*	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="question_id")
	@IndexColumn(name="idx")*/

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	@OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name="question_id", nullable=false)
	private Set<AssessmentOption> options = new HashSet<AssessmentOption>();
	
	
	public AssessmentQuestion(String questionName, Set<AssessmentOption> options, String optionType) {
		this.questionName = questionName;
		this.options = options;
		this.optionType = optionType;
	}
	
	public AssessmentQuestion() {}
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public Set<AssessmentOption> getOptions() {
		return options;
	}

	public void setOptions(Set<AssessmentOption> options) {
		this.options = options;
	}

	
	
	

}
