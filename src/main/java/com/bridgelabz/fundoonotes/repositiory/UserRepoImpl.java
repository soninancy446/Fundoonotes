package com.bridgelabz.fundoonotes.repositiory;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.entity.User;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {

	private Logger logger = Logger.getLogger(this.getClass());

	private EntityManager entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public User save(User user) {

		Session session = entityManager.unwrap(Session.class);
		session.save(user);
		return user;

	}

	@Override
	@Transactional
	public User checkByEmail(String email) {

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from User where email='" + email + "'", User.class);
		List<User> users = query.getResultList();
		// System.out.println(email);
		System.out.println(users);

		if (users.size() > 0) {
			return users.get(0);
		} else {

			return null;
		}
	}

	@Transactional
	@Override
	public User checkById(Long userid) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from User where userid=" + userid, User.class);
		List<User> userList = query.getResultList();
		System.out.println(userList);
		System.out.println(userList.get(0));
		return userList.get(0);
	}

	@Transactional
	@Override
	public boolean verify(String email) {
		boolean status = true;
		List<User> result = new ArrayList<>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession
				.createQuery("update User set isverified=" + status + " where email='" + email + "'");
		int i = query.executeUpdate();
		if (i > 0)
			return true;
		else
			return false;
	}

	@Transactional
	@Override
	public List<User> getUsers() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("update User u set u.password=:password where u.userid=:userid");
		List<User> userList = query.getResultList();
		return userList;
	}

	@Transactional
	@Override
	public Optional<User> getUserById(Long userid) {
		System.out.println("hello repo");
		Session currentsession = entityManager.unwrap(Session.class);
		Query query = currentsession.createQuery("from User where  userid=:userid");
		query.setParameter("userid", userid);
		User user =  (User) query.uniqueResult();
		Optional<User> opt = Optional.ofNullable(user);

		return opt;

	}

	@Transactional
	@Override
	public void resetPassword(String email, String password) {

		boolean status = true;
		List<User> result = new ArrayList<>();
		Session currentSession = entityManager.unwrap(Session.class);
		try {

			currentSession.createQuery("update User set password ='" + password + "' where email='" + email + "'")
					.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Transactional
	@Override
	public int getByEmail(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("from User where  email=:email");
		
		int s = query.executeUpdate();
		if (s > 0)
			return 1;
		else
			return 0;


	}

	@Transactional
	@Override
	public boolean deleteByEmail(String email) {
		Session currentsession = entityManager.unwrap(Session.class);
		// Query query = currentSession.get(Query.class, email);
		Query query = currentsession.createQuery("delete From User where email='" + email + "'");
		query.executeUpdate();
		return false;

	}

//	@Transactional
//	@Override
//	public boolean updateByEmail(String email) {
//		Session currentSession=entityManager.unwrap(Session.class);
//		Query query=currentSession.createQuery("update set email='"+email+"'");
//		currentSession.update(query);
//		
//		
//		return false;
//		
//	}
	@Transactional
	@Override
	public int deleteUserById(Long userid) {
		Session currentsession = entityManager.unwrap(Session.class);
		Query query = currentsession.createQuery("delete From User where id=" + userid);
		int s = query.executeUpdate();
		if (s > 0)
			return 1;
		else
			return 0;
	}

	@Transactional
	@Override
	public boolean isValidUser(Long userid) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<User> result = currentSession.createQuery("from User where id='" + userid + "'").getResultList();
		return (result.size() > 0) ? true : false;

	}

	@Override
	public User getUser(String Email) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery(" FROM User where email=:email");
		q.setParameter("email", Email);
		return (User) q.uniqueResult();
	}

	@Override
	public boolean upDate(RegistrationDto registrationDto, Long userid) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("update User set password=:p" + " " + " " + "where userid=:i");
		q.setParameter("p", registrationDto.getPassword());
		q.setParameter("i", userid);

		int status = q.executeUpdate();
		if (status > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean verify(Long id) {
		boolean status = true;
		List<User> result = new ArrayList<>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession
				.createQuery("update User set isverified=" + status + " where id='" + id + "'");
		int i = query.executeUpdate();
		if (i > 0)
			return true;
		else
			return false;
	}
}
