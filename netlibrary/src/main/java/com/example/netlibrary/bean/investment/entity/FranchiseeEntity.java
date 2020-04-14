package com.example.netlibrary.bean.investment.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.Investor;
import com.example.netlibrary.bean.common.entity.Locale;

import java.math.BigDecimal;
import java.util.List;

public class FranchiseeEntity {
    //列表展示类别
    public enum listType {
        //我的加盟商
        me
    }

    private String id;
    private StaffPerson customerDirector;//客户管家
    private ReceiptCompanyEnum receiptCompany;//收款公司
    private String bankBranch;//开户名称
    private String bankName;//开户银行
    private String bankAccount;//银行账号
    private Long latestFollowUpDate;//最新跟进时间

    private Integer userId;//加盟商关联立项人ID
    private String username;//加盟商关联立项人姓名
    private int departmentId;//加盟商关联立项人部门ID
    private String departmentName;//加盟商关联立项人部门名称
    /*
     * 店面信息
     * */
    private String name;//客户姓名
    private String firstPhone;//联系电话1
    private String secondPhone; //联系电话2
    private String shopName;//店铺名称
    private Locale locale;//地理位置
    private String shopCategoryLevel;//店面类型级别
    private String shopCategoryDecoration;//店面类型装修
    private String shopCategoryLocation;//店面类型位置
    private String monthPurchaseTask;//月采购任务
    /*
     * 店面结构
     */
    private BigDecimal rent;// 租金/年
    private long contractDeadline;// 合同截止日期
    private List<Attachment> contractFile;//合同附件
    private float useArea;// 使用面积
    private float high;//* 层高/米
    /*
     投资结构
     */
    private EnumConstant.InvestmentType investmentType = EnumConstant.InvestmentType.sole;
    private String investor;//独资投资人姓名
    private String investorPhone;//独资投资人电话
    private List<Investor> investorList;//合资投资人信息
    /*
     * 店面负责人信息
     */
    private String leaderName;//* 负责人姓名
    private String leaderPhone;//联系电话
    private int leaderAge;//年龄
    private String leaderExperience;//从业经历
    private Boolean leaderBusinessItem = true;//同业   异业
    private String leaderAdvantage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReceiptCompanyEnum getReceiptCompany() {
        return receiptCompany;
    }

    public void setReceiptCompany(ReceiptCompanyEnum receiptCompany) {
        this.receiptCompany = receiptCompany;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Long getLatestFollowUpDate() {
        return latestFollowUpDate;
    }

    public void setLatestFollowUpDate(Long latestFollowUpDate) {
        this.latestFollowUpDate = latestFollowUpDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMonthPurchaseTask() {
        return monthPurchaseTask;
    }

    public void setMonthPurchaseTask(String monthPurchaseTask) {
        this.monthPurchaseTask = monthPurchaseTask;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
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

    public float getUseArea() {
        return useArea;
    }

    public void setUseArea(float useArea) {
        this.useArea = useArea;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
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

    public Boolean getLeaderBusinessItem() {
        return leaderBusinessItem;
    }

    public void setLeaderBusinessItem(Boolean leaderBusinessItem) {
        this.leaderBusinessItem = leaderBusinessItem;
    }

    public String getLeaderAdvantage() {
        return leaderAdvantage;
    }

    public void setLeaderAdvantage(String leaderAdvantage) {
        this.leaderAdvantage = leaderAdvantage;
    }
}
