package com.bridgelabz.fundoonotes.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.PasswordForgotDto;
import com.bridgelabz.fundoonotes.dto.PasswordResetDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.repositiory.UserRepo;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.JwtProvider;

@Validated
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "JwtToken" })
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userrepo;
	private User user1 = new User();
	@Autowired
	private JwtProvider jwtProvider;

//id =jwtProvider.verifyToken(token);
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDto) {
		String user = userService.login(loginDto);
		User data = userrepo.getUser(loginDto.getEmail());
		String token = jwtProvider.generateToken(data.getUserid());
		System.out.println(token);
		if (user != null) {

			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Successfully Login", token),
					HttpStatus.OK);
		} else {
			System.out.println(user);
			return new ResponseEntity<Response>(
					new Response(HttpStatus.BAD_REQUEST.value(), "Unsuccessfully Login", token),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody RegistrationDto registrationDto) {

		RegistrationDto user = userService.register(registrationDto);

		if (user != null) {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.OK.value(), "Successfully Registered 123", user), HttpStatus.OK);

		} else {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.BAD_REQUEST.value(), "Unsuccessfully Registration", user),
					HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<String> isverified(@PathVariable("token") String token) throws SQLException {
        System.out.println("token inside of controller"+token);
		if (userService.Verify(token)) {
			return new ResponseEntity<String>("Verified", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not verified", HttpStatus.OK);
		}
	}

//	@PostMapping("/hello")
//	public String getString() {
//        
//		System.out.println("hello controller");
//		return "Hello";
//	}
	@PostMapping("/forgetpassword")
	public ResponseEntity<String> forgetPassword(@Valid @RequestBody PasswordForgotDto passwordForgotDto) {
		// System.out.println(passworddto.getEmail());
		String res = userService.forgetPassword(passwordForgotDto);
		System.out.println(res);
//		System.out.println("hiii..........");
		if (res != null) {

			return new ResponseEntity<String>("your password is correct", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("your password is incorrect", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/resetPassword/{token}")
	public ResponseEntity<String> resetPassword(@PathVariable("token") String token,
			@RequestBody PasswordResetDto passwordResetDto) {

		if (userService.resetPassword(token, passwordResetDto.getPassword())) {
			return new ResponseEntity<String>("Password reset", HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Password not reset", HttpStatus.OK);
		}
	}

//	@PostMapping("/update")
//	public ResponseEntity<String> updateByEmail(@RequestBody RegistrationDto registrationDto) {
//
//		if (userService.updateByEmail(registrationDto.getEmail())) {
//			return new ResponseEntity<String>("Updated", HttpStatus.OK);
//
//		} else {
//			return new ResponseEntity<String>("Not Updated", HttpStatus.OK);
//		}
//	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") Long id) {

		if (userService.deleteUserById(id) > 0) {
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Deleted", id), HttpStatus.OK);

		} else {
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Not Deleted", id),
					HttpStatus.BAD_REQUEST);
		}
	}

//	@PostMapping("/delete/{email}")
//	public ResponseEntity<String> deleteByEmail(@PathVariable("email")String email ) {
//		
//		if (userService.deleteByEmail(email)) {
//			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
//
//		} else {
//			return new ResponseEntity<String>("Not Deleted", HttpStatus.OK);
//		}
//	}
//	@SuppressWarnings("unused")
	@PostMapping("getUserById/{id}")
	public ResponseEntity<Response> getUserById(@PathVariable("id") Long id) {
		// System.out.println("hello ");
		User user = userService.getuserById(id);
		System.out.println("id" + id);

		if (user != null) {
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "OK", user), HttpStatus.OK);

		} else {
		}
		return new ResponseEntity<Response>(HttpStatus.OK);
	}
//	@GetMapping("user/getusers")
//	@Cacheable(value="users")
//	public ResponseEntity<Response> getUsers(){
//		System.out.println("inside get users contr...");
//		List<User> users=userService.getUsers();
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"OK",null),HttpStatus.OK);
//		
//		
//	}

	@GetMapping("user/getSingleUser")
	public Response getOneUsers(@RequestHeader("token") String token) {
		User user = userService.getSingleUser(token);
		return new Response(HttpStatus.OK.value(), "ok", user);

	}

}
