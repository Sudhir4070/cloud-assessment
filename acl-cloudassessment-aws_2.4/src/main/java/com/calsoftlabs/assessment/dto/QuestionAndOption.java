package com.calsoftlabs.assessment.dto;

import java.util.Set;

public class QuestionAndOption {

	private String questionName;
	private Set<String> options;
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public Set<String> getOptions() {
		return options;
	}
	public void setOptions(Set<String> options) {
		this.options = options;
	}
}
