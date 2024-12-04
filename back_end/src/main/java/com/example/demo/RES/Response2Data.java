package com.example.demo.RES;

public class Response2Data<T> extends Response2{
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
