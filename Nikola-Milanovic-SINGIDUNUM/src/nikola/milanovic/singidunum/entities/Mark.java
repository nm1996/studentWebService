package nikola.milanovic.singidunum.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;


/**
 * The persistent class for the MARK database table.
 * 
 */
@Entity
@Table(name = "mark")
public class Mark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mark_id", nullable = false)
	private long markId;

	@Column(length=2)
	@NotNull
	private int mark;

	
	@ManyToOne(fetch=FetchType.LAZY)
	private Exam exam;

	
	@ManyToOne(fetch=FetchType.LAZY)
	private Student student;

	
	@ManyToOne(fetch=FetchType.LAZY)
	private Subject subject;

	public Mark() {
	}

	public long getMarkId() {
		return this.markId;
	}

	public void setMarkId(long markId) {
		this.markId = markId;
	}

	public int getMark() {
		return this.mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}