package nikola.milanovic.singidunum.services;

import java.util.Set;

public interface ProfessorRelationsServiceInterface<T,U> {
	public void setSubject(long id, long id2);
	public Set<T> allSubjects(long id);
	public Set<U> allExams(long id);
}
