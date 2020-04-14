package com.example.netlibrary.bean.investment.entity;

import android.graphics.Region;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.Locale;

import java.util.List;

public class AmbientEntity extends AnalysisEntity {

//    private String id;
//    public String nickName;//提交人姓名
//    public String poster;//提交人头像
//    private Locale locale;//所在地区
    private String market;//商场/商圈名
    private EnumConstant.StoreLevel level;//定位
    private Float buildingVolume;//建筑体量 (单位：万/平米)
    private Double rent;//软装区月租金 (单位：万/平方)
    private String investmentCharge;//招商负责人
    private String investmentChargePhone;//招商负责人电话
    private Long marketRenewalTime;//商场续约时间
    private List<Attachment> softDecorationFlatDraws;//软装区位平面图

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public EnumConstant.StoreLevel getLevel() {
        return level;
    }

    public void setLevel(EnumConstant.StoreLevel level) {
        this.level = level;
    }

    public Float getBuildingVolume() {
        return buildingVolume;
    }

    public void setBuildingVolume(Float buildingVolume) {
        this.buildingVolume = buildingVolume;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getInvestmentCharge() {
        return investmentCharge;
    }

    public void setInvestmentCharge(String investmentCharge) {
        this.investmentCharge = investmentCharge;
    }

    public String getInvestmentChargePhone() {
        return investmentChargePhone;
    }

    public void setInvestmentChargePhone(String investmentChargePhone) {
        this.investmentChargePhone = investmentChargePhone;
    }

    public Long getMarketRenewalTime() {
        return marketRenewalTime;
    }

    public void setMarketRenewalTime(Long marketRenewalTime) {
        this.marketRenewalTime = marketRenewalTime;
    }

    public List<Attachment> getSoftDecorationFlatDraws() {
        return softDecorationFlatDraws;
    }

    public void setSoftDecorationFlatDraws(List<Attachment> softDecorationFlatDraws) {
        this.softDecorationFlatDraws = softDecorationFlatDraws;
    }
}
