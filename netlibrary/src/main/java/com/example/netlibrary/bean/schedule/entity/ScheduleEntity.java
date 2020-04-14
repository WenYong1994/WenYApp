package com.example.netlibrary.bean.schedule.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleEntity extends BaseScheduleEntity implements Parcelable {
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

    public ScheduleEntity() {
    }

    protected ScheduleEntity(Parcel in) {
        super(in);
        this.id = in.readString();
    }

    public static final Creator<ScheduleEntity> CREATOR = new Creator<ScheduleEntity>() {
        @Override
        public ScheduleEntity createFromParcel(Parcel source) {
            return new ScheduleEntity(source);
        }

        @Override
        public ScheduleEntity[] newArray(int size) {
            return new ScheduleEntity[size];
        }
    };
}
