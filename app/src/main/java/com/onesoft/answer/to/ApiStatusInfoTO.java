package com.onesoft.answer.to;

import com.onesoft.fero.answer.enums.ApiStatusInfoEnum;

import java.io.Serializable;

/**
 * Obje na prenasanie doplnkovy informacii o statuse
 * 
 * @author vlk
 *
 */

public class ApiStatusInfoTO implements Serializable {

	/** Default serial number */
	private static final long serialVersionUID = 1L;
	
	private ApiStatusInfoEnum apiStatusInfo;
	
	private String message;
	
	/**
	 * Defaultny konstruktor
	 */
	public ApiStatusInfoTO() {
		super();
	}

	public ApiStatusInfoTO(ApiStatusInfoEnum apiStatusInfo, String message) {
		super();
		this.apiStatusInfo = apiStatusInfo;
		this.message = message;
	}

	/**
	 * @return the apiStatusInfo
	 */
	public ApiStatusInfoEnum getApiStatusInfo() {
		return apiStatusInfo;
	}

	/**
	 * @param apiStatusInfo the apiStatusInfo to set
	 */
	public void setApiStatusInfo(ApiStatusInfoEnum apiStatusInfo) {
		this.apiStatusInfo = apiStatusInfo;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		StringBuilder sb  =new StringBuilder();
		
		sb.append("{");
		
		sb.append("apiStatusInfo: ").append(apiStatusInfo);
		sb.append(", message: ").append(message);
		
		sb.append("}");
		
		return sb.toString();
	}
	
	
}
