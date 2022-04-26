package com.example.demo_m.controller;

import com.example.demo_m.entities.Event;
import com.example.demo_m.repository.EventRepository;
import com.example.demo_m.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("event")
public class EventController {
    @Autowired
    EventService eventService;
    @GetMapping("list")
    public ResponseEntity<List<Event>> findAll(){
        return ResponseEntity.ok(eventService.findAll());
    }
    @PostMapping("create")
    public ResponseEntity<Event> create(@RequestBody Event event){
        return ResponseEntity.ok(eventService.save(event));
    }
    @PutMapping("update")
    public ResponseEntity<Event> update(@RequestBody Event event){
        Optional<Event> eventOptional= eventService.findById(event.getId());
        if(eventOptional.isPresent()){
            BeanUtils.copyProperties(eventOptional,event);

            return ResponseEntity.ok(eventService.save(eventOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("delete")
    public  ResponseEntity<?> delete(@RequestParam("id") Long id){
        eventService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
    @GetMapping("findById")
    public ResponseEntity<Event> findById(@RequestParam("id")Long id){
        Optional<Event> eventOptional= eventService.findById(id);
        if(eventOptional.isPresent()){


            return ResponseEntity.ok(eventService.save(eventOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
