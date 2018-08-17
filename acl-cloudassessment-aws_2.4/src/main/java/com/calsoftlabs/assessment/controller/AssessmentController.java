package com.calsoftlabs.assessment.controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.calsoftlabs.assessment.dto.AssessmentAnswer;
import com.calsoftlabs.assessment.dto.AssessmentQuestion;
import com.calsoftlabs.assessment.dto.Message;
import com.calsoftlabs.assessment.dto.Question;
import com.calsoftlabs.assessment.dto.QuestionAndOption;
import com.calsoftlabs.assessment.dto.QuestionBank;
import com.calsoftlabs.assessment.dto.SelectedQuestion;
import com.calsoftlabs.assessment.dto.User;

import com.calsoftlabs.assessment.dto.UserObject;
import com.calsoftlabs.assessment.exception.DuplicateEntryException;
import com.calsoftlabs.assessment.service.AssessmentService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/assessmentCloud")
public class AssessmentController {
	
	@Autowired
	public AssessmentService assessmentService;
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserObject userObject) {
		Message message  = new Message();
		User user = assessmentService.findUserById(userObject.getEmail());
		if(user != null) {
			if(user.getPassword().equals(user.getPassword())) {
				return Response.status(200).entity(user).build();
			}else
			{
				//	return Response.status(200).entity(userDetails).build();
				message.setMessage("Invalid Email or Password");
				return Response.status(401).entity(message).build();
			}
		}else
		{
			message.setMessage("User Doesn't Exist");
			return Response.status(Response.Status.NOT_FOUND).entity(message).build();
		}
	}
	
	@POST
	@Path("/signUp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(User user) {
		 boolean isUserExist = false;
		 User userObject = null;
		try {
			   userObject = assessmentService.findUserById(user.getEmail());
					  
	       
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		  if (userObject != null) {
		 //	   logger.info("User already exist.");
		        return Response.status(Status.CONFLICT).entity("User already exist.").build();
		         }
		        assessmentService.signUp(user);
		         return Response.created(URI.create("/cloudassessment/assessment/user/"+ user.getUserId())).build();
				
		
	}
	

	@Path("/questionnaire")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionnaire() {
		List<Question> questionnaire = assessmentService.getQuestionnaire();
		return Response.ok(questionnaire).build();
	}
	
	@Path("/addQuestion")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestion(QuestionBank question) {
		assessmentService.addQuestion(question);
		return Response.ok().build();
	}
	
	@Path("/addQuestions")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestions(AssessmentQuestion question) {
		assessmentService.addQuestions(question);
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assessmentQuestions")
	public List<AssessmentQuestion> getAssessmentQuestion() {
		List<AssessmentQuestion> assessmentQuestions = assessmentService.getAssessmentQuestion();
		return assessmentQuestions;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/submitAnswer")
	public Response submitAnswer(List<AssessmentAnswer> answerList) {
		assessmentService.submitAnswer(answerList);
		return Response.status(201).build();
}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assessmentAnswers/{customerEmail}")
	public List<QuestionAndOption> getAssessmentAnswer(@PathParam("customerEmail") String email) {
		List<QuestionAndOption> assessmentAnswers = assessmentService.getAssessmentAnswer(email);
		return assessmentAnswers;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/setQuestions")
	public Response setSelectedQuestions(SelectedQuestion selectedQuestions) throws DuplicateEntryException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			assessmentService.setSelectedQuestion(selectedQuestions);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DuplicateEntryException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DuplicateEntryException();
		}
		System.out.println(jsonInString);
		
		return Response.status(201).build();
}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customerQuestion/{customerEmail}")
	public Response getCustomerQuestion(@PathParam("customerEmail") String email) {
		List<AssessmentQuestion> assessmentQuestions = assessmentService.getCustomerQuestion(email);
		return Response.status(200).entity(assessmentQuestions).build();
	}
}