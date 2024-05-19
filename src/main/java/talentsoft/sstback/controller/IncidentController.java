package talentsoft.sstback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Incident;
import talentsoft.sstback.payload.request.IncidentRequest;
import talentsoft.sstback.payload.request.UpdateIncidentRequest;
import talentsoft.sstback.payload.request.updateIncidentStatusRequest;
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

    @Operation(summary = "Obtiene todos los incidentes")
    @ApiResponse(responseCode = "200", description = "Incidentes encontrados")
    @ApiResponse(responseCode = "404", description = "Incidentes no encontrados")
    @ApiResponse(responseCode = "500", description = "Error en la base de datos")
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

    @Operation(summary = "Obtiene todos los incidentes de una empresa")
    @ApiResponse(responseCode = "200", description = "Incidentes encontrados")
    @ApiResponse(responseCode = "404", description = "Incidentes no encontrados")
    @ApiResponse(responseCode = "500", description = "Error en la base de datos")
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Incident>> getIncidentsByCompany(@PathVariable Integer companyId) {
        List<Incident> incidents = incidentService.getIncidentsByCompany(companyId);
        if (incidents != null) {
            return ResponseEntity.ok(incidents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addIncident(@RequestBody IncidentRequest incident) {
        try {
            return ResponseEntity.ok(incidentService.saveIncident(incident));
        } catch (ErrorDatabaseServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncident(@PathVariable Integer id, @RequestBody UpdateIncidentRequest incidentRequest) {
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

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateIncidentStatus(@PathVariable Integer id, @RequestBody updateIncidentStatusRequest status) {
        Incident incident = incidentService.updateIncidentStatus(id, status);
        if (incident != null) {
            return ResponseEntity.ok(incident);
        } else {
            return ResponseEntity.notFound().build();
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
