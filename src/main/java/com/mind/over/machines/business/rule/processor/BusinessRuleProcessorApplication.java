package com.mind.over.machines.business.rule.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusinessRuleProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessRuleProcessorApplication.class, args);
	}

}
