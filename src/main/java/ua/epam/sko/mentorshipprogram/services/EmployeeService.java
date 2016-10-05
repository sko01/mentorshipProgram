package ua.epam.sko.mentorshipprogram.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.epam.sko.mentorshipprogram.model.Employee;

@Service
public interface EmployeeService {
	Employee getEmployeById(int id);
	void saveEmploye(Employee employe);
	Employee updateName(Employee employe, String name);
	Employee updateSecondName(Employee employe, String secondName);
	Employee updateEmail(Employee employe, String email);
	Employee updateBirthday(Employee employe, String birthday);
	void deleteEmploye(Employee employe);
	boolean checkIfEmployeExist(String email);
	Employee getEmployeByEmail(String email);
	List<Employee> getEmployees();
}
