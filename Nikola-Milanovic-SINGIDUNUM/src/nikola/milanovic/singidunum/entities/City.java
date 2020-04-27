package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.Set;


/**
 * The persistent class for the CITY database table.
 * 
 */
@Entity
@Table(name="city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="city_id", nullable=false)
	private long cityId;

	@Column(nullable=false, unique=true, length=40)
	@Size(min=2, max=20, message="Name must be between 2 and 20")
	private String name;

	@OneToMany(mappedBy="city", cascade=CascadeType.ALL)
	private Set<Professor> professors;

	@OneToMany(mappedBy="city", cascade=CascadeType.ALL)
	private Set<Student> students;

	public City() {
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setCity(this);
		
		return student;
	}
	
	public Student removeStudent(Student student) {
        getStudents().remove(student);
        student.setCity(null);
        
        return student;
    }

	public Professor addProfessor(Professor professor) {
		getProfessors().add(professor);
		professor.setCity(this);
		
		return professor;
	}
	
	public Professor removeProfessor(Professor professor) {
		getProfessors().remove(professor);
        professor.setCity(null);
        
        return professor;
    }

}