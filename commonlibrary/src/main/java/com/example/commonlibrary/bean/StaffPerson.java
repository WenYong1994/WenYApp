package com.example.commonlibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.staff.BaseStaff;
import com.example.commonlibrary.staff.StaffImp;
import com.example.commonlibrary.utils.StringUtils;

public class StaffPerson extends BaseStaff implements StaffImp, Parcelable {
    private int id;
    private String name;
    private String nickName;
    private String nickname;
    private String poster;
    private int roleId;
    private String roleName;
    private String departmentName;
    private String departmentId;
    private int functionaryId;
    private int directLeaderId;
    private String directLeaderName;
    private long updateAt;
    private String userName;
    private String username;
    private long createAt;
    private String phone;

    //附加字段
    private int parentId;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getFunctionaryId() {
        return functionaryId;
    }

    public void setFunctionaryId(int functionaryId) {
        this.functionaryId = functionaryId;
    }

    public int getDirectLeaderId() {
        return directLeaderId;
    }

    public void setDirectLeaderId(int directLeaderId) {
        this.directLeaderId = directLeaderId;
    }

    public String getDirectLeaderName() {
        return directLeaderName;
    }

    public void setDirectLeaderName(String directLeaderName) {
        this.directLeaderName = directLeaderName;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        if(StringUtils.isEmpty(nickName)){
            return nickname;
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String getAbsName() {
        if(StringUtils.isEmpty(nickName)){
            return nickname;
        }
        return nickName;
    }

    @Override
    public String getAbsJob() {
        return roleName;
    }

    @Override
    public String getAbsAvatar() {
        return poster;
    }

    @Override
    public int getId() {
        return functionaryId!=0?functionaryId:id;
    }

    @Override
    public boolean isGroup() {
        return false;
    }

    @Override
    public int getAbsParentId() {
        return parentId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nickName);
        dest.writeString(this.poster);
        dest.writeInt(this.roleId);
        dest.writeString(this.roleName);
        dest.writeString(this.departmentName);
        dest.writeString(this.departmentId);
        dest.writeInt(this.functionaryId);
        dest.writeInt(this.directLeaderId);
        dest.writeString(this.directLeaderName);
        dest.writeLong(this.updateAt);
        dest.writeString(this.userName);
        dest.writeLong(this.createAt);
        dest.writeString(this.phone);
        dest.writeInt(this.parentId);
    }

    public StaffPerson() {
    }

    protected StaffPerson(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.nickName = in.readString();
        this.poster = in.readString();
        this.roleId = in.readInt();
        this.roleName = in.readString();
        this.departmentName = in.readString();
        this.departmentId = in.readString();
        this.functionaryId = in.readInt();
        this.directLeaderId = in.readInt();
        this.directLeaderName = in.readString();
        this.updateAt = in.readLong();
        this.userName = in.readString();
        this.createAt = in.readLong();
        this.phone = in.readString();
        this.parentId = in.readInt();
    }

    public static final Creator<StaffPerson> CREATOR = new Creator<StaffPerson>() {
        @Override
        public StaffPerson createFromParcel(Parcel source) {
            return new StaffPerson(source);
        }

        @Override
        public StaffPerson[] newArray(int size) {
            return new StaffPerson[size];
        }
    };
}
