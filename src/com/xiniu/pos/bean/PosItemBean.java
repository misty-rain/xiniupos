package com.xiniu.pos.bean;

import java.math.BigDecimal;

import android.os.Parcel;
import android.os.Parcelable;

public class PosItemBean extends Entity implements Parcelable {
	private String id;
	private String name;
	private String picture_url;
	private double unit_price;
	private String is_active;
	private String specification;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Override
	    public int describeContents() {
	        return 0;
	    }

	    @Override
	    public void writeToParcel(Parcel dest, int flags) {
	        dest.writeString(this.id);
	        dest.writeString(this.name);
	        dest.writeString(this.picture_url);
	        dest.writeDouble(this.unit_price);
	        dest.writeString(this.is_active);
	        dest.writeString(this.specification);
	    }

	    public PosItemBean() {
	    }

	    private PosItemBean(Parcel in) {
	        this.id = in.readString();
	        this.name = in.readString();
	        this.picture_url = in.readString();
	        this.unit_price = in.readDouble();
	        this.is_active = in.readString();
	        this.specification = in.readString();
	    }

	    public static final Parcelable.Creator<PosItemBean> CREATOR = new Parcelable.Creator<PosItemBean>() {
	        public PosItemBean createFromParcel(Parcel source) {
	            return new PosItemBean(source);
	        }

	        public PosItemBean[] newArray(int size) {
	            return new PosItemBean[size];
	        }
	    };
	
	
	

}
