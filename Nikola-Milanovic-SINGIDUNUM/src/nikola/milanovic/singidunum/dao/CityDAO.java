package nikola.milanovic.singidunum.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.City;

@Repository
public class CityDAO implements CrudDAOInterface<City>, ReadLimitDAOInterface<City> {

	private SessionFactory sessionFactory;

	@Autowired
	public CityDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<City> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<City> query = session.createQuery("from City", City.class);
		return query.getResultList();
	}

	@Override
	public City getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(City.class, id);
	}

	@Override
	public void add(City c) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(c);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		City c = (City) session.get(City.class, id);
		if (null != c)
			session.delete(c);
	}

	@Override
	public List<City> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<City> query = session.createQuery("from City", City.class);
		query.setFirstResult((page - 1) * number);
		query.setMaxResults(number);
		return query.getResultList();
	}
}
