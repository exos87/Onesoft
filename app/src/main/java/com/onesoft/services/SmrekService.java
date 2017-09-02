package com.onesoft.services;

import com.google.gson.GsonBuilder;
import com.onesoft.api.utils.CalendarDeserializer;
import com.onesoft.api.utils.TimeDeserializer;
import com.onesoft.fero.answer.ParamFeroAllAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKotolAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKurenieAnswer;
import com.onesoft.fero.answer.ParamFeroChangeTuvAnswer;
import com.onesoft.fero.answer.ParamFeroKotolChangeAnswer;
import com.onesoft.fero.answer.ParamFeroKurenieChangeAnswer;
import com.onesoft.fero.answer.ParamFeroTuvChangeAnswer;
import com.onesoft.param.to.ParametrFeroKotolChangeTO;
import com.onesoft.param.to.ParametrFeroKurenieChangeTO;
import com.onesoft.param.to.ParametrFeroTuvChangeTO;
import com.onesoft.smrek.answer.GetKurenieAnswer;

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

public class SmrekService extends AbstractRestGW implements ISmrekService {

	/** Logger */
    static final Logger LOGGER = LoggerFactory.getLogger(SmrekService.class);
	
    /** Endpoint */
    private String endpoint; 
    
    public SmrekService() {
    	super();

		endpoint = "http://212.5.197.89:52120/ext-gw-smrek/smrek/";
    	//endpoint ="http://192.168.100.3:7001/ext-gw-smrek/smrek/";// getProperties().getProperty("endpointSalesService");
    }
    


	@Override
	public ParamFeroAllAnswer allFeroParam(Integer limit, Integer change) {
		ParamFeroAllAnswer answer = new ParamFeroAllAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("limit", String.valueOf(limit)));
			restParams.add(new BasicNameValuePair("change", String.valueOf(change)));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");

			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "baseParamFeroAll");
			restUrl.append("?");
			restUrl.append(queryString);

			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);

			log(LOGGER, endpoint, "baseParamFeroAll", queryString, responseBody, false);

			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());

			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroAllAnswer.class);

		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}

		return answer;
	}
	@Override
	public ParamFeroKurenieChangeAnswer getKurenieChange(Integer limit, Integer change) {
		ParamFeroKurenieChangeAnswer answer = new ParamFeroKurenieChangeAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("limit", String.valueOf(limit)));
			restParams.add(new BasicNameValuePair("change", String.valueOf(change)));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");
			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "FeroParamKurenieChange");
			restUrl.append("?");
			restUrl.append(queryString);
			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);
			log(LOGGER, endpoint, "FeroParamKurenieChange", queryString, responseBody, false);
			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroKurenieChangeAnswer.class);
		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}
		return answer;
	}
	@Override
	public ParamFeroKotolChangeAnswer getKotolChange(Integer limit, Integer change) {
		ParamFeroKotolChangeAnswer answer = new ParamFeroKotolChangeAnswer();
		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("limit", String.valueOf(limit)));
			restParams.add(new BasicNameValuePair("change", String.valueOf(change)));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");
			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "feroParamKotolChange");
			restUrl.append("?");
			restUrl.append(queryString);
			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);
			log(LOGGER, endpoint, "feroParamKotolChange", queryString, responseBody, false);
			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroKotolChangeAnswer.class);
		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}
		return answer;
	}
	@Override
	public ParamFeroChangeKurenieAnswer insertChangeKurenie(ParametrFeroKurenieChangeTO request) {
		ParamFeroChangeKurenieAnswer answer = new ParamFeroChangeKurenieAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("chod_reg_uk1", String.valueOf(request.getChod_reg_uk1())));
			restParams.add(new BasicNameValuePair("cprog_uk1", String.valueOf(request.getCprog_uk1())));
			restParams.add(new BasicNameValuePair("tm15_uk1", String.valueOf(request.getTm15_uk1())));
			restParams.add(new BasicNameValuePair("n_tvon_uk1", String.valueOf(request.getN_tvon_uk1())));
			restParams.add(new BasicNameValuePair("tzmi_1", String.valueOf(request.getTzmi_1())));
			restParams.add(new BasicNameValuePair("tzmi_2", String.valueOf(request.getTzmi_2())));
			restParams.add(new BasicNameValuePair("tzmi_3", String.valueOf(request.getTzmi_3())));
			restParams.add(new BasicNameValuePair("tzmi_4", String.valueOf(request.getTzmi_4())));
			restParams.add(new BasicNameValuePair("tzmi_0", String.valueOf(request.getTzmi_0())));
			restParams.add(new BasicNameValuePair("tzmi_9", String.valueOf(request.getTzmi_9())));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");

			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "feroParamChangeKurenie");
			restUrl.append("?");
			restUrl.append(queryString);

			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);

			log(LOGGER, endpoint, "feroParamChangeKurenie", queryString, responseBody, false);

			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());

			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroChangeKurenieAnswer.class);

		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}

		return answer;
	}
	@Override
	public ParamFeroChangeKotolAnswer insertChangeKotol(ParametrFeroKotolChangeTO request) {
		ParamFeroChangeKotolAnswer answer = new ParamFeroChangeKotolAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("chod_reg_ine", String.valueOf(request.getChod_reg_ine())));
			restParams.add(new BasicNameValuePair("n_zia_kot", String.valueOf(request.getN_zia_kot())));
			restParams.add(new BasicNameValuePair("tz_ine", String.valueOf(request.getTz_ine())));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");
			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "FeroParamChangeKotollRS");
			restUrl.append("?");
			restUrl.append(queryString);
			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);
			log(LOGGER, endpoint, "feroParamChangeKurenie", queryString, responseBody, false);
			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());

			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroChangeKotolAnswer.class);

		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}

		return answer;
	}
	@Override
	public ParamFeroTuvChangeAnswer getTuvChange(Integer limit, Integer change) {
		ParamFeroTuvChangeAnswer answer = new ParamFeroTuvChangeAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("limit", String.valueOf(limit)));
			restParams.add(new BasicNameValuePair("change", String.valueOf(change)));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");
			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "FeroParamTuvChangeRS");
			restUrl.append("?");
			restUrl.append(queryString);
			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);
			log(LOGGER, endpoint, "FeroParamTuvChangeRS", queryString, responseBody, false);
			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroTuvChangeAnswer.class);
		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}
		return answer;
	}
	@Override
	public ParamFeroChangeTuvAnswer insertChangeKurenie(ParametrFeroTuvChangeTO request) {
		ParamFeroChangeTuvAnswer answer = new ParamFeroChangeTuvAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("chod_reg_tuv", String.valueOf(request.getChod_reg_tuv())));
			restParams.add(new BasicNameValuePair("cprog_tuv", String.valueOf(request.getCprog_tuv())));
			restParams.add(new BasicNameValuePair("tzmi_5", String.valueOf(request.getTzmi_5())));
			restParams.add(new BasicNameValuePair("tzmi_6", String.valueOf(request.getTzmi_6())));
			restParams.add(new BasicNameValuePair("tzmi_7", String.valueOf(request.getTzmi_7())));
			restParams.add(new BasicNameValuePair("tzmi_8", String.valueOf(request.getTzmi_8())));
			String queryString = URLEncodedUtils.format(restParams, "utf-8");

			// Pripravim si REST volanie
			StringBuilder restUrl = new StringBuilder(endpoint + "FeroParamChangeTuvlRS");
			restUrl.append("?");
			restUrl.append(queryString);

			// Spracujem a namapujem uspesny response
			String responseBody = executeGetRequest(restUrl, true);

			log(LOGGER, endpoint, "feroParamChangeTuv", queryString, responseBody, false);

			// Pre parsovanie casu/datumu je potrebna inicializacia GsonBuildra a prislusnych adapterov pre datove typy (String->Calendar a String->Time)
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());

			answer = gsonBuilder.create().fromJson(responseBody, ParamFeroChangeTuvAnswer.class);

		} catch (Exception ex) {
			LOGGER.error("Chyba pri ziskavani parametra!", ex);
		}

		return answer;
	}

	@Override
	public GetKurenieAnswer getSmrekApratmanKurenieParam(Integer request) {
		GetKurenieAnswer answer = new GetKurenieAnswer();

		try {
			// Pripravim si parametre
			List<NameValuePair> restParams = new LinkedList<NameValuePair>();
			restParams.add(new BasicNameValuePair("cisloApartmanu", String.valueOf(request)));
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
}
