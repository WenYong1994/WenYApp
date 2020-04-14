package com.example.netlibrary.bean.project.entity;

import com.example.commonlibrary.bean.IFlowDynamicItem;
import com.example.commonlibrary.constant.EnumConstant;

public class LogDynamicItemEntity implements IFlowDynamicItem {

    private EnumConstant.ProjectDynamicDepartment dynamicDepartment;

    private String relatedEntity;
    private String relatedEntityId;
    private String typeId;
    private String id;
    private EnumConstant.LogType type;
    private String department;
    private String title;
    private int userId;
    private String content;
    private long createAt;
    private String userName;
    private String userNick;
    private String nickName;
    private EnumConstant.FollowType followType = EnumConstant.FollowType.visit;//动态类型


    public EnumConstant.FollowType getFollowType() {
        return followType;
    }

    public void setFollowType(EnumConstant.FollowType followType) {
        this.followType = followType;
    }



    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRelatedEntity(String relatedEntity) {
        this.relatedEntity = relatedEntity;
    }
    public String getRelatedEntity() {
        return relatedEntity;
    }

    public void setRelatedEntityId(String relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }
    public String getRelatedEntityId() {
        return relatedEntityId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeId() {
        return typeId;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setType(EnumConstant.LogType type) {
        this.type = type;
    }

    public EnumConstant.LogType getType() {
        return type;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
    public long getCreateAt() {
        return createAt;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public void setDynamicDepartment(EnumConstant.ProjectDynamicDepartment dynamicDepartment) {
        this.dynamicDepartment = dynamicDepartment;
    }

    public EnumConstant.ProjectDynamicDepartment getDynamicDepartment() {
        return dynamicDepartment;
    }



    @Override
    public String getAbsTitle() {
        return title;
    }

    @Override
    public String getAbsContent() {
//        switch (dynamicDepartment){
//            case investment:
//                return
//                break;
//            case space:
//
//                break;
//            case customer_service:
//
//                break;
//
//        }
        return getContent();
    }

    @Override
    public String getAbsHanlder() {
        return "操作人："+nickName;
    }

    @Override
    public long getAbsTime() {
        return createAt;
    }
}