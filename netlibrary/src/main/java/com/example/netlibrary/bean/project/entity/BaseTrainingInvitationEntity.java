package com.example.netlibrary.bean.project.entity;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;

import java.util.List;

public class BaseTrainingInvitationEntity extends BaseRequest {
    /*
     * 培训跟踪
     * */
    private Boolean attended;//是否参加培训
    private Long date;//参加培训时间
    private List<Member> participants;//培训人员
    /*
     * 团队建设
     * */
    private List<Member> teamBuildingMembers;//团建人员
    /*
     * 渠道建设
     * */
    private String channel;//渠道
    private List<Attachment> attachments;//附件


    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<Member> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Member> participants) {
        this.participants = participants;
    }

    public List<Member> getTeamBuildingMembers() {
        return teamBuildingMembers;
    }

    public void setTeamBuildingMembers(List<Member> teamBuildingMembers) {
        this.teamBuildingMembers = teamBuildingMembers;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public static class Member {
        private String name;//姓名
        private String position;//职位
        private String phone;//电话

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
