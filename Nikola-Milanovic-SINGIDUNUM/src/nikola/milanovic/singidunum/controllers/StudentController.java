package nikola.milanovic.singidunum.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nikola.milanovic.singidunum.entities.City;
import nikola.milanovic.singidunum.entities.Exam;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;
import nikola.milanovic.singidunum.services.CityService;
import nikola.milanovic.singidunum.services.ExamService;
import nikola.milanovic.singidunum.services.StudentService;
import nikola.milanovic.singidunum.services.SubjectService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private CityService cityService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ExamService examService;
	
	private Set<Subject> subjectCart;
	private Set<Exam> examCart;
	
	@RequestMapping("")
	public String allStudents(Model model) {
		model.addAttribute("students", studentService.getAll());
		return "students";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedStudents(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)studentService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("students", studentService.getPaginated(page, num));
		return "students";
	}
	
	@RequestMapping("/{id}")
	public String oneStudent(@PathVariable("id") long id, Model model) {
		model.addAttribute("student", studentService.getOneById(id));
		model.addAttribute("subjects", studentService.allSubjects(id));
		model.addAttribute("exams", studentService.allExams(id));
		model.addAttribute("marks", studentService.getOneById(id).getMarks());
		return "student";
	}
	
	@RequestMapping("/create-student")
	public String createStudent(Model model) {
		model.addAttribute("cities", cityService.getAll());
		return "create-student";
	}
	
	@RequestMapping(value="/do-create-student", method=RequestMethod.POST)
	public String saveStudent(@Validated Student student, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			model.addAttribute("cities", cityService.getAll());
			return "create-student";
		}
		studentService.create(student);
		return "redirect:/students";
	}
	
	@RequestMapping("/edit-student/{id}")
	public String editStudent(@PathVariable("id") long id, Model model) {
		model.addAttribute("student", studentService.getOneById(id));
		model.addAttribute("cities", cityService.getAll());
		
		examCart = new HashSet<Exam>();
		subjectCart = new HashSet<Subject>();
		
		for(Exam e : studentService.getOneById(id).getExams()) {
			examCart.add(e);
		}
		
		for(Subject s : studentService.getOneById(id).getSubjects()) {
			subjectCart.add(s);
		}
		return "edit-student";
	}
	
	@RequestMapping(value="/do-edit-student", method=RequestMethod.POST)
	public String updateStudent(@Validated Student student, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("cities", cityService.getAll());
			model.addAttribute("messages", messages);
			return "edit-student";
		}
		
		if(examCart != null)student.setExams(examCart);
		if(subjectCart != null)student.setSubjects(subjectCart);
		
		
		studentService.create(student);
		return "redirect:/students";
	}
	
	@RequestMapping("/delete-student/{id}")
	public String removeStudent(@PathVariable("id") long id) {
		studentService.delete(id);
		return "redirect:/students";
	}
	
	@RequestMapping("/{id}/set-subjects")
	public String setSubjects(@PathVariable("id") long id, Model model) {
		model.addAttribute("student", studentService.getOneById(id));
		model.addAttribute("subjects", subjectService.getNotStudentSubjects(id));
		return "student-set-subjects";
	}
	
	@RequestMapping(value="/do-set-subjects",method=RequestMethod.POST)
	public String addSubjects(long student, long subject) {
		studentService.setSubject(student, subject);
		return "redirect:/students";
	}
	
	@RequestMapping("/{id}/set-exams")
	public String setExams(@PathVariable("id") long id, Model model) {
		model.addAttribute("student", studentService.getOneById(id));
		model.addAttribute("exams", examService.getExamForStudent(id,studentService.getOneById(id).getCurrentYearOfStudy()));
		return "student-set-exams";
	}
	
	@RequestMapping(value="/do-set-exams",method=RequestMethod.POST)
	public String addExams(long student, long exam) {
		studentService.setExam(student, exam);
		return "redirect:/students";
	}
}
