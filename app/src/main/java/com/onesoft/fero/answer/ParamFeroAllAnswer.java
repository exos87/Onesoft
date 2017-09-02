package com.onesoft.fero.answer;

import android.os.Parcel;
import android.os.Parcelable;

import com.onesoft.param.to.ParametrFeroAllTO;

import java.io.Serializable;

/**
 * Odpoved na poziadavku ziskania zakladnych parametrov uk1 
 * @author lovas
 *
 */

public class ParamFeroAllAnswer extends AnswerTO implements Serializable, Parcelable {
	
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	
	private ParametrFeroAllTO paramFeroAll;

	public ParametrFeroAllTO getParamFeroAll() {
		return this.paramFeroAll;
	}

	public void setParamFeroAll() {
		this.paramFeroAll = paramFeroAll;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(this.paramFeroAll);
	}

	public ParamFeroAllAnswer() {
	}

	protected ParamFeroAllAnswer(Parcel in) {
		this.paramFeroAll = (ParametrFeroAllTO) in.readSerializable();
	}

	public static final Creator<ParamFeroAllAnswer> CREATOR = new Creator<ParamFeroAllAnswer>() {
		@Override
		public ParamFeroAllAnswer createFromParcel(Parcel source) {
			return new ParamFeroAllAnswer(source);
		}

		@Override
		public ParamFeroAllAnswer[] newArray(int size) {
			return new ParamFeroAllAnswer[size];
		}
	};
}
