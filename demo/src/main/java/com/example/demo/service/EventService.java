package com.example.demo.service;

import com.example.demo.DTO.EventDTO;
import com.example.demo.response.ResponseForEvent;
import com.example.demo.dataAccess.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

     String addEvent(Event event);

    //long updateEvent(Event event,Long id);

     void deleteEventByUid(Long uid);

     List<LocalDate> findDateByUserId(String User);

     List<ResponseForEvent<List<EventDTO>>> getFormatEventsByUserId(String User);
}
