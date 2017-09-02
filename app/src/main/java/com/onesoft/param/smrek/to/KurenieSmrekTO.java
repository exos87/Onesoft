package com.onesoft.param.smrek.to;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Parametre pre apartmany base
 * 
 * @author lovas
 *
 */

public class KurenieSmrekTO implements Serializable, Parcelable {

	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	ApiOsadaKurenieSqlStatusInfoEnum status;
	
	Integer chod_reg_uk1;
	Integer cprog_uk1;
	Integer tzmi_ctz_uk1;
	
	Integer t_vonk_disp;
	Float tuk1k;
	Float tuk1r;
	
	Float tuk1v;
	Float tzuk1;
	Integer cisloApp;
	Integer ctz_uk1;

	public Integer getCtz_uk1() {
		return ctz_uk1;
	}

	public void setCtz_uk1(Integer ctz_uk1) {
		this.ctz_uk1 = ctz_uk1;
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
	public Integer getTzmi_ctz_uk1() {
		return tzmi_ctz_uk1;
	}
	public void setTzmi_ctz_uk1(Integer tzmi_ctz_uk1) {
		this.tzmi_ctz_uk1 = tzmi_ctz_uk1;
	}
	public Integer getT_vonk_disp() {
		return t_vonk_disp;
	}
	public void setT_vonk_disp(Integer t_vonk_disp) {
		this.t_vonk_disp = t_vonk_disp;
	}
	public Float getTuk1k() {
		return tuk1k;
	}
	public void setTuk1k(Float tuk1k) {
		this.tuk1k = tuk1k;
	}
	public Float getTuk1r() {
		return tuk1r;
	}
	public void setTuk1r(Float tuk1r) {
		this.tuk1r = tuk1r;
	}
	public Float getTuk1v() {
		return tuk1v;
	}
	public void setTuk1v(Float tuk1v) {
		this.tuk1v = tuk1v;
	}
	public Float getTzuk1() {
		return tzuk1;
	}
	public void setTzuk1(Float tzuk1) {
		this.tzuk1 = tzuk1;
	}

	public Integer getCisloApp() {
		return cisloApp;
	}
	public void setCisloApp(Integer cisloApp) {
		this.cisloApp = cisloApp;
	}
	public KurenieSmrekTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "KurenieSmrekTO{" +
				"status=" + status +
				", chod_reg_uk1=" + chod_reg_uk1 +
				", cprog_uk1=" + cprog_uk1 +
				", tzmi_ctz_uk1=" + tzmi_ctz_uk1 +
				", t_vonk_disp=" + t_vonk_disp +
				", tuk1k=" + tuk1k +
				", tuk1r=" + tuk1r +
				", tuk1v=" + tuk1v +
				", tzuk1=" + tzuk1 +
				", cisloApp=" + cisloApp +
				", ctz_uk1=" + ctz_uk1 +
				'}';
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.status == null ? -1 : this.status.ordinal());
		dest.writeValue(this.chod_reg_uk1);
		dest.writeValue(this.cprog_uk1);
		dest.writeValue(this.tzmi_ctz_uk1);
		dest.writeValue(this.t_vonk_disp);
		dest.writeValue(this.tuk1k);
		dest.writeValue(this.tuk1r);
		dest.writeValue(this.tuk1v);
		dest.writeValue(this.tzuk1);
		dest.writeValue(this.cisloApp);
		dest.writeValue(this.ctz_uk1);
	}

	protected KurenieSmrekTO(Parcel in) {
		int tmpStatus = in.readInt();
		this.status = tmpStatus == -1 ? null : ApiOsadaKurenieSqlStatusInfoEnum.values()[tmpStatus];
		this.chod_reg_uk1 = (Integer) in.readValue(Integer.class.getClassLoader());
		this.cprog_uk1 = (Integer) in.readValue(Integer.class.getClassLoader());
		this.tzmi_ctz_uk1 = (Integer) in.readValue(Integer.class.getClassLoader());
		this.t_vonk_disp = (Integer) in.readValue(Integer.class.getClassLoader());
		this.tuk1k = (Float) in.readValue(Float.class.getClassLoader());
		this.tuk1r = (Float) in.readValue(Float.class.getClassLoader());
		this.tuk1v = (Float) in.readValue(Float.class.getClassLoader());
		this.tzuk1 = (Float) in.readValue(Float.class.getClassLoader());
		this.cisloApp = (Integer) in.readValue(Integer.class.getClassLoader());
		this.ctz_uk1 = (Integer) in.readValue(Integer.class.getClassLoader());
	}

	public static final Creator<KurenieSmrekTO> CREATOR = new Creator<KurenieSmrekTO>() {
		@Override
		public KurenieSmrekTO createFromParcel(Parcel source) {
			return new KurenieSmrekTO(source);
		}

		@Override
		public KurenieSmrekTO[] newArray(int size) {
			return new KurenieSmrekTO[size];
		}
	};
}
