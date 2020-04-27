package nikola.milanovic.singidunum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nikola.milanovic.singidunum.dao.CityDAO;
import nikola.milanovic.singidunum.entities.City;

@Service("cityService")
public class CityService implements CrudServiceInterface<City>, ReadLimitServiceInterface<City> {
	
	private CityDAO cityDao;
	
	@Autowired
	public void setCityDao(CityDAO cityDao) {
		this.cityDao = cityDao;
	}

	@Transactional
	@Override
	public List<City> getAll() {
		return cityDao.getAll();
	}

	@Transactional
	@Override
	public City getOneById(long id) {
		return cityDao.getOneById(id);
	}

	@Transactional
	@Override
	public void create(City c) {
		this.cityDao.add(c);
		
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.cityDao.remove(id);
	}

	@Transactional
	@Override
	public List<City> getPaginated(int page, int number) {
		return cityDao.getPaginated(page, number);
	}
	
}
