package com.example.netlibrary.bean.investment.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqAnalysisList extends ReqPageList {
    EnumConstant.InvestmentTypeEnum investmentType;
    private String keyword;
    private String province;
    private String city;
    private String area;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public EnumConstant.InvestmentTypeEnum getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(EnumConstant.InvestmentTypeEnum investmentType) {
        this.investmentType = investmentType;
    }
}
