package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name="title")
public class Title implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="title_id", unique=true, nullable = false)
	private long titleId;

	@Column(length=30)
	@Size(min=2, max=30, message="Name must be beetween 2 and 30 characters")
	private String name;

	@OneToMany(mappedBy="title", cascade= CascadeType.ALL)
	private Set<Professor> professors;

	public Title() {
	}

	public long getTitleId() {
		return titleId;
	}

	public void setTitleId(long titleId) {
		this.titleId = titleId;
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
	
	public Professor addProfessor(Professor professor) {
        getProfessors().add(professor);
        professor.setTitle(this);

        return professor;
    }

    public Professor removeProfessor(Professor professor) {
        getProfessors().remove(professor);
        professor.setTitle(null);

        return professor;
    }

}