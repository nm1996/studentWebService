package nikola.milanovic.singidunum.dao;

import java.util.List;

public interface CrudDAOInterface<T> {
	public List<T> getAll();
	public T getOneById(long id);
	public void add(T t);
	public void remove(long id);
}
