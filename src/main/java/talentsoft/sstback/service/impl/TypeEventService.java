package talentsoft.sstback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talentsoft.sstback.model.TypeEvent;
import talentsoft.sstback.repository.TypeEventRepository;
import talentsoft.sstback.service.intf.ITypeEventService;

import java.util.List;
import java.util.Optional;

@Service
public class TypeEventService implements ITypeEventService {

    private final TypeEventRepository typeEventRepository;

    @Autowired
    public TypeEventService(TypeEventRepository typeEventRepository) {
        this.typeEventRepository = typeEventRepository;
    }

    @Override
    public Optional<TypeEvent> getTypeEventById(Integer id) {
        return typeEventRepository.findById(id);
    }

    @Override
    public List<TypeEvent> getAllTypeEvents() {
        return typeEventRepository.findAll();
    }
}
