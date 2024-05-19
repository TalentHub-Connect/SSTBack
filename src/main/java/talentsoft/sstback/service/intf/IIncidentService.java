package talentsoft.sstback.service.intf;

import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Incident;
import talentsoft.sstback.payload.request.IncidentRequest;
import talentsoft.sstback.payload.request.updateIncidentStatusRequest;

import java.util.List;

public interface IIncidentService {
    Incident saveIncident(IncidentRequest incident) throws ErrorDatabaseServiceException;
    void deleteIncident(Integer id) throws ErrorDatabaseServiceException;
    Incident updateIncident(int id, IncidentRequest incident) throws ErrorDatabaseServiceException;
    Incident getIncidentById(Integer id);
    List<Incident> getAllIncidents();
    List<Incident> getIncidentsByCompany(Integer companyId);
    Incident updateIncidentStatus(Integer id, updateIncidentStatusRequest status);
}
