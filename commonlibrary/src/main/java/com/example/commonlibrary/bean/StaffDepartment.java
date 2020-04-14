package com.example.commonlibrary.bean;

import com.example.commonlibrary.staff.BaseStaff;
import com.example.commonlibrary.staff.StaffGroupImp;
import com.example.commonlibrary.staff.StaffImp;

import java.util.ArrayList;
import java.util.List;

public class StaffDepartment extends BaseStaff implements StaffGroupImp {

    private List<? extends StaffImp> list;
    private int departmentId;
    private int funcCount;
    private int higherDeptId;//这里是需要int
    private String leaderName;
    private String higherDeptName;
    private String name;
    private String leaderId;
    private long updateAt;
    private boolean enable;

    //附加字段
    int parentId;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getFuncCount() {
        return funcCount;
    }

    public void setFuncCount(int funcCount) {
        this.funcCount = funcCount;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public int getHigherDeptId() {
        return higherDeptId;
    }

    public void setHigherDeptId(int higherDeptId) {
        this.higherDeptId = higherDeptId;
    }

    public String getHigherDeptName() {
        return higherDeptName;
    }

    public void setHigherDeptName(String higherDeptName) {
        this.higherDeptName = higherDeptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public List<? extends StaffImp> getList() {
        return list;
    }

    public void setList(List<? extends StaffImp> list) {
        this.list = list;
    }

    @Override
    public int getId() {
        return departmentId;
    }

    @Override
    public boolean isGroup() {
        return true;
    }

    @Override
    public String getAbsName() {
        return name;
    }

    @Override
    public String getAbsJob() {
        return null;
    }

    @Override
    public String getAbsAvatar() {
        return null;
    }

    @Override
    public int getCount() {
        return funcCount;
    }

    @Override
    public List<? extends StaffImp> getAbsChild() {
        return list;
    }

    @Override
    public int getAbsParentId() {
        return higherDeptId;
    }
}
