package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Configuration
@Data
public class RegistrationDto {
	
	@NotBlank(message="field should not be empty")
	@Pattern(regexp="[a-zA-Z]*",message="only alphabets are allowed")
	private String firstname;
	
	private String lastname;

	@Email
	private String email;
	
	@NotNull(message="field should not be empty")
	@Pattern(regexp = "[0-9]*",message="only numbers is allowed")
	private String mobileno;
	
	
	@NotBlank(message="field should not be empty")
	@Pattern(regexp ="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
	                      message="Enter a valid password")
	private String password;

}
