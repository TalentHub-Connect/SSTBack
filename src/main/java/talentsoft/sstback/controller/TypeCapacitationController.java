package talentsoft.sstback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talentsoft.sstback.model.TypeCapacitation;
import talentsoft.sstback.service.impl.TypeCapacitationService;
import java.util.List;

@RestController
@RequestMapping("/api/type/capacitations")
public class TypeCapacitationController {
    private final TypeCapacitationService typeCapacitationService;

    @Autowired
    public TypeCapacitationController(TypeCapacitationService typeCapacitationService) {
        this.typeCapacitationService = typeCapacitationService;
    }

    @GetMapping
    public ResponseEntity<List<TypeCapacitation>> getAllTypeCapacitations() {
        return ResponseEntity.ok(typeCapacitationService.getAllTypeCapacitations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCapacitation> getTypeCapacitationById(@PathVariable Integer id) {
        return typeCapacitationService.getTypeCapacitationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
