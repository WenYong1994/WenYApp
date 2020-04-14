package com.example.commonlibrary.constant;

import java.text.SimpleDateFormat;

public interface JMJConstant {
    String SharedPreferencesKey = "jmj_SharedPreferences";
    String USER_DATA_KEY = "user_data_key";
    String LOGIN_DATA_KEY = "login_data_key";
    String RELOGIN = "relogin";

    String ADDRESS = "address";
    String USE_AREA = "user_area";

    String SUPERVISION_ENUM = "supervision_enum";

    String PHOTO_OUT_PATH = "jmj_photo";

    String OFFICE_SUPPLIES = "office_supplies";
    String ENABLE_EDITOR = "enable_editor";

    String SUPERVISION_STEP_ENTITY= "supervision_step_entity";

    String MINE_HINT_TAG = "mine_hint_tag";
    String UNDER_HINT_TAG = "under_hint_tag";
    String USER_AREA = "user_area";
    String FOLLOW_UP_ENTITY="follow_up_entity";


    String STAFF_PERSON = "staff_person";
    String APPROVE_TYPE = "approve_type";
    String APPROVE_WAIT = "approve_wait";




    String LOCATION="location";

    interface Project{
        String PROJECT_ID = "project_id";

    }

    interface WorkReport {
        String WORK_REPORT_TYPE = "work_report_type";
        String WORK_REPORT_ID = "work_report_id";
        String CAN_READ_OVER = "can_read_over";

    }

    interface Charge{
        String CHARGE_ID = "charge_id";

    }



    interface Contacts {
        int  CHOICE_STIFF = 101;
        int SEARCH_BUSINESS_STIFF = 102;
        int CONTACTS_LIST= 103;
        String CONTACT_ACTION = "contact_action";
    }

    interface Customer{
        String CUSTOMER_ENTITY = "customer_ENTITY";
        String CUSTOMER_ID = "customer_id";
        String CUSTOMER_NAME = "customer_name";
        String TAG_LIST = "tagList";
        String ORIGIN="origin";
        String CONDITION = "condition";
        String CATEGORY_LIST= "categoryList";
        String TRAITS_LIST = "traitsList";

        String CUSTOMER_FOLLOW_UP_ID = "customer_follow_up_id";
        String CUSTOMER_COOPERATION_ID = "customer_cooperation_id";
        String CHARGE_TYPE = "charge_type";
        String TASK_ID="task_id";
        String TASK_ENTITY="task_entity";

    }

    interface Refund{
        String REFUND_ID="refund_id";

    }


    interface Schedule{
        String SCHEDULE_ID = "schedule_id";
        String SCHEDULE = "schedule";
        String IS_MINE = "is_mine";

    }

    interface Action{
        String PROJECT_CHANGE = "paoject_change";
        String CUSTOMER_CHANGE = "customer_changeu";
        String CUSTOMER_LIST_CHANGE = "customer_list_change";
        String TRANSFER_CUSTOMER_SUCCESS = "transfer_customer_success";
        String CUSTOMER_TASK_CHANGE = "customer_task_change";

        String REFUND_LIST_CHANGE = "refund_list_change";
        String REFUND_CHANGE = "refund_change";


        String ANALYZE_CHANGE = "analyze_change";
        String MARK_ANALYZE_CHANGE = "mark_analyze_change";
        String INVENSTMENT_ANALYZE_CHANGE = "invenstment_mark_analyze_change";

        String APPROVE_LIST_CHANGE = "approve_list_change";

        String WORK_TASK_LIST_CHANGE = "work_task_list_change";
        String WORK_TASK_CHANGE = "work_task_list_change";

        String SCHEDULE_LIST_CHANGE = "schedule_list_change";
        String SCHEDULE_CHANGE = "schedule_change";


        String ANNOUNCEMENT_CHANGE = "announcement_change";

        String USER_INFO_CHANGE = "user_info_change";

    }


    interface Analysis {
        String ANALYSIS_ID = "analysis_id";

    }

    interface OfficeSupplies {
        String OFFICE_SUPPLIES_ID = "OfficeSuppliesId";
    }

    interface Approve{
        String changeType = "changeType";


    }

    interface WorkTask{
        String WORK_TASK_ID = "work_task_id";
        String WORK_TASK = "work_task";
    }

    interface Announcement{
        String ANNOUNCEMENT_ID = "announcement_id";
    }


    interface BusinessStaff{
        String USER_ID = "user_id";
        String USER_NAME = "user_name";
    }


    interface Franchisee{
        String FRANCHISEE_ID="franchisee_id";
    }


    interface Department{
        String SPACE="空间部";
        String SPACE_RED="空间部-红军";
        String SPACE_BLUE="空间部-蓝军";


        String SERVER="运营部";
        String SERVER_X_RED="运营部-小魔女红军";
        String SERVER_X_BLUE="运营部-小魔女蓝军";
        String SERVER_RED="运营部-垂感大师红军";
        String SERVER_BLUE="运营部-垂感大师蓝军";


        String MARKETING="营销部";
        String MARKETING_X_RED="营销部-小魔女红军";
        String MARKETING_X_BLUE="营销部-小魔女蓝军";
        String MARKETING_RED="营销部-垂感大师红军";
        String MARKETING_BLUE="营销部-垂感大师蓝军";



    }

}
