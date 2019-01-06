package com.ant.app.entity;

/**
 * @author lchunlei
 * @date 2018/12/12
 */
public class AppWebResult<T> {
    private String resultCode;
    private String resultMsg;
    private String balance;//账户余额
    private Integer coin;//系统积分
    private T data;

    public AppWebResult() {
        this.resultCode="R000";
        this.resultMsg = "操作成功";
    }

    @Override
    public String toString() {
        return "AppWebResult{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
    public void setFail(String failMsg) {
        this.resultCode="R404";
        this.resultMsg = failMsg;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
