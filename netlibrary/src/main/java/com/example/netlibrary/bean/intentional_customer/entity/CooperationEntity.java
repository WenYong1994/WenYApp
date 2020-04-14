package com.example.netlibrary.bean.intentional_customer.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;

import java.util.List;

public class CooperationEntity extends BaseRequest {
    private String id;
    //潜在客户id
    private String customerId;
    //标题
    private String title;
    //客户行程
    List<Attachment> scheduleFile;
    //邀约人数
    private int peopleNum;
    //是否安排住宿
    private boolean isArrangeHotel;
    //是否安排接送
    private boolean isArrangeShuttle;
    //协作人的id
    private List<StaffPerson> associates;
    //协作任务状态
    private EnumConstant.TaskStatusEnum cooperationStatus = EnumConstant.TaskStatusEnum.wait;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Attachment> getScheduleFile() {
        return scheduleFile;
    }

    public void setScheduleFile(List<Attachment> scheduleFile) {
        this.scheduleFile = scheduleFile;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public boolean isArrangeHotel() {
        return isArrangeHotel;
    }

    public void setArrangeHotel(boolean arrangeHotel) {
        isArrangeHotel = arrangeHotel;
    }

    public boolean isArrangeShuttle() {
        return isArrangeShuttle;
    }

    public void setArrangeShuttle(boolean arrangeShuttle) {
        isArrangeShuttle = arrangeShuttle;
    }

    public List<StaffPerson> getAssociates() {
        return associates;
    }

    public void setAssociates(List<StaffPerson> associates) {
        this.associates = associates;
    }

    public EnumConstant.TaskStatusEnum getCooperationStatus() {
        return cooperationStatus;
    }

    public void setCooperationStatus(EnumConstant.TaskStatusEnum cooperationStatus) {
        this.cooperationStatus = cooperationStatus;
    }


}
