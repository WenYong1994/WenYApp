package com.example.netlibrary.bean.project.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;

import java.util.List;

public class BaseFollowUpEntity extends BaseRequest implements Parcelable {
    private String projectApprovalId;
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum;
    private long date;
    private String content;
    private List<Attachment> attachments;
    private long createAt;




    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }

    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }


    public BaseFollowUpEntity() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.projectApprovalId);
        dest.writeInt(this.constructSupervisionEnum == null ? -1 : this.constructSupervisionEnum.ordinal());
        dest.writeLong(this.date);
        dest.writeString(this.content);
        dest.writeTypedList(this.attachments);
    }

    protected BaseFollowUpEntity(Parcel in) {
        this.projectApprovalId = in.readString();
        int tmpConstructSupervisionEnum = in.readInt();
        this.constructSupervisionEnum = tmpConstructSupervisionEnum == -1 ? null : EnumConstant.ConstructSupervisionEnum.values()[tmpConstructSupervisionEnum];
        this.date = in.readLong();
        this.content = in.readString();
        this.attachments = in.createTypedArrayList(Attachment.CREATOR);
    }

    public static final Creator<BaseFollowUpEntity> CREATOR = new Creator<BaseFollowUpEntity>() {
        @Override
        public BaseFollowUpEntity createFromParcel(Parcel source) {
            return new BaseFollowUpEntity(source);
        }

        @Override
        public BaseFollowUpEntity[] newArray(int size) {
            return new BaseFollowUpEntity[size];
        }
    };
}
