package com.ant.app.entity.resp;

/**
 * Created by sfb_liuchunlei on 2019/2/26.
 */
public class UserWebInit {
    private String userNum;
    private Integer vipTotal;
    private Integer registCoin;
    private Integer point;

    @Override
    public String toString() {
        return "UserWebInit{" +
                "vipTotal=" + vipTotal +
                ", registCoin=" + registCoin +
                ", point=" + point +
                '}';
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
