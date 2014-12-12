package com.xiniu.pos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 临时购物单 bean
 * 
 * @ClassName: TempComCarBean
 * @Description: 临时购物单 bean
 * @author misty-rain
 * @date 2014-12-11 上午11:24:18
 * 
 */
public class TempComCarBean extends Entity implements Parcelable {
	private String id;
	private String commodName;
	private Integer singleCommodTotalCount;
	private Integer commodTotalCount;
	private double singleCommodPrice;
	private double commodTotalPrice;
	private String singleComPicUrl;
	private String operationTime;

	
	public String getSingleComPicUrl() {
		return singleComPicUrl;
	}

	public void setSingleComPicUrl(String singleComPicUrl) {
		this.singleComPicUrl = singleComPicUrl;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getCommodName() {
		return commodName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCommodName(String commodName) {
		this.commodName = commodName;
	}

	public Integer getSingleCommodTotalCount() {
		return singleCommodTotalCount;
	}

	public void setSingleCommodTotalCount(Integer singleCommodTotalCount) {
		this.singleCommodTotalCount = singleCommodTotalCount;
	}

	public Integer getCommodTotalCount() {
		return commodTotalCount;
	}

	public void setCommodTotalCount(Integer commodTotalCount) {
		this.commodTotalCount = commodTotalCount;
	}

	public double getSingleCommodPrice() {
		return singleCommodPrice;
	}

	public void setSingleCommodPrice(double singleCommodPrice) {
		this.singleCommodPrice = singleCommodPrice;
	}

	public double getCommodTotalPrice() {
		return commodTotalPrice;
	}

	public void setCommodTotalPrice(double commodTotalPrice) {
		this.commodTotalPrice = commodTotalPrice;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.commodName);
		dest.writeValue(this.singleCommodTotalCount);
		dest.writeValue(this.commodTotalCount);
		dest.writeDouble(this.singleCommodPrice);
		dest.writeDouble(this.commodTotalPrice);
		dest.writeString(this.singleComPicUrl);
		dest.writeString(this.operationTime);

	}

	public TempComCarBean() {
	}

	private TempComCarBean(Parcel in) {
		this.id = in.readString();
		this.commodName = in.readString();
		this.singleCommodTotalCount = (Integer) in.readValue(Integer.class
				.getClassLoader());
		this.commodTotalCount = (Integer) in.readValue(Integer.class
				.getClassLoader());
		this.singleCommodPrice = in.readDouble();
		this.commodTotalPrice = in.readDouble();
		this.singleComPicUrl = in.readString();
		this.operationTime=in.readString();

	}

	public static final Creator<TempComCarBean> CREATOR = new Creator<TempComCarBean>() {
		public TempComCarBean createFromParcel(Parcel source) {
			return new TempComCarBean(source);
		}

		public TempComCarBean[] newArray(int size) {
			return new TempComCarBean[size];
		}
	};

}
