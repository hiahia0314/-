package com.example.demo.response;

public class Response <T>{

    private T data;
    private String isSuccess;
    private String msg;


    public static <K>Response<K> newSuccess(K data,String msg){
        Response<K> response = new Response<K>();
        response.setData(data);
        response.setIsSuccess("success");
        return response;
    }

    public static Response<Void> newError(String Msg){
        Response<Void> response = new Response<Void>();
        response.setIsSuccess("failure");
        response.setMsg(Msg);
        return response;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
}
