package com.example.demo.controller;

import com.example.demo.DTO.EventDTO;
import com.example.demo.Response;
import com.example.demo.ResponseForEvent;
import com.example.demo.dataAccess.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public Response<Long> addEvent(@RequestBody Event event){
        return Response.newSuccess(eventService.addEvent(event));
    }

    @DeleteMapping("/event/{id}")
    public void deleteEventById(@PathVariable long id){
        eventService.deleteEventById(id);
    }

    @GetMapping("/eventDate/{id}")
    public List<ResponseForEvent<List<EventDTO>>> getFormatEventsByUserId( @PathVariable long id){
        return eventService.getFormatEventsByUserId(id);
    }



}
