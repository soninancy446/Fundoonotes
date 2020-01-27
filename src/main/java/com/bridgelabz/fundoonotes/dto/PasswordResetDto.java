package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Data

	public class PasswordResetDto {

	    @NotEmpty
	    private String password;

    
}
