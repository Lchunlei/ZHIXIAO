package com.ant.app.model;

import com.ant.app.utils.MoneyUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sfb_liuchunlei on 2019/1/6.
 */
public class UserIncome implements Serializable{
    private Integer incomeId;
    private Integer userId;
    private String ins;
    private Integer money;
    private Date cTime;

    private String moneyYuan;

    public UserIncome() {
    }

    public UserIncome(Integer userId, String ins, Integer money) {
        this.userId = userId;
        this.ins = ins;
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserIncome{" +
                "incomeId=" + incomeId +
                ", userId=" + userId +
                ", ins='" + ins + '\'' +
                ", money=" + money +
                ", cTime=" + cTime +
                '}';
    }

    public String getMoneyYuan() {
        return moneyYuan;
    }

    public void setMoneyYuan(String moneyYuan) {
        this.moneyYuan = moneyYuan;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.setMoneyYuan(MoneyUtil.FenTurnYuan(money+""));
        this.money = money;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
