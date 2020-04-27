package nikola.milanovic.singidunum.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.City;
import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Professor;
import nikola.milanovic.singidunum.entities.Subject;
import nikola.milanovic.singidunum.entities.Title;

@Repository
public class ProfessorDAO implements CrudDAOInterface<Professor>, ProfessorRelationsDAOInterface<Subject,Exam>, ReadLimitDAOInterface<Professor> {

	private SessionFactory sessionFactory;
	
	@Autowired
	public ProfessorDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Professor> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Professor> query = session.createQuery("from Professor", Professor.class);
		return query.getResultList();
	}

	@Override
	public Professor getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Professor professor = session.get(Professor.class, id);		
		return professor;
	}

	@Override
	public void add(Professor p) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Professor p = session.get(Professor.class, id);
		if(null != p) session.delete(p);
	}

	@Override
	public void setSubject(long id, long id2) {
		Session session = sessionFactory.getCurrentSession();
		Professor professor = session.get(Professor.class, id);
		Subject subject = session.get(Subject.class, id2);
		professor.addSubject(subject);
		if(subject.getProfessors().isEmpty()) {
			Set<Professor> professors = new HashSet<Professor>();
			professors.add(professor);
			subject.setProfessors(professors);
		}else {
			subject.addProfessor(professor);
		}
		session.saveOrUpdate(professor);
		session.saveOrUpdate(subject);
	}


	@Override
	public Set<Subject> allSubjects(long id) {
		Session session = sessionFactory.getCurrentSession();
		Professor professor = session.get(Professor.class, id);
		return professor.getSubjects();
	}

	@Override
	public Set<Exam> allExams(long id) {
		Session session = sessionFactory.getCurrentSession();
		Professor professor = session.get(Professor.class, id);
		return professor.getExams();
	}

	@Override
	public List<Professor> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Professor> query = session.createQuery("FROM Professor", Professor.class);
		query.setFirstResult((page - 1) * number);
		query.setMaxResults(number);
		return query.getResultList();
	}

}
