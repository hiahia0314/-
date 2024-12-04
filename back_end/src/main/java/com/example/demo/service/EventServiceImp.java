package com.example.demo.service;

import com.example.demo.DTO.EventDTO;
import com.example.demo.RES.Response2;
//import com.example.demo.RES.Response2Event;
import com.example.demo.response.ResponseForEvent;
import com.example.demo.converter.EventConverter;
import com.example.demo.dataAccess.Event;
import com.example.demo.dataAccess.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class EventServiceImp implements EventService {

    @Autowired
    EventRepository eventRepository;


    @Override
    public Response2<?> addEvent(Event event){
        Event newEvent = new Event();
        newEvent.setType(event.getType());
        newEvent.setUser(event.getUser());
        newEvent.setDescription(event.getDescription());
        newEvent.setTitle(event.getTitle());
        newEvent.setDate(event.getDate());
        newEvent.setUid(event.getUid());
        Event savedEvent = eventRepository.save(newEvent);
        return Response2.newSuccess(null);
    }

    @Override
    public List<LocalDate> findDateByUserId(String UserId){
        return eventRepository.findDateByUser(UserId);
    }
    @Override
    public Response2<?> deleteEventByUid(Long Uid){
        eventRepository.deleteByUid(Uid);
        return Response2.newSuccess(null);
    }

    @Override
    public Response2<List<ResponseForEvent<List<EventDTO>>>> getFormatEventsByUserId(String UserId){
        List<ResponseForEvent<List<EventDTO>>> list = new ArrayList<>();
        List<LocalDate> dates = findDateByUserId(UserId);
        for(LocalDate date : dates){
            List<EventDTO> eventDTOList =EventConverter.EventListConvert
                    (eventRepository.findByDateAndUser(date,UserId));
            ResponseForEvent<List<EventDTO>> responseForEvent = ResponseForEvent.newSuccess(eventDTOList,date);
            list.add(responseForEvent);
        }
        return Response2.newSuccess(list);
    }
}
