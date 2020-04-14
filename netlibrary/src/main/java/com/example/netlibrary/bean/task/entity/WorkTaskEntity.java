package com.example.netlibrary.bean.task.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkTaskEntity extends BaseWorkTaskEntity implements Parcelable {
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

    public WorkTaskEntity() {
    }

    protected WorkTaskEntity(Parcel in) {
        super(in);
        this.id = in.readString();
    }

    public static final Creator<WorkTaskEntity> CREATOR = new Creator<WorkTaskEntity>() {
        @Override
        public WorkTaskEntity createFromParcel(Parcel source) {
            return new WorkTaskEntity(source);
        }

        @Override
        public WorkTaskEntity[] newArray(int size) {
            return new WorkTaskEntity[size];
        }
    };
}
