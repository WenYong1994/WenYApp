package com.example.netlibrary.bean.project.req;

import com.example.netlibrary.bean.body.BaseRequest;

public class ReqFixAddresAndUseArea extends BaseRequest {
    private String id;
    private String fixedAddress;
    private String fixedUseArea;
    private double fixedLat;
    private double fixedLng;

    public double getFixedLat() {
        return fixedLat;
    }

    public void setFixedLat(double fixedLat) {
        this.fixedLat = fixedLat;
    }

    public double getFixedLng() {
        return fixedLng;
    }

    public void setFixedLng(double fixedLng) {
        this.fixedLng = fixedLng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFixedAddress() {
        return fixedAddress;
    }

    public void setFixedAddress(String fixedAddress) {
        this.fixedAddress = fixedAddress;
    }

    public String getFixedUseArea() {
        return fixedUseArea;
    }

    public void setFixedUseArea(String fixedUseArea) {
        this.fixedUseArea = fixedUseArea;
    }
}
