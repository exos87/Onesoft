package com.onesoft.smrek.answer;


import android.os.Parcel;
import android.os.Parcelable;

import com.onesoft.fero.answer.AnswerTO;
import com.onesoft.param.smrek.to.KurenieSmrekTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Odpoved na poziadavku ziskania zakladnych parametrov kurenia 
 * @author lovas
 *
 */

public class GetKurenieAnswer extends AnswerTO implements Serializable, Parcelable {
	
	/** Default serial number */
	private static final long serialVersionUID = 1L;
	
	List<KurenieSmrekTO> kurenieSmrekList;

	public List<KurenieSmrekTO> getKurenieSmrekList() {
		return kurenieSmrekList;
	}

	public void setKurenieSmrekList(List<KurenieSmrekTO> kurenieSmrekList) {
		this.kurenieSmrekList = kurenieSmrekList;
	}

	@Override
	public String toString() {
		return "GetKurenieAnswer [kurenieSmrekList=" + kurenieSmrekList + "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(this.kurenieSmrekList);
	}

	public GetKurenieAnswer() {
	}

	protected GetKurenieAnswer(Parcel in) {
		this.kurenieSmrekList = new ArrayList<KurenieSmrekTO>();
		in.readList(this.kurenieSmrekList, KurenieSmrekTO.class.getClassLoader());
	}

	public static final Parcelable.Creator<GetKurenieAnswer> CREATOR = new Parcelable.Creator<GetKurenieAnswer>() {
		@Override
		public GetKurenieAnswer createFromParcel(Parcel source) {
			return new GetKurenieAnswer(source);
		}

		@Override
		public GetKurenieAnswer[] newArray(int size) {
			return new GetKurenieAnswer[size];
		}
	};
}
