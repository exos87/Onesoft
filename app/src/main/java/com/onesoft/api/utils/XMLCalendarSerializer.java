package com.onesoft.api.utils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * JSON Serializer pre upravu formatu XMLCalendar pre pozadovany String format v requestoch
 */
public class XMLCalendarSerializer implements JsonSerializer<XMLGregorianCalendar> {

	/**
	 * Implementacia serializacie a konverzie Calendar -> String
	 * @return String
	 */
	@Override
	public JsonElement serialize(XMLGregorianCalendar calendar, Type arg1, JsonSerializationContext arg2) {
		
		SimpleDateFormat formatter = null;
		
		// ISO8601 format, napr. 2015-07-14T10:08:15Z
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		Calendar calendarGreg = calendar.toGregorianCalendar();
		// Sparsujem a skusim vratit sformatovany datum v type String
		try {
			formatter.setTimeZone(calendarGreg.getTimeZone());
			String formatted = formatter.format(calendarGreg.getTime());
			JsonElement element = new JsonPrimitive(formatted);
			return element;
		} catch (Exception e) {
			return null;
		}
	}
}
