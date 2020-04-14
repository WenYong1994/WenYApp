package com.example.netlibrary.bean.common.req;

public class ReqFilterPageList extends ReqPageList {
    private String keyword;
    private long beginDate =0;
    private long endDate = Long.MAX_VALUE/1001;
    private long startDate=0;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
        this.startDate=beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}
