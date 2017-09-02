package com.onesoft.services;

import com.google.gson.GsonBuilder;
import com.onesoft.api.utils.CalendarDeserializer;
import com.onesoft.api.utils.TimeDeserializer;
import com.onesoft.param.smrek.to.KurenieSmrekZmenaTO;
import com.onesoft.smrek.answer.GetKurenieAnswer;
import com.onesoft.smrek.answer.GetParamForChangeKurenieAnswer;
import com.onesoft.smrek.answer.ParamChangeKurenieAnswer;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


/**
 * Sluzba pre pracu s predajom
 * 
 * @author lovas
 *
 */

public class SmrekServiceApartman extends AbstractRestGW implements ISmrekServiceApartman {

	/** Logger */
    static final Logger LOGGER = LoggerFactory.getLogger(SmrekServiceApartman.class);

    /** Endpoint */
    private String endpoint;

    public SmrekServiceApartman() {
    	super();

		endpoint = "http://212.5.197.89:52120/ext-gw-osada-kurenie/osadaKurenie/";

    }

	@Override
	public GetKurenieAnswer getParamForApartmanKurenie(Integer cisloApratmanu) {
		GetKurenieAnswer answer = new GetKurenieAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("cisloApratmanu", String.valueOf(cisloApratmanu)));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");

			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "getKurenieAll");
			restUrl.append("?");
			restUrl.append(queryString);

			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);

			log(LOGGER, endpoint, "getKurenieAll", queryString, responseBody, false);

			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());

			answer = gsonBuilder.create().fromJson(responseBody, GetKurenieAnswer.class);

		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}

		return answer;
	}
	@Override
	public GetParamForChangeKurenieAnswer getKurenieChangeApartman(Integer cisloApartmanu) {
		GetParamForChangeKurenieAnswer answer = new GetParamForChangeKurenieAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("cisloApartmanu", String.valueOf(cisloApartmanu)));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");
			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "getZmenaKurenieSmrekApartmanParameters");
			restUrl.append("?");
			restUrl.append(queryString);
			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);
			log(LOGGER, endpoint, "getZmenaKurenieSmrekApartmanParameters", queryString, responseBody, false);
			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
			answer = gsonBuilder.create().fromJson(responseBody, GetParamForChangeKurenieAnswer.class);
		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}
		return answer;
	}

	@Override
	public ParamChangeKurenieAnswer insertChangeKurenie(KurenieSmrekZmenaTO request) {
		ParamChangeKurenieAnswer answer = new ParamChangeKurenieAnswer();

        try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("chod_reg_uk1", String.valueOf(request.getChod_reg_uk1())));
			restParams.add(new BasicNameValuePair("cprog_uk1", String.valueOf(request.getCprog_uk1())));
			restParams.add(new BasicNameValuePair("cisloApartmanu", String.valueOf(request.getCisloApartmanu())));

			restParams.add(new BasicNameValuePair("tzmi_1", String.valueOf(request.getTzmi_1())));
			restParams.add(new BasicNameValuePair("tzmi_2", String.valueOf(request.getTzmi_2())));
			restParams.add(new BasicNameValuePair("tzmi_3", String.valueOf(request.getTzmi_3())));
			restParams.add(new BasicNameValuePair("tzmi_4", String.valueOf(request.getTzmi_4())));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");

			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "zmenaKurenieSmrekApartman");
			restUrl.append("?");
			restUrl.append(queryString);

			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);

			log(LOGGER, endpoint, "zmenaKurenieSmrekApartman", queryString, responseBody, false);

			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());

			answer = gsonBuilder.create().fromJson(responseBody, ParamChangeKurenieAnswer.class);

		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}

		return answer;
	}
}
