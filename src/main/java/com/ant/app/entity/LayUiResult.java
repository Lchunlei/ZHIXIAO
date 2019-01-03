package com.ant.app.entity;

import java.util.List;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class LayUiResult<T>  {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public LayUiResult() {
        this.code=0;
        this.msg="成功";
    }

    @Override
    public String toString() {
        return "LayUiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
