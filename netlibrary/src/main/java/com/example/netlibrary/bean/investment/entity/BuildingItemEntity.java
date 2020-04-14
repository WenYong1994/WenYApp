package com.example.netlibrary.bean.investment.entity;

public class BuildingItemEntity {
    /**
     * 楼盘名
     */
    private String housesName;

    /**
     * 户数
     */
    private int housesholds;

    /**
     * 房价
     */
    private double housesPrice;

    public String getHousesName() {
        return housesName;
    }

    public void setHousesName(String housesName) {
        this.housesName = housesName;
    }

    public int getHousesholds() {
        return housesholds;
    }

    public void setHousesholds(int housesholds) {
        this.housesholds = housesholds;
    }

    public double getHousesPrice() {
        return housesPrice;
    }

    public void setHousesPrice(double housesPrice) {
        this.housesPrice = housesPrice;
    }
}
