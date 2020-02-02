package com.bridgelabz.fundoonotes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.PasswordForgotDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Configuration
@Data
@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String mobileno;

	// @Column(unique = true)
	private String email;
	@Column
	private String createdate;
	@Column
	private String password;
	@Column
	private String updatedate;
	@Column
	private boolean isverified;

	public User(RegistrationDto registrationDto) {

	}

	public User(LoginDto loginDto) {

		BeanUtils.copyProperties(loginDto, User.class);
		this.email = loginDto.getEmail();
		this.password = loginDto.getPassword();
	}

	public User(PasswordForgotDto passwordForgotDto) {
		this.email = passwordForgotDto.getEmail();

	}

	public User() {
	}

	@OneToMany(targetEntity = Note.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private List<Note> note;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Collaborator_Note", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = {
			@JoinColumn(name = "id") })
//	@JsonManagedReference
	@JsonIgnore
	private List<Note> colaborateNote;

}