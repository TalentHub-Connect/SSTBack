package talentsoft.sstback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Capacitation;
import talentsoft.sstback.payload.request.CapacitationRequest;
import talentsoft.sstback.payload.request.CapacitationStatusUpdateRequest;
import talentsoft.sstback.payload.response.CapacitationResponse;
import talentsoft.sstback.service.impl.CapacitationService;

import java.util.List;

@RestController
@RequestMapping("/api/capacitations")
public class CapacitationController {


    private final CapacitationService capacitationService;

    @Autowired
    public CapacitationController(CapacitationService capacitationService) {
        this.capacitationService = capacitationService;
    }

    // Obtener todas las capacitaciones
    @GetMapping
    public ResponseEntity<List<Capacitation>> getAllCapacitations() {
        return ResponseEntity.ok(capacitationService.getAllCapacitations());
    }

    @Operation(summary = "Get a capacitation by ID")
    @ApiResponse(responseCode = "200", description = "Capacitation found")
    @ApiResponse(responseCode = "404", description = "Capacitation not found")
    @GetMapping("/{id}")
    public ResponseEntity<CapacitationResponse> getCapacitationById(@PathVariable Integer id) {
        Capacitation capacitation = capacitationService.getCapacitationById(id);
        if (capacitation != null) {
            return ResponseEntity.ok(CapacitationResponse.builder()
                    .id(capacitation.getId())
                    .description(capacitation.getDescription())
                    .capacitationDate(capacitation.getCapacitationdate())
                    .status(capacitation.getStatus())
                    .place(capacitation.getPlace())
                    .typeCapacitationId(capacitation.getTypecapacitationid())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar una nueva capacitación
    @PostMapping
    public ResponseEntity<Capacitation> createCapacitation(@RequestBody CapacitationRequest capacitation) {
       try{
           return ResponseEntity.ok(capacitationService.saveCapacitation(capacitation));
       }catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
    }

    @Operation(summary = "Update the status of a capacitation by ID")
    @ApiResponse(responseCode = "200", description = "Capacitation updated successfully")
    @ApiResponse(responseCode = "404", description = "Capacitation not found")
    @PutMapping("/{id}")
    public ResponseEntity<Capacitation> updateCapacitationStatus(@PathVariable Integer id, @RequestBody CapacitationStatusUpdateRequest newStatus) throws ErrorDatabaseServiceException {
        Capacitation updated = capacitationService.updateCapacitationStatus(id, newStatus.getStatus());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Editar una capacitación (cambiar estado y descripción)
    @PutMapping("/{id}/details")
    public ResponseEntity<Capacitation> updateCapacitationDetails(@PathVariable Integer id, @RequestBody Capacitation updates) {
        Capacitation updated = capacitationService.updateCapacitationDetails(id, updates.getStatus(), updates.getDescription());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
