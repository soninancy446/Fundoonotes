package com.bridgelabz.fundoonotes.utility;

import java.time.LocalDateTime;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.dto.Notedto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;

public class Util {

	public static Note noteDtoToNote(Notedto notedto) {
		if (notedto != null) {
			Note note = new Note();
//		note.setTitle(notedto.getTitle());
//		note.setDescription(notedto.getDescription());
			note.setPinned(false);
			note.setArchive(false);
			// note.setColabsUser(notedto.getColor());
			// note.setRemainder(notedto.getReminder());
			note.setInTrash(false);
			LocalDateTime date = LocalDateTime.now();
			note.setCreatedDateAndTime(date);
			note.setUpDateAndTime(date);
			note.setId(note.getId());
			return note;
		} else {
			return null;
		}
	}

	public static Label labelDtotoLabel(LabelDto labelDto) {
		if (labelDto != null) {
			Label label = new Label();
			label.setLabelName(labelDto.getLabelName());
			// label.setLabelid(labelDto.get);
			// label.getNoteid();

			return label;
		} else {
			return null;
		}
	}
}