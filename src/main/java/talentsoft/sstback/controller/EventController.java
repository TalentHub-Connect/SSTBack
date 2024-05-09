package talentsoft.sstback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Event;
import talentsoft.sstback.payload.request.EventRequest;
import talentsoft.sstback.payload.request.EventUpdateRequest;
import talentsoft.sstback.service.impl.EventService;


import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody EventRequest event) {
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer id, @RequestBody EventUpdateRequest eventRequest) throws ErrorDatabaseServiceException {
       try {
              if(eventService.updateEvent(id, eventRequest) != null) {
                  return ResponseEntity.ok(eventService.updateEvent(id, eventRequest));
              }
              return ResponseEntity.notFound().build();
       }catch (ErrorDatabaseServiceException e){
           return ResponseEntity.status(e.getStatusCode()).build();
       }
    }
}
