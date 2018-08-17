package com.calsoftlabs.assessment.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import com.calsoftlabs.assessment.controller.AssessmentController;
@Component
@ApplicationPath("/assessment")
public class JerseyConfig extends ResourceConfig {
   public JerseyConfig() {
	register(AssessmentController.class);
	 
   }
} 
