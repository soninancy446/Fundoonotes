package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;

public interface LabelService {

	public Label createLabel(LabelDto label, String token);

	public void updateLabel(LabelDto labelDto, String token);

	public void deleteLabel(LabelDto labelDto, String token);

	public void addLabel(Long labelId, Long noteId, String token);

//	List<LabelInformation> getLabel(Long userId);

	public List<Label> getLabel(String token);

	public List<Note> getAllNotes(String token, Long labelId);

	public void removeLabel(Long labelId, Long noteId, String token);

	public void createLabelAndMap(LabelDto label, String token, Long noteId);
}
