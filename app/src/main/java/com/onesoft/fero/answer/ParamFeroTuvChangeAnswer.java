package com.onesoft.fero.answer;

import com.onesoft.param.to.ParametrFeroTuvChangeTO;

import java.io.Serializable;


/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */
public class ParamFeroTuvChangeAnswer extends AnswerTO implements Serializable {
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	private ParametrFeroTuvChangeTO paramTuvChange;

	public ParametrFeroTuvChangeTO getParamTuvChange() {
		return paramTuvChange;
	}

	public void setParamTuvChange(ParametrFeroTuvChangeTO paramTuvChange) {
		this.paramTuvChange = paramTuvChange;
	}

	@Override
	public String toString() {
		return "ParamFeroTuvChangeAnswer{" +
				"paramTuvChange=" + paramTuvChange +
				'}';
	}
}
