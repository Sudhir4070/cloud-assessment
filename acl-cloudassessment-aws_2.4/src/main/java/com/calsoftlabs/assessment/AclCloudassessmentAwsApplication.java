package com.calsoftlabs.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@ComponentScan(basePackages = "com.calsoftlabs.assessment")
@SpringBootApplication
public class AclCloudassessmentAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AclCloudassessmentAwsApplication.class, args);
	}
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}
}
