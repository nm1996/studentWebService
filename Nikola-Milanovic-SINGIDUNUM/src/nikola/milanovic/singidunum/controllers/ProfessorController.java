package nikola.milanovic.singidunum.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nikola.milanovic.singidunum.entities.Professor;
import nikola.milanovic.singidunum.entities.Subject;
import nikola.milanovic.singidunum.services.CityService;
import nikola.milanovic.singidunum.services.ProfessorService;
import nikola.milanovic.singidunum.services.SubjectService;
import nikola.milanovic.singidunum.services.TitleService;

@Controller
@RequestMapping("/professors")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private CityService cityService;
	@Autowired
	private TitleService titleService;
	@Autowired
	private SubjectService subjectService;
	
	private Set<Subject> subjectCart;
	
	@RequestMapping("")
	public String allProfessors(Model model) {
		model.addAttribute("professors", professorService.getAll());
		return "professors";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedProfessors(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)professorService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("professors", professorService.getPaginated(page, num));
		return "professors";
	}
	
	@RequestMapping("/{id}")
	public String oneProfessor(@PathVariable("id") long id, Model model) {
		model.addAttribute("professor", professorService.getOneById(id));
		model.addAttribute("subjects", professorService.allSubjects(id));
		model.addAttribute("exams", professorService.allExams(id));
		return "professor";
	}
	
	@RequestMapping("/create-professor")
	public String createProfessor(Model model) {
		model.addAttribute("cities", cityService.getAll());
		model.addAttribute("titles", titleService.getAll());
		return "create-professor";
	}
	
	@RequestMapping(value="/do-create-professor", method=RequestMethod.POST)
	public String saveProfessor(@Validated Professor professor, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			model.addAttribute("cities", cityService.getAll());
			model.addAttribute("titles", titleService.getAll());
			return "create-professor";
		}
		professorService.create(professor);
		return "redirect:/professors";
	}
	
	@RequestMapping("/edit-professor/{id}")
	public String editProfessor(@PathVariable("id") long id, Model model) {
		model.addAttribute("professor", professorService.getOneById(id));
		model.addAttribute("cities", cityService.getAll());
		model.addAttribute("titles", titleService.getAll());
		
		subjectCart = new HashSet<Subject>();
		
		for(Subject s : professorService.getOneById(id).getSubjects()) {
			subjectCart.add(s);
		}
		
		return "edit-professor";
	}
	
	@RequestMapping(value="/do-edit-professor", method=RequestMethod.POST)
	public String updateProfessor(@Validated Professor professor, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("cities", cityService.getAll());
			model.addAttribute("titles", titleService.getAll());
			model.addAttribute("messages", messages);
			return "edit-professor";
		}
		
		if(subjectCart != null) {
			professor.setSubjects(subjectCart);
		}
		
		professorService.create(professor);
		return "redirect:/professors";
	}
	
	@RequestMapping("/delete-professor/{id}")
	public String deleteProfessor(@PathVariable("id") long id) {
		professorService.delete(id);
		return "redirect:/professors";
	}
	
	@RequestMapping("/{id}/set-subjects")
	public String setSubjects(@PathVariable("id") long id, Model model) {
		model.addAttribute("professor", professorService.getOneById(id));
		model.addAttribute("subjects", subjectService.getNotProfessorSubjects(id));
		return "professor-set-subjects";
	}
	
	@RequestMapping(value="/do-set-subjects",method=RequestMethod.POST)
	public String addSubjects(long professor, long subject) {
		professorService.setSubject(professor, subject);
		return "redirect:/professors";
	}

}
