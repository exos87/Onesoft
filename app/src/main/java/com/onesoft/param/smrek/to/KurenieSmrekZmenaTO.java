package com.onesoft.param.smrek.to;

import java.io.Serializable;

/**
 * Parametre pre apartmany pre zmenu base
 * 
 * @author lovas
 *
 */

public class KurenieSmrekZmenaTO implements Serializable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	ApiOsadaKurenieSqlStatusInfoEnum status;
	
	Integer chod_reg_uk1;
	Integer cprog_uk1;
	Integer tzmi_1;
	Integer tzmi_2;
	Integer tzmi_3;
	Integer tzmi_4;
	Integer cisloApartmanu;
	
	
	
	public Integer getCisloApartmanu() {
		return cisloApartmanu;
	}
	public void setCisloApartmanu(Integer cisloApartmanu) {
		this.cisloApartmanu = cisloApartmanu;
	}
	public ApiOsadaKurenieSqlStatusInfoEnum getStatus() {
		return status;
	}
	public void setStatus(ApiOsadaKurenieSqlStatusInfoEnum status) {
		this.status = status;
	}
	public Integer getChod_reg_uk1() {
		return chod_reg_uk1;
	}
	public void setChod_reg_uk1(Integer chod_reg_uk1) {
		this.chod_reg_uk1 = chod_reg_uk1;
	}
	public Integer getCprog_uk1() {
		return cprog_uk1;
	}
	public void setCprog_uk1(Integer cprog_uk1) {
		this.cprog_uk1 = cprog_uk1;
	}
	public Integer getTzmi_1() {
		return tzmi_1;
	}
	public void setTzmi_1(Integer tzmi_1) {
		this.tzmi_1 = tzmi_1;
	}
	public Integer getTzmi_2() {
		return tzmi_2;
	}
	public void setTzmi_2(Integer tzmi_2) {
		this.tzmi_2 = tzmi_2;
	}
	public Integer getTzmi_3() {
		return tzmi_3;
	}
	public void setTzmi_3(Integer tzmi_3) {
		this.tzmi_3 = tzmi_3;
	}
	public Integer getTzmi_4() {
		return tzmi_4;
	}
	public void setTzmi_4(Integer tzmi_4) {
		this.tzmi_4 = tzmi_4;
	}
	
	public KurenieSmrekZmenaTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "KurenieSmrekZmenaTO [status=" + status + ", chod_reg_uk1=" + chod_reg_uk1 + ", cprog_uk1=" + cprog_uk1
				+ ", tzmi_1=" + tzmi_1 + ", tzmi_2=" + tzmi_2 + ", tzmi_3=" + tzmi_3 + ", tzmi_4=" + tzmi_4
				+ ", cisloApartmanu=" + cisloApartmanu + "]";
	}
	
}
