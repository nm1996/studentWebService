package nikola.milanovic.singidunum.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.City;
import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Mark;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;

@Repository
public class StudentDAO implements CrudDAOInterface<Student>, StudentRelationsDAOInterface<Subject,Exam>, ReadLimitDAOInterface<Student> {

	private SessionFactory sessionFactory;
	
	@Autowired
	public StudentDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Student> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Student> query = session.createQuery("from Student", Student.class);
		return query.getResultList();
	}

	@Override
	public Student getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		return student;
	}

	@Override
	public void add(Student s) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(s);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Student s = session.get(Student.class, id);
		if(null != s) session.remove(s);
	}

	@Override
	public void setSubject(long id, long id2) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		Subject subject = session.get(Subject.class, id2);
		student.addSubject(subject);
		if(subject.getStudents().isEmpty()) {
			Set<Student> students = new HashSet<Student>();
			students.add(student);
			subject.setStudents(students);
		}else {
			subject.addStudent(student);
		}
		session.saveOrUpdate(student);
		session.saveOrUpdate(subject);
	}

	@Override
	public void setExam(long id, long id2) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		Exam exam = session.get(Exam.class, id2);
		student.addExam(exam);
		if(exam.getStudents().isEmpty()) {
			Set<Student> students = new HashSet<Student>();
			students.add(student);
			exam.setStudents(students);
		}else {
			exam.addStudent(student);
		}
		session.saveOrUpdate(student);
		session.saveOrUpdate(exam);
	}

	@Override
	public Set<Subject> allSubjects(long id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		return student.getSubjects();
	}

	@Override
	public Set<Exam> allExams(long id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		return student.getExams();
	}

	@Override
	public List<Student> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Student> query = session.createQuery("from Student", Student.class);
		query.setFirstResult((page - 1) * number);
		query.setMaxResults(number);
		return query.getResultList();
	}

	@Override
	public Set<Student> usersForExam(long id) {
		Session session = sessionFactory.getCurrentSession();
		Exam exam = session.get(Exam.class, id);
		Set<Student> students = exam.getStudents();
		Set<Mark> marks = exam.getMarks();
		Iterator<Mark> iterator = marks.iterator();
		for(Mark mark: marks) {
			students.remove(mark.getStudent());
		}
		return students;
	}
}
