package com.example.netlibrary.bean.project.req;

import android.os.Parcel;

import com.example.netlibrary.bean.project.entity.BaseSupervisionStep;

public class ReqSupervisionStep extends BaseSupervisionStep {




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public ReqSupervisionStep() {
    }

    protected ReqSupervisionStep(Parcel in) {
        super(in);
    }

    public static final Creator<ReqSupervisionStep> CREATOR = new Creator<ReqSupervisionStep>() {
        @Override
        public ReqSupervisionStep createFromParcel(Parcel source) {
            return new ReqSupervisionStep(source);
        }

        @Override
        public ReqSupervisionStep[] newArray(int size) {
            return new ReqSupervisionStep[size];
        }
    };
}
