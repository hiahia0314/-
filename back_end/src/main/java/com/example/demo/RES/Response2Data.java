package com.example.demo.RES;

public class Response2Data<T> extends Response2{
    private T data;

    public static  <K>Response2Data<K> newSuccess(K data,String Msg) {
        Response2Data<K> response = new Response2Event<K>();
        response.setData(data);
        response.setIsSuccess("success");
        response.setMsg(Msg);
        return response;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
