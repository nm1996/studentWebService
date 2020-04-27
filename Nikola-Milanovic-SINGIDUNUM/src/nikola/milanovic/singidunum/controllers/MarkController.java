package nikola.milanovic.singidunum.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nikola.milanovic.singidunum.entities.Mark;
import nikola.milanovic.singidunum.services.ExamService;
import nikola.milanovic.singidunum.services.MarkService;
import nikola.milanovic.singidunum.services.StudentService;
import nikola.milanovic.singidunum.services.SubjectService;

@Controller
@RequestMapping("/marks")
public class MarkController {
	
	@Autowired
	private MarkService markService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ExamService examService;
	
	@RequestMapping("")
	public String allMarks(Model model) {
		model.addAttribute("marks", markService.getAll());
		return "marks";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedMarks(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)markService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("marks", markService.getPaginated(page, num));
		return "marks";
	}
	
	@RequestMapping("/{id}")
	public String oneMark(@PathVariable("id") long id, Model model) {
		model.addAttribute("mark", markService.getOneById(id));
		return "mark";
	}
	
	@RequestMapping("/create-mark")
	public String createMark(Model model) {
		model.addAttribute("exams", examService.finishedExams());
		return "create-mark";
	}
	
	@PostMapping("/create-mark")
	public String createMark(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("examId"));
		model.addAttribute("exam", examService.getOneById(id));
		model.addAttribute("subject", examService.getOneById(id).getSubject());
		model.addAttribute("students", studentService.usersForExam(id));
		return "create-mark";
	}
	
	@RequestMapping(value="/do-create-mark", method=RequestMethod.POST)
	public String saveMark(Mark mark) {
		markService.create(mark);
		return "redirect:/marks";
	}
	
	@RequestMapping("/edit-mark/{id}")
	public String editMark(@PathVariable("id") long id, Model model) {
		model.addAttribute("mark", markService.getOneById(id));
		model.addAttribute("student", markService.getOneById(id).getStudent());
		model.addAttribute("exam", markService.getOneById(id).getExam());
		model.addAttribute("subject", markService.getOneById(id).getSubject());
		return "edit-mark";
	}
	
	@RequestMapping(value="/do-edit-mark", method=RequestMethod.POST)
	public String updateMark(Mark mark) {
		markService.create(mark);
		return "redirect:/marks";
	}
	
	@RequestMapping("/delete-mark/{id}")
	public String deleteMark(@PathVariable("id") long id) {
		markService.delete(id);
		return "redirect:/marks";
	}
}
