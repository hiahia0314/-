package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResponseForEvent <T>{
    private LocalDate localDate;
    private T data;


    public static <K>ResponseForEvent<K> newSuccess(K data,LocalDate localDate){
        ResponseForEvent<K> response = new ResponseForEvent<K>();
        response.setData(data);
        response.setLocalDate(localDate);
        return response;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
