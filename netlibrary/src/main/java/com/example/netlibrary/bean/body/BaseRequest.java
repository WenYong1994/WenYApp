package com.example.netlibrary.bean.body;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseRequest implements Parcelable{
    private int uid;
    private String token;
    private Integer userId;
    private String username;//这个代表 phone
    private String nickname;//这个代表 真名
    private Integer departmentId;
    private String departmentName;
    private String poster;
    private String os;
    private String userName;
    private String nickName;


    public static final Creator<BaseRequest> CREATOR = new Creator<BaseRequest>() {
        @Override
        public BaseRequest createFromParcel(Parcel in) {
            return new BaseRequest(in);
        }

        @Override
        public BaseRequest[] newArray(int size) {
            return new BaseRequest[size];
        }
    };

    public String getUserName() {
        return userName==null?username:userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName == null?nickname:nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return userName==null?username:userName;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public BaseRequest() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.token);
        dest.writeValue(this.userId);
        dest.writeString(this.username);
        dest.writeString(this.nickname);
        dest.writeValue(this.departmentId);
        dest.writeString(this.departmentName);
        dest.writeString(this.poster);
        dest.writeString(this.os);
        dest.writeString(this.userName);
        dest.writeString(this.nickName);
    }

    protected BaseRequest(Parcel in) {
        this.uid = in.readInt();
        this.token = in.readString();
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.username = in.readString();
        this.nickname = in.readString();
        this.departmentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.departmentName = in.readString();
        this.poster = in.readString();
        this.os = in.readString();
        this.userName = in.readString();
        this.nickName = in.readString();
    }



}
