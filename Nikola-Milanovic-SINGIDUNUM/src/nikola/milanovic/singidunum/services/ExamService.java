package nikola.milanovic.singidunum.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.ExamDAO;
import nikola.milanovic.singidunum.entities.Exam;

@Service("examService")
public class ExamService implements CrudServiceInterface<Exam>, ExamServiceInterface, ReadLimitServiceInterface<Exam> {

	private ExamDAO examDao;
	
	@Autowired
	public void setExamDao(ExamDAO examDao) {
		this.examDao = examDao;
	}
	
	@Transactional
	@Override
	public List<Exam> getAll() {
		return examDao.getAll();
	}

	@Transactional
	@Override
	public Exam getOneById(long id) {
		return examDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(Exam e) {
		this.examDao.add(e);
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.examDao.remove(id);
	}

	@Transactional
	@Override
	public Set<Exam> getExamForStudent(long id, int year) {
		return examDao.getExamForStudent(id, year);
	}

	@Transactional
	@Override
	public List<Exam> getPaginated(int page, int num) {
		return examDao.getPaginated(page, num);
	}

	@Transactional
	@Override
	public Set<Exam> finishedExams() {
		return examDao.finishedExams();
	}

}
