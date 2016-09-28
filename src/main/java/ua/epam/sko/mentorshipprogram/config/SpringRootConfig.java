package ua.epam.sko.mentorshipprogram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ua.epam.sko.mentorshipprogram.dao.EmployeeDao;
import ua.epam.sko.mentorshipprogram.initializer.TestDataInitializer;
import ua.epam.sko.mentorshipprogram.services.EmployeeService;

@Configuration
@ComponentScan(basePackages="ua.epam.sko.mentorshipprogram")
public class SpringRootConfig {
	@Autowired
	private EmployeeDao employeDao;
	
	@Autowired
	private EmployeeService employeService;
	
	@Autowired
	private TestDataInitializer testDataInitializer;
	
}
