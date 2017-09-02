package com.onesoft.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

//mport java.util.Base64;

public class AbstractRestGW {

	/** Logger */
    static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestGW.class);
	
    /** Basic Authentication properties pre REST komunikaciu */
	private String restBasicAuthCredentialsLogin = "158";
	private String restBasicAuthCredentialsPassword = "158158158";   
	
    private boolean logRequestWithResponse = false;
    private boolean logPrettyRequestWithResponse = true;
    
    /** Properties s nastaveniami */
    private Properties properties;
    
    public AbstractRestGW() {
    /*	Properties p = new Properties();
		try {
			InputStream res = getClass().getResourceAsStream("/api-tests.properties");
			p.load(res);
		} catch (Exception e) {
			LOGGER.error("Nemozem nacitat default.api-tests.properties: ", e);
			p = null;
		}
		
		if (p != null) {
			if (p.getProperty("custom.property.file.location")!= null && !p.getProperty("custom.property.file.location").isEmpty() ) {
				try {
					String res =(p.getProperty("custom.property.file.location"));
					FileInputStream input = new FileInputStream(res);

					p.load(input);
				} catch (Exception e) {
					LOGGER.error("Nemozem nacitat custom-api-tests.properties: ", e);
					p = null;
				}
			}




			properties = p;

			restBasicAuthCredentialsLogin = properties.getProperty("restBasicAuthCredentialsLogin", "158");
			restBasicAuthCredentialsPassword = properties.getProperty("restBasicAuthCredentialsPassword", "158158158");
		}*/
    }
    

    public void log(Logger logger, String endpoint, String name, String request, String response, boolean jsonRequest) {
    	if (logRequestWithResponse) {
    		logger.info("Request: {}{}{}{}, Response: {}", endpoint, name, jsonRequest ? "?" : " ", request, response);            
        }
        if (logPrettyRequestWithResponse) {
        	if (jsonRequest) {
        		logger.info("{}: {}{}\nREQUEST:\n{}\nRESPONSE:\n{}", name, endpoint, name, prettyJson(request), prettyJson(response));
        	} else {
        		logger.info("{}: {}{}\nREQUEST: ?{}\nRESPONSE:\n{}", name, endpoint, name, request, prettyJson(response));	
        	}
        	
        }
    }


	protected String executePostRequest(String restUrl, String jsonSerializedParameters, boolean secured) throws ClientProtocolException, IOException {
		
	    // REST volanie, prijimam strukturu typu application/json
	    HttpPost httpPost = new HttpPost(restUrl);
	    
	    StringEntity postingParams = new StringEntity(jsonSerializedParameters, "UTF-8");
	    postingParams.setContentEncoding("UTF-8");
	    postingParams.setContentType("application/json; charset=UTF-8");

	    // Ostatne parametre
	    httpPost.addHeader("accept", "application/json");
	    httpPost.addHeader("content-type", "application/json; charset=UTF-8");
	    httpPost.setEntity(postingParams);
		
	
	    // Response handler
	    ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	
	        @Override
	        public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
	            int status = response.getStatusLine().getStatusCode();
	            if (status >= 200 && status < 300) {	                    	
	                HttpEntity entity = response.getEntity();
	                return entity != null ? EntityUtils.toString(entity, String.valueOf(Consts.UTF_8)) : null;
	            } else {
	                throw new ClientProtocolException("Neocakavany response status: " + status);
	            }
	        }
	    };

		// uskutocnime http volanie, po nom uzavrieme spojenie
		String responseBody = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			responseBody = httpClient.execute(httpPost, responseHandler);
		} catch (IOException e) {
			LOGGER.error("chyba pri volani " + httpPost);
		}
	    
	    
	    return responseBody;
    }
	

	protected String executeGetRequest(StringBuilder restUrl, boolean secured) throws ClientProtocolException, IOException {

		// REST volanie, prijimam strukturu typu application/json
	    HttpGet httpGet = new HttpGet(restUrl.toString());
	    
		    // Ostatne parametre
	    httpGet.addHeader("accept", "application/json");
	    
	    // Response handler
	    ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	
	        @Override
	        public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
	            int status = response.getStatusLine().getStatusCode();
	            if (status >= 200 && status < 300) {	                    	
	                HttpEntity entity = response.getEntity();
	                return entity != null ? EntityUtils.toString(entity, String.valueOf(Consts.UTF_8)) : null;
	            } else {
	                throw new ClientProtocolException("Neocakavany response status: " + status);
	            }
	        }
	    };
	    
		// uskutocnime http volanie, po nom uzavrieme spojenie
		String responseBody = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			responseBody = httpClient.execute(httpGet, responseHandler);
		} catch (IOException e) {
			LOGGER.error("chyba pri volani " + httpGet);
		}
	    
	    return responseBody;
    }
	
	/**
	 * Metoda pre vyskladanie basic-auth credentials do base64
	 * @return String
	 */
/*	private String encodeBasicAuthenticationCredentials() {
		String basicAuthLogin = getRestBasicAuthCredentialsLogin();
		String basicAuthPassword = getRestBasicAuthCredentialsPassword();
		String base64EncodedCredentials = "Basic " + Base64.encodeToString((basicAuthLogin + ":" + basicAuthPassword).getBytes());

		return base64EncodedCredentials;
	}*/
	
	/**
	 * Konverzia datumu na string obsahujuci datum v iso formate pouzivany pre requesty
	 * @return String isoDate
	 */
	protected String formatToIsoDateString(Date date) {
		String isoSearchDate = "";
		
		// Format ktory pouzivaju requesty
		SimpleDateFormat outputDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		outputDf.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
		isoSearchDate = outputDf.format(date);
		
		return isoSearchDate;
	}
	
	/**
	 * @return the restBasicAuthCredentialsLogin
	 */
	public String getRestBasicAuthCredentialsLogin() {
		return restBasicAuthCredentialsLogin;
	}

	/**
	 * @param restBasicAuthCredentialsLogin the restBasicAuthCredentialsLogin to set
	 */
	public void setRestBasicAuthCredentialsLogin(String restBasicAuthCredentialsLogin) {
		this.restBasicAuthCredentialsLogin = restBasicAuthCredentialsLogin;
	}

	/**
	 * @return the restBasicAuthCredentialsPassword
	 */
	public String getRestBasicAuthCredentialsPassword() {
		return restBasicAuthCredentialsPassword;
	}

	/**
	 * @param restBasicAuthCredentialsPassword the restBasicAuthCredentialsPassword to set
	 */
	public void setRestBasicAuthCredentialsPassword(String restBasicAuthCredentialsPassword) {
		this.restBasicAuthCredentialsPassword = restBasicAuthCredentialsPassword;
	}
	
	/**
	 * Sformatuje Json
	 * 
	 * @param uglyJson
	 * @return
	 */
	public String prettyJson(String uglyJson) {
		if (uglyJson != null) {
			JsonParser parser = new JsonParser();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			JsonElement el = parser.parse(uglyJson);
			String prettyString = gson.toJson(el);
			
			return prettyString;
		} else {
			return null;
		}
	}		

	public boolean isLogRequestWithResponse() {
		return logRequestWithResponse;
	}

	public void setLogRequestWithResponse(boolean logRequestWithResponse) {
		this.logRequestWithResponse = logRequestWithResponse;
	}

	public boolean isLogPrettyRequestWithResponse() {
		return logPrettyRequestWithResponse;
	}

	public void setLogPrettyRequestWithResponse(boolean logPrettyRequestWithResponse) {
		this.logPrettyRequestWithResponse = logPrettyRequestWithResponse;
	}

	public Properties getProperties() {
		return properties;
	}
}
