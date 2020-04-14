package com.example.netlibrary.bean.schedule.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.netlibrary.bean.body.BaseRequest;

public class BaseScheduleEntity extends BaseRequest implements Parcelable {

    /**
     * 任务（日程）状态枚举
     *
     * @author lixufeng
     * @version 1.0
     * @date 2019/11/15 21:18
     */
    public enum TaskStatusEnum {

        //等待进行,进行中，已完成，未完成，已取消,已超时
        wait("未开始"), doing("进行中"), done("已完成"), unfinished("未完成"), canceled("已取消"), timeout("已超时");

        public String cnStr;

        TaskStatusEnum(String cnStr) {
            this.cnStr = cnStr;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    private String title;//日程标题
    private long from;//开始时间
    private long to;//结束时间
    private String description;//日程描述
    private long notice;//任务提醒，提前多久时间开始提醒（单位：秒。不提醒：-1。准时提醒：0。）
    private TaskStatusEnum scheduleStatus = TaskStatusEnum.wait;//日程状态


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getNotice() {
        return notice;
    }

    public void setNotice(long notice) {
        this.notice = notice;
    }


    public TaskStatusEnum getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(TaskStatusEnum scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public BaseScheduleEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.title);
        dest.writeLong(this.from);
        dest.writeLong(this.to);
        dest.writeString(this.description);
        dest.writeLong(this.notice);
        dest.writeInt(this.scheduleStatus == null ? -1 : this.scheduleStatus.ordinal());
    }

    protected BaseScheduleEntity(Parcel in) {
        super(in);
        this.title = in.readString();
        this.from = in.readLong();
        this.to = in.readLong();
        this.description = in.readString();
        this.notice = in.readLong();
        int tmpScheduleStatus = in.readInt();
        this.scheduleStatus = tmpScheduleStatus == -1 ? null : TaskStatusEnum.values()[tmpScheduleStatus];
    }

    public static final Creator<BaseScheduleEntity> CREATOR = new Creator<BaseScheduleEntity>() {
        @Override
        public BaseScheduleEntity createFromParcel(Parcel source) {
            return new BaseScheduleEntity(source);
        }

        @Override
        public BaseScheduleEntity[] newArray(int size) {
            return new BaseScheduleEntity[size];
        }
    };
}
