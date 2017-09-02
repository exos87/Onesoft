package com.onesoft.api.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * JSON Deserializer pre zmapovanie poli XMLGregorianCalendar implementuje metodu deserialize
 * 
 */
public class XMLCalendarDeserializer implements JsonDeserializer<XMLGregorianCalendar> {

	/**
	 * Implementacia deserializacie a konverzie String -> Calendar
	 * 
	 * @return Calendar
	 */
	@Override
	public XMLGregorianCalendar deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		
		String input = element.getAsString();
		
		List<SimpleDateFormat> formatterList = new ArrayList<SimpleDateFormat>();
		
		// ISO8601 format, napr. 2015-07-14T10:08:15Z
		formatterList.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"));		
		formatterList.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
		
		// Sparsujem a skusim vratit Calendar objekt, ak bude pasovat aspon na jeden format
		while (formatterList.size() > 0) {
			try {
				Date formattedDate = formatterList.remove(0).parse(input);
				
				GregorianCalendar cal = new GregorianCalendar();
				XMLGregorianCalendar xmlDate = null;
				if (formattedDate != null) {
					cal.setTime(formattedDate);

					try {
						xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}
				}
				
				return xmlDate;
			} catch (ParseException e) {
				continue;
			}
		}
		
		return null;
	}
}
