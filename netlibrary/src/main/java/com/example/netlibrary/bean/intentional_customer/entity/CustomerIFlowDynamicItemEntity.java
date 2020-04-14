package com.example.netlibrary.bean.intentional_customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.bean.IFlowDynamicItem;
import com.example.commonlibrary.constant.EnumConstant;

public class CustomerIFlowDynamicItemEntity implements IFlowDynamicItem, Parcelable {
    private String id;
    private long time;
    private String title;
    private String content;
    private String username;
    private String nickName;
    private long createAt;
    private String connectId;//关联id

    public String getConnectId() {
        return connectId;
    }

    public void setConnectId(String connectId) {
        this.connectId = connectId;
    }

    private EnumConstant.FollowType followType = EnumConstant.FollowType.visit;//动态类型

    public EnumConstant.FollowType getFollowType() {
        return followType;
    }

    public void setFollowType(EnumConstant.FollowType followType) {
        this.followType = followType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getAbsTitle() {
        return title;
    }

    @Override
    public String getAbsContent() {
        return content;
    }

    @Override
    public String getAbsHanlder() {
        return nickName;
    }

    @Override
    public long getAbsTime() {
        return createAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(this.time);
        dest.writeLong(this.createAt);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.username);
    }

    public CustomerIFlowDynamicItemEntity() {
    }

    protected CustomerIFlowDynamicItemEntity(Parcel in) {
        this.id = in.readString();
        this.time = in.readLong();
        this.createAt = in.readLong();
        this.title = in.readString();
        this.content = in.readString();
        this.username = in.readString();
    }

    public static final Creator<CustomerIFlowDynamicItemEntity> CREATOR = new Creator<CustomerIFlowDynamicItemEntity>() {
        @Override
        public CustomerIFlowDynamicItemEntity createFromParcel(Parcel source) {
            return new CustomerIFlowDynamicItemEntity(source);
        }

        @Override
        public CustomerIFlowDynamicItemEntity[] newArray(int size) {
            return new CustomerIFlowDynamicItemEntity[size];
        }
    };
}
