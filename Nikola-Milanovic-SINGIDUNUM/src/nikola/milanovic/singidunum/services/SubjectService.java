package nikola.milanovic.singidunum.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.SubjectDAO;
import nikola.milanovic.singidunum.entities.Subject;

@Service("subjectService")
public class SubjectService implements CrudServiceInterface<Subject>, SubjectServiceInterface,ReadLimitServiceInterface<Subject> {

	private SubjectDAO subjectDao;
	
	@Autowired
	public void setSubjectDao(SubjectDAO subjectDao) {
		this.subjectDao = subjectDao;
	}
	
	@Transactional
	@Override
	public List<Subject> getAll() {
		return subjectDao.getAll();
	}

	@Transactional
	@Override
	public Subject getOneById(long id) {
		return subjectDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(Subject s) {
		this.subjectDao.add(s);
		
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.subjectDao.remove(id);
	}

	@Transactional
	@Override
	public Set<Subject> getSubjectsWithoutExam() {
		return subjectDao.getSubjectsWithoutExam();
	}

	@Transactional
	@Override
	public Set<Subject> getNotStudentSubjects(long id) {
		return subjectDao.getNotStudentSubjects(id);
	}

	@Transactional
	@Override
	public Set<Subject> getNotProfessorSubjects(long id) {
		return subjectDao.getNotProfessorSubjects(id);
	}

	@Transactional
	@Override
	public List<Subject> getPaginated(int page, int number) {
		return subjectDao.getPaginated(page, number);
	}
	
	
}
