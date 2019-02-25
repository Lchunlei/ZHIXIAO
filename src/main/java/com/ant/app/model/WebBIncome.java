package com.ant.app.model;

import com.ant.app.utils.MoneyUtil;

import java.util.Date;

/**
 * Created by sfb_liuchunlei on 2019/2/25.
 */
public class WebBIncome {
    private Integer webBid;
    private Integer userId;
    private Integer money;
    private String moneyYuan;
    private String ins;
    private Date cTime;

    public WebBIncome() {
    }

    public WebBIncome(Integer userId, Integer money) {
        this.userId = userId;
        this.money = money;
    }

    public String getMoneyYuan() {
        return moneyYuan;
    }

    public void setMoneyYuan(String moneyYuan) {
        this.moneyYuan = moneyYuan;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public Integer getWebBid() {
        return webBid;
    }

    public void setWebBid(Integer webBid) {
        this.webBid = webBid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.moneyYuan = MoneyUtil.FenTurnYuan(money.toString());
        this.money = money;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
