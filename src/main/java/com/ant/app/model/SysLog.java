package com.ant.app.model;

import java.util.Date;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class SysLog {
    private Integer logId;
    private Integer adminId;
    private String logInfo;
    private Date cTime;

    public SysLog() {
    }

    public SysLog(Integer adminId, String logInfo) {
        this.adminId = adminId;
        this.logInfo = logInfo;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "logId=" + logId +
                ", adminId=" + adminId +
                ", logInfo='" + logInfo + '\'' +
                ", cTime=" + cTime +
                '}';
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
