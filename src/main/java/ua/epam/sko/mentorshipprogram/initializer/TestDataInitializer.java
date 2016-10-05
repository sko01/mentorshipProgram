package ua.epam.sko.mentorshipprogram.initializer;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.epam.sko.mentorshipprogram.model.Employee;
import ua.epam.sko.mentorshipprogram.services.EmployeeService;
import ua.epam.sko.mentorshipprogram.utils.StringToDateConverter;

@Component
public class TestDataInitializer {
	
	@Autowired
	private EmployeeService employeService;
	
	@Autowired
	private StringToDateConverter stringToDateConverter;
	
	@PostConstruct
	public void init() throws ParseException{
		String firstEmployeeName = "Serhiy";
		String firstEmployeeSecondName = "Kovalenko";
		String firstEmployeeEmail = "mail@mail.com";
		String firstEmployeeBirthday="1980-11-11";
		
		String secondEmployeeName = "Andriy";
		String secondEmployeeSecondName = "Petrenko";
		String secondEmployeeEmail = "drive@mail.com";
		String secondEmployeeBirthday="1995-09-02";
		
		Employee firstEmployee = new Employee();
		firstEmployee.setEmployeeName(firstEmployeeName);
		firstEmployee.setEmployeeSecondName(firstEmployeeSecondName);
		firstEmployee.setEmployeeEmail(firstEmployeeEmail);
		firstEmployee.setEmployeeBirthday(stringToDateConverter.stringToDateConvert(firstEmployeeBirthday));
		firstEmployee.setCreatedBy("Automatic");
		firstEmployee.setCreationDate(new Date());
		firstEmployee.setModificationDate(new Date());
		
		Employee secondEmployee = new Employee();
		secondEmployee.setEmployeeName(secondEmployeeName);
		secondEmployee.setEmployeeSecondName(secondEmployeeSecondName);
		secondEmployee.setEmployeeEmail(secondEmployeeEmail);
		secondEmployee.setEmployeeBirthday(stringToDateConverter.stringToDateConvert(secondEmployeeBirthday));
		secondEmployee.setCreatedBy("Automatic");
		secondEmployee.setCreationDate(new Date());
		secondEmployee.setModificationDate(new Date());
		
		employeService.saveEmploye(firstEmployee);
		employeService.saveEmploye(secondEmployee);
	}
}
