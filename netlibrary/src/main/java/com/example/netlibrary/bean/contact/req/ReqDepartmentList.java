package com.example.netlibrary.bean.contact.req;

import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqDepartmentList extends ReqPageList {
    private int deptId;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
        setDepartmentId(deptId);
    }

}
