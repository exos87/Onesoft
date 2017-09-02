package com.onesoft.param.to;

import java.io.Serializable;

/**
 * Parametre Fero kotol zmena
 * 
 * @author lovas
 *
 */
public class ParametrFeroKotolChangeTO implements Serializable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Integer chod_reg_ine;
	private Integer n_zia_kot;// Zmena kotol
	private Integer tz_ine;// Ziadana teplota

	public Integer getChod_reg_ine() {
		return chod_reg_ine;
	}

	public void setChod_reg_ine(Integer chod_reg_ine) {
		this.chod_reg_ine = chod_reg_ine;
	}

	public Integer getN_zia_kot() {
		return n_zia_kot;
	}

	public void setN_zia_kot(Integer n_zia_kot) {
		this.n_zia_kot = n_zia_kot;
	}

	public Integer getTz_ine() {
		return tz_ine;
	}

	public void setTz_ine(Integer tz_ine) {
		this.tz_ine = tz_ine;
	}

	@Override
	public String toString() {
		return "ParametrFeroKotolChangeTO{" +
				"chod_reg_ine=" + chod_reg_ine +
				", n_zia_kot=" + n_zia_kot +
				", tz_ine=" + tz_ine +
				'}';
	}
}
