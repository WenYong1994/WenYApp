package com.example.netlibrary.bean.intentional_customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class BaseCustomerTaskEntity extends BaseRequest implements Parcelable {
    private String description;
    private long from;
    private String id;
    private long to;
    private String title;
    private EnumConstant.TaskStatusEnum taskStatus;
    private long notice;//任务提醒，提前多久时间开始提醒（单位：秒。不提醒：-1。准时提醒：0。）

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnumConstant.TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getNotice() {
        return notice;
    }

    public void setNotice(long notice) {
        this.notice = notice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.description);
        dest.writeLong(this.from);
        dest.writeString(this.id);
        dest.writeLong(this.to);
        dest.writeString(this.title);
        dest.writeInt(this.taskStatus == null ? -1 : this.taskStatus.ordinal());
        dest.writeLong(this.notice);
    }

    public BaseCustomerTaskEntity() {
    }

    protected BaseCustomerTaskEntity(Parcel in) {
        super(in);
        this.description = in.readString();
        this.from = in.readLong();
        this.id = in.readString();
        this.to = in.readLong();
        this.title = in.readString();
        int tmpTaskStatus = in.readInt();
        this.taskStatus = tmpTaskStatus == -1 ? null : EnumConstant.TaskStatusEnum.values()[tmpTaskStatus];
        this.notice = in.readLong();
    }

}
