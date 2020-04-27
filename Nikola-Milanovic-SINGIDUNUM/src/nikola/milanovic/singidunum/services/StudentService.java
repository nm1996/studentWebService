package nikola.milanovic.singidunum.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.StudentDAO;
import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;

@Service("studentService")
public class StudentService implements CrudServiceInterface<Student>, StudentRelationsServiceInterface<Subject,Exam>, ReadLimitServiceInterface<Student>{
	
	private StudentDAO studentDao;
	
	@Autowired
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	@Transactional
	@Override
	public List<Student> getAll() {
		return studentDao.getAll();
	}

	@Transactional
	@Override
	public Student getOneById(long id) {
		return studentDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(Student s) {
		this.studentDao.add(s);
		
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.studentDao.remove(id);
	}

	@Transactional
	@Override
	public void setSubject(long id, long id2) {
		this.studentDao.setSubject(id, id2);
	}

	@Transactional
	@Override
	public void setExam(long id, long id2) {
		this.studentDao.setExam(id, id2);
		
	}

	@Transactional
	@Override
	public Set<Subject> allSubjects(long id) {
		return studentDao.allSubjects(id);
	}

	@Transactional
	@Override
	public Set<Exam> allExams(long id) {
		return studentDao.allExams(id);
	}

	@Transactional
	@Override
	public List<Student> getPaginated(int page, int num) {
		return studentDao.getPaginated(page, num);
	}

	@Transactional
	@Override
	public Set<Student> usersForExam(long id) {
		return studentDao.usersForExam(id);
	}
	
	
}
