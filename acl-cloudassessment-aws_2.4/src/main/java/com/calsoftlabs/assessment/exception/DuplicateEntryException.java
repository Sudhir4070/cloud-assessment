package com.calsoftlabs.assessment.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicateEntryException extends Exception implements ExceptionMapper<DuplicateEntryException>{

	private static final long serialVersionUID = 1L;
	 
    public DuplicateEntryException() {
        super("Duplicate question selected for customer, Please select new question");
    }
 
    public DuplicateEntryException(String string) {
        super(string);
    }
 
	@Override
	public Response toResponse(DuplicateEntryException exception) {
		// TODO Auto-generated method stub
		  return Response.status(500).entity(exception.getMessage())
                  .type("application/json").build();
	}

}
