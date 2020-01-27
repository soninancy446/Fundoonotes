package com.bridgelabz.fundoonotes.repositiory;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.entity.User;

public interface UserRepo {

	public User save(User user);

	public User getUser(String Email);
	
	public boolean verify(String email);

	public User checkByEmail(String email);

	public User checkById(Long id);

	public Optional<User> getUserById(Long id);

	public boolean isValidUser(Long id);

	public boolean verify(Long id);

	public int deleteUserById(Long id);

	public boolean deleteByEmail(String email);

//	public boolean updateByEmail(String email);

	public void resetPassword(String email, String password);

	public List<User> getUsers();

	public boolean upDate(RegistrationDto registrationDto, Long token);

	public int getByEmail(String email);

}
