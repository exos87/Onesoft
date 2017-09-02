package com.onesoft.param.to;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Parametre Fero kotol
 * 
 * @author lovas
 *
 */

public class ParametrFeroKurenieTO implements Serializable , Parcelable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	private int chod_reg_uk1; //Stav regulacie
	private int tzmi_ctz_uk1; // T-ziadana priestoru
	private Float tref_uk1; // T merana priestoru
	private Float tuk1v; // T vystup UK
	private int cprog_uk1; // cislo casoveho programu
	private int ctz_uk1; //cislo teplotnaj hladiny

	public ParametrFeroKurenieTO() {

	}

	public int getCtz_uk1() {
		return ctz_uk1;
	}

	public void setCtz_uk1(int ctz_uk1) {
		this.ctz_uk1 = ctz_uk1;
	}

	public int getChod_reg_uk1() {
		return chod_reg_uk1;
	}
	public void setChod_reg_uk1(int chod_reg_uk1) {
		this.chod_reg_uk1 = chod_reg_uk1;
	}
	public int getTzmi_ctz_uk1() {
		return tzmi_ctz_uk1;
	}
	public void setTzmi_ctz_uk1(int tzmi_ctz_uk1) {
		this.tzmi_ctz_uk1 = tzmi_ctz_uk1;
	}
	public Float getTref_uk1() {
		return tref_uk1;
	}
	public void setTref_uk1(Float tref_uk1) {
		this.tref_uk1 = tref_uk1;
	}
	public Float getTuk1v() {
		return tuk1v;
	}
	public void setTuk1v(Float tuk1v) {
		this.tuk1v = tuk1v;
	}
	public int getCprog_uk1() {
		return cprog_uk1;
	}
	public void setCprog_uk1(int cprog_uk1) {
		this.cprog_uk1 = cprog_uk1;
	}
	@Override
	public String toString() {
		return "ParametrFeroKurenieTO [chod_reg_uk1=" + chod_reg_uk1 + ", tzmi_ctz_uk1=" + tzmi_ctz_uk1 + ", tref_uk1="
				+ tref_uk1 + ", tuk1v=" + tuk1v + ", cprog_uk1=" + cprog_uk1 + ", ctz_uk1=" + ctz_uk1 + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.chod_reg_uk1);
		dest.writeInt(this.tzmi_ctz_uk1);
		dest.writeValue(this.tref_uk1);
		dest.writeValue(this.tuk1v);
		dest.writeInt(this.cprog_uk1);
		dest.writeInt(this.ctz_uk1);
	}

	protected ParametrFeroKurenieTO(Parcel in) {
		this.chod_reg_uk1 = in.readInt();
		this.tzmi_ctz_uk1 = in.readInt();
		this.tref_uk1 = (Float) in.readValue(Float.class.getClassLoader());
		this.tuk1v = (Float) in.readValue(Float.class.getClassLoader());
		this.cprog_uk1 = in.readInt();
		this.ctz_uk1 = in.readInt();
	}

	public static final Creator<ParametrFeroKurenieTO> CREATOR = new Creator<ParametrFeroKurenieTO>() {
		@Override
		public ParametrFeroKurenieTO createFromParcel(Parcel source) {
			return new ParametrFeroKurenieTO(source);
		}

		@Override
		public ParametrFeroKurenieTO[] newArray(int size) {
			return new ParametrFeroKurenieTO[size];
		}
	};
}
