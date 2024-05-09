package talentsoft.sstback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talentsoft.sstback.model.TypeCapacitation;
import talentsoft.sstback.repository.TypeCapacitationRepository;
import talentsoft.sstback.service.intf.ICapacitationService;
import talentsoft.sstback.service.intf.ITypeCapacitationService;

import java.util.List;
import java.util.Optional;

@Service
public class TypeCapacitationService implements ITypeCapacitationService {
    private final TypeCapacitationRepository typeCapacitationRepository;

    @Autowired
    public TypeCapacitationService(TypeCapacitationRepository typeCapacitationRepository) {
        this.typeCapacitationRepository = typeCapacitationRepository;
    }

    @Override
    public Optional<TypeCapacitation> getTypeCapacitationById(Integer id) {
        return typeCapacitationRepository.findById(id);
    }

    @Override
    public List<TypeCapacitation> getAllTypeCapacitations() {
        return typeCapacitationRepository.findAll();
    }

}
