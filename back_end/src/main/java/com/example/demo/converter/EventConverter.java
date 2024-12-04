package com.example.demo.converter;

import com.example.demo.DTO.EventDTO;
import com.example.demo.dataAccess.Event;

import java.util.ArrayList;
import java.util.List;

public class EventConverter {
    public static EventDTO EventConvert(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setDescription(event.getDescription());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setType(event.getType());
        eventDTO.setUid(event.getUid());
        return eventDTO;
    }
    public static List<EventDTO> EventListConvert(List<Event> eventList) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventList) {
            eventDTOList.add(EventConvert(event));
        }
        return eventDTOList;
    }
}
