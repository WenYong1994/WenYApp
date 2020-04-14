package com.example.netlibrary.bean.common.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqDownAttachmentList extends BaseRequest {
    private EnumConstant.RelatedFilesType type;

    private String typeId;
    private String customerId;


    public EnumConstant.RelatedFilesType getType() {
        return type;
    }

    public void setType(EnumConstant.RelatedFilesType type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
