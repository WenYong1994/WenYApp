package com.example.netlibrary.bean.project.req;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.example.netlibrary.bean.project.entity.BaseFollowUpEntity;

public class ReqCreateFollowUp extends BaseFollowUpEntity {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public ReqCreateFollowUp() {
    }

    protected ReqCreateFollowUp(Parcel in) {
        super(in);
    }

    public static final Creator<ReqCreateFollowUp> CREATOR = new Creator<ReqCreateFollowUp>() {
        @Override
        public ReqCreateFollowUp createFromParcel(Parcel source) {
            return new ReqCreateFollowUp(source);
        }

        @Override
        public ReqCreateFollowUp[] newArray(int size) {
            return new ReqCreateFollowUp[size];
        }
    };
}
