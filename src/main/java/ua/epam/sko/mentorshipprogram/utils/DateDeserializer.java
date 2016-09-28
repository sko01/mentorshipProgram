package ua.epam.sko.mentorshipprogram.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import ua.epam.sko.mentorshipprogram.constants.MentorshipProgramConstants;

public class DateDeserializer extends JsonDeserializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat();
	
	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		String formattedDate = parser.getText();
		if(formattedDate.contains("/")) {
			dateFormat.applyPattern(MentorshipProgramConstants.REVERTED_DATE_FROMAT);
		}
		else if(formattedDate.contains("-")) {
			dateFormat.applyPattern(MentorshipProgramConstants.DATE_FROMAT);
		}
		Date date;
		try {
			date = dateFormat.parse(formattedDate);
			if(validateDate(date)){
				return date;
			}
			else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean validateDate(Date date){
		Date currentDate = new Date();
		Calendar oneHundredYears = Calendar.getInstance();
		oneHundredYears.setTime(currentDate);
		oneHundredYears.add(Calendar.YEAR, -100);
		
		if(date == null){
			return false;
		}
		if(date.after(currentDate) || date.before(oneHundredYears.getTime())){
			return false;	
		}
		
		return true;
	}

}
