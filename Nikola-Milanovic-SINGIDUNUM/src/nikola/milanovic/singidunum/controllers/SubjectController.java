package nikola.milanovic.singidunum.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nikola.milanovic.singidunum.entities.Professor;
import nikola.milanovic.singidunum.entities.Student;
import nikola.milanovic.singidunum.entities.Subject;
import nikola.milanovic.singidunum.services.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	private Set<Student>studentCart;
	private Set<Professor>professorCart;
	
	@RequestMapping("")
	public String allSubjects(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subjects";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedSubjects(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)subjectService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("subjects", subjectService.getPaginated(page, num));
		return "subjects";
	}
	
	@RequestMapping("/{id}")
	public String oneSubject(@PathVariable("id") long id, Model model) {
		model.addAttribute("subject", subjectService.getOneById(id));
		return "subject";
	}
	
	@RequestMapping("/create-subject")
	public String createSubject() {
		return "create-subject";
	}
	
	@RequestMapping(value="/do-create-subject", method=RequestMethod.POST)
	public String saveSubject(@Validated Subject subject, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			return "create-subject";
		}
		subjectService.create(subject);
		return "redirect:/subjects";
	}
	
	@RequestMapping("/edit-subject/{id}")
	public String editSubject(@PathVariable("id") long id, Model model) {
		model.addAttribute("subject", subjectService.getOneById(id));
		studentCart = new HashSet<Student>();
		professorCart = new HashSet<Professor>();
		for(Student s : subjectService.getOneById(id).getStudents()) {
			studentCart.add(s);
		}
		for(Professor p : subjectService.getOneById(id).getProfessors()) {
			professorCart.add(p);
		}
		return "edit-subject";
	}
	
	@RequestMapping(value="/do-edit-subject", method=RequestMethod.POST)
	public String updateSubject(@Validated Subject subject, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			return "edit-subject";
		}
		if(studentCart != null)subject.setStudents(studentCart);
		if(professorCart != null)subject.setProfessors(professorCart);
		subjectService.create(subject);
		return "redirect:/subjects";
	}
	
	@RequestMapping("/delete-subject/{id}")
	public String removeSubject(@PathVariable("id") long id) {
		subjectService.delete(id);
		return "redirect:/subjects";
	}
	
	
}
