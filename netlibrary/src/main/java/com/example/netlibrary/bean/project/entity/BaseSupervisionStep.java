package com.example.netlibrary.bean.project.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.Locale;
import com.example.netlibrary.bean.project.req.ReqSupervisionStep;

import java.util.List;

public abstract class BaseSupervisionStep extends BaseRequest implements Parcelable {
    private String projectApprovalId;//关联立项书
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum;//施工监理第几阶段
    /*
     * 第一阶段数据
     * */
    private ReqSupervisionStep.MeasurementIndex stringing;//测量放线
    private ReqSupervisionStep.MeasurementIndex material;//材料进场
    /*
     * 第二阶段数据
     * */
    private ReqSupervisionStep.MeasurementIndex doorway;//门头外形制作
    private ReqSupervisionStep.MeasurementIndex wall;//隔墙搭建
    private ReqSupervisionStep.MeasurementIndex ceil;//木工吊顶，吊筋.龙骨
    /*
     * 第三阶段数据
     * */
    private ReqSupervisionStep.MeasurementIndex electricBox;//布线穿管.预埋线盒.配电箱
    private ReqSupervisionStep.MeasurementIndex ceilShrouding;//墙面.顶面封板(预留检修口)
    private ReqSupervisionStep.MeasurementIndex curtainBox;//墙面.顶面细部造型.窗帘盒安装
    /*
     * 第四阶段数据
     * */
    private ReqSupervisionStep.MeasurementIndex electricStringing;//电工精确放线,开孔尺寸
    private ReqSupervisionStep.MeasurementIndex floorTile;//地砖铺贴.地板安装
    private ReqSupervisionStep.MeasurementIndex wallPolish;//墙面打磨、顶面乳胶漆.油漆
    /*
     * 第五阶段数据
     * */
    private ReqSupervisionStep.MeasurementIndex wallDecoration;//墙纸、墙布、墙画粘贴
    private ReqSupervisionStep.MeasurementIndex decorationInstallation;//外部装饰安装(扣条.软膜.灯具.字体等）
    /*
     * 第六阶段数据
     * */
    private ReqSupervisionStep.MeasurementIndex deliver;//工程验收.交付
    private ReqSupervisionStep.MeasurementIndex videoCollection;//现场视频收集
    private ReqSupervisionStep.MeasurementIndex decorationEffectVideo;//样品效果视频

    private Locale locale;//到访地址
    private List<Attachment> attachments;//上传附件
    private String note;//备注

    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }


    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }

    public ReqSupervisionStep.MeasurementIndex getStringing() {
        return stringing;
    }

    public void setStringing(ReqSupervisionStep.MeasurementIndex stringing) {
        this.stringing = stringing;
    }

    public ReqSupervisionStep.MeasurementIndex getMaterial() {
        return material;
    }

    public void setMaterial(ReqSupervisionStep.MeasurementIndex material) {
        this.material = material;
    }

    public ReqSupervisionStep.MeasurementIndex getDoorway() {
        return doorway;
    }

    public void setDoorway(ReqSupervisionStep.MeasurementIndex doorway) {
        this.doorway = doorway;
    }

    public ReqSupervisionStep.MeasurementIndex getWall() {
        return wall;
    }

    public void setWall(ReqSupervisionStep.MeasurementIndex wall) {
        this.wall = wall;
    }

    public ReqSupervisionStep.MeasurementIndex getCeil() {
        return ceil;
    }

    public void setCeil(ReqSupervisionStep.MeasurementIndex ceil) {
        this.ceil = ceil;
    }

    public ReqSupervisionStep.MeasurementIndex getElectricBox() {
        return electricBox;
    }

    public void setElectricBox(ReqSupervisionStep.MeasurementIndex electricBox) {
        this.electricBox = electricBox;
    }

    public ReqSupervisionStep.MeasurementIndex getCeilShrouding() {
        return ceilShrouding;
    }

    public void setCeilShrouding(ReqSupervisionStep.MeasurementIndex ceilShrouding) {
        this.ceilShrouding = ceilShrouding;
    }

    public ReqSupervisionStep.MeasurementIndex getCurtainBox() {
        return curtainBox;
    }

    public void setCurtainBox(ReqSupervisionStep.MeasurementIndex curtainBox) {
        this.curtainBox = curtainBox;
    }

    public ReqSupervisionStep.MeasurementIndex getElectricStringing() {
        return electricStringing;
    }

    public void setElectricStringing(ReqSupervisionStep.MeasurementIndex electricStringing) {
        this.electricStringing = electricStringing;
    }

    public ReqSupervisionStep.MeasurementIndex getFloorTile() {
        return floorTile;
    }

    public void setFloorTile(ReqSupervisionStep.MeasurementIndex floorTile) {
        this.floorTile = floorTile;
    }

    public ReqSupervisionStep.MeasurementIndex getWallPolish() {
        return wallPolish;
    }

    public void setWallPolish(ReqSupervisionStep.MeasurementIndex wallPolish) {
        this.wallPolish = wallPolish;
    }

    public ReqSupervisionStep.MeasurementIndex getWallDecoration() {
        return wallDecoration;
    }

    public void setWallDecoration(ReqSupervisionStep.MeasurementIndex wallDecoration) {
        this.wallDecoration = wallDecoration;
    }

    public ReqSupervisionStep.MeasurementIndex getDecorationInstallation() {
        return decorationInstallation;
    }

    public void setDecorationInstallation(ReqSupervisionStep.MeasurementIndex decorationInstallation) {
        this.decorationInstallation = decorationInstallation;
    }

    public ReqSupervisionStep.MeasurementIndex getDeliver() {
        return deliver;
    }

    public void setDeliver(ReqSupervisionStep.MeasurementIndex deliver) {
        this.deliver = deliver;
    }

    public ReqSupervisionStep.MeasurementIndex getVideoCollection() {
        return videoCollection;
    }

    public void setVideoCollection(ReqSupervisionStep.MeasurementIndex videoCollection) {
        this.videoCollection = videoCollection;
    }

    public ReqSupervisionStep.MeasurementIndex getDecorationEffectVideo() {
        return decorationEffectVideo;
    }

    public void setDecorationEffectVideo(ReqSupervisionStep.MeasurementIndex decorationEffectVideo) {
        this.decorationEffectVideo = decorationEffectVideo;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public static class MeasurementIndex implements Parcelable {
        private Boolean timeFit = false;//时间是否吻合
        private Boolean reachTheStandard = false;//施工是否达标

        public boolean getTimeFit() {
            if(timeFit == null){
                return false;
            }
            return timeFit;
        }

        public void setTimeFit(boolean timeFit) {
            this.timeFit = timeFit;
        }

        public boolean getReachTheStandard() {
            if(reachTheStandard == null){
                return false;
            }
            return reachTheStandard;
        }

        public void setReachTheStandard(boolean reachTheStandard) {
            this.reachTheStandard = reachTheStandard;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.timeFit);
            dest.writeValue(this.reachTheStandard);
        }

        public MeasurementIndex() {
        }

        protected MeasurementIndex(Parcel in) {
            this.timeFit = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.reachTheStandard = (Boolean) in.readValue(Boolean.class.getClassLoader());
        }

        public static final Creator<MeasurementIndex> CREATOR = new Creator<MeasurementIndex>() {
            @Override
            public MeasurementIndex createFromParcel(Parcel source) {
                return new MeasurementIndex(source);
            }

            @Override
            public MeasurementIndex[] newArray(int size) {
                return new MeasurementIndex[size];
            }
        };

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.projectApprovalId);
        dest.writeInt(this.constructSupervisionEnum == null ? -1 : this.constructSupervisionEnum.ordinal());
        dest.writeParcelable(this.stringing, flags);
        dest.writeParcelable(this.material, flags);
        dest.writeParcelable(this.doorway, flags);
        dest.writeParcelable(this.wall, flags);
        dest.writeParcelable(this.ceil, flags);
        dest.writeParcelable(this.electricBox, flags);
        dest.writeParcelable(this.ceilShrouding, flags);
        dest.writeParcelable(this.curtainBox, flags);
        dest.writeParcelable(this.electricStringing, flags);
        dest.writeParcelable(this.floorTile, flags);
        dest.writeParcelable(this.wallPolish, flags);
        dest.writeParcelable(this.wallDecoration, flags);
        dest.writeParcelable(this.decorationInstallation, flags);
        dest.writeParcelable(this.deliver, flags);
        dest.writeParcelable(this.videoCollection, flags);
        dest.writeParcelable(this.decorationEffectVideo, flags);
        dest.writeSerializable(this.locale);
        dest.writeTypedList(this.attachments);
        dest.writeString(this.note);
    }

    public BaseSupervisionStep() {
    }

    protected BaseSupervisionStep(Parcel in) {
        this.projectApprovalId = in.readString();
        int tmpConstructSupervisionEnum = in.readInt();
        this.constructSupervisionEnum = tmpConstructSupervisionEnum == -1 ? null : EnumConstant.ConstructSupervisionEnum.values()[tmpConstructSupervisionEnum];
        this.stringing = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.material = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.doorway = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.wall = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.ceil = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.electricBox = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.ceilShrouding = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.curtainBox = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.electricStringing = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.floorTile = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.wallPolish = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.wallDecoration = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.decorationInstallation = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.deliver = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.videoCollection = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.decorationEffectVideo = in.readParcelable(MeasurementIndex.class.getClassLoader());
        this.locale = (Locale) in.readSerializable();
        this.attachments = in.createTypedArrayList(Attachment.CREATOR);
        this.note = in.readString();
    }

}
