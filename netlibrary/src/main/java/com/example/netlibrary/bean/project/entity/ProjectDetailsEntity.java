package com.example.netlibrary.bean.project.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.BasicFlowableEntity;
import com.example.netlibrary.bean.common.entity.Investor;
import com.example.netlibrary.bean.common.entity.Locale;

import java.util.List;

public class ProjectDetailsEntity extends BasicFlowableEntity {

    /**
     * 成交来源
     *
     * @author xiaohua
     * @version 1.0
     * @date 2020-03-27 09:14
     */
    public enum DealSource {
        //电话邀约、地推邀约、转分配资源、经销商介绍、其他
        phone("电话邀约"), ground_promote("地推邀约"), transfer("转分配资源"), agent_introduce("经销商转介绍"), other("其他");

        public String cnStr;

        DealSource(String cnStr) {
            this.cnStr = cnStr;
        }
    }

    /**
     * 立项属性
     *
     * @author xiaohua
     * @version 1.0
     * @date 2020-03-27 09:15
     */
    public enum ProjectType {
        //新招、换商
        first("新招"), replace("换商");
        public String cnStr;

        ProjectType(String cnStr) {
            this.cnStr = cnStr;
        }
    }


    private String intentionalCustomerId;
    private String customerName;
    private String customerIdCard;//客户身份证
    private int customerAge;//客户年龄
    private String firstPhone;
    private String secondPhone;
    private ProjectType projectType;//立项属性
    private DealSource dealSource;//成交来源
    private String otherDealSource;//其他成交来源
    private String shopName;
    private Locale locale;
    private String shopCategoryLevel;
    private String shopCategoryDecoration;
    private String shopCategoryLocation;
    private double monthPurchaseTask;
    private double rent;
    private long contractDeadline;
    private List<Attachment> contractFile;
    private double useArea;
    private double high;
    private EnumConstant.InvestmentType investmentType;
    private String investor;
    private String investorPhone;
    private List<Investor> investorList;
    private String leaderName;
    private String leaderPhone;
    private int leaderAge;
    private String leaderExperience;
    private boolean leaderBusinessItem;
    private String leaderAdvantage;
    private double budgetPrice;
    private double budgetYZPrice;
    private double budgetRZPrice;
    private boolean acceptRZ;
    private double budgetSample;
    private int manSampleNum;
    private long sampleOrderTime;
    private double budgetBrandMaterial;
    private double budgetBrand;
    private double budgetDisplayWindow;
    private double budgetSchemeMachine;
    private double budgetColorCardCabinet;
    private double budgetOther;
    private long plannedConstructionTime;
    private long plannedBusinessTime;
    private String note;

    private String id;

    private List<StaffPerson> ccList;//抄送人集合
    /*
     * 空间部对立项详情校准字段
     */
    private String fixedAddress;//校准后详细地址
    private float fixedUseArea;//校准后使用面积
    private String fixedDetailAuditReason;//审核理由
    private List<Attachment> fixedDetailAuditAttachments;//审核上传附件

    private EnumConstant.ConstructStatus constructStatus;
    private EnumConstant.DrawingSheetEnum drawingSheetEnum;
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum = EnumConstant.ConstructSupervisionEnum.step0;
    private EnumConstant.FixedDetailStatus fixedDetailStatus = EnumConstant.FixedDetailStatus.not_fixed;//是否校准过详细地址和使用面积


    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public DealSource getDealSource() {
        return dealSource;
    }

    public void setDealSource(DealSource dealSource) {
        this.dealSource = dealSource;
    }

    public String getOtherDealSource() {
        return otherDealSource;
    }

    public void setOtherDealSource(String otherDealSource) {
        this.otherDealSource = otherDealSource;
    }

    public List<StaffPerson> getCcList() {
        return ccList;
    }

    public void setCcList(List<StaffPerson> ccList) {
        this.ccList = ccList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnumConstant.ConstructStatus getConstructStatus() {
        return constructStatus;
    }

    public void setConstructStatus(EnumConstant.ConstructStatus constructStatus) {
        this.constructStatus = constructStatus;
    }

    public EnumConstant.FixedDetailStatus getFixedDetailStatus() {
        return fixedDetailStatus;
    }

    public void setFixedDetailStatus(EnumConstant.FixedDetailStatus fixedDetailStatus) {
        this.fixedDetailStatus = fixedDetailStatus;
    }

    public String getFixedAddress() {
        return fixedAddress;
    }

    public void setFixedAddress(String fixedAddress) {
        this.fixedAddress = fixedAddress;
    }

    public float getFixedUseArea() {
        return fixedUseArea;
    }

    public void setFixedUseArea(float fixedUseArea) {
        this.fixedUseArea = fixedUseArea;
    }

    public List<Attachment> getFixedDetailAuditAttachments() {
        return fixedDetailAuditAttachments;
    }

    public void setFixedDetailAuditAttachments(List<Attachment> fixedDetailAuditAttachments) {
        this.fixedDetailAuditAttachments = fixedDetailAuditAttachments;
    }

    public String getFixedDetailAuditReason() {
        return fixedDetailAuditReason;
    }

    public void setFixedDetailAuditReason(String fixedDetailAuditReason) {
        this.fixedDetailAuditReason = fixedDetailAuditReason;
    }

    public String getIntentionalCustomerId() {
        return intentionalCustomerId;
    }

    public void setIntentionalCustomerId(String intentionalCustomerId) {
        this.intentionalCustomerId = intentionalCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getShopCategoryLevel() {
        return shopCategoryLevel;
    }

    public void setShopCategoryLevel(String shopCategoryLevel) {
        this.shopCategoryLevel = shopCategoryLevel;
    }

    public String getShopCategoryDecoration() {
        return shopCategoryDecoration;
    }

    public void setShopCategoryDecoration(String shopCategoryDecoration) {
        this.shopCategoryDecoration = shopCategoryDecoration;
    }

    public String getShopCategoryLocation() {
        return shopCategoryLocation;
    }

    public void setShopCategoryLocation(String shopCategoryLocation) {
        this.shopCategoryLocation = shopCategoryLocation;
    }

    public double getMonthPurchaseTask() {
        return monthPurchaseTask;
    }

    public void setMonthPurchaseTask(double monthPurchaseTask) {
        this.monthPurchaseTask = monthPurchaseTask;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public long getContractDeadline() {
        return contractDeadline;
    }

    public void setContractDeadline(long contractDeadline) {
        this.contractDeadline = contractDeadline;
    }

    public List<Attachment> getContractFile() {
        return contractFile;
    }

    public void setContractFile(List<Attachment> contractFile) {
        this.contractFile = contractFile;
    }

    public double getUseArea() {
        return useArea;
    }

    public void setUseArea(double useArea) {
        this.useArea = useArea;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public EnumConstant.InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(EnumConstant.InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInvestorPhone() {
        return investorPhone;
    }

    public void setInvestorPhone(String investorPhone) {
        this.investorPhone = investorPhone;
    }

    public List<Investor> getInvestorList() {
        return investorList;
    }

    public void setInvestorList(List<Investor> investorList) {
        this.investorList = investorList;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public int getLeaderAge() {
        return leaderAge;
    }

    public void setLeaderAge(int leaderAge) {
        this.leaderAge = leaderAge;
    }

    public String getLeaderExperience() {
        return leaderExperience;
    }

    public void setLeaderExperience(String leaderExperience) {
        this.leaderExperience = leaderExperience;
    }

    public boolean isLeaderBusinessItem() {
        return leaderBusinessItem;
    }

    public void setLeaderBusinessItem(boolean leaderBusinessItem) {
        this.leaderBusinessItem = leaderBusinessItem;
    }

    public String getLeaderAdvantage() {
        return leaderAdvantage;
    }

    public void setLeaderAdvantage(String leaderAdvantage) {
        this.leaderAdvantage = leaderAdvantage;
    }

    public double getBudgetPrice() {
        return budgetPrice;
    }

    public void setBudgetPrice(double budgetPrice) {
        this.budgetPrice = budgetPrice;
    }

    public double getBudgetYZPrice() {
        return budgetYZPrice;
    }

    public void setBudgetYZPrice(double budgetYZPrice) {
        this.budgetYZPrice = budgetYZPrice;
    }

    public double getBudgetRZPrice() {
        return budgetRZPrice;
    }

    public void setBudgetRZPrice(double budgetRZPrice) {
        this.budgetRZPrice = budgetRZPrice;
    }

    public boolean isAcceptRZ() {
        return acceptRZ;
    }

    public void setAcceptRZ(boolean acceptRZ) {
        this.acceptRZ = acceptRZ;
    }

    public double getBudgetSample() {
        return budgetSample;
    }

    public void setBudgetSample(double budgetSample) {
        this.budgetSample = budgetSample;
    }

    public int getManSampleNum() {
        return manSampleNum;
    }

    public void setManSampleNum(int manSampleNum) {
        this.manSampleNum = manSampleNum;
    }

    public long getSampleOrderTime() {
        return sampleOrderTime;
    }

    public void setSampleOrderTime(long sampleOrderTime) {
        this.sampleOrderTime = sampleOrderTime;
    }

    public double getBudgetBrandMaterial() {
        return budgetBrandMaterial;
    }

    public void setBudgetBrandMaterial(double budgetBrandMaterial) {
        this.budgetBrandMaterial = budgetBrandMaterial;
    }

    public double getBudgetBrand() {
        return budgetBrand;
    }

    public void setBudgetBrand(double budgetBrand) {
        this.budgetBrand = budgetBrand;
    }

    public double getBudgetDisplayWindow() {
        return budgetDisplayWindow;
    }

    public void setBudgetDisplayWindow(double budgetDisplayWindow) {
        this.budgetDisplayWindow = budgetDisplayWindow;
    }

    public double getBudgetSchemeMachine() {
        return budgetSchemeMachine;
    }

    public void setBudgetSchemeMachine(double budgetSchemeMachine) {
        this.budgetSchemeMachine = budgetSchemeMachine;
    }

    public double getBudgetColorCardCabinet() {
        return budgetColorCardCabinet;
    }

    public void setBudgetColorCardCabinet(double budgetColorCardCabinet) {
        this.budgetColorCardCabinet = budgetColorCardCabinet;
    }

    public double getBudgetOther() {
        return budgetOther;
    }

    public void setBudgetOther(double budgetOther) {
        this.budgetOther = budgetOther;
    }

    public long getPlannedConstructionTime() {
        return plannedConstructionTime;
    }

    public void setPlannedConstructionTime(long plannedConstructionTime) {
        this.plannedConstructionTime = plannedConstructionTime;
    }

    public long getPlannedBusinessTime() {
        return plannedBusinessTime;
    }

    public void setPlannedBusinessTime(long plannedBusinessTime) {
        this.plannedBusinessTime = plannedBusinessTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public EnumConstant.DrawingSheetEnum getDrawingSheetEnum() {
        return drawingSheetEnum;
    }

    public void setDrawingSheetEnum(EnumConstant.DrawingSheetEnum drawingSheetEnum) {
        this.drawingSheetEnum = drawingSheetEnum;
    }

    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }
}
