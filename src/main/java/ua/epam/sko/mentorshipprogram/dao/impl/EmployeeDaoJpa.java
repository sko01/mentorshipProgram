package ua.epam.sko.mentorshipprogram.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import ua.epam.sko.mentorshipprogram.dao.EmployeeDao;
import ua.epam.sko.mentorshipprogram.model.Employee;

@Component(value="jpaDao")
public class EmployeeDaoJpa implements EmployeeDao{
	
	@PersistenceContext
	private EntityManager em;

	public Employee getEmployeById(int id) {
		String getEmployeeByIdQuery = "SELECT e FROM Employee e WHERE e.employeeId = ?1";
		TypedQuery<Employee> query = em.createQuery(getEmployeeByIdQuery, Employee.class);
		query.setParameter(1, id);
		
		
		return query.getSingleResult();
	}
	
	public Employee saveEmploye(Employee employe) {
		em.persist(employe);
		em.flush();
		return employe;
	}

	public void updateEmploye(Employee employe) {
		em.merge(employe);
		em.flush();
	}

	public Employee deleteEmploye(Employee employe) {
		Employee employeeToBeRemoved = em.getReference(Employee.class, employe.getEmployeeId());
		em.remove(employeeToBeRemoved);
		return employeeToBeRemoved;
	}

	public boolean checkIfEmployeExist(Employee employe) {
		return em.equals(employe);
	}

	public Employee getEmploye(String email) {
		String getEmployeeByIdQuery = "SELECT e FROM Employee e WHERE e.employeeEmail = ?1";
		TypedQuery<Employee> query = em.createQuery(getEmployeeByIdQuery, Employee.class);
		query.setParameter(1, email);
		
		if(query.getResultList().isEmpty()){
			return null;
		}
		
		return query.getSingleResult();
	}

	public List<Employee> getEmployees() {
		String getAllEmployees = "SELECT e FROM Employee e";
		TypedQuery<Employee> query = em.createQuery(getAllEmployees, Employee.class);
		
		return query.getResultList();
	}

}
