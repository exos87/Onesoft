package com.onesoft.api.utils;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * JSON Deserializer pre zmapovanie poli Time implementuje metodu deserialize
 * 
 * @author ondrist
 *
 */
public class TimeDeserializer implements JsonDeserializer<Time> {

	/**
	 * Implementacia deserializacie a konverzie String -> Time
	 * 
	 * @return Time
	 */
	@Override
	public Time deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
	    
		Calendar cal = Calendar.getInstance();
		String input = element.getAsString();
		
		// Regex pre rozklad a nastavenie kalendara zo Stringu napr.: 10:08:15Z
		final Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])(.*)");
	    final Matcher matcher = pattern.matcher(input);		
		
	    if (matcher.find()) {
	    	cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(matcher.group(1)));
	    	cal.set(Calendar.MINUTE, Integer.parseInt(matcher.group(2)));
	    	cal.set(Calendar.SECOND, Integer.parseInt(matcher.group(3)));
	    }
		
		Time time = new Time(cal.getTimeInMillis());
	
		return time;
	}
}
