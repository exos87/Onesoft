package com.onesoft.fero.answer;

import com.onesoft.param.to.ParametrFeroBaseTO;

import java.io.Serializable;


/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */

public class ParamFeroAnswer extends AnswerTO implements Serializable {
	
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	
	private ParametrFeroBaseTO paramFeroBase;
	
	/** Default konstruktor */
	public ParamFeroAnswer() {
		super();
	}

	public ParametrFeroBaseTO getParamFeroBase() {
		return paramFeroBase;
	}

	public void setParamFeroBase(ParametrFeroBaseTO paramFeroBase) {
		this.paramFeroBase = paramFeroBase;
	}

	@Override
	public String toString() {
		return "ParamFeroAnswer [paramFeroBase=" + paramFeroBase + "]";
	}

	
}
