package com.example.netlibrary.bean.approve.req;

import com.example.netlibrary.bean.approve.entity.FlowBaseEntity;
import com.example.netlibrary.bean.approve.entity.OfficeSuppiesItemEntity;
import com.example.netlibrary.bean.approve.entity.OfficeSuppliesTypeEntity;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.BasicFlowableEntity;
import com.example.netlibrary.bean.project.entity.BaseFollowUpEntity;

import java.util.ArrayList;
import java.util.List;

public class ReqCraeteOfficeSuppiesBill extends BasicFlowableEntity {
    private String department;
    private String note;
    private List<OfficeSuppiesItemEntity> officeSupplyItems;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OfficeSuppiesItemEntity> getOfficeSupplyItems() {
        return officeSupplyItems;
    }

    public void setOfficeSupplyItems(List<OfficeSuppiesItemEntity> officeSupplyItems) {
        this.officeSupplyItems = officeSupplyItems;
    }

}
