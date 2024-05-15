package talentsoft.sstback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talentsoft.sstback.model.TypeIncident;
import talentsoft.sstback.repository.TypeIncidentRepository;
import talentsoft.sstback.service.intf.ITypeIncidentService;

import java.util.List;
import java.util.Optional;

@Service
public class TypeIncidentService implements ITypeIncidentService {

    private final TypeIncidentRepository typeIncidentRepository;

    @Autowired
    public TypeIncidentService(TypeIncidentRepository typeIncidentRepository) {
        this.typeIncidentRepository = typeIncidentRepository;
    }
    @Override
    public Optional<TypeIncident> getTypeIncidentById(Integer id) {
        return typeIncidentRepository.findById(id);
    }
    @Override
    public List<TypeIncident> getAllTypeIncidents() {
        return typeIncidentRepository.findAll();
    }
}
