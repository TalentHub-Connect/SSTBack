package talentsoft.sstback.service.intf;


import talentsoft.sstback.model.TypeCapacitation;

import java.util.List;
import java.util.Optional;

public interface ITypeCapacitationService {
    Optional<TypeCapacitation> getTypeCapacitationById(Integer id);
    List<TypeCapacitation> getAllTypeCapacitations();
}
