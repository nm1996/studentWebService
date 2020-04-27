package nikola.milanovic.singidunum.dao;

import java.util.List;

public interface ReadLimitDAOInterface<T> {
	public List<T> getPaginated(int page, int number);
}
