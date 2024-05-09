package talentsoft.sstback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Incident;
import talentsoft.sstback.payload.request.IncidentRequest;
import talentsoft.sstback.service.impl.IncidentService;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllEvents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Integer id) {
        Incident incident = incidentService.getIncidentById(id);
        if (incident != null) {
            return ResponseEntity.ok(incident);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Incident> addIncident(@RequestBody IncidentRequest incident) throws ErrorDatabaseServiceException {
        try {
            return ResponseEntity.ok(incidentService.saveIncident(incident));
        } catch (ErrorDatabaseServiceException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Integer id, @RequestBody IncidentRequest incidentRequest) {
        try {
            Incident incident = incidentService.updateIncident(id, incidentRequest);
            if (incident != null) {
                return ResponseEntity.ok(incident);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ErrorDatabaseServiceException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Integer id) {
        try {
            incidentService.deleteIncident(id);
            return ResponseEntity.ok().build();
        } catch (ErrorDatabaseServiceException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }




}
