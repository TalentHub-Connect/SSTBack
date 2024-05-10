package talentsoft.sstback.service.intf;

import talentsoft.sstback.model.TypeIncident;

import java.util.List;
import java.util.Optional;

public interface ITypeIncidentService {
    Optional<TypeIncident> getTypeIncidentById(Integer id);
    List<TypeIncident> getAllTypeIncidents();
}
