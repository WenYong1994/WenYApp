package com.example.netlibrary.bean.intentional_customer.req;

import android.os.Parcel;

import com.example.netlibrary.bean.body.BaseRequest;

public class BaseReqCustomerTask extends BaseRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.id);
    }

    public BaseReqCustomerTask() {
    }

    protected BaseReqCustomerTask(Parcel in) {
        super(in);
        this.id = in.readString();
    }

    public static final Creator<BaseReqCustomerTask> CREATOR = new Creator<BaseReqCustomerTask>() {
        @Override
        public BaseReqCustomerTask createFromParcel(Parcel source) {
            return new BaseReqCustomerTask(source);
        }

        @Override
        public BaseReqCustomerTask[] newArray(int size) {
            return new BaseReqCustomerTask[size];
        }
    };
}
