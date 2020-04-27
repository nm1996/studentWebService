package nikola.milanovic.singidunum.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.ProfessorDAO;
import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Professor;
import nikola.milanovic.singidunum.entities.Subject;

@Service("professorService")
public class ProfessorService implements CrudServiceInterface<Professor>, ProfessorRelationsServiceInterface<Subject,Exam>, ReadLimitServiceInterface<Professor> {

	private ProfessorDAO professorDao;
	
	@Autowired
	public void SetProfessorDao(ProfessorDAO professorDao) {
		this.professorDao = professorDao;
	}
	
	@Transactional
	@Override
	public List<Professor> getAll() {
		return professorDao.getAll();
	}

	@Transactional
	@Override
	public Professor getOneById(long id) {
		return professorDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(Professor p) {
		this.professorDao.add(p);
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.professorDao.remove(id);
	}

	@Transactional
	@Override
	public void setSubject(long id, long id2) {
		this.professorDao.setSubject(id, id2);
		
	}

	@Transactional
	@Override
	public Set<Subject> allSubjects(long id) {
		return professorDao.allSubjects(id);
	}

	@Transactional
	@Override
	public Set<Exam> allExams(long id) {
		return professorDao.allExams(id);
	}

	@Transactional
	@Override
	public List<Professor> getPaginated(int page, int num) {
		return professorDao.getPaginated(page, num);
	}

}
