package com.example.netlibrary.bean.investment.entity;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Locale;

import java.util.List;

public class RivalEntity extends AnalysisEntity {

    private List<BrandEntity> brands;



    public List<BrandEntity> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandEntity> brands) {
        this.brands = brands;
    }

}
