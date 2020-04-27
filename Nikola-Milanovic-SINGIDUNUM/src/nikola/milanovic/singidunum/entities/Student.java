package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name="student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id", nullable = false)
	private long studentId;
	
	@Column(name="index_number", unique=true, nullable = false, length=20)
	@Size(min = 10, message= "Index must contain 10 chars.")
	private String indexNumber;

	@Column(length=50)
	@Size(min = 3, max = 40, message = "Address must be between 3 and 50 characters")
	private String address;

	@Column(name="current_year_of_study", precision=7)
	@NotNull
	private int currentYearOfStudy;

	@Column(length=30, nullable=false, unique = true)
	@Email(message= "Not email format")
	private String email;

	@Column(nullable=false, length=30)
	private String finance;

	@Column(name="first_name", nullable=false, length=30)
	@Size(min=3, max=30, message="Name must be beetween 3 and 30 characters")
	private String firstName;

	@Column(name="last_name", nullable=false, length=30)
	@Size(min=3, max=30, message="Lastname must be beetween 3 and 30 characters")
	private String lastName;

	@Column(length=15)
	@Size(min=6, max=15, message="Phone must be beetween 6 and 15 characters")
	private String phone;

	@OneToMany(mappedBy="student", cascade= CascadeType.ALL)
	private Set<Mark> marks;

	@ManyToOne(fetch=FetchType.LAZY)
	private City city;

	@ManyToMany
	@JoinTable(
		name="student_exam"
		, joinColumns={
			@JoinColumn(name="student_student_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="exam_exam_id")
			}
		)
	private Set<Exam> exams;

	@ManyToMany
	@JoinTable(
		name="student_subject"
		, joinColumns={
			@JoinColumn(name="student_student_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="subject_subject_id")
			}
		)
	private Set<Subject> subjects;

	public Student() {
	}
		
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFinance() {
		return finance;
	}

	public void setFinance(String finance) {
		this.finance = finance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Mark> getMarks() {
		return marks;
	}

	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
 
    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }
    
    public void addExam(Exam exams) {
        this.exams.add(exams);
    }
 
    public void removeExam(Exam exams) {
        this.exams.remove(exams);
    }
	
	public Mark addMark(Mark mark) {
        getMarks().add(mark);
        mark.setStudent(this);

        return mark;
    }

    public Mark removeMark(Mark mark) {
    	getMarks().remove(mark);
        mark.setStudent(null);

        return mark;
    }

}