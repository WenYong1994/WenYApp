package com.example.netlibrary.bean.intentional_customer.entity;

import androidx.annotation.NonNull;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.Locale;

import java.util.List;

public class CustomerFollowUpEntity extends BaseRequest {

    public enum IntentionalBrandType {
        cgds("垂感大师"), xmn("小魔女");
        public String cnStr;

        IntentionalBrandType(String cnStr) {
            this.cnStr = cnStr;
        }

        @NonNull
        @Override
        public String toString() {
            return cnStr;
        }
    }

    private String customerId;
    private EnumConstant.FollowType followType;
    private long time;
    private String content;
    private Locale locale;
    private List<Attachment> attachments;




    //根据20200326新需求添加
    //意向加盟品牌名称
    private IntentionalBrandType intentionalBrand;

    //客户微信号
    private String wechat;

    public IntentionalBrandType getIntentionalBrand() {
        return intentionalBrand;
    }

    public void setIntentionalBrand(IntentionalBrandType intentionalBrand) {
        this.intentionalBrand = intentionalBrand;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public EnumConstant.FollowType getFollowType() {
        return followType;
    }

    public void setFollowType(EnumConstant.FollowType followType) {
        this.followType = followType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
