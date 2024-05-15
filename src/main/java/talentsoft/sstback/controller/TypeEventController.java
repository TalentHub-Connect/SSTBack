package talentsoft.sstback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talentsoft.sstback.model.TypeEvent;
import talentsoft.sstback.service.impl.TypeEventService;

import java.util.List;

@RestController
@RequestMapping("/api/type/events")
public class TypeEventController {

    private final TypeEventService typeEventService;

    @Autowired
    public TypeEventController(TypeEventService typeEventService) {
        this.typeEventService = typeEventService;
    }

    @GetMapping
    public ResponseEntity<List<TypeEvent>> getAllTypeEvents() {
        return ResponseEntity.ok(typeEventService.getAllTypeEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeEvent> getTypeEventById(@PathVariable Integer id) {
        return typeEventService.getTypeEventById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
