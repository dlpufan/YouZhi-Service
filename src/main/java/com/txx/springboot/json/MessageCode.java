package com.txx.springboot.json;

/**
 * @author 淘米水浇花
 */
public class MessageCode {
    private String message;
    private int code;

    public MessageCode() {
        message = "返回成功";
        code = 1;
    }

    public MessageCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
