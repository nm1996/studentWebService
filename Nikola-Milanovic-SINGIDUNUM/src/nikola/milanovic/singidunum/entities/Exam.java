package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.Customizer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the EXAM database table.
 * 
 */
@Entity
@Table(name="exam")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exam_id", nullable=false)
	private long examId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="exam_date")
	private LocalDate examDate;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Subject subject;

	@OneToMany(mappedBy="exam", cascade= CascadeType.ALL)
	private Set<Mark> marks;

	
	@ManyToOne(fetch=FetchType.LAZY)
	private Professor professor;

	@ManyToMany(mappedBy="exams", cascade= CascadeType.ALL)
	private Set<Student> students;

	public Exam() {
	}

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Set<Mark> getMarks() {
		return marks;
	}

	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

}