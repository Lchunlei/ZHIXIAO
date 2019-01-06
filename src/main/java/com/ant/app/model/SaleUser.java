package com.ant.app.model;

import com.ant.app.utils.MoneyUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lchunlei
 * @date 2018/12/12
 */
public class SaleUser implements Serializable{

    private Integer userId;
    private String userName;
    private String phoneNum;
    private String firstPwd;
    private String secondPwd;
    private String thirdPwd;
    private Integer activated;//激活状态0未激活1已激活
    private Integer userStatus;
    private Integer treeSupId;
    private Integer treeLeft;
    private Integer treeRight;
    private Integer refereeId;//推荐人ID
    private Integer puserId;//所属报单中心ID
    private Integer luckEnd;//0幸运分红未结束1已结束
    private Integer joinMoney;//加盟费
    private Integer totalIncome;
    private Integer balance;
    private Integer coin;
    private Integer registeCore;
    private Integer registeCoreMoney;//报单中心费
    private Date uTime;
    private Date cTime;

    private String refereePhoneNum;
    private String oldFirstPwd;
    private String joinMoneyYuan;
    @Override
    public String toString() {
        return "SaleUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", puserId=" + puserId +
                ", refereeId=" + refereeId +
                ", treeSupId=" + treeSupId +
                ", joinMoney=" + joinMoney +
                ", registeCore=" + registeCore +
                ", uTime=" + uTime +
                ", cTime=" + cTime +
                '}';
    }

    public Integer getLuckEnd() {
        return luckEnd;
    }

    public void setLuckEnd(Integer luckEnd) {
        this.luckEnd = luckEnd;
    }

    public String getJoinMoneyYuan() {
        return joinMoneyYuan;
    }

    public void setJoinMoneyYuan(String joinMoneyYuan) {
        this.joinMoneyYuan = joinMoneyYuan;
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public String getOldFirstPwd() {
        return oldFirstPwd;
    }

    public void setOldFirstPwd(String oldFirstPwd) {
        this.oldFirstPwd = oldFirstPwd;
    }

    public String getRefereePhoneNum() {
        return refereePhoneNum;
    }

    public void setRefereePhoneNum(String refereePhoneNum) {
        this.refereePhoneNum = refereePhoneNum;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

    public Integer getTreeLeft() {
        return treeLeft;
    }

    public void setTreeLeft(Integer treeLeft) {
        this.treeLeft = treeLeft;
    }

    public Integer getTreeRight() {
        return treeRight;
    }

    public void setTreeRight(Integer treeRight) {
        this.treeRight = treeRight;
    }

    public Integer getTreeSupId() {
        return treeSupId;
    }

    public void setTreeSupId(Integer treeSupId) {
        this.treeSupId = treeSupId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getRegisteCoreMoney() {
        return registeCoreMoney;
    }

    public void setRegisteCoreMoney(Integer registeCoreMoney) {
        this.registeCoreMoney = registeCoreMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFirstPwd() {
        return firstPwd;
    }

    public void setFirstPwd(String firstPwd) {
        this.firstPwd = firstPwd;
    }

    public String getSecondPwd() {
        return secondPwd;
    }

    public void setSecondPwd(String secondPwd) {
        this.secondPwd = secondPwd;
    }

    public String getThirdPwd() {
        return thirdPwd;
    }

    public void setThirdPwd(String thirdPwd) {
        this.thirdPwd = thirdPwd;
    }

    public Integer getPuserId() {
        return puserId;
    }

    public void setPuserId(Integer puserId) {
        this.puserId = puserId;
    }

    public Integer getJoinMoney() {
        return joinMoney;
    }

    public void setJoinMoney(Integer joinMoney) {
        this.joinMoneyYuan = MoneyUtil.FenTurnYuan(joinMoney+"");
        this.joinMoney = joinMoney;
    }

    public Integer getRegisteCore() {
        return registeCore;
    }

    public void setRegisteCore(Integer registeCore) {
        this.registeCore = registeCore;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
