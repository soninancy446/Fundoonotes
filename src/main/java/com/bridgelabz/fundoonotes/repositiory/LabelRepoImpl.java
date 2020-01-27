package com.bridgelabz.fundoonotes.repositiory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;

@Repository
public class LabelRepoImpl implements LabelRepo {
	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public Note saveNote(Note note) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(note);
		return note;

	}

	@Transactional
	@Override
	public Label findLabel(Long userid, String labelname) {

		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from Label where userid=:id and name=:name");
		q.setParameter("id", userid);
		q.setParameter("name", labelname);
		return (Label) q.uniqueResult();

	}

	@Transactional
	@Override
	public Label saveLabel(Label label) {
//	try {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(label);
//		return 1;
//	}catch (Exception e) {
//		
//		e.printStackTrace();
//	}
//	return 0;
		return label;

	}

	@Transactional
	@Override
	public Label getLabel(Long userid, String labelname) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from Label where userid=:userid and labelname=:labelname");
//	q.setParameter("id", userid);
//	q.setParameter("name", labelname);
		return (Label) q.uniqueResult();

	}

	@Transactional
	@Override
	public void deleteLabel(Long labelId) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.createQuery("delete from Label where id='" + labelId + "'").executeUpdate();
		System.out.println("deleted");

	}

	@Transactional
	@Override
	public void updateLabel(Long labelId) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.createQuery("update Label set name ='Hiiiiii' where id'" + labelId + "'").executeUpdate();
		System.out.println("updated");

	}

	@Transactional
	@Override
	public List<Label> getLabelById(Long userid) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<Label> noteDetails = currentSession.createQuery("from Label where userid='" + userid + "'")
				.getResultList();
		return noteDetails;

	}

	@Transactional
	@Override
	public List<Note> getNoteByLabelId(String labelName) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<Label> noteDetails = currentSession.createQuery("from Label where labelName='" + labelName + "'")
				.getResultList();
		int size = noteDetails.size();
//List<Note> notes=noteDetails.get(0).getNotes();
		List<Note> notes = noteDetails.get(0).getList();
		return notes;
//return null;

	}

	@Transactional
	@Override
	public int removeLabel(Long labelId, Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		int status = currentSession
				.createQuery("delete from label_note where labelid='" + labelId + "'and id='" + id + "'")
				.executeUpdate();
		return status;

	}

	public List<Label> getAllLabel(Long userid) {

		Session session = entityManager.unwrap(Session.class);

		return session.createQuery("from Label where userid='" + userid + "'").getResultList();

	}

	@Override
	public Label GetLabel(Long labelId) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from Label where labelid='" + labelId + "'");
		return (Label) q.uniqueResult();

	}

}
