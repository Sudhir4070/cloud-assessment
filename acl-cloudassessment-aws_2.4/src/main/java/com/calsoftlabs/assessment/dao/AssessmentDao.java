package com.calsoftlabs.assessment.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.calsoftlabs.assessment.dto.AssessmentAnswer;
import com.calsoftlabs.assessment.dto.AssessmentQuestion;
import com.calsoftlabs.assessment.dto.Question;
import com.calsoftlabs.assessment.dto.QuestionAndOption;
import com.calsoftlabs.assessment.dto.QuestionBank;
import com.calsoftlabs.assessment.dto.SelectedQuestion;
import com.calsoftlabs.assessment.dto.User;

public interface AssessmentDao {
	public List<QuestionBank> getQuestionnaire();
	public void addQuestion(QuestionBank question);
	public void addQuestions(AssessmentQuestion question);
	public List<AssessmentQuestion> getAssessmentQuestion();
	public void submitAnswer(List<AssessmentAnswer> answerList);
	public List<QuestionAndOption> getAssessmentAnswer(String email);
	public User findUserById(String email);
	public void setSelectedQuestion(SelectedQuestion selectedQuestions) throws HibernateException;
	public List<AssessmentQuestion> getCustomerQuestion(String email);
	public void signUp(User user);

}
