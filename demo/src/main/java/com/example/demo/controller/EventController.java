package com.example.demo.controller;

import com.example.demo.DTO.EventDTO;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseForEvent;
import com.example.demo.dataAccess.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public Response<String> addEvent(@RequestBody Event event){
        return Response.newSuccess(eventService.addEvent(event),"success");
    }

    @DeleteMapping("/event/{Uid}")
    public void deleteEventByUid(@PathVariable long Uid){
        eventService.deleteEventByUid(Uid);
    }

    @GetMapping("/event/{UserId}")
    public List<ResponseForEvent<List<EventDTO>>> getFormatEventsByUserId( @PathVariable String UserId){
        return eventService.getFormatEventsByUserId(UserId);
    }



}
