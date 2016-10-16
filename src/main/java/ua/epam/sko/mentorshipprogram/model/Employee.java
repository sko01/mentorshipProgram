package ua.epam.sko.mentorshipprogram.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ua.epam.sko.mentorshipprogram.utils.DateDeserializer;
import ua.epam.sko.mentorshipprogram.utils.DateSerializer;

@Entity
@Table(name="Employee", schema="dbo")
public class Employee extends AbstractGlobalEntity implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	private String employeeName;
	private String employeeSecondName;
	@NotNull
	private String employeeEmail;
	
	@Temporal(TemporalType.DATE)
	@Past
	@JsonSerialize(using=DateSerializer.class)
	@JsonDeserialize(using=DateDeserializer.class)
	private Date employeeBirthday;
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeId) {
		this.employeeId = employeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeName) {
		this.employeeName = employeName;
	}
	
	public String getEmployeeSecondName() {
		return employeeSecondName;
	}
	
	public void setEmployeeSecondName(String employeSecondName) {
		this.employeeSecondName = employeSecondName;
	}
	
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	public void setEmployeeEmail(String employeEmail) {
		this.employeeEmail = employeEmail;
	}
	
	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}
	
	public void setEmployeeBirthday(Date employeBirthday) {
		this.employeeBirthday = employeBirthday;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeEmail == null) ? 0 : employeeEmail.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeEmail == null) {
			if (other.employeeEmail != null)
				return false;
		} else if (!employeeEmail.equals(other.employeeEmail))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Employee [employeId=" + employeeId + ", employeName=" + employeeName + ", employeSecondName="
				+ employeeSecondName + ", employeEmail=" + employeeEmail + "]";
	}
}
