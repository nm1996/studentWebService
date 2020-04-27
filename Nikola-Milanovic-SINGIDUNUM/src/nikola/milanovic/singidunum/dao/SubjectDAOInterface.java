package nikola.milanovic.singidunum.dao;

import java.util.Set;

import nikola.milanovic.singidunum.entities.Subject;

public interface SubjectDAOInterface {
	public Set<Subject> getSubjectsWithoutExam();
	public Set<Subject> getNotStudentSubjects(long id);
	public Set<Subject> getNotProfessorSubjects(long id);
}
