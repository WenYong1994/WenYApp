package com.example.netlibrary.bean.approve.entity;

import android.telecom.Call;

import com.example.netlibrary.bean.approve.req.ReqCraeteOfficeSuppiesBill;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.BasicFlowableEntity;

import java.util.ArrayList;

public class OfficeSuppiesBillEntity  extends ReqCraeteOfficeSuppiesBill {
    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
