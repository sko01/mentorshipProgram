package ua.epam.sko.mentorshipprogram.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.epam.sko.mentorshipprogram.model.Employee;

@Component
public interface EmployeeDao {
	Employee getEmployeById(int id);
	Employee saveEmploye(Employee employe);
	void updateEmploye(Employee employe);
	Employee deleteEmploye(Employee employe);
	boolean checkIfEmployeExist(Employee employe);
	Employee getEmploye(String email);
	List<Employee> getEmployees();
}
