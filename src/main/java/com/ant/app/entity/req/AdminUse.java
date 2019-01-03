package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class AdminUse {
    private Integer adminId;
    private Integer usable;

    @Override
    public String toString() {
        return "AdminUse{" +
                "adminId=" + adminId +
                ", usable=" + usable +
                '}';
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }
}
