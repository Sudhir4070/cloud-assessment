package com.calsoftlabs.assessment.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calsoftlabs.assessment.dao.AssessmentDao;
import com.calsoftlabs.assessment.dto.AssessmentAnswer;
import com.calsoftlabs.assessment.dto.AssessmentQuestion;
import com.calsoftlabs.assessment.dto.Question;
import com.calsoftlabs.assessment.dto.QuestionAndOption;
import com.calsoftlabs.assessment.dto.QuestionBank;
import com.calsoftlabs.assessment.dto.SelectedQuestion;
import com.calsoftlabs.assessment.dto.User;


@Service
public class AssessmentServiceImpl implements AssessmentService{

	@Autowired
	public AssessmentDao assessmentDao;
	
	@Override
	public void signUp(User user) {
		assessmentDao.signUp(user);
		
	}
	
	@Override
	public List<Question> getQuestionnaire() {
		List<QuestionBank> questionBankList = assessmentDao.getQuestionnaire();
		
		List<Question> questionList = new ArrayList<Question>();
		for(QuestionBank questionBank : questionBankList) {
			List<String> optionList = new ArrayList<String>();
			List<String> options = new ArrayList<String>();
			
			 optionList.add(questionBank.getOption1()) ;
			 optionList.add(questionBank.getOption2()) ;
			 optionList.add(questionBank.getOption3()) ;
			 optionList.add(questionBank.getOption4()) ;
			 optionList.add(questionBank.getOption5()) ;
			 optionList.add(questionBank.getOption6()) ;
			 optionList.add(questionBank.getOption7()) ;
			 optionList.add(questionBank.getOption8()) ;
			 optionList.add(questionBank.getOption9()) ;
			 optionList.add(questionBank.getOption10()) ;
			 
			 Iterator iterator = optionList.iterator();
			 while(iterator.hasNext()) {
				String option = (String) iterator.next(); 
				if(option != null) {
					options.add(option);
				}
			 }
		
			
			Question question = new Question();
			question.setQuestionName(questionBank.getQuestionName());
			question.setOptionType(questionBank.getOptionType());
			question.setOptions( Arrays.copyOf(options.toArray(new String[options.size()]), options.toArray(new String[options.size()]).length, String[].class));
			questionList.add(question);
	
			
		}
		return questionList;
	}
	@Override
	public void addQuestion(QuestionBank questionbank) {
		assessmentDao.addQuestion(questionbank);
		
	}
	@Override
	public void addQuestions(AssessmentQuestion question) {
		assessmentDao.addQuestions(question);
		
	}
	@Override
	public List<AssessmentQuestion> getAssessmentQuestion() {
		List<AssessmentQuestion> assessmentQuestions = assessmentDao.getAssessmentQuestion();		
		return assessmentQuestions;
	}
	@Override
	public void submitAnswer(List<AssessmentAnswer> answerList) {
		/*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	   formatter.format(date) */
		assessmentDao.submitAnswer(answerList);
		
	}
	@Override
	public List<QuestionAndOption> getAssessmentAnswer(String email) {
		// TODO Auto-generated method stub
		List<QuestionAndOption> assessmentAnswers = assessmentDao.getAssessmentAnswer( email);		
		return assessmentAnswers;
	}
	@Override
	public User findUserById(String emailId) {
		// TODO Auto-generated method stub
	return assessmentDao.findUserById(emailId);
	
	}
	@Override
	public void setSelectedQuestion(SelectedQuestion selectedQuestions) {
		try {
		assessmentDao.setSelectedQuestion(selectedQuestions);
		}catch(HibernateException hibernateException) {
			throw hibernateException;
		}catch(Exception exception) {
			throw exception;
		}
	}
	@Override
	public List<AssessmentQuestion> getCustomerQuestion(String email) {
		return assessmentDao.getCustomerQuestion(email);
	}
	

	


	 
}
