package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NoteUpdation;
import com.bridgelabz.fundoonotes.dto.Notedto;
import com.bridgelabz.fundoonotes.dto.ReminderDto;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.exceptions.ErrorResponse;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utility.JwtProvider;

@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:3000", exposedHeaders = { "JwtToken" })
@RestController
@RequestMapping("/notes")
public class NoteController {
	@Autowired
	private NoteService noteService;
	@Autowired
	private JwtProvider jwtprovider;

	@PostMapping("/createNote/{token}")
	public ResponseEntity<ErrorResponse> createNote(@RequestBody Notedto notedto, @PathVariable("token") String token) {
		System.out.println("token in controller" + token);
		Note note = noteService.createNote(notedto, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "note created"), HttpStatus.OK);
	}

	@PostMapping("update/{token}")
	public Response updateNote(@RequestBody NoteUpdation note, @PathVariable("token") String token) {
		System.out.println("inside update controller" + note);
		Note newNote = noteService.updateNote(note, token);
		return new Response(HttpStatus.OK.value(), "note updated", newNote);
	}

	@GetMapping("delete/{id}/{token}")
	public ResponseEntity<ErrorResponse> DeleteNote(@PathVariable("id") Long id, @PathVariable("token") String token) {
		noteService.deleteNote(id, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "note deleted"), HttpStatus.OK);
	}

	@DeleteMapping("deletePermanently/{id}/{token}")
	public ResponseEntity<ErrorResponse> deletePermenently(@PathVariable long id, @PathVariable("token") String token) {
		System.out.println("inside delete controller" + id);
		noteService.deleteNotePemenetly(id, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "note permanently deleted"),
				HttpStatus.OK);

	}

	@GetMapping("getAllNote/{token}")
	public Response getAllNotes(@PathVariable("token") String token) {
		System.out.println("Notes of userid:" + token);
		List<Note> notes = noteService.getAllNotes(token);
		System.out.println(notes);
		return new Response(HttpStatus.OK.value(), "got all notes", notes);

	}

	@GetMapping("getTrashedNote/{token}")
	public ResponseEntity<ErrorResponse> getTrashedNotes(@PathVariable("token") String token) {
		List<Note> notes = noteService.getTrashedNotes(token);

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "trash notes"), HttpStatus.OK);

	}

	@GetMapping("getAcharchivenote/{id}/{token}")
	public ResponseEntity<ErrorResponse> getArchiveNote(@PathVariable long id, @PathVariable("token") String token) {
		List<Note> notes = noteService.getArchiveNote(id, token);

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "The archive notes"), HttpStatus.OK);

	}

	@GetMapping("getpinnednote/{token}")
	public ResponseEntity<ErrorResponse> getPinnedNote(@PathVariable("token") String token) {
		List<Note> notes = noteService.getAllPinnedNotes(token);

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Pinned notes"), HttpStatus.OK);

	}

	@PostMapping("addColour/{token}")
	public ResponseEntity<ErrorResponse> addColour(@RequestParam("id") Long id,
			@RequestParam("colour") String ColorCode, @PathVariable("token") String token) {
		System.out.println("Color Add :" + token);
		noteService.addColour(id, token, ColorCode);

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "ColorCode added"), HttpStatus.OK);

	}

	@PostMapping("addreminder/{token}")
	public ResponseEntity<ErrorResponse> addReminder(@RequestParam("noteId") Long id,
			@PathVariable("token") String token, @RequestBody ReminderDto reminder) {
		noteService.addReminder(id, token, reminder);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "added reminder"), HttpStatus.OK);

	}

	@PostMapping("removereminder/{token}")
	public ResponseEntity<ErrorResponse> removeReminder(@RequestParam("id") Long id,
			@PathVariable("token") String token) {
		noteService.removeReminder(id, token, null);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "removed reminder"), HttpStatus.OK);

	}

//		@GetMapping("search")
//		public ResponseEntity<ErrorResponse> search(@RequestParam("title") String title,
//				 @RequestHeader("token") String token) {
//			     List<Note> notes=noteService.searchByTitle(title);
//			     return new ResponseEntity<>(
//							new ErrorResponse(HttpStatus.OK.value(), "searched"), HttpStatus.OK);
//		}
}