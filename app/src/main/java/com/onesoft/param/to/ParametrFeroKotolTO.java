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

public class ParametrFeroKotolTO implements Serializable, Parcelable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;

	private int n_zia_kot; // Stav regulacie
	private Float tzine; // T ziadana
	private Float tkotv; // T merana
	private Float tvon_uk1; //T vonkajsia

	public ParametrFeroKotolTO() {

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
	@Override
	public String toString() {
		return "ParametrFeroKotolTO [n_zia_kot=" + n_zia_kot + ", tzine=" + tzine + ", tkotv=" + tkotv + ", tvon_uk1="
				+ tvon_uk1 + "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.n_zia_kot);
		dest.writeValue(this.tzine);
		dest.writeValue(this.tkotv);
		dest.writeValue(this.tvon_uk1);
	}

	protected ParametrFeroKotolTO(Parcel in) {
		this.n_zia_kot = in.readInt();
		this.tzine = (Float) in.readValue(Float.class.getClassLoader());
		this.tkotv = (Float) in.readValue(Float.class.getClassLoader());
		this.tvon_uk1 = (Float) in.readValue(Float.class.getClassLoader());
	}

	public static final Creator<ParametrFeroKotolTO> CREATOR = new Creator<ParametrFeroKotolTO>() {
		@Override
		public ParametrFeroKotolTO createFromParcel(Parcel source) {
			return new ParametrFeroKotolTO(source);
		}

		@Override
		public ParametrFeroKotolTO[] newArray(int size) {
			return new ParametrFeroKotolTO[size];
		}
	};
}