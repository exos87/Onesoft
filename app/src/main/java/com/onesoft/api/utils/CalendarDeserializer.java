package com.onesoft.api.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * JSON Deserializer pre zmapovanie poli Calendar implementuje metodu deserialize
 * 
 * @author ondrist
 *
 */
public class CalendarDeserializer implements JsonDeserializer<Calendar> {

	/**
	 * Implementacia deserializacie a konverzie String -> Calendar
	 * 
	 * @return Calendar
	 */
	@Override
	public Calendar deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		
		String input = element.getAsString();
		
		List<SimpleDateFormat> formatterList = new ArrayList<SimpleDateFormat>();
		
		// ISO8601 format, napr. 2015-07-14T10:08:15Z
		formatterList.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"));		
		formatterList.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
		
		// Sparsujem a skusim vratit Calendar objekt, ak bude pasovat aspon na jeden format
		while (formatterList.size() > 0) {
			try {
				Date formattedDate = formatterList.remove(0).parse(input);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(formattedDate);
				return calendar;
			} catch (ParseException e) {
				continue;
			}
		}
		
		return null;
	}
}
