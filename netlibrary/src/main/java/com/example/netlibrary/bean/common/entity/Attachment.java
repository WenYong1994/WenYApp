package com.example.netlibrary.bean.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Attachment implements Parcelable {

    private Integer userId;//上传人ID
    private String username;//上传人姓名
    private String name;//文件名
    private String url;//文件路径
    private long size;//文件大小(单位：byte)
    private long createAt;//附件上传时间
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeLong(this.size);
        dest.writeLong(this.createAt);
    }

    public Attachment() {
    }

    protected Attachment(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.username = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.size = in.readLong();
        this.createAt = in.readLong();
    }

    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel source) {
            return new Attachment(source);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };

}

