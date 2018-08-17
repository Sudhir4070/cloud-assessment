package com.calsoftlabs.assessment.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WebApplicationException extends Exception implements ExceptionMapper<Throwable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2217422957107515191L;

	@Override
	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		  return Response.status(500).entity("Something Bad happened, please try after some time!!")
                  .type("appication/json").build();
	}

}
