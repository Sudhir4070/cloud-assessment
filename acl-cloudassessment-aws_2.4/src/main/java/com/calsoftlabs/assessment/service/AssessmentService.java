package com.calsoftlabs.assessment.service;

import java.util.List;

import com.calsoftlabs.assessment.dto.AssessmentAnswer;
import com.calsoftlabs.assessment.dto.AssessmentQuestion;
import com.calsoftlabs.assessment.dto.Question;
import com.calsoftlabs.assessment.dto.QuestionAndOption;
import com.calsoftlabs.assessment.dto.QuestionBank;
import com.calsoftlabs.assessment.dto.SelectedQuestion;
import com.calsoftlabs.assessment.dto.User;

public interface AssessmentService {
	public List<Question> getQuestionnaire();
	public void addQuestion(QuestionBank question);
	public void addQuestions(AssessmentQuestion question);
	public List<AssessmentQuestion> getAssessmentQuestion();
	public void submitAnswer(List<AssessmentAnswer> answerLIst);
	public List<QuestionAndOption> getAssessmentAnswer(String email);
	public User findUserById(String emailId);
	public void setSelectedQuestion(SelectedQuestion selectedQuestions);
	public List<AssessmentQuestion> getCustomerQuestion(String email);
	public void signUp(User user);

}
