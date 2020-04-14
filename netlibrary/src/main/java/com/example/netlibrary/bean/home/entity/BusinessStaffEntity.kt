package com.example.netlibrary.bean.home.entity

class BusinessStaffEntity{

    //收款类型
    enum class Type(var cnStr: String) {
        sincerity_price("诚意金收款"),  //诚意金收款
        security_deposit("保证金收款"),  //保证金收款
        service_fee("服务费收款"),  //服务费收款
        sample("样品收款"),  //样品收款
        hard_decoration("硬装收款"),  //硬装收款
        soft_decoration("软装收款");

        override fun toString(): String {
            return cnStr
        }
    }

    var departmentName:String?=null
    var total:Float?=null
    var departmentId:Int?=null
    var nickName:String?=null
    var userId:Int?=null
    var poster:String?=null



}