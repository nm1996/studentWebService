package nikola.milanovic.singidunum.services;

import java.util.Set;

import nikola.milanovic.singidunum.entities.Exam;

public interface ExamServiceInterface {
	public Set<Exam> getExamForStudent(long id, int year);
	public Set<Exam> finishedExams();
}
