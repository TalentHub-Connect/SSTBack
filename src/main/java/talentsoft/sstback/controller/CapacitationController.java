package talentsoft.sstback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Capacitation;
import talentsoft.sstback.payload.request.CapacitationRequest;
import talentsoft.sstback.payload.request.CapacitationStatusUpdateRequest;
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

    @Operation(summary = "Obtener una capacitación por ID")
    @ApiResponse(responseCode = "200", description = "Capacitation encontrada")
    @ApiResponse(responseCode = "404", description = "Capacitation no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @GetMapping("/{id}")
    public ResponseEntity<Capacitation> getCapacitationById(@PathVariable Integer id) {
        Capacitation capacitation = capacitationService.getCapacitationById(id);
        if (capacitation != null) {
            return ResponseEntity.ok(capacitation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Agregar una nueva capacitación
    @Operation(summary = "Agregar una nueva capacitación")
    @ApiResponse(responseCode = "200", description = "Capacitation creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Error al crear la capacitación")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @PostMapping
    public ResponseEntity<Capacitation> createCapacitation(@RequestBody CapacitationRequest capacitation) {
       try{
              Capacitation newCapacitation = capacitationService.saveCapacitation(capacitation);
              return ResponseEntity.ok(newCapacitation);
       }catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
    }

    @Operation(summary = "Actualiza el estado de una capacitación")
    @ApiResponse(responseCode = "200", description = "Capacitation actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Capacitation no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @PutMapping("/{id}")
    public ResponseEntity<Capacitation> updateCapacitationStatus(@PathVariable Integer id, @RequestBody CapacitationStatusUpdateRequest newStatus) throws ErrorDatabaseServiceException {
        Capacitation updated = capacitationService.updateCapacitationStatus(id, newStatus.getStatus());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Actualiza los detalles de una capacitación")
    @ApiResponse(responseCode = "200", description = "Capacitation actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Capacitation no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
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
