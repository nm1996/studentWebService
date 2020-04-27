package nikola.milanovic.singidunum.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Mark;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;

@Repository
public class MarkDAO implements CrudDAOInterface<Mark>, ReadLimitDAOInterface<Mark> {

	private SessionFactory sessionFactory;
	
	@Autowired
	public MarkDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Mark> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Mark> query = session.createQuery("from Mark", Mark.class);
		return query.getResultList();
	}

	@Override
	public Mark getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Mark mark = session.get(Mark.class, id);
		return mark;
	}

	@Override
	public void add(Mark m) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(m);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Mark m =  session.get(Mark.class, id);
		if(null != m) session.remove(m);
	}

	@Override
	public List<Mark> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Mark> query = session.createQuery("from Mark", Mark.class);
		query.setFirstResult((page - 1) * number);
		query.setMaxResults(number);
		return query.getResultList();
	}

}
