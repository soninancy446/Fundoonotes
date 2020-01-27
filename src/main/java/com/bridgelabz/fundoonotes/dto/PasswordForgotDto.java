package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordForgotDto {
	
		
		@Email
		private String email;
		//private String password;

	}

