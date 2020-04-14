package com.example.netlibrary.bean.announcement.entity;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;

import java.util.List;

public class BaseAnnouncementEntity extends BaseRequest {

    private String id;


    //通告标题
    private String title;

    //公告详情
    private String description;

    //附件
    private List<Attachment> attachments;

    private long createAt;

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
