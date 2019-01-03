package com.ant.app.exception;

/**
 * @author lchunlei
 * @date 2019/1/2
 */
public class AddUserException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        this.message="添加用户异常！";
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
