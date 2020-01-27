package com.bridgelabz.fundoonotes.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exceptions.ErrorResponse;
import com.bridgelabz.fundoonotes.service.CollaboratorService;

@Configuration
@RestController
	@RequestMapping("/collaborator")
	@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "token" })
	public class CollaboratorController {
	
		@Autowired
		private CollaboratorService colabService;
		
		@PostMapping("/addcollab/{noteId}/{emailId}")
		public ResponseEntity<ErrorResponse>  addCollaborator(@PathVariable("emailId") String emailId,@PathVariable("id") Long id,@RequestHeader("token") String token)
		{	
			System.out.println("in  controller");
			colabService.addCollaborator(id, emailId, token);
//			List<User> colaborator= colabService.getColabNotes(token);
			return new ResponseEntity<>(
					new ErrorResponse(HttpStatus.OK.value(), "Collaborated"), HttpStatus.OK);
		}
//		
//		
//		@GetMapping("/colabuser/{noteId}")
//		public List<User> getCollaboratedList(@RequestHeader("token") String token,@PathVariable("noteId") Long noteId){
//			
//			return colabService.getCollaboratedList(token, noteId);
//		}
//		@DeleteMapping("/deleteuser/{noteId}/{emailId}")
//		public ResponseEntity<ErrorResponse> deletetCollaboratedList(@PathVariable("emailId") String emailId,@RequestHeader("token") String token,@PathVariable("noteId") Long noteId){
//			System.out.println("in colab delete");
//			colabService.deleteCollaboratedList(token, emailId, noteId);
//			return new ResponseEntity<>(
//					new ErrorResponse(HttpStatus.OK.value(), "Deleted"), HttpStatus.OK);
//		}
		
		}