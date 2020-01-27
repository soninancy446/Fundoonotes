package com.bridgelabz.fundoonotes.service;

import java.io.UnsupportedEncodingException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exceptions.UserException;
import com.bridgelabz.fundoonotes.repositiory.NoteRepository;
import com.bridgelabz.fundoonotes.repositiory.UserRepo;
import com.bridgelabz.fundoonotes.utility.JwtProvider;

@Configuration
public class CollaboratorImpl {
	
	@Service	
	public class CollaboratorServiceImplementation implements CollaboratorService{

		@Autowired
		private JwtProvider jwtProvider;
		@Autowired
		private UserRepo userRepo;
		
		@Autowired
		private NoteRepository noteRepo;
		
		@Override
		public Note addCollaborator(Long id, String email, String  token) {

			User collaborator = userRepo.getUser(email);
//			try {
				System.out.println("in service");
				Long userid = jwtProvider.decodeToken(token);
				System.out.println("inside note service" + userid);

				User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
//			}
//			
//			catch(Exception e) {
//				throw new UserException("user is not present with the given id ");
//			}
			if(user !=null) {
				if(collaborator !=null) {
			
					Note note=noteRepo.getNotebyNoteId(id);
					//note.getCollabList().add(collaborator);
					//user.getColaborateNote().add(note);
					//noteRepo.saveNote(note);
//					collaborator.getColaborateNote().add(note);
					collaborator.getCollaborateNote().add(note);
//					userRepo.save(collaborator);
//					noteRepo.saveNote(note);
					return note;
				}
				else {
					throw new UserException("user is not present with the given id ");
				}
			}
			else {
				throw new UserException("collaborator does not exist ");
			}
			
			
		}
		@Override
		public List<Note> getColabNotes(String token) throws JWTVerificationException,IllegalArgumentException,UnsupportedEncodingException{
			Long userid = jwtProvider.decodeToken(token);
			User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));			
			List<Note> notes=user.getCollaborateNote();
			return notes;
			
			
		}
		@Override
		public Note removeCollaborator(Long id, String email, String  token) {
			User user;
			User  collaborator = userRepo.getUser(email);
			try {
				System.out.println("in service");
				Long userid = jwtProvider.decodeToken(token);
				System.out.println("inside note service" + userid);

				User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
			}
			
			catch(Exception e) {
				throw new UserException("user is not present with the given id ");
			}
			Note note=noteRepo.getNotebyNoteId(id);
			note.getColabUser().remove(collaborator);

	
		return null;
		}
	}}


