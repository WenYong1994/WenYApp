package com.example.netlibrary.bean.common.req;

import com.example.netlibrary.bean.body.BaseRequest;

public class ReqPageList extends BaseRequest {
    int limit;
    int page;


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
