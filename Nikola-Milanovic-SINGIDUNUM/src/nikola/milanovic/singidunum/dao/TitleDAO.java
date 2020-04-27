package nikola.milanovic.singidunum.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.Title;

@Repository
public class TitleDAO implements CrudDAOInterface<Title>,ReadLimitDAOInterface<Title> {
	private SessionFactory sessionFactory;
	
	@Autowired
	public TitleDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Title> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Title> query = session.createQuery("from Title", Title.class);
		return query.getResultList();
	}

	@Override
	public Title getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Title.class, id);
	}

	@Override
	public void add(Title title) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(title);
		
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Title t = (Title) session.get(Title.class, id);
		if(null != t) session.delete(t);
	}

	@Override
	public List<Title> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Title> query = session.createQuery("from Title", Title.class);
		query.setFirstResult((page - 1) * number);
		query.setMaxResults(number);
		return query.getResultList();
	}
	
}
