package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqRefundDynamic extends ReqPageList {
    private String typeId;


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
