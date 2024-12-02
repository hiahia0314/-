package com.example.demo.service;

import com.example.demo.DTO.EventDTO;
import com.example.demo.response.ResponseForEvent;
import com.example.demo.dataAccess.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
     List<EventDTO> getEventsByUserId(long UserId);

     long addEvent(Event event);

    //long updateEvent(Event event,Long id);

     void deleteEventByUid(Long uid);

     List<LocalDate> findDateByUserId(long UserId);

     List<ResponseForEvent<List<EventDTO>>> getFormatEventsByUserId(long UserId);
}
