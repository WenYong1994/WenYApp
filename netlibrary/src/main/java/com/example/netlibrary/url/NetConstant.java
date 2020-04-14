package com.example.netlibrary.url;

public interface NetConstant {

//    String baseUrl = "http://192.168.0.191:5000";//本地开发地址
//    String baseUrl = "http://192.168.0.192:5000";//测试地址

    String baseUrl = "https://service-dev.jmjcm.com";//云端开发
//    String baseUrl = "https://service.jmjcm.com";//生产环境


    interface Umc{
    String baseUrl = "http://api.dev.jmjcm.com";//云端开发
//    String baseUrl = "http://192.168.0.192:5000";//云端测试地址

        String umcName = "/umc/doService";
        String login = "functionaryLoginService";
        //        String contactsListService = "contactsListService";
        String departmentListService = "departmentListService";
        String departmentLowerListService = "departmentLowerListService";
        String functionaryListService = "functionaryListService";
        String contactsListService = "contactsListService";
        String functionaryDetailService = "functionaryDetailService";
        String functionaryEditService = "functionaryEditService";
        String functionaryPwdModifyService = "functionaryPwdModifyService";
        String functionaryPwdRetrieveService = "functionaryPwdRetrieveService";
    }

    interface Crm {
        String crmName = "/crm/doService";

        String createReport = "workReportAddService";
        String workReportList = "workReportListService";
        String workReportDetailService = "workReportDetailService";
        String workReportAuditService = "workReportAuditService";


        String createProject = "projectApprovalAddService";
        String projectList = "projectApprovalListService";
        String projectDetsil = "projectApprovalDetailService";
        String fixedEditProject = "projectApprovalFixedEditService";
        String projectApproval = "projectApprovalAuditService";
        String changeConstructStatus = "constructStatusEditService";
        String submitDrawingSheet = "drawingSheetAddService";
        String downFileList = "relatedFilesListService";


        String supervisionStepListService = "supervisionStepListService";
        String supervisionStepAddService = "supervisionStepAddService";
        String supervisionCompleteService = "supervisionCompleteService";
        String supervisionFollowUpAddService = "supervisionFollowUpAddService";
        String supervisionFollowUpDetailService = "supervisionFollowUpDetailService";
        String supervisionFollowUpListService = "supervisionFollowUpListService";
        String trainingInvitationDetailService = "trainingInvitationDetailService";
        String trainingInvitationAddService = "trainingInvitationAddService";
        String receivablesOrderAddService = "receivablesOrderAddService";
        String flowLogListService = "flowLogListService";


        String intentionalCustomerAddService = "intentionalCustomerAddService";
        String intentionalCustomerEditService = "intentionalCustomerEditService";
        String intentionalCustomerListService = "intentionalCustomerListService";
        String intentionalCustomerDetailService = "intentionalCustomerDetailService";
        String customerAttachmentListService = "customerAttachmentListService";
        String customerFollowListService = "customerFollowListService";
        String customerTaskListService = "customerTaskListService";
        String customerFollowAddService = "customerFollowAddService";

        String customerFollowDetailService = "customerFollowDetailService";
        String customerTaskAddService = "customerTaskAddService";
        String cooperationAddService = "cooperationAddService";

        String intentionalCustomerTransferService = "intentionalCustomerTransferService";
        String intentionalCustomerSeaGetService = "intentionalCustomerSeaGetService";
        String customerTaskDetailService = "customerTaskDetailService";
        String customerTaskUpdateStatusService = "customerTaskUpdateStatusService";
        String customerTaskDelService = "customerTaskDelService";
        String customerTaskEditService = "customerTaskEditService";


        String refundListService = "refundListService";
        String refundAddService = "refundAddService";
        String refundDetailService = "refundDetailService";
        String refundAuditService = "refundAuditService";


        String investmentListService = "investmentListService";

        String investmentAmbientAddService = "investmentAmbientAddService";
        String investmentAmbientEditService = "investmentAmbientEditService";
        String investmentAmbientDetailService = "investmentAmbientDetailService";

        String marketAddService = "marketAddService";
        String marketEditService = "marketEditService";
        String marketDetailService = "marketDetailService";

        String investmentRivalAddService = "investmentRivalAddService";
        String investmentRivalDetailService = "investmentRivalDetailService";


        String receivablesOrderAuditService = "receivablesOrderAuditService";
        String receivablesOrderDetailService = "receivablesOrderDetailService";

        String workTaskAddService = "workTaskAddService";
        String workTaskListService = "workTaskListService";
        String workTaskDetailService = "workTaskDetailService";
        String workTaskStatusEditService = "workTaskStatusEditService";
        String workTaskDelService = "workTaskDelService";
        String workTaskEditService = "workTaskEditService";


        String scheduleAddService = "scheduleAddService";
        String scheduleListService = "scheduleListService";
        String scheduleDetailService = "scheduleDetailService";
        String scheduleDelService = "scheduleDelService";
        String scheduleUpdateStatusService = "scheduleUpdateStatusService";
        String scheduleEditService = "scheduleEditService";

        String bulletinAddService = "bulletinAddService";
        String bulletinListService = "bulletinListService";
        String bulletinDetailService = "bulletinDetailService";


        String investmentStatisticsService = "investmentStatisticsService";  //招商部首页基本数据
        String receivablesOrderRankingService = "receivablesOrderRankingService";//招商部
        String investmentFunctionaryStatisticsService = "investmentFunctionaryStatisticsService"; //招商部个人详情页数据
        String drawDepartDrawSheetModel = "drawDepartDrawSheetModel";//招商部绘图统计


        String spaceStatisticsService = "spaceStatisticsService";//空间部首页数据
        String spaceFunctionaryStatisticsService = "spaceFunctionaryStatisticsService";//空间部首页数据
        String franchiseeListService = "franchiseeListService";
        String franchiseeDetailService = "franchiseeDetailService";

        String cooperationDetailService = "cooperationDetailService";
        String cooperationEditService = "cooperationEditService";

    }

    interface Flowable {
        String FlowableName = "/flowable/doService";
        String processUserAndTaskListService = "processUserAndTaskListService";
        String approvalProcessListService = "approvalProcessListService";
        String userFlowGroupTaskPreviewService = "userFlowGroupTaskPreviewService";
        String flowableGroupUnfinishedCountService  = "flowableGroupUnfinishedCountService";


    }

    interface Product {
        String ProductName = "/product/doService";
        String officeCategoryModelList = "officeCategoryModelListService";
        String officeSupplyBillAddService = "officeSupplyBillAddService";
        String processInstListService = "processInstListService";
        String officeSupplyDetailService = "officeSupplyDetailService";
        String officeSupplyBillAuditService = "officeSupplyBillAuditService";
    }

    interface Mms {
        String MmsName = "/mms/doService";
        String userSigCreate = "userSigCreateService";
        String codeSendService = "codeSendService";


    }
}
