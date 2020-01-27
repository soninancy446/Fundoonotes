package com.bridgelabz.fundoonotes.repositiory;

import java.util.List;
import org.hibernate.query.Query;



import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;


@Repository
public class NoteRepositoryImpl implements NoteRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public Note saveNote(Note note) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(note);
		return note;
	}
	
	@Transactional
	@Override
	public boolean deleteNote(Long id, Long userid) {
		Session session = entityManager.unwrap(Session.class);
		String str = "DELETE FROM Note " + "WHERE id = :id";
		Query query = session.createQuery(str);
		query.setParameter("id", id);
//		query.setParameter("userid", userid);
		int result = query.executeUpdate();
		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Note checkById(Long id) {
//		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from Note where id=:id ");
		q.setParameter("id", id);
		return (Note) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Note getNotebyUserId(Long userid) {
//		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery("from Note where userid='" + userid + "'");
		q.setParameter("userid", userid);
		return (Note) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Note> getArchiveNotebyUserId(Long userid) {

//		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);

		return session.createQuery("from Note where userid='" + userid + "'").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Note> getTrashNotebyUserId(Long userid) {
//		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);

		return session.createQuery("from Note where userid='" + userid + "'")
				.getResultList();
	}
	@Transactional
	@Override
	public boolean updateColour(long id, long userid, String colour) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Note u set u.colour = :colour" + " where u.id = :id");

		query.setParameter("colour", "red");
		query.setParameter("noteid", id);
		int result = query.executeUpdate();

		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}
public List<Note> getNotes(long userid) {
		
		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Note where userid='" + userid + "'").getResultList();
	}
	

	@Transactional
	@Override
	public Note getNotebyNoteId(Long id){
		Session session = entityManager.unwrap(Session.class);	
		  Query query = session.createQuery("from Note where id ="+id);
		    Note note=(Note) query.uniqueResult();
			return note;
		    
		
	}
	
	public List<Note> getNotes(Long userid) {

		Session session=entityManager.unwrap(Session.class);
		 Query <Note>query = session.createQuery("from Note where user_id='" + userid + "'" );
		 List<Note>noteList=query.getResultList();
		 System.out.println(noteList);

		return noteList ;
		}


	@Override
	@Transactional
	public void saveColab(Note colab) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(colab);
		System.out.println("note saved");
	
}

	@Override
	public List<Note> getAllNote(Long userid) {
		Session session=entityManager.unwrap(Session.class);
		 Query <Note>query = session.createQuery("from Note where userid='" + userid + "'" );
		 List<Note>noteList=query.getResultList();
		 System.out.println(noteList);

		return noteList ;
		}
	}




