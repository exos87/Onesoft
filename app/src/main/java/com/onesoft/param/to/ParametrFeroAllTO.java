package com.onesoft.param.to;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
/**
 * Parametre Fero base
 *
 * @author lovas
 *
 */

public class ParametrFeroAllTO implements Serializable, Parcelable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	public ParametrFeroAllTO(){
		feroKotol = new ParametrFeroKotolTO();
		feroKurenie = new ParametrFeroKurenieTO();
		feroVoda = new ParametrFeroVodaTO();
	}
	private ParametrFeroKotolTO feroKotol;
	private ParametrFeroKurenieTO feroKurenie;
	private ParametrFeroVodaTO feroVoda;
	// Kotol
	private int n_zia_kot; // Stav regulacie
	private Float tzine; // T ziadana
	private Float tkotv; // T merana
	private Float tvon_uk1; //T vonkajsia
	// Kurenie
	private int ctz_uk1; //cislo teplotnaj hladiny
	private int chod_reg_uk1; //Stav regulacie
	private int tzmi_ctz_uk1; // T-ziadana priestoru
	private Float tref_uk1; // T merana priestoru
	private Float tuk1v; // T vystup UK
	private int cprog_uk1; // cislo casoveho programu
	// Voda
	private int chod_reg_tuv; //Stav regulacie
	private int tzmi_ctz_tuv; // T ziadana
	private Float ttuv_1; // T merana
	private int cprog_tuv; // Cislo casoveho programu
	private int ctz_tuv; // Teplotna hladina

	public void setAll(){
		this.feroKotol.setN_zia_kot(getN_zia_kot());
		this.feroKotol.setTzine(getTzine());
		this.feroKotol.setTkotv(getTkotv());
		this.feroKotol.setTvon_uk1(getTvon_uk1());
		this.feroKurenie.setChod_reg_uk1(getChod_reg_uk1());
		this.feroKurenie.setTzmi_ctz_uk1(getCtz_uk1());
		this.feroKurenie.setTref_uk1(getTref_uk1());
		this.feroKurenie.setTuk1v(getTuk1v());
		this.feroKurenie.setCprog_uk1(getCprog_uk1());
		this.feroVoda.setChod_reg_tuv(getChod_reg_tuv());
		this.feroVoda.setTzmi_ctz_tuv(getTzmi_ctz_tuv());
		this.feroVoda.setTtuv_1(getTtuv_1());
		this.feroVoda.setCprog_tuv(getCprog_tuv());
		this.feroVoda.setCtz_tuv(getCtz_tuv());
	}

	public int getCtz_uk1() {
		return ctz_uk1;
	}

	public void setCtz_uk1(int ctz_uk1) {
		this.ctz_uk1 = ctz_uk1;
	}

	public ParametrFeroKotolTO getFeroKotol() {
		return feroKotol;
	}

	public void setFeroKotol(ParametrFeroKotolTO feroKotol) {
		this.feroKotol = feroKotol;
	}

	public ParametrFeroKurenieTO getFeroKurenie() {
		return feroKurenie;
	}

	public void setFeroKurenie(ParametrFeroKurenieTO feroKurenie) {
		this.feroKurenie = feroKurenie;
	}

	public ParametrFeroVodaTO getFeroVoda() {
		return feroVoda;
	}

	public void setFeroVoda(ParametrFeroVodaTO feroVoda) {
		this.feroVoda = feroVoda;
	}


	public int getN_zia_kot() {
		return n_zia_kot;
	}


	public void setN_zia_kot(int n_zia_kot) {
		this.n_zia_kot = n_zia_kot;
	}


	public Float getTzine() {
		return tzine;
	}


	public void setTzine(Float tzine) {
		this.tzine = tzine;
	}


	public Float getTkotv() {
		return tkotv;
	}


	public void setTkotv(Float tkotv) {
		this.tkotv = tkotv;
	}


	public Float getTvon_uk1() {
		return tvon_uk1;
	}


	public void setTvon_uk1(Float tvon_uk1) {
		this.tvon_uk1 = tvon_uk1;
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


	public int getChod_reg_tuv() {
		return chod_reg_tuv;
	}


	public void setChod_reg_tuv(int chod_reg_tuv) {
		this.chod_reg_tuv = chod_reg_tuv;
	}


	public int getTzmi_ctz_tuv() {
		return tzmi_ctz_tuv;
	}


	public void setTzmi_ctz_tuv(int tzmi_ctz_tuv) {
		this.tzmi_ctz_tuv = tzmi_ctz_tuv;
	}


	public Float getTtuv_1() {
		return ttuv_1;
	}


	public void setTtuv_1(Float ttuv_1) {
		this.ttuv_1 = ttuv_1;
	}


	public int getCprog_tuv() {
		return cprog_tuv;
	}


	public void setCprog_tuv(int cprog_tuv) {
		this.cprog_tuv = cprog_tuv;
	}

	public int getCtz_tuv() {
		return ctz_tuv;
	}

	public void setCtz_tuv(int ctz_tuv) {
		this.ctz_tuv = ctz_tuv;
	}

	@Override
	public String toString() {
		return "ParametrFeroAllTO [feroKotol=" + feroKotol + ", feroKurenie=" + feroKurenie + ", feroVoda=" + feroVoda
				+ "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.feroKotol, flags);
		dest.writeParcelable(this.feroKurenie, flags);
		dest.writeParcelable(this.feroVoda, flags);
		dest.writeInt(this.n_zia_kot);
		dest.writeValue(this.tzine);
		dest.writeValue(this.tkotv);
		dest.writeValue(this.tvon_uk1);
		dest.writeInt(this.ctz_uk1);
		dest.writeInt(this.chod_reg_uk1);
		dest.writeInt(this.tzmi_ctz_uk1);
		dest.writeValue(this.tref_uk1);
		dest.writeValue(this.tuk1v);
		dest.writeInt(this.cprog_uk1);
		dest.writeInt(this.chod_reg_tuv);
		dest.writeInt(this.tzmi_ctz_tuv);
		dest.writeValue(this.ttuv_1);
		dest.writeInt(this.cprog_tuv);
		dest.writeInt(this.ctz_tuv);
	}

	protected ParametrFeroAllTO(Parcel in) {
		this.feroKotol = in.readParcelable(ParametrFeroKotolTO.class.getClassLoader());
		this.feroKurenie = in.readParcelable(ParametrFeroKurenieTO.class.getClassLoader());
		this.feroVoda = in.readParcelable(ParametrFeroVodaTO.class.getClassLoader());
		this.n_zia_kot = in.readInt();
		this.tzine = (Float) in.readValue(Float.class.getClassLoader());
		this.tkotv = (Float) in.readValue(Float.class.getClassLoader());
		this.tvon_uk1 = (Float) in.readValue(Float.class.getClassLoader());
		this.ctz_uk1 = in.readInt();
		this.chod_reg_uk1 = in.readInt();
		this.tzmi_ctz_uk1 = in.readInt();
		this.tref_uk1 = (Float) in.readValue(Float.class.getClassLoader());
		this.tuk1v = (Float) in.readValue(Float.class.getClassLoader());
		this.cprog_uk1 = in.readInt();
		this.chod_reg_tuv = in.readInt();
		this.tzmi_ctz_tuv = in.readInt();
		this.ttuv_1 = (Float) in.readValue(Float.class.getClassLoader());
		this.cprog_tuv = in.readInt();
		this.ctz_tuv = in.readInt();
	}

	public static final Creator<ParametrFeroAllTO> CREATOR = new Creator<ParametrFeroAllTO>() {
		@Override
		public ParametrFeroAllTO createFromParcel(Parcel source) {
			return new ParametrFeroAllTO(source);
		}

		@Override
		public ParametrFeroAllTO[] newArray(int size) {
			return new ParametrFeroAllTO[size];
		}
	};
}