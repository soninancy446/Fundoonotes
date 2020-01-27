package com.bridgelabz.fundoonotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bridgelabz.fundoonotes.utility.EmailService;

@SpringBootApplication
public class FundoonotesApplication { // implements ApplicationRunner{

	@Autowired
	private EmailService emailService;
	private static Logger log = LoggerFactory.getLogger(FundoonotesApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(FundoonotesApplication.class, args);
	}

}
