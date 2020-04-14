package com.example.netlibrary.bean.approve.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class OfficeSuppliesEntity implements Serializable {

    private String category;
    private String sn;
    private int id;
    private ArrayList<OfficeSuppliesTypeEntity> officeModels;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<OfficeSuppliesTypeEntity> getOfficeModels() {
        return officeModels;
    }

    public void setOfficeModels(ArrayList<OfficeSuppliesTypeEntity> officeModels) {
        this.officeModels = officeModels;
    }
}
