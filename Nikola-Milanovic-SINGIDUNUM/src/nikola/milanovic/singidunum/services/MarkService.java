package nikola.milanovic.singidunum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.MarkDAO;
import nikola.milanovic.singidunum.entities.Mark;

@Service("markService")
public class MarkService implements CrudServiceInterface<Mark>,ReadLimitServiceInterface<Mark> {

	private MarkDAO markDao;
	
	@Autowired
	public void setMarkDao(MarkDAO markDao) {
		this.markDao = markDao;
	}
	
	@Transactional
	@Override
	public List<Mark> getAll() {
		return markDao.getAll();
	}

	@Transactional
	@Override
	public Mark getOneById(long id) {
		return markDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(Mark m) {
		this.markDao.add(m);
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.markDao.remove(id);
	}

	@Transactional
	@Override
	public List<Mark> getPaginated(int page, int number) {
		return markDao.getPaginated(page, number);
	}
	
}
