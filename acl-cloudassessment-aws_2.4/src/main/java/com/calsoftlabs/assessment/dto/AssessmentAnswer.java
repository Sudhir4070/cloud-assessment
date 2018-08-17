package com.calsoftlabs.assessment.dto;

import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "assessment_answer")
public class AssessmentAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private int answerId;
	
	@Column(name = "question_id")
	private int questionId;

	
	@OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name="answer_id", nullable=false)
	private Set<SelectedOption> optionIds;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date submittedOn;
	
	@Column(name = "email")
	private String email;
	
	public AssessmentAnswer() {};
	
	public AssessmentAnswer(int questionId, String email, Set<SelectedOption> optionIds) {
		this.questionId = questionId;
		this.email = email;
		this.optionIds = optionIds;
	}
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public Set<SelectedOption> getOptionIds() {
		return optionIds;
	}
	public void setOptionIds(Set<SelectedOption> optionIds) {
		this.optionIds = optionIds;
	}
	public java.util.Date getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(java.util.Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
