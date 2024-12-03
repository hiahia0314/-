package com.example.demo.response;

public class Response <T>{

    private T data;
    private boolean success;
    private String Msg;
    //errorMsg 可以改成Msg，success or fail都可以返回Msg

    public static <K>Response<K> newSuccess(K data,String msg){
        Response<K> response = new Response<K>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static Response<Void> newError(String Msg){
        Response<Void> response = new Response<Void>();
        response.setSuccess(false);
        response.setMsg(Msg);
        return response;
    }
    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
