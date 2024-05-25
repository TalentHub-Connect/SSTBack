package talentsoft.sstback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Event;
import talentsoft.sstback.payload.request.EventRequest;
import talentsoft.sstback.payload.request.EventUpdateRequest;
import talentsoft.sstback.payload.request.EventUpdateStatusRequest;
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

    @Operation(summary = "Obtiene todos los eventos")
    @ApiResponse(responseCode = "200", description = "Eventos encontrados")
    @ApiResponse(responseCode = "404", description = "Eventos no encontrados")
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @Operation(summary = "Obtiene un evento por su id")
    @ApiResponse(responseCode = "200", description = "Evento encontrado")
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @ApiResponse(responseCode = "500", description = "Error en la base de datos")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtiene todos los eventos de una empresa")
    @ApiResponse(responseCode = "200", description = "Eventos encontrados")
    @ApiResponse(responseCode = "404", description = "Eventos no encontrados")
    @ApiResponse(responseCode = "500", description = "Error en la base de datos")
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Event>> getEventsByCompany(@PathVariable Integer companyId) {
        List<Event> events = eventService.getEventsByCompany(companyId);
        if (events.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }


    @Operation(summary = "Añade un evento")
    @ApiResponse(responseCode = "200", description = "Evento añadido")
    @ApiResponse(responseCode = "400", description = "Error al añadir el evento")
    @ApiResponse(responseCode = "500", description = "Error en la base de datos")
    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody EventRequest event) {
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

    @Operation(summary = "Actualiza el estado de un evento")
    @ApiResponse(responseCode = "200", description = "Evento actualizado")
    @ApiResponse(responseCode = "400", description = "Error al actualizar el evento")
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @PutMapping("/{id}/status")
    public ResponseEntity<Event> updateEventStatus(@PathVariable Integer id, @RequestBody EventUpdateStatusRequest updateRequest) {
        try {
            return ResponseEntity.ok(eventService.updateEventStatus(id,updateRequest.getStatus()));
        } catch (ErrorDatabaseServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Actualiza los detalles de un evento")
    @ApiResponse(responseCode = "200", description = "Evento actualizado")
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @PutMapping("/{id}/details")
    public ResponseEntity<Event> updateEventDetails(@PathVariable Integer id, @RequestBody EventUpdateRequest updates) {
        Event updated = eventService.updateEventDetails(id, updates.getStatus(), updates.getDescription());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
