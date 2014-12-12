package com.xiniu.pos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
* @ClassName: Entity 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author misty-rain 
* @date 2014-11-28 下午5:29:28 
*
 */
public class Entity implements Parcelable {
	private String isError;
	private String message;
	private String code;
	private String row_version;
	private String is_deleted;
	private String created_by;
	private String creation_time;
	private String last_updated_by;
	private String last_update_time;

	public String getRow_version() {
		return row_version;
	}

	public void setRow_version(String row_version) {
		this.row_version = row_version;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public String getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public String getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}

	public String getIsError() {
		return isError;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(isError);
		dest.writeString(message);
		dest.writeString(code);
		dest.writeString(row_version);
		dest.writeString(is_deleted);
		dest.writeString(created_by);
		dest.writeString(creation_time);
		dest.writeString(last_updated_by);
		dest.writeString(last_update_time);

	}

	public static final Parcelable.Creator<Entity> CREATOR = new Creator<Entity>() {

		@Override
		public Entity[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Entity[size];
		}

		@Override
		public Entity createFromParcel(Parcel source) {
			Entity entity = new Entity();
			entity.isError = source.readString();
			entity.message = source.readString();
			entity.code=source.readString();
			entity.row_version=source.readString();
			entity.is_deleted=source.readString();
			entity.created_by=source.readString();
			entity.creation_time=source.readString();
			entity.last_updated_by=source.readString();
			entity.last_update_time=source.readString();
			return entity;
		}
	};

}
