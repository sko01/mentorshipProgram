package ua.epam.sko.mentorshipprogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import ua.epam.sko.mentorshipprogram.aop.EmployeeAspect;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="ua.epam.sko.mentorshipprogram.aop")
public class SpringAspectJConfig {

	@Bean
	public EmployeeAspect employeeAspect(){
		return new EmployeeAspect();
	}
}
