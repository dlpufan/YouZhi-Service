package com.txx.springboot.json;



/**
 * @author 淘米水浇花
 */
public class Json {
    private MessageCode message;
    private Object data;
    public Json() {

    }
    public Json(boolean isTrue) {
        this.message = (isTrue)?new MessageCode():new MessageCode("error",400);
        this.data = null;
    }

    public Json(MessageCode message, Object data) {
        this.message = message;
        this.data = data;
    }
    public Json(Object data) {
        this.message = new MessageCode();
        this.data = data;
    }


    public MessageCode getMessage() {
        return message;
    }

    public void setMessage(MessageCode message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
