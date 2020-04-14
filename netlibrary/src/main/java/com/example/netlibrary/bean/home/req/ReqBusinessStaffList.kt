package com.example.netlibrary.bean.home.req

import com.example.netlibrary.bean.common.req.ReqPageList
import com.example.netlibrary.bean.home.entity.BusinessStaffEntity

class ReqBusinessStaffList : ReqPageList() {
    var type : BusinessStaffEntity.Type = BusinessStaffEntity.Type.sincerity_price

}