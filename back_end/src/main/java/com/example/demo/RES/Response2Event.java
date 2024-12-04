package com.example.demo.RES;

import java.time.LocalDate;

public class Response2Event <T> extends Response2Data<T> {

    private T events;

    public static  <K>Response2Event<K> newSuccess(K events,String Msg) {
        Response2Event<K> response = new Response2Event<K>();
        response.setEvents(events);
        response.setIsSuccess("success");
        response.setMsg(Msg);
        return response;
    }


    public T getEvents() {
        return events;
    }

    public void setEvents(T events) {
        this.events = events;
    }
}
