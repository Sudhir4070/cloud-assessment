package com.calsoftlabs.assessment.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "selected_question")
public class SelectedQuestion {
	
	@Id
	@GeneratedValue
	@Column(name = "selected_id")
	private int selectedId;
	
	
	@Column
	private String customerEmail;
	

	@OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name="selected_id", nullable=false)
	private Set<QuestionId> questionIds;

	

	public SelectedQuestion() {}
	public SelectedQuestion(String customerEmail, Set<QuestionId> questionIds) {
		this.customerEmail = customerEmail;
		this.questionIds = questionIds;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	public Set<QuestionId> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(Set<QuestionId> questionId) {
		this.questionIds = questionId;
	}



}
