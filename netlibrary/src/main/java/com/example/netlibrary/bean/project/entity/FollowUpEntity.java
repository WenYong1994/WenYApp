package com.example.netlibrary.bean.project.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.bean.IFlowDynamicItem;
import com.example.commonlibrary.constant.EnumConstant;

public class FollowUpEntity extends BaseFollowUpEntity implements IFlowDynamicItem,Parcelable{

    private EnumConstant.FollowType followType = EnumConstant.FollowType.visit;//动态类型

    public EnumConstant.FollowType getFollowType() {
        return followType;
    }

    public void setFollowType(EnumConstant.FollowType followType) {
        this.followType = followType;
    }



    @Override
    public String getAbsTitle() {
        return "新建跟进";
    }

    @Override
    public String getAbsHanlder() {
        return "操作人："+getNickName();
    }

    @Override
    public String getAbsContent() {
        return "跟进内容："+getContent();
    }

    @Override
    public long getAbsTime() {
        return getCreateAt();
    }

    public FollowUpEntity() {
    }

    protected FollowUpEntity(Parcel in) {
        super(in);
    }

    public static final Creator<FollowUpEntity> CREATOR = new Creator<FollowUpEntity>() {
        @Override
        public FollowUpEntity createFromParcel(Parcel source) {
            return new FollowUpEntity(source);
        }

        @Override
        public FollowUpEntity[] newArray(int size) {
            return new FollowUpEntity[size];
        }
    };
}
