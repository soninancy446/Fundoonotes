package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NoteUpdation;
import com.bridgelabz.fundoonotes.dto.Notedto;
import com.bridgelabz.fundoonotes.dto.ReminderDto;
import com.bridgelabz.fundoonotes.entity.Note;

public interface NoteService {

	public Note createNote(Notedto notedto, String token);

//	public List <Note> getNote(String token);

	public Note updateNote(NoteUpdation noteUpdation, String token);

	void deleteNote(long id, String token);

	public List<Note> getAllNotes(String token);

	List<Note> getTrashedNotes(String token);

	boolean deleteNotePemenetly(long id, String token);

	void archievNote(long id, String token);

	List<Note> getArchiveNote(Long id, String token);

	void addColour(Long id, String token, String colour);

	void addReminder(Long id, String token, ReminderDto reminder);

	void removeReminder(Long id, String token, ReminderDto reminder);

	void pin(long id, String token);

//	List<Note> searchByTitle(String title);

	List<Note> getAllPinnedNotes(String token);

}
