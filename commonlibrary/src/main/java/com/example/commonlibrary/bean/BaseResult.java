package com.example.commonlibrary.bean;

import com.example.commonlibrary.utils.StringUtils;

public class BaseResult<T> {
    private int code;
    private T data;
    private String msg;
    private String message;
    private int status;





    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        if(msg==null&&message==null){
            return "未知异常";
        }
        return StringUtils.getNotNullString3(msg)+StringUtils.getNotNullString3(message);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
