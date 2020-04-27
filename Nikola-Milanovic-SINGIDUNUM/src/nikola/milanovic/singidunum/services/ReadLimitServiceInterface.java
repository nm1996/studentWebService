package nikola.milanovic.singidunum.services;

import java.util.List;

public interface ReadLimitServiceInterface<T> {
	public List<T> getPaginated(int page, int number);
}
