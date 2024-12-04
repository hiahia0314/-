package com.example.demo.controller;

import com.example.demo.DTO.EventDTO;
import com.example.demo.RES.Response2;
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
    public Response2 addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @DeleteMapping("/event/{Uid}")
    public Response2 deleteEventByUid(@PathVariable long Uid){
        return eventService.deleteEventByUid(Uid);

    }

    @GetMapping("/event/{UserId}")
    public Response2 getFormatEventsByUserId( @PathVariable String UserId){
        return eventService.getFormatEventsByUserId(UserId);
    }



}
