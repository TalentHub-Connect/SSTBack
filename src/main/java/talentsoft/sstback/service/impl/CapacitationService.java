package talentsoft.sstback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Capacitation;
import talentsoft.sstback.payload.request.CapacitationRequest;
import talentsoft.sstback.repository.CapacitationRepository;
import talentsoft.sstback.service.intf.ICapacitationService;

import java.util.List;

@Service
public class CapacitationService implements ICapacitationService {


    private final CapacitationRepository capacitationRepository;

    @Autowired
    public CapacitationService(CapacitationRepository capacitationRepository) {
        this.capacitationRepository = capacitationRepository;
    }

    @Override
    public Capacitation saveCapacitation(CapacitationRequest capacitation) throws ErrorDatabaseServiceException {
        try {
            return capacitationRepository.save(Capacitation.builder()
                    .description(capacitation.getDescription())
                    .capacitationDate(capacitation.getCapacitationDate())
                    .status(capacitation.getStatus())
                    .place(capacitation.getPlace())
                    .typeCapacitationId(capacitation.getTypeCapacitationId())
                    .build());
        } catch (Exception e) {
            throw new ErrorDatabaseServiceException("Error al guardar la capacitación", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public Capacitation getCapacitationById(Integer id) {
        return capacitationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Capacitation> getAllCapacitations() {
        return capacitationRepository.findAll();
    }

    @Override
    public Capacitation updateCapacitationStatus(Integer id, String status) throws ErrorDatabaseServiceException {
       try{
              Capacitation capacitation = capacitationRepository.findById(id).orElse(null);
              if(capacitation != null){
                capacitation.setStatus(status);
                return capacitationRepository.save(capacitation);
              }
              return null;
       }catch (Exception e){
           throw new ErrorDatabaseServiceException("Error al actualizar la capacitación", HttpStatus.BAD_REQUEST.value());
       }
    }

    @Override
    public Capacitation updateCapacitationDetails(Integer id, String status, String description) {
        Capacitation capacitation = capacitationRepository.findById(id).orElse(null);
        if(capacitation != null){
            capacitation.setStatus(status);
            capacitation.setDescription(description);
            return capacitationRepository.save(capacitation);
        }
        return null;
    }
}
