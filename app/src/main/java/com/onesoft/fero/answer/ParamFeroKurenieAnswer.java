package com.onesoft.fero.answer;

import com.onesoft.param.to.ParametrFeroKurenieTO;

import java.io.Serializable;

/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */
public class ParamFeroKurenieAnswer extends AnswerTO implements Serializable {
	
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	
	private ParametrFeroKurenieTO paramFerokurenie;
	
	/** Default konstruktor */
	public ParamFeroKurenieAnswer() {
		super();
	}

	public ParametrFeroKurenieTO getParamFerokotol() {
		return paramFerokurenie;
	}

	public void setParamFerokotol(ParametrFeroKurenieTO paramFerokurenie) {
		this.paramFerokurenie = paramFerokurenie;
	}

	@Override
	public String toString() {
		return "ParamFeroKotolAnswer [paramFerokotol=" + paramFerokurenie + "]";
	}

	

	
}
