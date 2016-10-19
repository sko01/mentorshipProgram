package ua.epam.sko.mentorshipprogram.aop;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.sko.mentorshipprogram.model.Employee;
import ua.epam.sko.mentorshipprogram.services.EmployeeService;

@Aspect
public class EmployeeAspect {
	private static Logger LOG = Logger.getLogger(EmployeeAspect.class);
	
	@Autowired
	EmployeeService employeeService;
	
	@Before("execution(* ua.epam.sko.mentorshipprogram.dao..*.*(..))")
	public void addLoggingToDao(JoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
		LOG.info("Method invoked: " + methodName + " with arguments "
				+ arguments + "\nand the full toString: " + stuff);
	}
	
	@AfterThrowing(pointcut="execution(* ua.epam.sko.mentorshipprogram.dao..*.*(..))", throwing="e")
	public void addLoggingToDaoErrorHandling(JoinPoint joinPoint, Throwable e){
		Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    LOG.info("We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + e.getMessage(), e);
	}
	
	@Around(value="execution(* ua.epam.sko.mentorshipprogram.controller.MentorshipProgramRESTController.addEmployee(..))&&args(newEmployee, request)")
	public Object addMetadataInfoWhenCreatingNewEmployee(ProceedingJoinPoint joinPoint, Employee newEmployee, HttpServletRequest request) throws Throwable{
		Date creationDate = new Date();
		newEmployee.setCreatedBy(request.getRemoteAddr());
		newEmployee.setCreationDate(creationDate);
		newEmployee.setModificationDate(creationDate);
		
		Object result = joinPoint.proceed();
		return result;
	}
	
	@Around(value="execution(* ua.epam.sko.mentorshipprogram.controller.MentorshipProgramRESTController.updateEmployee(..))&&args(employeId, updatedEmployee, request)")
	public Object updateMetadataInfoForEmployee(ProceedingJoinPoint joinPoint, String employeId, Employee updatedEmployee, HttpServletRequest request) throws Throwable{
		Date modificationDate = new Date();
		Employee employee = employeeService.getEmployeById(Integer.parseInt(employeId));
		employee.setModificationDate(modificationDate);
		employee.setCreatedBy(request.getRemoteAddr());
		
		Object result = joinPoint.proceed();
		return result;
	}
}
