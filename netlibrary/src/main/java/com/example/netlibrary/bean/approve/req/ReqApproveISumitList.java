package com.example.netlibrary.bean.approve.req;

import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqApproveISumitList extends ReqPageList {
    private int starterId;
    private Boolean finished;//是否审批完成 true 已审批  false未审批（待我审批）


    public int getStarterId() {
        return starterId;
    }

    public void setStarterId(int starterId) {
        this.starterId = starterId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
