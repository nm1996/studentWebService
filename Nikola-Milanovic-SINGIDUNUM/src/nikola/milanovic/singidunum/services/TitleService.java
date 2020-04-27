package nikola.milanovic.singidunum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.TitleDAO;
import nikola.milanovic.singidunum.entities.Title;

@Service("titleService")
public class TitleService implements CrudServiceInterface<Title>,ReadLimitServiceInterface<Title> {
	
	
	private TitleDAO titleDao;
	
	@Autowired
	public void setTitleDao(TitleDAO titleDao) {
		this.titleDao = titleDao;
	}
	
	@Transactional
	@Override
	public List<Title> getAll() {
		return titleDao.getAll();
	}

	@Transactional
	@Override
	public Title getOneById(long id) {
		return titleDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(Title t) {
		this.titleDao.add(t);
		
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.titleDao.remove(id);
		
	}

	@Transactional
	@Override
	public List<Title> getPaginated(int page, int number) {
		return titleDao.getPaginated(page, number);
	}

}
