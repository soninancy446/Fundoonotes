package com.bridgelabz.fundoonotes.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.config.RabbitMqSender;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.PasswordForgotDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exceptions.UserException;
import com.bridgelabz.fundoonotes.repositiory.UserRepo;
import com.bridgelabz.fundoonotes.utility.EmailService;
//import com.bridgelabz.fundoonotes.utility.JmsProvider;
import com.bridgelabz.fundoonotes.utility.JwtProvider;
import com.bridgelabz.fundoonotes.utility.Mail;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	// @Autowired
	// private JmsProvider jmsProvider;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private User user;
	@Autowired
	RabbitMqSender rabbitMqSender;

	@Autowired
	RegistrationDto registrationDto;

	@Autowired
	private EmailService emailService;

	@Transactional
	@Override
	public RegistrationDto register(RegistrationDto registrationdto) {

		User user = new User();
		BeanUtils.copyProperties(registrationdto, user);

		User use = userRepo.checkByEmail(user.getEmail());
		if (use != null) {
			throw new  UserException("all ready exist");
		}
	User users=	userRepo.save(user);
	users.getUserid();

		String url = "http://localhost:8080/users/verify/";
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// JmsProvider.sendEmail(user.getEmail(), "For Authentication",
		// url + jwtProvider.generateToken(user.getEmail()));
		// rabbitMqSender.send(new Mail(user.getEmail(),"for
		// authentication",url,jwtProvider.generateToken(user.getEmail())));
		
		String token = jwtProvider.generateToken(users.getUserid());
		System.out.println(token);
//		System.out.println("user email" + user.getEmail());
		Mail mail = new Mail("mailfromsanjeet@gmail.com", registrationdto.getEmail(), "mail from Nancy", url + token);
		emailService.sendSimpleMessage(mail);
		return registrationdto;

	}

	@Transactional
	@Override
	public String login(LoginDto logindto) {
		User user = userRepo.checkByEmail(logindto.getEmail());
		String message = null;

		if (user != null) {

			boolean encryptedPassword = passwordEncoder.matches(logindto.getPassword(), user.getPassword());
			System.out.println(encryptedPassword);
			if (encryptedPassword) {
				String token = jwtProvider.generateToken(user.getUserid());
				message = token;
				return message;
			} else if ((user.isIsverified() == false) && (encryptedPassword == false)) {
				throw new UserException("Check mail and password");
			} else if (user.isIsverified() && (encryptedPassword == false)) {

				throw new UserException("check password");
			} else if (!user.isIsverified()) {

				throw new UserException("check ur email");
			} else {
				System.out.println("u login");
			}

		}
		return message;
	}

	@Transactional
	@Override
	public boolean Verify(String token) throws SQLException {
		Long userid=jwtProvider.decodeToken(token);
		 User user1=userRepo.checkById(userid);
		 if(user1.isIsverified())
		 { 
			 throw new UserException("u r already verified");
//			System.out.println("you are already verified"); 
		 }
		 System.out.println("verification code in service"+user1.getUserid());
		if(user1!=null&&(!user1.isIsverified()))
		{   System.out.println(" within if condotion");
			
				user1.setIsverified(true);
				userRepo.save(user1);
				
			return true;
		

			}
		else {
			return false;
			
		}
		
		
	}

	@Transactional
	@Override
	public User isUserPresent(Long Id) {
		return userRepo.checkById(Id);
	}

	@Transactional
	@Override
	public String forgetPassword(PasswordForgotDto passwordForgotDto) {
		// User user = new User(passwordForgotDto);

		User use = userRepo.checkByEmail(passwordForgotDto.getEmail());

		String url = "http://localhost:8080/users/resetPassword/";
		// String emailid = user.getEmail();

		if (use != null) {
//			String token = jwtProvider.generateToken(use.getEmail());
			String token=jwtProvider.generateToken(use.getUserid());
			// JmsProvider.sendEmail(emailid, "for update password", url + token);
			Mail mail = new Mail("mailfromsanjeet@gmail.com", passwordForgotDto.getEmail(), "mail from Nancy",
					url + token);
			emailService.sendSimpleMessage(mail);
			return (use.getEmail());
		}
		return "Not Found";
	}

	@Transactional
	@Override
	public boolean resetPassword(String token, String password) {
		User user = new User();
		Long userid=jwtProvider.decodeToken(token);
//		User newuser=userRepo.checkById(userid) throw ;
		User newuser = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
		if(newuser!=null) 
		{
			String newPassword = passwordEncoder.encode(password);
			newuser.setPassword(newPassword);
			
			userRepo.save(newuser)	;
			return true;
		}
		return false;
		
	}

	

//	@Transactional
//	@Override
//	public boolean updateByEmail(String email) {
//		User use = userRepo.checkByEmail(user.getEmail()).orElseThrow(()->new UserException("user not found"));
//		if (use!=null)
//		{
//			
//		}
//			
//		return false;
//		
//
//	}

	@Transactional
	@Override
	public boolean deleteByEmail(String email) {
		if ((userRepo.checkByEmail(email)).equals(user.getEmail())) {
			userRepo.deleteByEmail(user.getEmail());
		}

		else {
			throw new UserException("not deleted");
		}
		return false;

	}

	@Transactional
	@Override
	public int deleteUserById(Long id) {
		// suserDAO.deleteUser(userId);
		if (userRepo.deleteUserById(id) > 0) {
			return 1;
		} else {
			throw new UserException("NOt deleted");
		}

	}

	@Transactional
	@Override
	public User getuserById(Long id) {
		User user = userRepo.getUserById(id).orElseThrow(() -> new UserException("user not found"));
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public List<User> getUsers() {
		System.out.println("inside get users");
		List<User> users = userRepo.getUsers();
		User user = users.get(0);
//		List<Note> note = user.getCollaborateNote();
		// System.out.println(note.get(0).getId());
		return users;
	}

	@Transactional
	@Override
	public User getSingleUser(String token) {
		Long id;
		try {
			id = jwtProvider.decodeToken(token);
		} catch (Exception e) {

			throw new UserException("User doesn't exist");
		}

		User	 user = userRepo.getUserById(id).orElseThrow(() -> new UserException("user not found"));
//		System.out.println(user.getCollaborateNote().toString());
		return null;
	}

}
