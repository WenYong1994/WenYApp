package com.example.netlibrary.bean.project.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SupervisionStepEntity extends BaseSupervisionStep implements Parcelable {
    private long createAt;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        super.writeToParcel(dest, flags);
        dest.writeLong(this.createAt);
        dest.writeString(this.title);
        dest.writeString(this.getProjectApprovalId());
        dest.writeValue(this.getUserId());
    }

    public SupervisionStepEntity() {
    }

    protected SupervisionStepEntity(Parcel in) {
        super(in);
        this.createAt = in.readLong();
        this.title = in.readString();
        this.setProjectApprovalId(in.readString());
    }

    public static final Creator<SupervisionStepEntity> CREATOR = new Creator<SupervisionStepEntity>() {
        @Override
        public SupervisionStepEntity createFromParcel(Parcel source) {
            return new SupervisionStepEntity(source);
        }

        @Override
        public SupervisionStepEntity[] newArray(int size) {
            return new SupervisionStepEntity[size];
        }
    };
}
