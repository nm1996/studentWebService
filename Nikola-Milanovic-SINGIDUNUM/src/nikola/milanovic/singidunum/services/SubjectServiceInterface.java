package nikola.milanovic.singidunum.services;

import java.util.Set;

import nikola.milanovic.singidunum.entities.Subject;

public interface SubjectServiceInterface {
	public Set<Subject> getSubjectsWithoutExam();
	public Set<Subject> getNotStudentSubjects(long id);
	public Set<Subject> getNotProfessorSubjects(long id);
}
