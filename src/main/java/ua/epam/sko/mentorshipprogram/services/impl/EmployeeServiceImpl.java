package ua.epam.sko.mentorshipprogram.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.epam.sko.mentorshipprogram.constants.MentorshipProgramConstants;
import ua.epam.sko.mentorshipprogram.dao.EmployeeDao;
import ua.epam.sko.mentorshipprogram.model.Employee;
import ua.epam.sko.mentorshipprogram.services.EmployeeService;
import ua.epam.sko.mentorshipprogram.utils.StringToDateConverter;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private StringToDateConverter stringToDateConverter;
	
	@Autowired
	private EmployeeDao employeDao;
	
	public EmployeeDao getEmployeDao() {
		return employeDao;
	}

	public void setEmployeDao(EmployeeDao employeDao) {
		this.employeDao = employeDao;
	}

	public void saveEmploye(Employee employe) {
		employeDao.saveEmploye(employe);		
	}

	public Employee updateName(Employee employe, String name) {
		employe.setEmployeeName(name);
		employeDao.updateEmploye(employe);
		
		return employe;
	}

	public Employee updateSecondName(Employee employe, String secondName) {
		employe.setEmployeeSecondName(secondName);
		employeDao.updateEmploye(employe);
		
		return employe;
	}

	public Employee updateEmail(Employee employe, String email) {
		employe.setEmployeeEmail(email);
		employeDao.updateEmploye(employe);
		
		return employe;
	}

	public Employee updateBirthday(Employee employe, String birthday) {
		try{
			Date employeBirthday = stringToDateConverter.stringToDateConvert(birthday);
			employe.setEmployeeBirthday(employeBirthday);
			employeDao.updateEmploye(employe);
			
			return employe;
		} catch (ParseException e){
			System.err.println(e.getMessage());
		}
		
		return null;
	}

	public void deleteEmploye(Employee employe) {
		employeDao.deleteEmploye(employe);
	}

	public boolean checkIfEmployeExist(String email) {
		if(employeDao.getEmploye(email) != null)
			return true;
		
		return false;
	}

	public Employee getEmployeByEmail(String email) {
		return employeDao.getEmploye(email);
	}

	public List<Employee> getEmployees() {
		return employeDao.getEmployees();
	}

	public Employee getEmployeById(int id) {
		return employeDao.getEmployeById(id);
	}
}
