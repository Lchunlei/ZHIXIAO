package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
public class DrawMoneyReq {
    private Integer userId;
    private String drawMoney;
    private String thirdPwd;
    private String drawWayAccount;
    private String drawWayUser;
    private String drawWay;

    @Override
    public String toString() {
        return "DrawMoneyReq{" +
                "userId=" + userId +
                ", drawMoney='" + drawMoney + '\'' +
                ", thirdPwd='" + thirdPwd + '\'' +
                ", drawWayAccount='" + drawWayAccount + '\'' +
                ", drawWayUser='" + drawWayUser + '\'' +
                ", drawWay='" + drawWay + '\'' +
                '}';
    }

    public String getDrawWayUser() {
        return drawWayUser;
    }

    public void setDrawWayUser(String drawWayUser) {
        this.drawWayUser = drawWayUser;
    }

    public String getDrawWayAccount() {
        return drawWayAccount;
    }

    public void setDrawWayAccount(String drawWayAccount) {
        this.drawWayAccount = drawWayAccount;
    }

    public String getDrawWay() {
        return drawWay;
    }

    public void setDrawWay(String drawWay) {
        this.drawWay = drawWay;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDrawMoney() {
        return drawMoney;
    }

    public void setDrawMoney(String drawMoney) {
        this.drawMoney = drawMoney;
    }

    public String getThirdPwd() {
        return thirdPwd;
    }

    public void setThirdPwd(String thirdPwd) {
        this.thirdPwd = thirdPwd;
    }
}
