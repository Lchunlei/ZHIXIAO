package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2018/12/12
 */
public class UserLogin {
    private String phoneNum;
    private String firstPwd;

    @Override
    public String toString() {
        return "UserLogin{" +
                "phoneNum='" + phoneNum + '\'' +
                ", firstPwd='" + firstPwd + '\'' +
                '}';
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
}
