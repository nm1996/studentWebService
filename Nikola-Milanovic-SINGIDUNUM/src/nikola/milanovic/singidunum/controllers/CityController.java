package nikola.milanovic.singidunum.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nikola.milanovic.singidunum.entities.City;
import nikola.milanovic.singidunum.services.CityService;

@Controller
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("")
	public String allCities(Model model) {
		model.addAttribute("cities", cityService.getAll());
		return "cities";
	}
	
	@RequestMapping("/{page}/{num}")
	public String paginatedCities(@PathVariable("page") int page, @PathVariable("num") int num, Model model) {
		model.addAttribute("pages", Math.ceil((double)cityService.getAll().size()/num));
		model.addAttribute("num", num);
		model.addAttribute("cities", cityService.getPaginated(page, num));
		return "cities";
	}
	
	@RequestMapping("/{id}")
	public String oneCity(@PathVariable("id") long id, Model model) {
		model.addAttribute("city", cityService.getOneById(id));
		return "city";
	}
	
	@RequestMapping("/create-city")
	public String createCity() {
		return "create-city";
	}
	
	@RequestMapping(value = "/do-create-city", method= RequestMethod.POST)
	public String saveCity(@Validated City city, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			return "create-city";
		}
		cityService.create(city);
		return "redirect:/cities";
	}
	
	@RequestMapping("/edit-city/{id}")
	public String editCity(@PathVariable("id") long id, Model model) {
		model.addAttribute("city", cityService.getOneById(id));
		return "edit-city";
	}
	
	@RequestMapping(value = "/do-edit-city", method = RequestMethod.POST)
	public String updateCity(@Validated City city, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			Set<String> messages = new HashSet<String>();
			for(ObjectError e : errors) {
				messages.add(e.getDefaultMessage());
			}
			model.addAttribute("messages", messages);
			return "edit-city";
		}
		cityService.create(city);
		return "redirect:/cities";
	}
	
	@RequestMapping("/delete-city/{id}")
	public String removeCity(@PathVariable("id") long id) {
		cityService.delete(id);
		return "redirect:/cities";
	}

}
