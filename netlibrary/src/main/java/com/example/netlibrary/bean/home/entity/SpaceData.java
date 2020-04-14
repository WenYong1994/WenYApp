package com.example.netlibrary.bean.home.entity;

public class SpaceData {
    private int departmentId;
    private String departmentName;//String
    private float securityDepositTotal;//float             //保证金总额
    private float sampleTotal;//float                      //样品总额
    private float hardDecorationTotal;//float              //硬装总额
    private float sincerityPriceTotal;//float              //诚意金总额
    private int projectApprovalCompleteCount;//int       //结案个数
    private float softDecorationTotal;//float              //软装总额
    private float serviceFeeTotal;//float                  //服务费总额
    private int hardDecorationCount;//int                //硬装个数
    private int softDecorationCount;//int                //软装个数
    private int projectApprovalCount;//int               //立项个数
    private DrawDepartDrawSheetModel drawSheet;//drawDepartDrawSheetModel     //

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public float getSecurityDepositTotal() {
        return securityDepositTotal;
    }

    public void setSecurityDepositTotal(float securityDepositTotal) {
        this.securityDepositTotal = securityDepositTotal;
    }

    public float getSampleTotal() {
        return sampleTotal;
    }

    public void setSampleTotal(float sampleTotal) {
        this.sampleTotal = sampleTotal;
    }

    public float getHardDecorationTotal() {
        return hardDecorationTotal;
    }

    public void setHardDecorationTotal(float hardDecorationTotal) {
        this.hardDecorationTotal = hardDecorationTotal;
    }

    public float getSincerityPriceTotal() {
        return sincerityPriceTotal;
    }

    public void setSincerityPriceTotal(float sincerityPriceTotal) {
        this.sincerityPriceTotal = sincerityPriceTotal;
    }

    public int getProjectApprovalCompleteCount() {
        return projectApprovalCompleteCount;
    }

    public void setProjectApprovalCompleteCount(int projectApprovalCompleteCount) {
        this.projectApprovalCompleteCount = projectApprovalCompleteCount;
    }

    public float getSoftDecorationTotal() {
        return softDecorationTotal;
    }

    public void setSoftDecorationTotal(float softDecorationTotal) {
        this.softDecorationTotal = softDecorationTotal;
    }

    public float getServiceFeeTotal() {
        return serviceFeeTotal;
    }

    public void setServiceFeeTotal(float serviceFeeTotal) {
        this.serviceFeeTotal = serviceFeeTotal;
    }

    public int getHardDecorationCount() {
        return hardDecorationCount;
    }

    public void setHardDecorationCount(int hardDecorationCount) {
        this.hardDecorationCount = hardDecorationCount;
    }

    public int getSoftDecorationCount() {
        return softDecorationCount;
    }

    public void setSoftDecorationCount(int softDecorationCount) {
        this.softDecorationCount = softDecorationCount;
    }

    public int getProjectApprovalCount() {
        return projectApprovalCount;
    }

    public void setProjectApprovalCount(int projectApprovalCount) {
        this.projectApprovalCount = projectApprovalCount;
    }

    public DrawDepartDrawSheetModel getDrawSheet() {
        return drawSheet;
    }

    public void setDrawSheet(DrawDepartDrawSheetModel drawSheet) {
        this.drawSheet = drawSheet;
    }

    public static class DrawDepartDrawSheetModel{
        private int departmentId=0;
        private String departmentName="";
        private int effectCompleteCount=0;        //效果图完成数
        private int effectRedesignCount=0;        //效果图设计中
        private int constructCompleteCount=0;     //施工图完成数
        private int constructRedesignCount=0;     //施工图二次设计
        private int flatCompleteCount=0;          //平面图完成数
        private int flatRedesignCount = 0;//平面图二次设计（个）




        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getEffectCompleteCount() {
            return effectCompleteCount;
        }

        public void setEffectCompleteCount(int effectCompleteCount) {
            this.effectCompleteCount = effectCompleteCount;
        }

        public int getEffectRedesignCount() {
            return effectRedesignCount;
        }

        public void setEffectRedesignCount(int effectRedesignCount) {
            this.effectRedesignCount = effectRedesignCount;
        }

        public int getConstructCompleteCount() {
            return constructCompleteCount;
        }

        public void setConstructCompleteCount(int constructCompleteCount) {
            this.constructCompleteCount = constructCompleteCount;
        }

        public int getConstructRedesignCount() {
            return constructRedesignCount;
        }

        public void setConstructRedesignCount(int constructRedesignCount) {
            this.constructRedesignCount = constructRedesignCount;
        }

        public int getFlatCompleteCount() {
            return flatCompleteCount;
        }

        public void setFlatCompleteCount(int flatCompleteCount) {
            this.flatCompleteCount = flatCompleteCount;
        }

        public int getFlatRedesignCount() {
            return flatRedesignCount;
        }

        public void setFlatRedesignCount(int flatRedesignCount) {
            this.flatRedesignCount = flatRedesignCount;
        }
    }

}

