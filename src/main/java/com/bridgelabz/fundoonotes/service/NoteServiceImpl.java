package com.bridgelabz.fundoonotes.service;


import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NoteUpdation;
import com.bridgelabz.fundoonotes.dto.Notedto;
import com.bridgelabz.fundoonotes.dto.ReminderDto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exceptions.UserException;
import com.bridgelabz.fundoonotes.repositiory.NoteRepository;
import com.bridgelabz.fundoonotes.repositiory.UserRepo;
import com.bridgelabz.fundoonotes.utility.JwtProvider;
import com.bridgelabz.fundoonotes.utility.Util;

import lombok.extern.slf4j.Slf4j;


//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6ImV5SjBlWEFpT2lKS1YxUWlMQ0poYkdjaU9pSklVelV4
//TWlKOS5leUpsYldGcGJDSTZJbk52Ym1sdVlXNWplVFEwTmtCbmJXRnBiQzVqYjIwaWZRLlpJLWdmS043dXl6VmE2SEJiX19
//WR01hYkJGNi1wQVFZUkFFemR2OW5tcjFlYTFxY3BwUHlZUTY3WWZXLW9qN2l4Y2YwTFdWVGNTVmJQdzJqYXVqRVBBIn0.DJ1
//VgW3YssHp7V_URJkgAB2k3ugSZc_OmmEQO5QrJcJbCpNBHy3gWUZojgWbCxI5mAOd6t4WFCmuoUE4ICqJbA

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {
	

	@Autowired
	private JwtProvider jwtProvider;	
	@Autowired
	private NoteRepository noterepo;

	@Autowired
	private NoteService noteService;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override

	public Note createNote(Notedto notedto, String token) {
//		 System.out.println("token in note is"+token);
		 Long userid=jwtProvider.decodeToken(token);
//		 System.out.println("userid in note is"+userid);
//		Long userid=jwtProvider.decodeToken(token);
			 System.out.println("userid in service"+userid);
		
//		 Note note=Util.noteDtoToNote(notedto);
	User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
		System.out.println("user id"+ user);
//		if(user!=null)
//		{
			Note note=new Note();
			BeanUtils.copyProperties(notedto,note);
			
			user.getNote().add(note);
	
			Note notes=noterepo.saveNote(note);
//		}
		return note;
	
	}
	@Override
	public Note updateNote(NoteUpdation noteUpdation, String token) {
		
			 Long userid=jwtProvider.decodeToken(token);
			 User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
//			if (user != null) {
				
				Note note1 = noterepo.checkById(noteUpdation.getId());
				note1.setDescription(noteUpdation.getDescription());
				note1.setTitle(noteUpdation.getTitle());
				note1.setPinned(noteUpdation.isPinned());
				note1.setArchive(noteUpdation.isArchieved());
				note1.setInTrash(noteUpdation.isTrashed());
				note1.setUpDateAndTime(LocalDateTime.now());
				Note newNote=noterepo.saveNote(note1);
				
//				if(note!=null) {
//					//elasticService.UpdateNote(note1);
//				}
//			} else {
//				throw new UserException("note is not present");
//			}
			return newNote;

	}
	
	@Override
	public void deleteNote(long id, String token) {
		Note note = noterepo.checkById(id);
		// note.setTrashed(true);
		note.setInTrash(!note.isInTrash());
		noterepo.saveNote(note);
		
	}
	@Override
	public List<Note> getAllNotes(String token)
	{
		Long userid = jwtProvider.decodeToken(token);

	List<Note>list=noterepo.getAllNote(userid);
	log.info("Note List :"+list);
	if(list!=null)
	{
	return list;
	}

	return null;

	}

	@Override
	public List<Note> getTrashedNotes(String token) {
		try {
			Long userid =jwtProvider.decodeToken(token);
			Long id = null;
			User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));

			if (user != null) {
				System.out.println(user);
				List<Note> list = noterepo.getTrashNotebyUserId(userid);
				System.out.println("note fetched is" + " " + list.get(0));
				return list;

			} else {
				System.out.println(user + "hello");
				throw new UserException("note does not exist");
			}

		} catch (Exception e) {
			throw new UserException("error occured");
		}

	}
	
	@Override
	public boolean deleteNotePemenetly(long id, String token) {
		try {
			Long userid = jwtProvider.decodeToken(token);
			System.out.println("user id" + " " + userid);
			
			Note note = noterepo.checkById(id);
			if (note != null) {
//				List<Label> labels = note.getList();
//				
//				if(labels!=null) {
//				labels.clear();
//				}
				noterepo.deleteNote(id, userid);
//				elasticService.DeleteNote(note);
			} else {
				throw new UserException("note is not present");
			}

		}

		catch (Exception e) {
			throw new UserException("user is not present");
		}
		return false;

	}
	
	@Override
	public void archievNote(long id, String token) {
		try {
			Long userid =jwtProvider.decodeToken(token);
			User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));

			if (user != null) {
				System.out.println(user);
				List<Note> list = noterepo.getArchiveNotebyUserId(userid);
				System.out.println("note fetched is" + " " + list.get(0).toString());
				return;

			} else {
				System.out.println(user + "hello");
				throw new UserException("note doesn't exist");
			}

		} catch (Exception e) {
			throw new UserException("error occured");
		}

	}

		
	
	@Override
	public List<Note> getArchiveNote(Long id,String token) {
		try {
			Long userid = jwtProvider.decodeToken(token);
			User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));

			if (user != null) {
				System.out.println(user);
				List<Note> list = noterepo.getArchiveNotebyUserId(userid);
				System.out.println("note fetched is" + " " + list.get(0).toString());
				return list;

			} else {
				System.out.println(user + "hello");
				throw new UserException("note doesn't exist");
			}

		} catch (Exception e) {
			throw new UserException("error occured");
		}

	}
	@Override
	public void addColour(Long id, String token, String colour) {
			Long userid = jwtProvider.decodeToken(token);
			System.out.println("user id" + " " + userid);
			Note note = noterepo.getNotebyNoteId(id);
			if (note != null) {
				System.out.println(note.getColorCode());
				note.setColorCode(colour);
				noterepo.saveNote(note);
			}
			else {
				throw new UserException("note is not present");
			}
	}

	@Override
	public void addReminder(Long id, String token, ReminderDto reminder) {
		
			Long userid =jwtProvider.decodeToken(token);
			System.out.println("user id" + " " + userid);
			User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
			
			if (user != null) {
				Note note = noterepo.getNotebyNoteId(id);
				System.out.println(note.getRemainder());
			
				note.setRemainder(reminder.getReminder());
				
				noterepo.saveNote(note);
			} else {
				throw new UserException("note doesn't exist");
			}
      }

	@Override
	public void removeReminder(Long id, String token, ReminderDto reminder) {
	
		try {
			Long userid =jwtProvider.decodeToken(token);
			System.out.println("user id" + " " + userid);
			Note note = noterepo.getNotebyNoteId(id);
			if (note != null) {
				System.out.println(note.getRemainder());
				System.out.println(reminder);

				note.setRemainder(null);
				System.out.println(note.getColorCode());
				noterepo.saveNote(note);
			} else {
				throw new UserException("note doesn't exist");
			}

		} catch (Exception e) {
			throw new UserException("authentication failed");
		}

	}
	
	
	
	@Override
	
	public List<Note> getAllPinnedNotes(String token) {
		List<Note> allNotes;
		try {
			Long userid = jwtProvider.decodeToken(token);
			User user = userRepo.getUserById(userid).orElseThrow(() -> new UserException("user not found"));
			if (user != null) {
				System.out.println("user logged in"+user.getUserid());
				System.out.println("user ");
				// List<Note> list=user.getNote();
				List<Note> list11 = (List<Note>) noterepo.getNotebyUserId(user.getUserid());
			  if(list11!=null) {
			 allNotes=list11.stream().filter(note -> note.isPinned()==true).collect(Collectors.toList());
				  return allNotes;
			  }
			
			}
		} catch (Exception e) {
			throw new UserException("error occured");
		}
		return null;

	}
	@Override
	public void pin(long id, String token) {
		Note note = noterepo.getNotebyNoteId(id);
		
		note.setPinned(!note.isPinned());
		noterepo.saveNote(note);

	}
		
	

//	@Override
//	public List<Note> searchByTitle(String title) {
//		List<Note> notes=elasticService.searchbytitle(title);
//		if(notes!=null) {
//		return notes;
//		}
//		else {
//			return null;
//		}
//	}
//	
}
	

	

		
	

