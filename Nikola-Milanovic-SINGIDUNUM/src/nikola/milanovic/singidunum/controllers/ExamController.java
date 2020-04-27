package nikola.milanovic.singidunum.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.derby.tools.sysinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Mark;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.services.ExamService;
import nikola.milanovic.singidunum.services.ProfessorService;
import nikola.milanovic.singidunum.services.SubjectService;

@Controller
@RequestMapping("/exams")
public class ExamController {

	@Autowired
	private ExamService examService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private SubjectService subjectService;
	
	private Set<Student> studentCart;
	
	@RequestMapping("")
	public String allExams(Model model) {
		model.addAttribute("exams", examService.getAll());
		return "exams";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedExams(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)examService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("exams", examService.getPaginated(page, num));
		return "exams";
	}
	
	@RequestMapping("/{id}")
	public String oneExam(@PathVariable("id") long id, Model model) {
		model.addAttribute("exam", examService.getOneById(id));
		return "exam";
	}
	
	@RequestMapping("/create-exam")
	public String createExam(Model model) {
		model.addAttribute("subjects", subjectService.getSubjectsWithoutExam());
//		model.addAttribute("professors", professorService.getAll());
		return "create-exam";
	}
	
	@RequestMapping(value="/do-create-exam", method=RequestMethod.POST)
	public String saveExam(HttpServletRequest request, Exam exam) {
		String ids = request.getParameter("ids");
		String[]strings = ids.split("_");
		int subjectid = Integer.parseInt(strings[0]);
		int professorid = Integer.parseInt(strings[1]);
		
		exam.setProfessor(professorService.getOneById(professorid));
		exam.setSubject(subjectService.getOneById(subjectid));
		examService.create(exam);
		return "redirect:/exams";
	}
	
	@RequestMapping("/edit-exam/{id}")
	public String editExam(@PathVariable("id") long id, Model model) {
		model.addAttribute("exam", examService.getOneById(id));
		model.addAttribute("subject", examService.getOneById(id).getSubject());
		model.addAttribute("professors", examService.getOneById(id).getSubject().getProfessors());
		
		studentCart = new HashSet<Student>();
		
		for(Student s : examService.getOneById(id).getStudents()) {
			studentCart.add(s);
		}
		
		return "edit-exam";
	}
	
	@RequestMapping(value="/do-edit-exam", method=RequestMethod.POST)
	public String updateExam(Exam exam) {
		if(studentCart != null) exam.setStudents(studentCart);
		examService.create(exam);
		return "redirect:/exams";
	}
	
	@RequestMapping("/delete-exam/{id}")
	public String deleteExam(@PathVariable("id") long id) {
		examService.delete(id);
		return "redirect:/exams";
	}
}
