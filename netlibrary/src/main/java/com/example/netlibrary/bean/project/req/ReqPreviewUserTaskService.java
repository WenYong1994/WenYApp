package com.example.netlibrary.bean.project.req;

import com.example.netlibrary.bean.body.BaseRequest;

public class ReqPreviewUserTaskService extends BaseRequest {

    private String processDefId;
    private String processInstId;


    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }
}
