package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqProjectDynamic extends ReqPageList {
    private String typeId;
    private EnumConstant.ProjectDynamicDepartment department;


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public EnumConstant.ProjectDynamicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(EnumConstant.ProjectDynamicDepartment department) {
        this.department = department;
    }
}
