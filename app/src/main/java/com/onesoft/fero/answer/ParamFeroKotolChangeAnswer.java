package com.onesoft.fero.answer;

import com.onesoft.param.to.ParametrFeroKotolChangeTO;

import java.io.Serializable;


/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */
public class ParamFeroKotolChangeAnswer extends AnswerTO implements Serializable {
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	private ParametrFeroKotolChangeTO paramKotolChange;

	public ParametrFeroKotolChangeTO getParamKotolChange() {
		return paramKotolChange;
	}

	public void setParamKotolChange(ParametrFeroKotolChangeTO paramKotolChange) {
		this.paramKotolChange = paramKotolChange;
	}

	@Override
	public String toString() {
		return "ParamFeroKotolChangeAnswer{" +
				"paramKotolChange=" + paramKotolChange +
				'}';
	}
}
