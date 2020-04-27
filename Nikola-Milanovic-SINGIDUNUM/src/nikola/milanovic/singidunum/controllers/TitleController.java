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

import nikola.milanovic.singidunum.entities.Title;
import nikola.milanovic.singidunum.services.TitleService;

@Controller
@RequestMapping("/titles")
public class TitleController {

	@Autowired
	private TitleService titleService;
	
	@RequestMapping("")
	public String allTitles(Model model) {
		model.addAttribute("titles", titleService.getAll());
		return "titles";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedTitles(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)titleService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("titles", titleService.getPaginated(page, num));
		return "titles";
	}
	
	@RequestMapping("/{id}")
	public String oneTitle(@PathVariable("id") long id, Model model) {
		model.addAttribute("title", titleService.getOneById(id));
		return "title";
	}
	
	@RequestMapping("/create-title")
	public String createTitle() {
		return "create-title";
	}
	
	@RequestMapping(value="/do-create-title", method=RequestMethod.POST)
	public String saveTitle(@Validated Title title, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			return "create-title";
		}
		titleService.create(title);
		return "redirect:/titles";
	}
	
	@RequestMapping("/edit-title/{id}")
	public String editTitle(@PathVariable("id") long id, Model model) {
		model.addAttribute("title", titleService.getOneById(id));
		return "edit-title";
	}
	
	@RequestMapping(value="/do-edit-title", method=RequestMethod.POST)
	public String updateTitle(@Validated Title title, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			return "edit-title";
		}
		titleService.create(title);
		return "redirect:/titles";
	}
	
	@RequestMapping(value="/delete-title/{id}")
	public String removeTitle(@PathVariable("id") long id) {
		titleService.delete(id);
		return "redirect:/titles";
	}
	
}
