package com.onesoft.fero.answer;

import com.onesoft.answer.to.ApiStatusInfoTO;
import com.onesoft.fero.answer.enums.AnswerStatusEnum;
import com.onesoft.fero.answer.enums.ApiStatusInfoEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstraktny predok pre odpovede na poziadavky
 * 
 * @author lovas
 *
 */
public abstract class AnswerTO implements Serializable {
	
	/** Default SerialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Stav vybavenia poziadavky */
	private AnswerStatusEnum status = AnswerStatusEnum.OK;
	
	/** Info pre status odpovede */
	private List<ApiStatusInfoTO> statusInfoList = new ArrayList<ApiStatusInfoTO>();	
	

	public boolean continueProcess() {
		boolean result = true;
		
		if (AnswerStatusEnum.OK.equals(status)) {
			// Existuju doplkove informacie k statusu OK
			if (!getStatusInfoList().isEmpty()) {
				result = false;
			}
		} else {
			// Stav odpovede nie je OK
			result = false;
		}
		
		return result;
	}
	

	public boolean continueProcess(boolean simulation) {
		boolean result = true;
		
		if (AnswerStatusEnum.OK.equals(status)) {
			// pri simulacii ignorujem status info list
			if (simulation == false) {
				// Existuju doplkove informacie k statusu OK
				if (!getStatusInfoList().isEmpty()) {
					result = false;
				}	
			}			
		} else {
			// Stav odpovede nie je OK
			result = false;
		}
		
		return result;
	}

	public void addStatusInfo(ApiStatusInfoEnum apiStatusInfo) {
		addStatusInfo(apiStatusInfo, null);
	}

	public void addStatusInfoList(List<ApiStatusInfoTO> apiStatusInfoList) {
		statusInfoList.addAll(statusInfoList);
	}
	

	public void addStatusInfo(ApiStatusInfoEnum apiStatusInfo, String message) {
	
		ApiStatusInfoTO info = new ApiStatusInfoTO(apiStatusInfo, message);

		statusInfoList.add(info);
	}

	public AnswerStatusEnum getStatus() {
		return status;
	}


	public void setStatus(AnswerStatusEnum status) {
		this.status = status;
	}



	public List<ApiStatusInfoTO> getStatusInfoList() {
		return statusInfoList;
	}	
	
	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append("Status: " + this.getStatus());
		
		if (this.statusInfoList != null && this.statusInfoList.size() > 0) {
			info.append("[");
			for (ApiStatusInfoTO statusInfo : this.statusInfoList) {
				info.append(statusInfo.getApiStatusInfo());				
			}
			info.append("]");
		}
		
		return info.toString();
	}
}
