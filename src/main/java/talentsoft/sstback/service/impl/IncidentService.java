package talentsoft.sstback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Incident;
import talentsoft.sstback.payload.request.IncidentRequest;
import talentsoft.sstback.payload.request.UpdateIncidentRequest;
import talentsoft.sstback.payload.request.updateIncidentStatusRequest;
import talentsoft.sstback.repository.IncidentRepository;
import talentsoft.sstback.service.intf.IIncidentService;

import java.util.List;

@Service
public class IncidentService implements IIncidentService {

    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Incident saveIncident(IncidentRequest incident) throws ErrorDatabaseServiceException {
        try {
            return incidentRepository.save(Incident.builder()
                    .description(incident.getDescription())
                    .incidentdate(incident.getIncidentdate())
                    .status(incident.getStatus())
                    .typeincidentid(incident.getTypeincidentid())
                    .employeeid(incident.getEmployeeid())
                    .companyid(incident.getCompanyid())
                    .build());
        } catch (Exception e) {
            throw new ErrorDatabaseServiceException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }



    @Override
    public void deleteIncident(Integer id) throws ErrorDatabaseServiceException {
        try {
            incidentRepository.deleteById(id);
        } catch (Exception e) {
            throw new ErrorDatabaseServiceException("Error al eliminar el incidente", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public Incident updateIncident(int id, UpdateIncidentRequest incidentRequest) throws ErrorDatabaseServiceException {
        try{
            Incident incident = incidentRepository.findById(id).orElse(null);
            if(incident != null){
                incident.setDescription(incidentRequest.getDescription());
                incident.setStatus(incidentRequest.getStatus());
                return incidentRepository.save(incident);
            }
            return null;
        }catch (Exception e){
            throw  new ErrorDatabaseServiceException("Error al actualizar el incidente", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public List<Incident> getIncidentsByCompany(Integer companyId) {
        return incidentRepository.findByCompanyid(companyId);
    }

    @Override
    public Incident updateIncidentStatus(Integer id, updateIncidentStatusRequest status) throws ErrorDatabaseServiceException {
       try {
              Incident incident = incidentRepository.findById(id).orElse(null);
              if (incident != null) {
                incident.setStatus(status.getStatus());
                return incidentRepository.save(incident);
              }
              return null;
       }catch (Exception e){
           throw new ErrorDatabaseServiceException("Error al actualizar el estado del incidente", HttpStatus.BAD_REQUEST.value());
       }
    }

    @Override
    public Incident getIncidentById(Integer id) {
        return incidentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }


}
