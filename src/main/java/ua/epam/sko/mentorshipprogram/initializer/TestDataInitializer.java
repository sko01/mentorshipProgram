package ua.epam.sko.mentorshipprogram.initializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.epam.sko.mentorshipprogram.services.EmployeeService;

@Component
public class TestDataInitializer {
	
	@Autowired
	private EmployeeService employeService;
	
	@PostConstruct
	public void init(){
		String firstEmployeName = "Serhiy";
		String firstEmployeSecondName = "Kovalenko";
		String firstEmployeEmail = "mail@mail.com";
		String firstEmployeBirthday="1980-11-11";
		
		String secondEmployeName = "Andriy";
		String secondEmployeSecondName = "Petrenko";
		String secondEmployeEmail = "drive@mail.com";
		String secondEmployeBirthday="1995-09-02";
		
		employeService.saveEmploye(firstEmployeName, firstEmployeSecondName, firstEmployeEmail, firstEmployeBirthday);
		employeService.saveEmploye(secondEmployeName, secondEmployeSecondName, secondEmployeEmail, secondEmployeBirthday);
	}
}
