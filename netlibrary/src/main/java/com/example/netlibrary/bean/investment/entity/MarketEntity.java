package com.example.netlibrary.bean.investment.entity;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Locale;

import java.util.List;

public class MarketEntity extends AnalysisEntity {
    private Double population;//人口(单位：万)
    private String industry;//支柱产业
    private Double gdp;//GDP(单位：亿)
    private String buildStoreFor;//建店定位
    private String curtainFirstFive;//窗帘前五品牌
    private int curtainStoreNum;//窗帘店数量
    private boolean isTrainAndAirport;//是否通高铁机场

    private List<BuildingItemEntity> newHouses;//在建楼盘
    private List<BuildingItemEntity> oldHouses;//交房楼盘

    private String note;//备注


    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public String getBuildStoreFor() {
        return buildStoreFor;
    }

    public void setBuildStoreFor(String buildStoreFor) {
        this.buildStoreFor = buildStoreFor;
    }

    public String getCurtainFirstFive() {
        return curtainFirstFive;
    }

    public void setCurtainFirstFive(String curtainFirstFive) {
        this.curtainFirstFive = curtainFirstFive;
    }

    public int getCurtainStoreNum() {
        return curtainStoreNum;
    }

    public void setCurtainStoreNum(int curtainStoreNum) {
        this.curtainStoreNum = curtainStoreNum;
    }

    public boolean isTrainAndAirport() {
        return isTrainAndAirport;
    }

    public void setTrainAndAirport(boolean trainAndAirport) {
        isTrainAndAirport = trainAndAirport;
    }

    public List<BuildingItemEntity> getNewHouses() {
        return newHouses;
    }

    public void setNewHouses(List<BuildingItemEntity> newHouses) {
        this.newHouses = newHouses;
    }

    public List<BuildingItemEntity> getOldHouses() {
        return oldHouses;
    }

    public void setOldHouses(List<BuildingItemEntity> oldHouses) {
        this.oldHouses = oldHouses;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
