package com.bridgelabz.fundoonotes.repositiory;

import java.util.List;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;

public interface NoteRepository {
	
public 	Note saveNote(Note note);

public boolean deleteNote(Long id,Long userid);

public Note checkById(Long id);

public Note  getNotebyUserId(Long userid);

public Note getNotebyNoteId(Long id);
	
public List<Note> getArchiveNotebyUserId(Long userid);

public List<Note> getTrashNotebyUserId(Long userid);	

public boolean updateColour(long id, long userid, String colour);

public	void saveColab(Note note);
public List<Note> getNotes(long userid);

public List<Note>getAllNote(Long userid);



}