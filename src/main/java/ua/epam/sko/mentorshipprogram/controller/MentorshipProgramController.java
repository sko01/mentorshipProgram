package ua.epam.sko.mentorshipprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.epam.sko.mentorshipprogram.model.Employee;
import ua.epam.sko.mentorshipprogram.services.EmployeeService;

@Controller
@RequestMapping("/")
public class MentorshipProgramController {
	@Autowired
	private EmployeeService employeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String employeIndex(ModelMap model){
		return "index";
	}
	
	@RequestMapping(value="employee/{employeId}", method=RequestMethod.GET)
	public String employePage(ModelMap model){
		return "employee";
	}
		
	@RequestMapping(value="update/{employeId}", method=RequestMethod.GET)
	public String updateEmploye(@PathVariable String employeId, ModelMap modelMap){
		Employee employeToUpdate = employeService.getEmployeById(Integer.parseInt(employeId));
		if(employeToUpdate != null){
			modelMap.addAttribute("employe", employeToUpdate);
		}
		return "employe";
	}
	
	@RequestMapping(value="update/{employeId}/save", method=RequestMethod.POST)
	public String saveUpdatedEmploye(@PathVariable String employeId, @RequestParam(value="name", required=true) String name,
							 @RequestParam(value="secondName", required=true) String secondName,
							 @RequestParam(value="email", required=true) String email,
							 @RequestParam(value="birthday", required=true) String birthday, ModelMap modelMap) {
		Employee employeToUpdate = employeService.getEmployeById(Integer.parseInt(employeId));
		if(employeToUpdate != null){
			employeService.updateName(employeToUpdate, name);
			employeService.updateSecondName(employeToUpdate, secondName);
			employeService.updateEmail(employeToUpdate, email);
			employeService.updateBirthday(employeToUpdate, birthday);
		}
		return "redirect:/employees";
	}
	
}
