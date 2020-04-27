package nikola.milanovic.singidunum.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Professor;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;

@Repository
public class SubjectDAO implements CrudDAOInterface<Subject>, SubjectDAOInterface, ReadLimitDAOInterface<Subject> {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public SubjectDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Subject> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Subject> query = session.createQuery("FROM Subject", Subject.class);
		return query.getResultList();
	}

	@Override
	public Subject getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Subject.class, id);
	}

	@Override
	public void add(Subject s) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(s);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Subject s = session.get(Subject.class, id);
		session.remove(s);
	}

	@Override
	public Set<Subject> getSubjectsWithoutExam() {
		Session session = sessionFactory.getCurrentSession();
		Set<Subject> subjects = new HashSet<Subject>();
		List<Subject> listSubject = session.createQuery("FROM Subject", Subject.class).getResultList();
		List<Exam> exams = session.createQuery("FROM Exam", Exam.class).getResultList();
		LocalDate currentDate = LocalDate.now();
		for(Subject subject : listSubject) {
			if(!subject.getProfessors().isEmpty()) {
				subjects.add(subject);
			}
		}
		for(Exam exam : exams) {
			if(exam.getExamDate().isAfter(currentDate)) {
				subjects.remove(exam.getSubject());
			}
		}
		return subjects;
	}

	@Override
	public Set<Subject> getNotStudentSubjects(long id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		List<Subject> listSubject = session.createQuery("FROM Subject", Subject.class).getResultList();
		Set<Subject> subjects = new HashSet<Subject>();
		
		for(Subject subject : listSubject) {
			if(!subject.getProfessors().isEmpty()) {
				if(!student.getSubjects().contains(subject)) {
					subjects.add(subject);
				}
			}		
		}	
		return subjects;
	}

	@Override
	public Set<Subject> getNotProfessorSubjects(long id) {
		Session session = sessionFactory.getCurrentSession();
		Professor professor = session.get(Professor.class, id);
		List<Subject> listSubject = session.createQuery("FROM Subject", Subject.class).getResultList();
		Set<Subject> subjects = new HashSet<Subject>();
		
		for(Subject subject : listSubject) {
			if(!professor.getSubjects().contains(subject)) {
				subjects.add(subject);
			}
		}
		return subjects;
	}

	@Override
	public List<Subject> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Subject> query = session.createQuery("from Subject", Subject.class);
		query.setFirstResult((page - 1) * number);
		query.setMaxResults(number);
		return query.getResultList();
	}

}
