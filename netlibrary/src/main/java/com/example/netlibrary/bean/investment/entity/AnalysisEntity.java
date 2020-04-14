package com.example.netlibrary.bean.investment.entity;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Locale;

public class AnalysisEntity extends BaseRequest {

    private Locale locale;//所在地区

    private String id;

    private long createAt;

    public AnalysisEntity() {

    }


    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
