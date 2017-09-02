package com.onesoft.param.to;

import java.io.Serializable;

/**
 * Parametre Fero kotol zmena
 * 
 * @author lovas
 *
 */
public class ParametrFeroTuvChangeTO implements Serializable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Integer chod_reg_tuv;
	private Integer cprog_tuv;
	private Integer tzmi_5;
	private Integer tzmi_6;
	private Integer tzmi_7;
	private Integer tzmi_8;

	public Integer getChod_reg_tuv() {
		return chod_reg_tuv;
	}

	public void setChod_reg_tuv(Integer chod_reg_tuv) {
		this.chod_reg_tuv = chod_reg_tuv;
	}

	public Integer getCprog_tuv() {
		return cprog_tuv;
	}

	public void setCprog_tuv(Integer cprog_tuv) {
		this.cprog_tuv = cprog_tuv;
	}

	public Integer getTzmi_5() {
		return tzmi_5;
	}

	public void setTzmi_5(Integer tzmi_5) {
		this.tzmi_5 = tzmi_5;
	}

	public Integer getTzmi_6() {
		return tzmi_6;
	}

	public void setTzmi_6(Integer tzmi_6) {
		this.tzmi_6 = tzmi_6;
	}

	public Integer getTzmi_7() {
		return tzmi_7;
	}

	public void setTzmi_7(Integer tzmi_7) {
		this.tzmi_7 = tzmi_7;
	}

	public Integer getTzmi_8() {
		return tzmi_8;
	}

	public void setTzmi_8(Integer tzmi_8) {
		this.tzmi_8 = tzmi_8;
	}

	@Override
	public String toString() {
		return "ParametrFeroTuvChangeTO{" +
				"chod_reg_tuv=" + chod_reg_tuv +
				", cprog_tuv=" + cprog_tuv +
				", tzmi_5=" + tzmi_5 +
				", tzmi_6=" + tzmi_6 +
				", tzmi_7=" + tzmi_7 +
				", tzmi_8=" + tzmi_8 +
				'}';
	}
}
