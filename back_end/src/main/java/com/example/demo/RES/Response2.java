package com.example.demo.RES;

import javax.swing.*;

public class Response2<T> {
    private String msg;
    private String isSuccess;
    private T data;

    public static <K>Response2<K> newSuccess(K data) {
        Response2<K> res = new Response2<K>();
        res.setMsg("successfully");
        res.setIsSuccess("success");
        res.setData(data);
        return res;
    }
    public static <K>Response2<K> newFailure(String Msg, K data) {
        Response2<K> res = new Response2<K>();
        res.setMsg(Msg);
        res.setIsSuccess("failure");
        res.setData(data);
        return res;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
