package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class AdminLogin {
    private String phoneNum;
    private String password;

    @Override
    public String toString() {
        return "AdminLogin{" +
                "phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
