package com.ant.app.model;

import com.ant.app.utils.MoneyUtil;

import java.util.Date;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
public class UserDraw {
    private Integer drawId;
    private Integer userId;

    private Integer drawMoney;
    private String drawMoneyYuan;
    private Integer realDrawMoney;
    private String realDrawMoneyYuan;
    private Integer serviceMoney;
    private String serviceMoneyYuan;

    private Integer drawStatus;
    private String drawWayUser;
    private String drawWay;
    private String drawWayAccount;
    private Integer adminId;
    private Date confirmTime;
    private String ins;
    private Date cTime;

    public UserDraw() {
    }

    public UserDraw(Integer userId, Integer drawMoney, Integer realDrawMoney, Integer serviceMoney, String ins, String drawWay, String drawWayAccount,String drawWayUser) {
        this.userId = userId;
        this.drawMoney = drawMoney;
        this.realDrawMoney = realDrawMoney;
        this.serviceMoney = serviceMoney;
        this.drawWay = drawWay;
        this.ins = ins;
        this.drawWayAccount = drawWayAccount;
        this.drawWayUser=drawWayUser;
    }

    @Override
    public String toString() {
        return "UserDraw{" +
                "drawId=" + drawId +
                ", userId=" + userId +
                ", drawMoney=" + drawMoney +
                ", realDrawMoney=" + realDrawMoney +
                ", serviceMoney=" + serviceMoney +
                ", drawStatus=" + drawStatus +
                ", adminId=" + adminId +
                ", confirmTime=" + confirmTime +
                ", ins='" + ins + '\'' +
                ", cTime=" + cTime +
                '}';
    }

    public String getRealDrawMoneyYuan() {
        return realDrawMoneyYuan;
    }

    public void setRealDrawMoneyYuan(String realDrawMoneyYuan) {
        this.realDrawMoneyYuan = realDrawMoneyYuan;
    }

    public String getServiceMoneyYuan() {
        return serviceMoneyYuan;
    }

    public void setServiceMoneyYuan(String serviceMoneyYuan) {
        this.serviceMoneyYuan = serviceMoneyYuan;
    }

    public String getDrawMoneyYuan() {
        return drawMoneyYuan;
    }

    public void setDrawMoneyYuan(String drawMoneyYuan) {
        this.drawMoneyYuan = drawMoneyYuan;
    }

    public String getDrawWayUser() {
        return drawWayUser;
    }

    public void setDrawWayUser(String drawWayUser) {
        this.drawWayUser = drawWayUser;
    }

    public String getDrawWay() {
        return drawWay;
    }

    public void setDrawWay(String drawWay) {
        this.drawWay = drawWay;
    }

    public String getDrawWayAccount() {
        return drawWayAccount;
    }

    public void setDrawWayAccount(String drawWayAccount) {
        this.drawWayAccount = drawWayAccount;
    }

    public Integer getDrawId() {
        return drawId;
    }

    public void setDrawId(Integer drawId) {
        this.drawId = drawId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDrawMoney() {
        return drawMoney;
    }

    public void setDrawMoney(Integer drawMoney) {
        this.drawMoneyYuan= MoneyUtil.FenTurnYuan(drawMoney.toString());
        this.drawMoney = drawMoney;
    }

    public Integer getRealDrawMoney() {
        return realDrawMoney;
    }

    public void setRealDrawMoney(Integer realDrawMoney) {
        this.realDrawMoneyYuan = MoneyUtil.FenTurnYuan(realDrawMoney.toString());
        this.realDrawMoney = realDrawMoney;
    }

    public Integer getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Integer serviceMoney) {
        this.serviceMoneyYuan=MoneyUtil.FenTurnYuan(serviceMoney.toString());
        this.serviceMoney = serviceMoney;
    }

    public Integer getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(Integer drawStatus) {
        this.drawStatus = drawStatus;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
