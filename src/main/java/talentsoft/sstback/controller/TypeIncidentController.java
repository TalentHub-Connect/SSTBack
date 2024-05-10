package talentsoft.sstback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import talentsoft.sstback.model.TypeIncident;
import talentsoft.sstback.service.impl.TypeIncidentService;

import java.util.List;

@RestController
@RequestMapping("/api/type/incidents")
public class TypeIncidentController {
    private final TypeIncidentService typeIncidentService;

    @Autowired
    public TypeIncidentController(TypeIncidentService typeIncidentService) {
        this.typeIncidentService = typeIncidentService;
    }

    @GetMapping
    public ResponseEntity<List<TypeIncident>> getAllTypeIncidents() {
        return ResponseEntity.ok(typeIncidentService.getAllTypeIncidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeIncident> getTypeIncidentById(@PathVariable Integer id) {
        return typeIncidentService.getTypeIncidentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
