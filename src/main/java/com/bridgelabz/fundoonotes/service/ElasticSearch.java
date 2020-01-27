package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.entity.Note;

public interface ElasticSearch {
	
	String CreateNote(Note note);

	String UpdateNote(Note note);

	String DeleteNote(Note note);

	List<Note> searchbytitle(String title);

}
