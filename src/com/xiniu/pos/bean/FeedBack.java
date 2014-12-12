package com.xiniu.pos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  意见反馈 实体 
 * @author Admin
 *
 */
public class FeedBack extends Entity implements Parcelable{
	
	private int id;
	private String author;
	private String authorCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String content;
	private String operationTime;

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	
	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.author);
        dest.writeString(this.authorCode);
        dest.writeString(this.content);
        dest.writeString(this.operationTime);
    }

    public FeedBack() {
    }

    private FeedBack(Parcel in) {
        this.id = in.readInt();
        this.author = in.readString();
        this.authorCode = in.readString();
        this.content = in.readString();
        this.operationTime = in.readString();
    }

    public static Creator<FeedBack> CREATOR = new Creator<FeedBack>() {
        public FeedBack createFromParcel(Parcel source) {
            return new FeedBack(source);
        }

        public FeedBack[] newArray(int size) {
            return new FeedBack[size];
        }
    };
	
	
	
	

}
