package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="subject")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subject_id", unique=true, nullable = false)
	private long subjectId;

	@Column(length=200)
	private String description;

	@Column(nullable=false, length=200)
	@Size(min=3, max=200, message="Name must contain at least 3 characters.")
	private String name;

	@Column(length=10)
	private String semestar;

	@Column(name="year_of_study", length=1)
	private int yearOfStudy;

	@OneToMany(mappedBy="subject")
	private Set<Exam> exams;

	@OneToMany(mappedBy="subject")
	private Set<Mark> marks;
	
	@ManyToMany(mappedBy="subjects", cascade= CascadeType.ALL)
	private Set<Professor> professors;

	@ManyToMany(mappedBy="subjects", cascade= CascadeType.ALL)
	private Set<Student> students;

	public Subject() {
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSemestar() {
		return semestar;
	}

	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public Set<Mark> getMarks() {
		return marks;
	}

	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	public Set<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
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
    
    public void addProfessor(Professor professor) {
        this.professors.add(professor);
    }

    public void removeProfessor(Professor professor) {
        this.professors.remove(professor);
    }
	
}