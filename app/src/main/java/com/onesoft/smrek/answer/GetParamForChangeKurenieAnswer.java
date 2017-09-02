package com.onesoft.smrek.answer;

import com.onesoft.fero.answer.AnswerTO;
import com.onesoft.param.smrek.to.KurenieSmrekZmenaTO;

import java.io.Serializable;
import java.util.List;




/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */

public class GetParamForChangeKurenieAnswer extends AnswerTO implements Serializable {
	
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	
	private List<KurenieSmrekZmenaTO> paramForChangeList;
	
	/** Default konstruktor */
	public GetParamForChangeKurenieAnswer() {
		super();
	}

	public List<KurenieSmrekZmenaTO> getParamForChangeList() {
		return paramForChangeList;
	}

	public void setParamForChangeList(List<KurenieSmrekZmenaTO> paramForChangeList) {
		this.paramForChangeList = paramForChangeList;
	}

	@Override
	public String toString() {
		return "GetParamForChangeKurenieAnswer [paramForChangeList=" + paramForChangeList + "]";
	}

	
}
