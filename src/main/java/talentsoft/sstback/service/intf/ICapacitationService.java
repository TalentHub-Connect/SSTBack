package talentsoft.sstback.service.intf;

import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Capacitation;
import talentsoft.sstback.payload.request.CapacitationRequest;

import java.util.List;

public interface ICapacitationService {
    Capacitation saveCapacitation(CapacitationRequest capacitation) throws ErrorDatabaseServiceException;
    Capacitation getCapacitationById(Integer id);
    List<Capacitation> getAllCapacitations();
    Capacitation updateCapacitationStatus(Integer id, String status) throws ErrorDatabaseServiceException;
    Capacitation updateCapacitationDetails(Integer id, String status, String description);
}
