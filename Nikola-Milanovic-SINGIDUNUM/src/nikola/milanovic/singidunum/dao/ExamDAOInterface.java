package nikola.milanovic.singidunum.dao;

import java.util.Set;

import nikola.milanovic.singidunum.entities.Exam;

public interface ExamDAOInterface {

	public Set<Exam> getExamForStudent(long id, int year);
	public Set<Exam> finishedExams();
}
