package com.example.netlibrary.bean.approve.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqFilterPageList;
import com.example.netlibrary.bean.common.req.ReqPageList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReqApproveList extends ReqFilterPageList {

    private Boolean finished;

    private EnumConstant.ListType listType;

    private List<EnumConstant.FlowableGroup> flowableGroups;

    private EnumConstant.ApproveAllStatus approvalStatus;


    public EnumConstant.ApproveAllStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(EnumConstant.ApproveAllStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public ReqApproveList() {
        flowableGroups = new ArrayList<>();//把供应商给区分开
        flowableGroups.clear();
        for(EnumConstant.FlowableGroup group: EnumConstant.FlowableGroup.values()){
            if(group!=EnumConstant.FlowableGroup.SUPPLIER_ADD){
                flowableGroups.add(group);
            }
        }
    }

    public void setType(EnumConstant.FlowableGroup type){
        if(flowableGroups==null){
            flowableGroups = new ArrayList<>();
        }
        flowableGroups.clear();
        if(type==null){
            for(EnumConstant.FlowableGroup group: EnumConstant.FlowableGroup.values()){
                if(group!=EnumConstant.FlowableGroup.SUPPLIER_ADD){
                    flowableGroups.add(group);
                }
            }
        }else {
            flowableGroups.add(type);
        }

    }




    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public EnumConstant.ListType getListType() {
        return listType;
    }

    public void setListType(EnumConstant.ListType listType) {
        this.listType = listType;
    }

}
