package ua.epam.sko.mentorshipprogram.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ua.epam.sko.mentorshipprogram.model.Employee;
import ua.epam.sko.mentorshipprogram.services.EmployeeService;
import ua.epam.sko.mentorshipprogram.utils.StringToDateConverter;

@RestController
@RequestMapping("/api")
public class MentorshipProgramRESTController {
	@Autowired
	private StringToDateConverter stringToDateConverter;
	
	@Autowired
	private EmployeeService employeService;
	
	@RequestMapping(path="/employees",  method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeeList(ModelMap model){
		List<Employee> employees = employeService.getEmployees();
		if(employees.isEmpty()){
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@RequestMapping(path="/employee/{employeId}",  method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable String employeId){
		Employee employee = employeService.getEmployeById(Integer.parseInt(employeId));
		if(employee == null){
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(value="delete/{employeId}", method=RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable String employeId){
		Employee employeToDelete = employeService.getEmployeById(Integer.parseInt(employeId));
		if(employeToDelete != null){
			employeService.deleteEmploye(employeToDelete);
			return new ResponseEntity<Employee>(employeToDelete, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Employee>(employeToDelete, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<Void> addEmployee(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Employee newEmploye = mapper.readValue(request.getInputStream(), Employee.class);
		if(employeService.getEmployeByEmail(newEmploye.getEmployeeEmail()) == null){
			employeService.saveEmploye(newEmploye);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
	
	@RequestMapping(value="/update/{employeId}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<Void> updateEmployee(@PathVariable String employeId, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Employee updatedEmployee = mapper.readValue(request.getInputStream(), Employee.class);
		Employee originalEmployee = employeService.getEmployeById(Integer.parseInt(employeId));
		if(originalEmployee != null){
			if(updatedEmployee.getEmployeeEmail().equals(originalEmployee.getEmployeeEmail()) || employeService.getEmployeByEmail(updatedEmployee.getEmployeeEmail()) == null){
				employeService.updateName(originalEmployee, updatedEmployee.getEmployeeName());
				employeService.updateSecondName(originalEmployee, updatedEmployee.getEmployeeSecondName());
				employeService.updateEmail(originalEmployee, updatedEmployee.getEmployeeEmail());
				employeService.updateBirthday(originalEmployee, stringToDateConverter.dateToStringConvert(updatedEmployee.getEmployeeBirthday()));
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
}
