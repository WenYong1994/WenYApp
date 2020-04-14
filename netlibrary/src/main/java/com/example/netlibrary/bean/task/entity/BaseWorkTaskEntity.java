package com.example.netlibrary.bean.task.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

import java.util.List;
import java.util.Set;

public class BaseWorkTaskEntity extends BaseRequest implements Parcelable {

    //任务状态
    public enum TaskStatus {
        not_started("未开始"),//未开始
        in_process("进行中"),//进行中
        complete("已完成"),//已完成
        incomplete("未完成"),//未完成
        abort("已取消"),//已取消
        timeout("已超时");//已超时

        public String cnStr;

        TaskStatus(String cnStr) {
            this.cnStr = cnStr;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }


    private String title;//任务标题
    private long from;//开始时间
    private long to;//结束时间
    private StaffPerson leader;//负责人
    private List<StaffPerson> participants;//参与人
    private String description;//任务描述
    private long notice;//任务提醒，提前多久时间开始提醒（单位：秒。不提醒：-1。准时提醒：0。）
    private TaskStatus taskStatus = TaskStatus.not_started;



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

    public StaffPerson getLeader() {
        return leader;
    }

    public void setLeader(StaffPerson leader) {
        this.leader = leader;
    }

    public List<StaffPerson> getParticipants() {
        return participants;
    }

    public void setParticipants(List<StaffPerson> participants) {
        this.participants = participants;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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
        dest.writeParcelable(this.leader, flags);
        dest.writeTypedList(this.participants);
        dest.writeString(this.description);
        dest.writeLong(this.notice);
        dest.writeInt(this.taskStatus == null ? -1 : this.taskStatus.ordinal());
    }

    public BaseWorkTaskEntity() {
    }

    protected BaseWorkTaskEntity(Parcel in) {
        super(in);
        this.title = in.readString();
        this.from = in.readLong();
        this.to = in.readLong();
        this.leader = in.readParcelable(StaffPerson.class.getClassLoader());
        this.participants = in.createTypedArrayList(StaffPerson.CREATOR);
        this.description = in.readString();
        this.notice = in.readLong();
        int tmpTaskStatus = in.readInt();
        this.taskStatus = tmpTaskStatus == -1 ? null : TaskStatus.values()[tmpTaskStatus];
    }

    public static final Creator<BaseWorkTaskEntity> CREATOR = new Creator<BaseWorkTaskEntity>() {
        @Override
        public BaseWorkTaskEntity createFromParcel(Parcel in) {
            return new BaseWorkTaskEntity(in);
        }

        @Override
        public BaseWorkTaskEntity[] newArray(int size) {
            return new BaseWorkTaskEntity[size];
        }
    };

}
