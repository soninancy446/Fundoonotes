	package com.bridgelabz.fundoonotes.service;

	import java.io.UnsupportedEncodingException;

	import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.entity.Note;
	
@Service
	public interface CollaboratorService {
		
		
		Note addCollaborator(Long noteId, String email, String token);

		Note removeCollaborator(Long noteId, String email, String token);

		List<Note> getColabNotes(String token )
				throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException;

	}
