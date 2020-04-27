package nikola.milanovic.singidunum.dao;

import java.util.Set;

public interface ProfessorRelationsDAOInterface<T,U> {
	public void setSubject(long id, long id2);
	public Set<T> allSubjects(long id);
	public Set<U> allExams(long id);
}
