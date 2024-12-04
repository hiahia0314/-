package com.example.demo.service;

import com.example.demo.DTO.EventDTO;
import com.example.demo.RES.Response2;
import com.example.demo.response.ResponseForEvent;
import com.example.demo.dataAccess.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

     Response2<?> addEvent(Event event);

    //long updateEvent(Event event,Long id);

     Response2<?> deleteEventByUid(Long uid);

     List<LocalDate> findDateByUserId(String User);

     Response2<List<ResponseForEvent<List<EventDTO>>>> getFormatEventsByUserId(String User);
}
