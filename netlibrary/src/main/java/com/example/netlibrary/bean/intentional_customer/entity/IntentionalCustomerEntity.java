package com.example.netlibrary.bean.intentional_customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Locale;

import java.util.ArrayList;
import java.util.List;

public class IntentionalCustomerEntity extends BaseRequest implements Parcelable {
    //id
    private String id;
    //客户姓名
    private String name;
    //联系电话1
    private String firstPhone;
    //联系电话2
    private String secondPhone;

    //地址
    Locale locale;

    //客户状态
    private EnumConstant.IntentionalCustomerStatus customerStatus;

    //客户情况
    private String situation;
    //经营痛点
    private String difficult;
    //客户等级
    private EnumConstant.Level level;
    //该客户归属的员工的id
    private int functionaryId;
    //该客户归属的员工姓名
//    private String nickName;
    //协作人的id
    private List<Integer> associateId;
    //该客户最新的跟进时间
    private long latestFollowTime;
    //公海还是私有
    private EnumConstant.Source source = EnumConstant.Source._private;

    //客户来源
    private EnumConstant.Origin origin;
    //客户品类
    private List<String> customerCategory;
    //开店条件
    private EnumConstant.Condition condition;
    //老板特质
    private List<String> traits;




    //    20200326新需求新增字段
    //现经营门店名称
    private String storeName;

    //现经营门店面积
    private String storeAcreage;

    //地推拜访人电话
    private String visitorPhone;


    private List<StaffPerson> associates;// 协作者列表

    public List<StaffPerson> getAssociates() {
        return associates;
    }

    public void setAssociates(List<StaffPerson> associates) {
        this.associates = associates;
    }

    public static Creator<IntentionalCustomerEntity> getCREATOR() {
        return CREATOR;
    }



    public EnumConstant.IntentionalCustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(EnumConstant.IntentionalCustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public EnumConstant.Level getLevel() {
        return level;
    }

    public void setLevel(EnumConstant.Level level) {
        this.level = level;
    }

    public int getFunctionaryId() {
        return functionaryId;
    }

    public void setFunctionaryId(int functionaryId) {
        this.functionaryId = functionaryId;
    }


    public List<Integer> getAssociateId() {
        return associateId;
    }

    public void setAssociateId(List<Integer> associateId) {
        this.associateId = associateId;
    }

    public long getLatestFollowTime() {
        return latestFollowTime;
    }

    public void setLatestFollowTime(long latestFollowTime) {
        this.latestFollowTime = latestFollowTime;
    }

    public EnumConstant.Source getSource() {
        return source;
    }

    public void setSource(EnumConstant.Source source) {
        this.source = source;
    }

    public EnumConstant.Origin getOrigin() {
        return origin;
    }

    public void setOrigin(EnumConstant.Origin origin) {
        this.origin = origin;
    }

    public List<String> getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(List<String> customerCategory) {
        this.customerCategory = customerCategory;
    }

    public EnumConstant.Condition getCondition() {
        return condition;
    }

    public void setCondition(EnumConstant.Condition condition) {
        this.condition = condition;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAcreage() {
        return storeAcreage;
    }

    public void setStoreAcreage(String storeAcreage) {
        this.storeAcreage = storeAcreage;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public List<Integer> getAssociateIds(){
        if(associates!=null){
            List<Integer> list = new ArrayList<>();
            for(StaffPerson staffPerson:associates){
                list.add(staffPerson.getId());
            }
            return list;
        }
        return null;
    }


    public IntentionalCustomerEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.firstPhone);
        dest.writeString(this.secondPhone);
        dest.writeSerializable(this.locale);
        dest.writeInt(this.customerStatus == null ? -1 : this.customerStatus.ordinal());
        dest.writeString(this.situation);
        dest.writeString(this.difficult);
        dest.writeInt(this.level == null ? -1 : this.level.ordinal());
        dest.writeInt(this.functionaryId);
        dest.writeList(this.associateId);
        dest.writeLong(this.latestFollowTime);
        dest.writeInt(this.source == null ? -1 : this.source.ordinal());
        dest.writeInt(this.origin == null ? -1 : this.origin.ordinal());
        dest.writeStringList(this.customerCategory);
        dest.writeInt(this.condition == null ? -1 : this.condition.ordinal());
        dest.writeStringList(this.traits);
        dest.writeString(this.storeName);
        dest.writeString(this.storeAcreage);
        dest.writeString(this.visitorPhone);
        dest.writeTypedList(this.associates);
    }

    protected IntentionalCustomerEntity(Parcel in) {
        super(in);
        this.id = in.readString();
        this.name = in.readString();
        this.firstPhone = in.readString();
        this.secondPhone = in.readString();
        this.locale = (Locale) in.readSerializable();
        int tmpCustomerStatus = in.readInt();
        this.customerStatus = tmpCustomerStatus == -1 ? null : EnumConstant.IntentionalCustomerStatus.values()[tmpCustomerStatus];
        this.situation = in.readString();
        this.difficult = in.readString();
        int tmpLevel = in.readInt();
        this.level = tmpLevel == -1 ? null : EnumConstant.Level.values()[tmpLevel];
        this.functionaryId = in.readInt();
        this.associateId = new ArrayList<Integer>();
        in.readList(this.associateId, Integer.class.getClassLoader());
        this.latestFollowTime = in.readLong();
        int tmpSource = in.readInt();
        this.source = tmpSource == -1 ? null : EnumConstant.Source.values()[tmpSource];
        int tmpOrigin = in.readInt();
        this.origin = tmpOrigin == -1 ? null : EnumConstant.Origin.values()[tmpOrigin];
        this.customerCategory = in.createStringArrayList();
        int tmpCondition = in.readInt();
        this.condition = tmpCondition == -1 ? null : EnumConstant.Condition.values()[tmpCondition];
        this.traits = in.createStringArrayList();
        this.storeName = in.readString();
        this.storeAcreage = in.readString();
        this.visitorPhone = in.readString();
        this.associates = in.createTypedArrayList(StaffPerson.CREATOR);
    }

    public static final Creator<IntentionalCustomerEntity> CREATOR = new Creator<IntentionalCustomerEntity>() {
        @Override
        public IntentionalCustomerEntity createFromParcel(Parcel source) {
            return new IntentionalCustomerEntity(source);
        }

        @Override
        public IntentionalCustomerEntity[] newArray(int size) {
            return new IntentionalCustomerEntity[size];
        }
    };
}
