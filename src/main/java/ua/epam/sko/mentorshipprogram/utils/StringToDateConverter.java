package ua.epam.sko.mentorshipprogram.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import ua.epam.sko.mentorshipprogram.constants.MentorshipProgramConstants;

@Component
public class StringToDateConverter {
	private static SimpleDateFormat dateFormater = new SimpleDateFormat(MentorshipProgramConstants.DATE_FROMAT);
	
	public Date stringToDateConvert(String date) throws ParseException{
		try{
			Date birthday = dateFormater.parse(date);
			return birthday;
		} catch (ParseException e){
			System.err.println("Wrong date format: " + date + " expected: " + MentorshipProgramConstants.DATE_FROMAT);
			throw e;
		}
		
	}
	
	public String dateToStringConvert(Date date){
		return dateFormater.format(date);	
	}
}
