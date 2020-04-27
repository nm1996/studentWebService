package nikola.milanovic.singidunum.dao;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Mark;
import nikola.milanovic.singidunum.entities.Professor;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;

@Repository
public class ExamDAO implements CrudDAOInterface<Exam>, ExamDAOInterface, ReadLimitDAOInterface<Exam> {

	private SessionFactory sessionFactory;

	public ExamDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Exam> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Exam> query = session.createQuery("from Exam", Exam.class);
		return query.getResultList();
	}

	@Override
	public Exam getOneById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Exam exam = session.get(Exam.class, id);
		return exam;
	}

	@Override
	public void add(Exam e) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(e);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Exam e = session.get(Exam.class, id);
		if (null != e)
			session.remove(e);
	}

	@Override
	public Set<Exam> getExamForStudent(long id, int year) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		Set<Exam> exams = new HashSet<Exam>();
		Set<Mark> marks = student.getMarks();
		Set<Subject> subjects = student.getSubjects();
		for (Subject subject : subjects) {
			Set<Exam> subjectExams = subject.getExams();
			for (Exam e : subjectExams) {
				if (e.getExamDate().isAfter(LocalDate.now())) {
					if (Period.between(LocalDate.now(), e.getExamDate()).getDays() <= 7) {
						if (!student.getExams().contains(e)) {
							exams.add(e);
						}
						if (student.getCurrentYearOfStudy() < e.getSubject().getYearOfStudy()) {
							exams.remove(e);
						}
					}
					for (Mark m : marks) {
						if (m.getSubject().equals(subject)) {
							if (m.getMark() > 5) {
								exams.remove(e);
							}
						}
					}
				}
			}
		}
		return exams;
	}

	@Override
	public List<Exam> getPaginated(int page, int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Exam> query = session.createQuery("from Exam", Exam.class);
		query.setFirstResult((page-1)*number);
		query.setMaxResults(number);
		return query.getResultList();
	}

	@Override
	public Set<Exam> finishedExams() {
		Session session = sessionFactory.getCurrentSession();
		Set<Exam> exams = new HashSet<Exam>();
		List<Exam> allExams = session.createQuery("from Exam", Exam.class).getResultList();
		for (Exam exam : allExams) {
			if (exam.getExamDate().isBefore(LocalDate.now())) {
				exams.add(exam);
			}
			Set<Student> students = exam.getStudents();
			Set<Mark> marks = exam.getMarks();
			if(students.size() == marks.size()) {
				exams.remove(exam);
			}
		}
		return exams;
	}

}
