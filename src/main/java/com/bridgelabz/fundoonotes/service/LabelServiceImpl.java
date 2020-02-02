package com.bridgelabz.fundoonotes.service;

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exceptions.UserException;
import com.bridgelabz.fundoonotes.repositiory.LabelRepo;
import com.bridgelabz.fundoonotes.repositiory.NoteRepository;
import com.bridgelabz.fundoonotes.repositiory.UserRepo;
import com.bridgelabz.fundoonotes.utility.JwtProvider;
import com.bridgelabz.fundoonotes.utility.Util;

@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private NoteRepository noteRepo;
	@Autowired
	private Note note;
	@Autowired
	private LabelRepo labelRepo;
	@Autowired
	private Label label;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public Label createLabel(LabelDto labelDto, String token) {

		Label label = Util.labelDtotoLabel(labelDto);
		Long userid = jwtProvider.decodeToken(token);

		User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));

		System.out.println("user id" + userid);
		if (user != null) {
			label.setUserid(user.getUserid());

			label.setLabelName(labelDto.getLabelName());
			Label labels = labelRepo.saveLabel(label);

		}
		return label;
	}

	@Override
	public void updateLabel(LabelDto labelDto, String token) {
		Label label = Util.labelDtotoLabel(labelDto);
		Long userid = jwtProvider.decodeToken(token);
		User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
		if (user != null) {
			 label = (Label) labelRepo.getLabelById(label.getLabelid());
			if (label != null) {
				label.setLabelName(labelDto.getLabelName());
				labelRepo.saveLabel(label);
			} else {
				throw new UserException("label with the given id does not exist");
			}

		} else {
			throw new UserException("user does not exist");
		}

	}

	@Override
	public void deleteLabel(LabelDto labelDto, String token) {
		Long userid = jwtProvider.decodeToken(token);
		User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
		if (user != null) {
			Label label = null;
			Label label1 = (Label) labelRepo.getLabelById(label.getLabelid());
			if (label1 != null) {
				labelRepo.deleteLabel(label.getLabelid());
			} else {
				throw new UserException("Note does not exist");
			}
		}
	}

	@Override
	public void addLabel(Long labelId, Long id, String token) {
		Note note = noteRepo.getNotebyNoteId(id);
		Label label1 = labelRepo.GetLabel(labelId);
		label1.getList().add(note);
		labelRepo.saveLabel(label1);
	}

	@Override
	public void removeLabel(Long labelId, Long id, String token) {
		Note note = noteRepo.getNotebyNoteId(id);
//		Label label1 = (Label) labelRepo.getLabelById();
//			note.getList().remove(label1);
		note.getId();

		noteRepo.saveNote(note);
	}

	@Override
	public List<Label> getLabel(String token) {

		Long userid = Long.parseLong(token);
		List<Label> labels = labelRepo.getAllLabel(userid);
		return labels;
	}

	@Override
	public List<Note> getAllNotes(String token, Long labelId) {
//		Label label1 = (Label) labelRepo.getLabelById(label.getLabelid());
//		List<Note> list = label.getList();
//		System.out.println("label1 is" + list);
//
		return null;
	}

	@Override
	public void createLabelAndMap(LabelDto labelDto, String token, Long id) {
		Long userid;
		try {
		userid = Long.parseLong(jwtProvider.parseToken(token));
		} catch (Exception e) {

			throw new UserException("user is not present ");
		}
		User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
		if (user != null) {
			Label label2 = labelRepo.getLabel(user.getUserid(), labelDto.getLabelName());

			if (label2 == null) {
				BeanUtils.copyProperties(labelDto, Label.class);
////					label.getLabelid();
////					label.getLabelName();
				label.setUserid(user.getUserid());
             
				Label label1 = labelRepo.saveLabel(label);
				Note note = noteRepo.getNotebyNoteId(id);
                note.getList().add(label);
                noteRepo.saveNote(note);

		} else {
			throw new UserException("Note does not exist with the given userid");
		}

	}

	}
}
