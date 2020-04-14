package com.example.commonlibrary.constant;

import androidx.annotation.NonNull;

import com.example.commonlibrary.utils.DateTimeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface EnumConstant {

    enum WorkReportType {
        // 日报、周报、月报
        day("日报"), week("周报"), month("月报");
        public String cnStr;

        WorkReportType(String cnStr) {
            this.cnStr = cnStr;
        }
    }

    enum ApproveStatus{
        //待审批，审批中，完成，终止
        to_be_audited("待审核"), in_process("审核中"), end("已通过"), abort("已拒绝");
        public String cnStr;

        ApproveStatus(String cnStr) {
            this.cnStr = cnStr;
        }

    }


    //审批流程状态,在所有审批列表的一个状态
    enum ApproveAllStatus {
        //待审核、审核中、已通过、已拒绝（说明：开启流程后状态为待审核，审核通过但为正常结束为审核中，正常结束为已通过，被审核拒绝为已拒绝）
        to_be_audited("待审核"), in_process("审核中"), end("已通过"), decline("已拒绝");

        public String cnStr;

        ApproveAllStatus(String cnStr) {
            this.cnStr = cnStr;
        }

    }

    enum ApproveType{
        buyingRequisition,//请购单
        project,//立项书
        cordiallyMoney,//诚意金
        ensureMoney,//保证金
        serviceMoney,//服务费
        sampleMoney,//样品收款
        softOutfitMoney,//软装收款
        hardOutfitMoney,//硬装收款
        refund,//退款
    }

    enum ProjectStatus{
       underway,//进行中
       complete,//已完成
       unfinished,//未完成
       termination,//已终止
   }
   
    enum InvestmentType {
        sole,// 独资
        joint_venture//合资
    }

    //施工状态
    enum ConstructStatus {
        //进行中、已完成、未完成、已终止
        in_process, complete, incomplete, abort
    }

    //空间部详情校准审核状态
    enum FixedDetailStatus {
        //未修改、待审核、已通过、已拒绝
        not_fixed, to_be_audited, pass, fail
    }

    enum ApprovalResultTypeEnum {

        //通过，拒绝
        pass, reject

    }

    enum DrawingSheetEnum {
        scene_measure("现场测量"),//现场测量
        flat("平面图"),//平面图
        soft_fit("软装方案"),//软装方案
        hard_fit("硬装方案"),//硬装方案
        construct("施工图"),//施工图
        effect("效果图");//效果图

        public String cnStr;

        DrawingSheetEnum(String cnStr) {
            this.cnStr = cnStr;
        }

        public static DrawingSheetEnum getEnumByCnStr(String cnStr){
            for(DrawingSheetEnum sheetEnum :values()){
                if(sheetEnum.cnStr.equalsIgnoreCase(cnStr)){
                    return sheetEnum;
                }
            }
            return null;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    enum ConstructSupervisionEnum implements Serializable {
        step0("未开始"),//未开始
        step1("第一阶段"),//第一阶段
        step2("第二阶段"),//第二阶段
        step3("第三阶段"),//第三阶段
        step4("第四阶段"),//第四阶段
        step5("第五阶段"),//第五阶段
        step6("第六阶段");//第六阶段

        public String cnStr;

        ConstructSupervisionEnum(String cnStr) {
            this.cnStr = cnStr;
        }

        public static ConstructSupervisionEnum getInstanceByIndex(int index){
            ConstructSupervisionEnum[] values = values();
            for(ConstructSupervisionEnum supervisionEnum:values){
                if(supervisionEnum.ordinal() == index){
                    return supervisionEnum;
                }
            }
            return null;
        }


    }

    //项目详情空间部
    enum ProjectSpaceFlow{
        scene_measure("现场测量"),//现场测量
        flat("平面图"),//平面图
        softorhard_fit("软/硬装方案"),// 软/硬装方案
        construct("施工图"),//施工图
        effect("效果图"),//效果图
        construction_supervisor("施工监理"),
        decorate_acceptance("装修验收"),
        complete("完成");

        public String cnStr;

        ProjectSpaceFlow(String cnStr) {
            this.cnStr = cnStr;
        }

    }


    enum RelatedFilesType{
        //立项，退款，工作报告,    （其他情况 客户）
        project_approval, refund, task,customer
    }

    //收款类型
    enum ChargeType {
        sincerity_price("缴纳诚意金"),//诚意金收款
        security_deposit("缴纳保证金"),//保证金收款
        service_fee("缴纳服务费"),//服务费收款
        sample("样品收款"),//样品收款
        hard_decoration("硬装收款"),//硬装收款
        soft_decoration("软装收款");//软装收款

        public String cnStr;

        ChargeType(String des) {
            this.cnStr = des;
        }


        public static ChargeType getInstanceByCnStr(String cnStr){
            for(ChargeType chargeType :values()){
                if(chargeType.cnStr.equalsIgnoreCase(cnStr)){
                    return chargeType;
                }
            }
            return null;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    //收款渠道
    enum PayType {
        //现金、银行转账、刷卡、微信、支付宝
        cash("现金"), bank("银行转账"), card("刷卡"), wechat("微信"), alipay("支付宝");
        public String cnStr;


        PayType(String des) {
            this.cnStr = des;
        }


        public static PayType getInstanceByCnStr(String cnStr){
            for(PayType payType :values()){
                if(payType.cnStr.equalsIgnoreCase(cnStr)){
                    return payType;
                }
            }
            return null;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    //项目动态相关部门
    enum ProjectDynamicDepartment {
        //招商部、空间部、客服部
        investment, space, customer_service
    }


    enum IntentionalCustomerStatus {
        //了解产品  正在跟进  到访总部 准备加盟  暂时搁置  已缴纳诚意金  已签订合同
        initial("了解产品"),
        follow_up("正在跟进"),
        visit("到访总部"),
        ready_to_join("准备加盟"),
        shelve("暂时搁置"),
        payed_sincerity("已缴纳诚意金"),
        signed_contract("已签订合同");

        public String cnStr;

        IntentionalCustomerStatus(String des) {
            this.cnStr = des;
        }

        public static List<String> toCnStrList(){
            List<String> list = new ArrayList<>();
            for(IntentionalCustomerStatus customerStatus:values()){
                list.add(customerStatus.cnStr);
            }
            return list;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }


    enum Level {

        //意向 可选 常规
        A("意向"), B("可选"), C("常规");

        public String cnStr;

        Level(String des) {
             this.cnStr = des;
         }

         public static List<String> toCnStrList(){
            List<String> list = new ArrayList<>();
             for(Level level:values()){
                 list.add(level.cnStr);
             }
             return list;
        }

         @NonNull
         @Override
         public String toString() {
             return cnStr;
         }

    }


    enum Source {
        _private("私有客户"),//私有客户
        _public("公海");// 公海，都可以去领取
         public String cnStr;

         Source(String cnStr) {
             this.cnStr = cnStr;
         }
     }

    enum Condition implements Serializable {
        //优质 良好 一般
        perfect("优质"), good("良好"), general("一般");
        public String cnStr;

        Condition(String cnStr) {
            this.cnStr = cnStr;
        }

        public static Condition getInstanceByCnStr(String cnStr){
            for(Condition condition :values()){
                if(condition.cnStr.equalsIgnoreCase(cnStr)){
                    return condition;
                }
            }
            return null;
        }
    }

    enum Origin implements Serializable{
        //地推 转介绍 展会 其他
        visitSell("地推"), recommended("转介绍"), exhibition("展会"),
        network_promotion("网络推广"), mall_recommend("商场推荐"),other("其他");

        public String cnStr;

        Origin(String cnStr) {
            this.cnStr = cnStr;
        }

        public static Origin getInstanceByCnStr(String cnStr){
            for(Origin origin :values()){
                if(origin.cnStr.equalsIgnoreCase(cnStr)){
                    return origin;
                }
            }
            return null;
        }
    }

    enum TaskStatusEnum{
        //未开始,进行中，已完成，未完成，已取消,已超时
        wait("未开始"),
        doing("进行中"),
        done("已完成"),
        unfinished("未完成"),
        canceled("已取消"),
        timeout("已超时");

        public String cnStr;

        TaskStatusEnum(String cnStr) {
            this.cnStr = cnStr;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    enum FollowType {
        visit("到访"),
        phone("电话"),
        wechat("微信"),
        sms("短信"),
        qq("QQ"),
        other("其他"),
        //        newFlow("新增跟进"),
        joinContract("加盟意向协议"),
        agencyContract("签订合同(区域代理协议)"),
        createAssociate("添加协作"),
        doAssociate("协作任务"),
        createCustomer("新建客户"),
        editCustomer("编辑客户"),
        transferCusToOther("转移客户"),
        transferCusToSea("转入公海"),
        getCustomerFromSea("领取公海客户"),
        createTask("新建任务");


        public String cnStr;

        FollowType(String des) {
            this.cnStr = des;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    enum CustomerMore{
        upload_join("上传《加盟意向书》"),
        upload_roxy("上传《区域代理协议》"),
        new_task("新建任务"),
        new_cooperation("添加协作"),
        transfer_customer("转让客户"),
        in_the_high_seas("移入公海"),
        ;

       public String cnStr;

        CustomerMore(String cnStr) {
            this.cnStr = cnStr;
        }

        public static CustomerMore getCustomerMoreByCnStr(String cnStr){
            for(CustomerMore customerMore :values()){
                if(customerMore.cnStr.equalsIgnoreCase(cnStr)){
                    return customerMore;
                }
            }
            return null;
        }
    }

    enum AlertTime{
        no(-1),
        timely(0),
        alert5m(60*5),
        alert10m(60*10),
        getAlert30m(60*30),
        getAlert1h(60*60),
        getAlert2h(60*60*2),
        getAlert1d(60*60*24),
        ;
        public long alertTime;//秒

        AlertTime(long alertTime) {
            this.alertTime = alertTime;
        }

        @NonNull
        @Override
        public String toString() {
            return DateTimeUtils.formatAlertStr(alertTime);
        }

        public static AlertTime getIntance(long time){
            for(AlertTime instance:values()){
                if(instance.alertTime == time) return instance;
            }
            return timely;
        }

    }

    //项目动态类别
    enum LogType {
        //立项，退款，工作报告，收款
        project_approval("立项"), refund("退款"), task("工作报告"), receivables_order("收款");

        public String cnStr;

        LogType(String cnStr) {
            this.cnStr = cnStr;
        }

        public static LogType getCustomerMoreByCnStr(String cnStr){
            for(LogType logType :values()){
                if(logType.cnStr.equalsIgnoreCase(cnStr)){
                    return logType;
                }
            }
            return null;
        }
    }

    //商场定位
    enum StoreLevel {
        //低端、中低端、中高端、高端
        low("低端"), middle_low("中低端"), middle_high("中高端"), high("高端");

        public String cnStr;

        StoreLevel(String cnStr) {
            this.cnStr = cnStr;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    enum InvestmentTypeEnum {
        //经营环境分析，竞品分析，市场分析
        ambient, rival, market
    }

    enum AnalysisRegion {
        //城区, 市区
        city("城区"), urban("市区");

        public String cnStr;

        AnalysisRegion(String cnStr) {
            this.cnStr = cnStr;
        }
    }

    //展示类别
    enum ListType {
        //我审批的、我发起的、抄送我的
        auditToMe, me, ccToMe
    }

    enum WorkReportReadStatus{
        //待批阅、已批阅
        to_be_audited, completed
    }

    enum FlowableGroup {

        OFFICE_SUPPLY_APPLY("请购单"), //办公用品申请
        PROJECT_ADD("立项书"), //新增立项审批
        SINCERITY_PRICE("诚意金"),//诚意金收款
        SECURITY_DEPOSIT("保证金"),//保证金收款
        SERVICE_FEE("服务费"),//服务费收款
        SAMPLE("样品收款"),//样品收款
        SOFT_DECORATION("软装收款"),//软装收款
        HARD_DECORATION("硬装收款"),//硬装收款
        PROJECT_REFUND("立项书退款"),//立项退款审批
        SUPPLIER_ADD("供应商");//新增供应商审批

        public String cnStr;

        FlowableGroup(String value) {
            this.cnStr = value;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }

    }

    //列表展示类别
    enum WorkListType {
        //我提交的、抄送我的
        me, toMe
    }

    enum Gender {

        male("男"), female("女"), unknown("不公布");

        public String cnStr;


        Gender(String cnStr) {
            this.cnStr = cnStr;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

}