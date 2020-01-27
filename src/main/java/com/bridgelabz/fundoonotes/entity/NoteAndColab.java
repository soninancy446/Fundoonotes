package com.bridgelabz.fundoonotes.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class NoteAndColab {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long collabid;
	@Column
	private Long id;
	@Column
	private Long userid;
	@Column
	private String emailid;
	
}

/*


@Data
@Entity
public class Collaborator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long c_id;
	@Column
	private Long userid;
	@Column
	private Long noteId;
	@Column
	private String emailid;
}

*/