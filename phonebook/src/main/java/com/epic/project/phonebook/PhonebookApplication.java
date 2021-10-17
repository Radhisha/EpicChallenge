package com.epic.project.phonebook;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("PMD")
@SpringBootApplication
public class PhonebookApplication {

	public static void main(String[] args) {
		//Starter
		Logger logger = LoggerFactory.getLogger(PhonebookApplication.class);
		logger.info("Starting PhoneBook Application ...");
		SpringApplication.run(PhonebookApplication.class, args);
		
	}

}
