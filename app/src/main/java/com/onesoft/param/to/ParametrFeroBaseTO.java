package com.onesoft.param.to;

import java.io.Serializable;


/**
 * Parametre Fero base 
 * 
 * @author lovas
 *
 */

public class ParametrFeroBaseTO  implements Serializable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id stanice (VIS) Ked nechcem aby sa zobrazila na vonka parameter pouzijem  anotaciu XmlTransient */
	/*@XmlTransient */
	
	 private Float tvon_uk1 ;

	public Float getTvon_uk1() {
		return tvon_uk1;
	}

	public void setTvon_uk1(Float tvon_uk1) {
		this.tvon_uk1 = tvon_uk1;
	}

	@Override
	public String toString() {
		return "ParametrFeroBaseTO [tvon_uk1=" + tvon_uk1 + "]";
	}
	
	
}
