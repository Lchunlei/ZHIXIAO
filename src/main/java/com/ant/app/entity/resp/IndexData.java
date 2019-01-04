package com.ant.app.entity.resp;

import com.ant.app.utils.MoneyUtil;

/**
 * @author lchunlei
 * @date 2019/1/4
 */
public class IndexData {
    private Integer totalIncome;
    private Integer totalUser;
    private Integer yesIncome;
    private Integer yesUser;

    private String totalIncomeYuan;
    private String yesIncomeYuan;

    @Override
    public String toString() {
        return "IndexData{" +
                "totalIncome=" + totalIncome +
                ", totalUser=" + totalUser +
                ", yesIncome=" + yesIncome +
                ", yesUser=" + yesUser +
                '}';
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncomeYuan= MoneyUtil.FenTurnYuan(totalIncome+"");
        this.totalIncome = totalIncome;
    }

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public Integer getYesIncome() {
        return yesIncome;
    }

    public void setYesIncome(Integer yesIncome) {
        this.yesIncomeYuan= MoneyUtil.FenTurnYuan(yesIncome+"");
        this.yesIncome = yesIncome;
    }

    public Integer getYesUser() {
        return yesUser;
    }

    public void setYesUser(Integer yesUser) {
        this.yesUser = yesUser;
    }

    public String getTotalIncomeYuan() {
        return totalIncomeYuan;
    }

    public void setTotalIncomeYuan(String totalIncomeYuan) {
        this.totalIncomeYuan = totalIncomeYuan;
    }

    public String getYesIncomeYuan() {
        return yesIncomeYuan;
    }

    public void setYesIncomeYuan(String yesIncomeYuan) {
        this.yesIncomeYuan = yesIncomeYuan;
    }
}
