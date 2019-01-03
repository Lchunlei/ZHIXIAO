package com.ant.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class SysAdmin implements Serializable{
    private Integer adminId;
    private String phoneNum;
    private String password;
    private String nickName;
    private Integer usable;
    private Integer sysRole;
    private Date cTime;
    private Date uTime;

    @Override
    public String toString() {
        return "SysAdmin{" +
                "adminId=" + adminId +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", usable='" + usable + '\'' +
                ", sysRole=" + sysRole +
                '}';
    }

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSysRole() {
        return sysRole;
    }

    public void setSysRole(Integer sysRole) {
        this.sysRole = sysRole;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }
}
