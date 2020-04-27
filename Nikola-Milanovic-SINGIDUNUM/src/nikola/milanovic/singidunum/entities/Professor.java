package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Set;

@Entity
@Table(name = "professor")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="professor_id", nullable=false)
	private long professorId;

	@Column(length=50)
	@Size(min = 3, max = 40, message = "Address must be between 3 and 50 characters")
	private String address;

	@Column(length=30, nullable = false, unique = true)
	@Email(message= "Not email format")
	private String email;

	@Column(name="first_name", length=30, nullable = false)
	@Size(min=3, max=30, message="Name must be beetween 3 and 30 characters")
	private String firstName;
	
	@Column(name="last_name", length=30, nullable = false)
	@Size(min=3, max=30, message="Lastname must be beetween 3 and 30 characters")
	private String lastName;

	@Column(length=15)
	@Size(min=6, max=15, message="Phone must be beetween 6 and 15 characters")
	private String phone;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate reelectiondate;

	@ManyToOne(fetch=FetchType.LAZY)
	private City city;

	@OneToMany(mappedBy="professor")
	private Set<Exam> exams;

	@ManyToMany
	@JoinTable(
		name="professor_subject"
		, joinColumns={
			@JoinColumn(name="professor_professor_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="subject_subject_id")
			}
		)
	private Set<Subject> subjects;

	
	@ManyToOne(fetch=FetchType.LAZY)
	private Title title;

	public Professor() {
	}

	public long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDate getReelectiondate() {
		return reelectiondate;
	}

	public void setReelectiondate(LocalDate reelectiondate) {
		this.reelectiondate = reelectiondate;
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

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
	
	public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
 
    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }
}