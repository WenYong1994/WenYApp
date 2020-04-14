package com.example.netlibrary.bean.common.entity;

import java.io.Serializable;

public class Locale implements Serializable {
    private String province;
    private int provinceCode;
    private String city;
    private int cityCode;
    private String area;
    private int areaCode;
    private String address;
    private double lat;
    private double lng;
    public void setProvince(String province) {
        this.province = province;
    }
    public String getProvince() {
        return province;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
    public int getProvinceCode() {
        return provinceCode;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
    public int getCityCode() {
        return cityCode;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getArea() {
        return area;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }
    public int getAreaCode() {
        return areaCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    public double getLng() {
        return lng;
    }


    public void setLocal(Locale local){
        setAddress(local.getAddress());
        setLat(local.getLat());
        setLng(local.getLng());
    }

}