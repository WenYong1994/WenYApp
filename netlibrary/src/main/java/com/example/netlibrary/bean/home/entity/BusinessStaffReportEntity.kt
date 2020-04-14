package com.example.netlibrary.bean.home.entity

import com.example.netlibrary.R

class BusinessStaffReportEntity {

    var serviceFeeTotal:Float?=0f
    var hardDecorationTotal:Float?=0f
    var softDecorationTotal:Float?=0f
    var securityDepositTotal:Float?=0f
    var sincerityPriceTotal:Float?=0f
    var sampleTotal:Float?=0f
    var sincerityPriceCount:Int?=0
    var projectApprovalCount:Int?=0
    var visitCustomerNum:Int?=0
    var customerInspectNum:Int?=0
    var customerInviteNum:Int?=0
    var ranking:Ranking?=null

}


class Ranking{

    var serviceFeeRank:Int?=null
    var sincerityPriceRank:Int?=null
    var securityDepositRank:Int?=null


}