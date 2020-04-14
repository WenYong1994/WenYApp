package com.example.netlibrary.net;

import com.example.commonlibrary.bean.BaseAppCompatActivity;
import com.example.commonlibrary.bean.StaffDepartment;
import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.http.HttpHelper;
import com.example.netlibrary.bean.approve.entity.OfficeSuppliesEntity;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.intentional_customer.entity.CustomerFollowUpEntity;
import com.example.netlibrary.bean.intentional_customer.entity.CustomerIFlowDynamicItemEntity;
import com.example.netlibrary.bean.intentional_customer.entity.CustomerTaskEntity;
import com.example.netlibrary.bean.intentional_customer.entity.IntentionalCustomerEntity;
import com.example.netlibrary.bean.project.entity.ApprovePreviewUserResultEntity;
import com.example.netlibrary.bean.project.entity.FollowUpEntity;
import com.example.netlibrary.bean.project.entity.LogDynamicItemEntity;
import com.example.netlibrary.bean.project.entity.ProjectDetailsEntity;
import com.example.netlibrary.bean.project.entity.ProjectListItemEntity;
import com.example.netlibrary.bean.project.entity.SupervisionStepEntity;
import com.example.netlibrary.bean.refund.entity.RefundEntity;
import com.example.netlibrary.bean.user.entity.UserInfo;
import com.example.netlibrary.bean.login.UserLogin;
import com.example.netlibrary.bean.work_report.entity.WorkReportEntity;
import com.example.netlibrary.helper.RequestHelper;
import com.example.netlibrary.url.NetConstant;

import java.util.ArrayList;

public class NetHelper {

    public static void login(BaseAppCompatActivity activity,boolean showPb, String userName, String password, HttpHelper.OnCompleteListener<UserInfo> listener) {
        UserLogin user = new UserLogin();
        user.setPassword(password);
        user.setUsername(userName);
        String s = RequestHelper.toJson(user, null, NetConstant.Umc.login);
        new HttpHelper<UserInfo>(activity)
                .url(NetConstant.baseUrl + NetConstant.Umc.umcName)
                .doInBackground(false)
                .showProgress(showPb, activity)
                .postJson(s)
                .execute(listener);
    }

    public static void craeteReport(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(true, activity)
                .postJson(json)
                .execute(listener);
    }


    public static void fixAddresAndUseArea(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(true, activity)
                .postJson(json)
                .execute(listener);
    }


    public static void downWorkReportList(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<ArrayList<WorkReportEntity>> listener) {
        new HttpHelper<ArrayList<WorkReportEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);

    }


    public static void downProjectList(BaseAppCompatActivity activity, String json, boolean showPb, HttpHelper.OnCompleteListener<ArrayList<ProjectListItemEntity>> listener) {
        new HttpHelper<ArrayList<ProjectListItemEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(showPb, activity)
                .postJson(json)
                .execute(listener);

    }

    public static void createProject(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //获取立项书详情
    public static void projectDetails(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<ProjectDetailsEntity> listener) {
        new HttpHelper<ProjectDetailsEntity>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }

    //获取立项书详情
    public static void flowablePreviewUserTask(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<ApprovePreviewUserResultEntity> listener) {
        new HttpHelper<ApprovePreviewUserResultEntity>(activity)
                .url(NetConstant.baseUrl + NetConstant.Flowable.FlowableName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }


    //获取办公用品列表
    public static void getOfficeGoodsList(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<ArrayList<OfficeSuppliesEntity>> listener) {
        new HttpHelper<ArrayList<OfficeSuppliesEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Product.ProductName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }


    //新建办公用品审批
    public static void createOfficeSupplyBill(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Product.ProductName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }

    //审批列表
    public static void processInstListService(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Product.ProductName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }

    //审批项目
    public static void approveProject(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(true, activity)
                .postJson(json)
                .execute(listener);
    }

    //更改立项书施工状态
    public static void changeConstructionStatus(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }

    //提交图紙
    public static void submitDrawingSheet(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //下载相关附件
    public static void downFileList(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<Attachment>> listener) {
        new HttpHelper<ArrayList<Attachment>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //施工监理列表
    public static void downSuperviseList(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<SupervisionStepEntity>> listener) {
        new HttpHelper<ArrayList<SupervisionStepEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建监理
    public static void createSupervise(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建跟进
    public static void createFollowUp(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建跟进
    public static void followUpList(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<FollowUpEntity>> listener) {
        new HttpHelper<ArrayList<FollowUpEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建跟进
    public static void acceptanceSupervision(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    //新建培训邀请
    public static void createTrainingInvite(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    //项目样品收款
    public static void sampleCharge(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //项目动态列表
    public static void flowLogList(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<LogDynamicItemEntity>> listener) {
        new HttpHelper<ArrayList<LogDynamicItemEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //获取部门列表
    public static void contactsListService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<StaffDepartment>> listener) {
        new HttpHelper<ArrayList<StaffDepartment>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Umc.umcName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //人员列表
    public static void staffPersonLst(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<StaffPerson>> listener) {
        new HttpHelper<ArrayList<StaffPerson>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Umc.umcName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建客户管理
    public static void intentionalCustomerAddService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //客户列表
    public static void intentionalCustomerListService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<IntentionalCustomerEntity>> listener) {
        new HttpHelper<ArrayList<IntentionalCustomerEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    //客户详情
    public static void intentionalCustomerDetailsService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<IntentionalCustomerEntity> listener) {
        new HttpHelper<IntentionalCustomerEntity>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //客户动态
    public static void intentionalCustomerFollowListService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<CustomerIFlowDynamicItemEntity>> listener) {
        new HttpHelper<ArrayList<CustomerIFlowDynamicItemEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //任务列表
    public static void customerTaskListService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<CustomerTaskEntity>> listener) {
        new HttpHelper<ArrayList<CustomerTaskEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建客户跟进
    public static void customerFollowAddService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    //客户跟进详情
    public static void customerFollowDetailsService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<CustomerFollowUpEntity> listener) {
        new HttpHelper<CustomerFollowUpEntity>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    //新建客户任务
    public static void createCustomerTask(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建客户协作
    public static void creatCooperation(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //新建转让客户
    public static void intentionalCustomerTransferService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //客户任务详情
    public static void customerTaskDetails(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<CustomerTaskEntity> listener) {
        new HttpHelper<CustomerTaskEntity>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //删除客户任务
    public static void customerTaskDelService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //删除客户任务
    public static void customerTaskEditService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    //退款列表
    public static void refundListService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<ArrayList<RefundEntity>> listener) {
        new HttpHelper<ArrayList<RefundEntity>>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    //新建退款
    public static void refundAddService(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<Object> listener) {
        new HttpHelper<Object>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    public static <T> void executeCrm(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    public static <T> void executeMMS(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url(NetConstant.baseUrl + NetConstant.Mms.MmsName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }


    public static <T> void executeCrmInBackground(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url(NetConstant.baseUrl + NetConstant.Crm.crmName)
                .doInBackground(true)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    public static <T> void executeFlowable(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url(NetConstant.baseUrl + NetConstant.Flowable.FlowableName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    public static <T> void executeProduct(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url(NetConstant.baseUrl + NetConstant.Product.ProductName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    public static <T> void executeUmc(BaseAppCompatActivity activity, String json, boolean isShowPb, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url(NetConstant.baseUrl + NetConstant.Umc.umcName)
                .doInBackground(false)
                .showProgress(isShowPb, activity)
                .postJson(json)
                .execute(listener);
    }

    public static <T> void getUserSig(BaseAppCompatActivity activity, String json, HttpHelper.OnCompleteListener<T> listener) {
        new HttpHelper<T>(activity)
                .url("http://api.dev.jmjcm.com" + NetConstant.Mms.MmsName)
                .doInBackground(false)
                .showProgress(false, activity)
                .postJson(json)
                .execute(listener);
    }
}
