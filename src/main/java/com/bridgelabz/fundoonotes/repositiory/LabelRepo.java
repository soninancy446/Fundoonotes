package com.bridgelabz.fundoonotes.repositiory;

import java.util.List;

import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;

public interface LabelRepo {

	public Note saveNote(Note notes);

	public Label saveLabel(Label label);

	public Label getLabel(Long userid, String labelname);

	public List<Label> getAllLabel(Long userid);

	public void deleteLabel(Long labelId);

	public void updateLabel(Long labelId);

	public Label GetLabel(Long labelId);

	public List<Note> getNoteByLabelId(String labelName);

	public List<Label> getLabelById(Long id);

	public Label findLabel(Long userid, String labelname);

	public int removeLabel(Long labelId, Long noteId);

}
