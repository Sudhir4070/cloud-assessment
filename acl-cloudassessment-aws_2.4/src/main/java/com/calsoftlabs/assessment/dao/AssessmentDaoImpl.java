package com.calsoftlabs.assessment.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.calsoftlabs.assessment.dto.AssessmentAnswer;
import com.calsoftlabs.assessment.dto.AssessmentOption;
import com.calsoftlabs.assessment.dto.AssessmentQuestion;
import com.calsoftlabs.assessment.dto.CustomerAssessmentAnswer;
import com.calsoftlabs.assessment.dto.Question;
import com.calsoftlabs.assessment.dto.QuestionAndOption;
import com.calsoftlabs.assessment.dto.QuestionBank;
import com.calsoftlabs.assessment.dto.QuestionId;
import com.calsoftlabs.assessment.dto.SelectedOption;
import com.calsoftlabs.assessment.dto.SelectedQuestion;
import com.calsoftlabs.assessment.dto.User;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientException;


@Repository
@Transactional
public class AssessmentDaoImpl implements AssessmentDao{

	@PersistenceContext	
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<QuestionBank> questionnaire = new ArrayList<QuestionBank>();
	List<AssessmentQuestion> assessmentQuestions = new ArrayList<AssessmentQuestion>();
	
	@Override
	public void signUp(User user) {
		entityManager.persist(user);
		
	}
	
	@Override
	public List<QuestionBank> getQuestionnaire() {
		String hql = "FROM questionbank as question ORDER BY question.questionId";
		questionnaire =  entityManager.createQuery(hql).getResultList();
		return questionnaire;
	}

	@Override
	public void addQuestion(QuestionBank question) {
		entityManager.persist(question);
		
	}

	@Override
	public void addQuestions(AssessmentQuestion question) {
		/*for (Options option: question.getOptions())
			option.setAssessmentQuestion(question);;*/
		entityManager.persist(question);
		
	}

	@Override
	public List<AssessmentQuestion> getAssessmentQuestion() {
		String hql = "FROM assessment_question as question ORDER BY question.questionId";
		assessmentQuestions =  entityManager.createQuery(hql).getResultList();
		return assessmentQuestions;
	}

	@Override
	public void submitAnswer(List<AssessmentAnswer> answerList) {
		
	/*	
	
		
		String hql = "from assessment_answer where  email = :email";
		List<AssessmentAnswer> answerIdList = entityManager.createQuery(hql).setParameter(email, email).getResultList();
		for(AssessmentAnswer answer_id : answerIdList) {
			String hql1 = "delete from selected_option where  answer_id = :answer_id";
			entityManager.createQuery(hql1).setParameter(answer_id, answer_id.).executeUpdate();
			
		}
		
		*/
	/*	
		
		
		AssessmentAnswer ans = entityManager.find(AssessmentAnswer.class, email);
		entityManager.remove(ans);
		
		
		Query query = entityManager.createQuery("Select aa from AssessmentAnswer aa where aa.email=:email");
		query.setParameter("email", email);
		List<AssessmentAnswer> assessmentAnswers = query.getResultList();
		for(AssessmentAnswer answer : assessmentAnswers) {
			entityManager.remove(answer);
		}*/
		
	/*	String hql1 = "from assessment_answer where email = :email ";
		List<AssessmentAnswer> list = entityManager.createQuery(hql1).setParameter(email, email).getResultList();
		System.out.println("List" + list);
		for( : list) {
			
		}
		if(list != null) {
			String hql2 = "delete from assessment_answer where  email = :email";
			entityManager.createQuery(hql2).setParameter(email, email).executeUpdate();
		}
		*/
		AssessmentAnswer assessmentAnswer = answerList.get(0);
		String email = assessmentAnswer.getEmail();
	
		
		Query query = entityManager.createQuery("from assessment_answer where email=:email");
		query.setParameter("email", email);
		List<AssessmentAnswer> assessmentAnswers = query.getResultList();
		for(AssessmentAnswer ans : assessmentAnswers) {
			entityManager.remove(ans);
		}
		
		for(AssessmentAnswer answer : answerList) {
			entityManager.persist(answer);
			
		}
		
		
	}

	@Override
	public List<QuestionAndOption> getAssessmentAnswer(String email) {
		AssessmentAnswer answer = null;
		List<AssessmentAnswer> assessmentAnswers = new ArrayList<AssessmentAnswer>();
		List<QuestionAndOption> questionAndOptions = new ArrayList<QuestionAndOption>();
		try {
		/*	
			User user = findUserById(email);
			int userId = user.getUserId();*/
			/*Criteria criteria = sessionFactory.openSession().createCriteria(AssessmentAnswer.class);
			criteria.add(Restrictions.eq("userId", userId));
		    assessmentAnswers = criteria.list();*/
			
			Map<Integer,String> questionMap = new HashMap<Integer,String>();
			Map<Integer,String> optionMap = new HashMap<Integer,String>();
			CustomerAssessmentAnswer customerAssessmentAnswer = new CustomerAssessmentAnswer();
			
			List<AssessmentQuestion> assessmentQuestions = getAssessmentQuestion();
			for(AssessmentQuestion assessmentQuestion: assessmentQuestions) {
				questionMap.put(assessmentQuestion.getQuestionId(), assessmentQuestion.getQuestionName());
				Set<AssessmentOption> assessmentOptions = assessmentQuestion.getOptions();
				for(AssessmentOption assessmentOption : assessmentOptions) {
					optionMap.put(assessmentOption.getOptionId(), assessmentOption.getOptionName());
				}
			}
			
			
			
		/*	Query query =  sessionFactory.openSession().createQuery("from assessment_answer where email = :email ");
			query.setParameter("email", email);
			 assessmentAnswers = query.list();
			 */
			
			Query query = entityManager.createQuery("from assessment_answer where email=:email");
			query.setParameter("email", email);
			assessmentAnswers = query.getResultList();
			
			 
			 for(AssessmentAnswer assessmentAnswer : assessmentAnswers) {
				 QuestionAndOption questionAndOption = new QuestionAndOption();
				 Set<String> options = new HashSet<String>();
				 questionAndOption.setQuestionName(questionMap.get(assessmentAnswer.getQuestionId()));
				 for(SelectedOption selectedOption : assessmentAnswer.getOptionIds()) {
					 options.add(optionMap.get(selectedOption.getOptionId()));
				 }
				 questionAndOption.setOptions(options);
				 questionAndOptions.add(questionAndOption);
			 }
			 
			 
			
			/* for(AssessmentQuestion question : assessmentQuestions) {
					questionMap.put(question.getQuestionId(), question);
				}
				
				for(SelectedQuestion selectedQuestion : selectedQuestions) {
					Set<QuestionId> questionIds = selectedQuestion.getQuestionIds();
					for(QuestionId questId : questionIds) {
						customerQuestion.add(questionMap.get(questId.getQuestionId()));

					}
					
				}*/
			
		}catch(Exception exception) {
			exception.printStackTrace();
		
		}
		return questionAndOptions;
	}
	
	@Override
	public User findUserById(String email) {
		User user = null;
		try {
			
			Criteria criteria = sessionFactory.openSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("email", email));
			List entityList = criteria.list();
					
			if(!entityList.isEmpty()) {
				user = (User) entityList.get(0);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		
		}
		return user;
	}

	@Override
	@Transactional
	public void setSelectedQuestion(SelectedQuestion selectedQuestions)  {
		entityManager.persist(selectedQuestions);
		
	}
	
	public List<AssessmentQuestion> getCustomerQuestion(String email) {
		List<SelectedQuestion> selectedQuestions = null;
		List<AssessmentQuestion> assessmentQuestions = getAssessmentQuestion();
		List<AssessmentQuestion> customerQuestion = new ArrayList<AssessmentQuestion>();
		
		Map<Integer, AssessmentQuestion> questionMap = new HashMap<Integer, AssessmentQuestion>();
		Session session = sessionFactory.openSession();
		
		org.hibernate.Query query = session.createQuery("from selected_question where customer_email = :email ");
		query.setParameter("email", email);
		selectedQuestions = query.list();
		
		
		for(AssessmentQuestion question : assessmentQuestions) {
			questionMap.put(question.getQuestionId(), question);
		}
		
		for(SelectedQuestion selectedQuestion : selectedQuestions) {
			Set<QuestionId> questionIds = selectedQuestion.getQuestionIds();
			for(QuestionId questId : questionIds) {
				customerQuestion.add(questionMap.get(questId.getQuestionId()));

			}
			
		}
		
		
		
		return customerQuestion;
	}

	

}
