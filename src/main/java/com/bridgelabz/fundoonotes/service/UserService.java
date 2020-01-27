package com.bridgelabz.fundoonotes.service;

import java.sql.SQLException;
import java.util.List;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.PasswordForgotDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.entity.User;

public interface UserService {

	public RegistrationDto register(RegistrationDto registrationdto);

	public String login(LoginDto logindto);

	public boolean Verify(String token) throws SQLException;

	public String forgetPassword(PasswordForgotDto email);

	public boolean resetPassword(String token, String password);

//	public boolean updateByEmail(String email);

	public boolean deleteByEmail(String email);

	public User isUserPresent(Long Id);

	public int deleteUserById(Long id);

	public User getuserById(Long id);

	List<User> getUsers();

	User getSingleUser(String token);

}
