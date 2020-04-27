package nikola.milanovic.singidunum.services;

import java.util.List;

public interface CrudServiceInterface<T> {
	
	public List<T> getAll();
	public T getOneById(long id);
	public void create(T t);
	public void delete(long id);

}
