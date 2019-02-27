package com.ant.app.entity.resp;

import com.ant.app.utils.MoneyUtil;

/**
 * Created by sfb_liuchunlei on 2019/2/26.
 */
public class UserWebInit {
    private String userNum;
    private Integer vipTotal;
    private Integer registCoin;
    private Integer point;

    private Integer balance;
    private Integer bWebIn;
    private String balanceYuan;
    private String bWebInYuan;
    @Override
    public String toString() {
        return "UserWebInit{" +
                "vipTotal=" + vipTotal +
                ", registCoin=" + registCoin +
                ", point=" + point +
                '}';
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balanceYuan= MoneyUtil.FenTurnYuan(balance.toString());
        this.balance = balance;
    }

    public Integer getbWebIn() {
        return bWebIn;
    }

    public void setbWebIn(Integer bWebIn) {
        this.bWebInYuan= MoneyUtil.FenTurnYuan(bWebIn.toString());
        this.bWebIn = bWebIn;
    }

    public String getBalanceYuan() {
        return balanceYuan;
    }

    public void setBalanceYuan(String balanceYuan) {
        this.balanceYuan = balanceYuan;
    }

    public String getbWebInYuan() {
        return bWebInYuan;
    }

    public void setbWebInYuan(String bWebInYuan) {
        this.bWebInYuan = bWebInYuan;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Integer getVipTotal() {
        return vipTotal;
    }

    public void setVipTotal(Integer vipTotal) {
        this.vipTotal = vipTotal;
    }

    public Integer getRegistCoin() {
        return registCoin;
    }

    public void setRegistCoin(Integer registCoin) {
        this.registCoin = registCoin;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
