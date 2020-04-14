package com.example.netlibrary.bean.schedule.req;

import com.example.netlibrary.bean.common.req.ReqPageList;
import com.example.netlibrary.bean.schedule.entity.BaseScheduleEntity;

public class ReqScheduleList extends ReqPageList {
    private Integer userIds;//下属日程 (约定：同一个userId，传userId查我的日程，传userIds,查我的下属的日程，两者只能传一个)
    private long from;
    private long to;

    BaseScheduleEntity.TaskStatusEnum scheduleStatus;

    public void setUserIds(Integer userIds) {
        this.userIds = userIds;
    }

    public BaseScheduleEntity.TaskStatusEnum getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(BaseScheduleEntity.TaskStatusEnum scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public int getUserIds() {
        return userIds;
    }

    public void setUserIds(int userIds){
        this.userIds = userIds;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}
