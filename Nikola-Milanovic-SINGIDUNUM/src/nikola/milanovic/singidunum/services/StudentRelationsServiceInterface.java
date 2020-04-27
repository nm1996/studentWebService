package nikola.milanovic.singidunum.services;

import java.util.Set;

import nikola.milanovic.singidunum.entities.Student;

public interface StudentRelationsServiceInterface <T,U>{
	public void setSubject(long id, long id2);
	public void setExam(long id, long id2);
	public Set<T> allSubjects(long id);
	public Set<U> allExams(long id);
	public Set<Student> usersForExam(long id);
}
