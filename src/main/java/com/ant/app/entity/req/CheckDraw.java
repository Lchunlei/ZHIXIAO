package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
public class CheckDraw {
    private Integer drawId;
    private Integer adminId;
    private Integer drawStatus;

    @Override
    public String toString() {
        return "CheckDraw{" +
                "drawId=" + drawId +
                ", drawStatus=" + drawStatus +
                '}';
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getDrawId() {
        return drawId;
    }

    public void setDrawId(Integer drawId) {
        this.drawId = drawId;
    }

    public Integer getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(Integer drawStatus) {
        this.drawStatus = drawStatus;
    }
}
