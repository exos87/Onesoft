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

public class ParametrFeroVodaTO implements Serializable, Parcelable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	private int chod_reg_tuv; //Stav regulacie
	private int tzmi_ctz_tuv; // T ziadana
	private Float ttuv_1; // T merana
	private int cprog_tuv; // Cislo casoveho programu
	private int ctz_tuv; // Teplotna hladina

	public ParametrFeroVodaTO() {

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
		return "ParametrFeroVodaTO{" +
				"chod_reg_tuv=" + chod_reg_tuv +
				", tzmi_ctz_tuv=" + tzmi_ctz_tuv +
				", ttuv_1=" + ttuv_1 +
				", cprog_tuv=" + cprog_tuv +
				", ctz_tuv=" + ctz_tuv +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.chod_reg_tuv);
		dest.writeInt(this.tzmi_ctz_tuv);
		dest.writeValue(this.ttuv_1);
		dest.writeInt(this.cprog_tuv);
		dest.writeInt(this.ctz_tuv);
	}

	protected ParametrFeroVodaTO(Parcel in) {
		this.chod_reg_tuv = in.readInt();
		this.tzmi_ctz_tuv = in.readInt();
		this.ttuv_1 = (Float) in.readValue(Float.class.getClassLoader());
		this.cprog_tuv = in.readInt();
		this.ctz_tuv = in.readInt();
	}

	public static final Creator<ParametrFeroVodaTO> CREATOR = new Creator<ParametrFeroVodaTO>() {
		@Override
		public ParametrFeroVodaTO createFromParcel(Parcel source) {
			return new ParametrFeroVodaTO(source);
		}

		@Override
		public ParametrFeroVodaTO[] newArray(int size) {
			return new ParametrFeroVodaTO[size];
		}
	};
}