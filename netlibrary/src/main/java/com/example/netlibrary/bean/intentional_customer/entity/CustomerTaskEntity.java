package com.example.netlibrary.bean.intentional_customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomerTaskEntity extends BaseCustomerTaskEntity implements Parcelable {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.customerId);
    }

    public CustomerTaskEntity() {
    }

    protected CustomerTaskEntity(Parcel in) {
        super(in);
        this.customerId = in.readString();
    }

    public static final Creator<CustomerTaskEntity> CREATOR = new Creator<CustomerTaskEntity>() {
        @Override
        public CustomerTaskEntity createFromParcel(Parcel source) {
            return new CustomerTaskEntity(source);
        }

        @Override
        public CustomerTaskEntity[] newArray(int size) {
            return new CustomerTaskEntity[size];
        }
    };
}