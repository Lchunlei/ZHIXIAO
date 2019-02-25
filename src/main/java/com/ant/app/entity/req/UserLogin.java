package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2018/12/12
 */
public class UserLogin {
    private String userNum;
    private String firstPwd;

    @Override
    public String toString() {
        return "UserLogin{" +
                "userNum='" + userNum + '\'' +
                ", firstPwd='" + firstPwd + '\'' +
                '}';
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getFirstPwd() {
        return firstPwd;
    }

    public void setFirstPwd(String firstPwd) {
        this.firstPwd = firstPwd;
    }
}
