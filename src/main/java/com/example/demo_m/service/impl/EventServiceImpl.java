package com.example.demo_m.service.impl;

import com.example.demo_m.entities.Event;
import com.example.demo_m.repository.EventRepository;
import com.example.demo_m.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;
    @Override
    public Event save(Event entity) {
        return eventRepository.save(entity);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
eventRepository.deleteById(id);
    }
}
