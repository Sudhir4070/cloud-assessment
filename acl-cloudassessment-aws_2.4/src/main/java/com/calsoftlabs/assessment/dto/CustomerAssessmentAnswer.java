package com.calsoftlabs.assessment.dto;

import java.util.List;



public class CustomerAssessmentAnswer {
	
	
	private String firstName;
	private String lastName;
	private String email;
	private java.util.Date submittedOn;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(java.util.Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public List<QuestionAndOption> getQuestionAndOption() {
		return questionAndOption;
	}

	public void setQuestionAndOption(List<QuestionAndOption> questionAndOption) {
		this.questionAndOption = questionAndOption;
	}

	private List<QuestionAndOption> questionAndOption;
	

	
	
   
	
	
	
	
}
