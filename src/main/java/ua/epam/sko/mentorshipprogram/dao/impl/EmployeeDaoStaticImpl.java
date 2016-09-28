package ua.epam.sko.mentorshipprogram.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import ua.epam.sko.mentorshipprogram.dao.EmployeeDao;
import ua.epam.sko.mentorshipprogram.model.Employee;

@Component
public class EmployeeDaoStaticImpl implements EmployeeDao {
	
	private static AtomicInteger employeId = new AtomicInteger();
	private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

	public Employee saveEmploye(Employee employe) {
		if(employe != null) {
			employe.setEmployeeId(employeId.getAndIncrement());
			employees.put(employe.getEmployeeId(), employe);
		}
		
		return employe;
	}

	public void updateEmploye(Employee employe) {
		if(employe != null) {
			if(employees.containsKey(employe.getEmployeeId())) {
				employees.put(employe.getEmployeeId(), employe);
			}
		}
	}

	public Employee deleteEmploye(Employee employe) {
		if(employe != null) {
			if(employees.containsKey(employe.getEmployeeId())) {
				Employee deletedEmploye = employees.remove(employe.getEmployeeId());
				return deletedEmploye;
			}
		}
		return null;
	}
	
	public boolean checkIfEmployeExist(Employee employe){
		return	employees.containsValue(employe);
	}

	public Employee getEmploye(String email) {
		for(Entry<Integer, Employee> employe : employees.entrySet()) {
			if(employe.getValue().getEmployeeEmail().equals(email)){
				return employe.getValue();
			}
		}
		return null;
	}

	public List<Employee> getEmployees() {
		return new ArrayList<Employee>(employees.values());
	}

	public Employee getEmployeById(int id) {
		return employees.get(id);
	}

}
