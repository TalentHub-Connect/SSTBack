package talentsoft.sstback.service.intf;

import talentsoft.sstback.model.TypeEvent;

import java.util.List;
import java.util.Optional;

public interface ITypeEventService {
    Optional<TypeEvent> getTypeEventById(Integer id);
    List<TypeEvent> getAllTypeEvents();
}
