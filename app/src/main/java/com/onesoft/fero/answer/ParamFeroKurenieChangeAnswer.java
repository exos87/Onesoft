package com.onesoft.fero.answer;

import com.onesoft.param.to.ParametrFeroKurenieChangeTO;

import java.io.Serializable;


/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */
public class ParamFeroKurenieChangeAnswer extends AnswerTO implements Serializable {
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	private ParametrFeroKurenieChangeTO changeKurenie;
	/** Default konstruktor */
	public ParamFeroKurenieChangeAnswer() {
		super();
	}
	public ParametrFeroKurenieChangeTO getChangeKurenie() {
		return changeKurenie;
	}
	public void setChangeKurenie(ParametrFeroKurenieChangeTO changeKurenie) {
		this.changeKurenie = changeKurenie;
	}
	@Override
	public String toString() {
		return "ParamFeroKurenieChangeAnswer [changeKurenie=" + changeKurenie.toString() + "]";
	}
}
