package com.example.netlibrary.bean.user.entity;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class UserInfo extends BaseRequest {

    private int roleId;
    private int directLeaderId;
    private String directLeaderName;
    private String phone;
    private boolean enable;
    private String roleName;
    private String email;
    private int functionaryId;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFunctionaryId() {
        return functionaryId;
    }

    public void setFunctionaryId(int functionaryId) {
        this.functionaryId = functionaryId;
    }

    private EnumConstant.Gender gender = EnumConstant.Gender.unknown;

    public EnumConstant.Gender getGender() {
        return gender;
    }

    public void setGender(EnumConstant.Gender gender) {
        this.gender = gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDirectLeaderId() {
        return directLeaderId;
    }

    public void setDirectLeaderId(int directLeaderId) {
        this.directLeaderId = directLeaderId;
    }

    public String getDirectLeaderName() {
        return directLeaderName;
    }

    public void setDirectLeaderName(String directLeaderName) {
        this.directLeaderName = directLeaderName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}