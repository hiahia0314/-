package com.example.demo.response;

import java.time.LocalDate;

public class ResponseForEvent <T>{
    private LocalDate date;
    private T events;


    public static <K>ResponseForEvent<K> newSuccess(K data,LocalDate localDate){
        ResponseForEvent<K> response = new ResponseForEvent<K>();
        response.setEvents(data);
        response.setDate(localDate);
        return response;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public T getEvents() {
        return events;
    }

    public void setEvents(T events) {
        this.events = events;
    }
}
