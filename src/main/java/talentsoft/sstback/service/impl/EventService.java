package talentsoft.sstback.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Event;
import talentsoft.sstback.payload.request.EventRequest;
import talentsoft.sstback.repository.EventRepository;
import talentsoft.sstback.service.intf.IEventService;

import java.util.Collections;
import java.util.List;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event saveEvent(EventRequest event) {
        return eventRepository.save(Event.builder()
                .description(event.getDescription())
                .dateEvent(event.getDateEvent())
                .status(event.getStatus())
                .place(event.getPlace())
                .typeeventid(event.getTypeeventid())
                .companyid(event.getCompanyid())
                .build());
    }

    @Override
    public Event updateEventStatus(int id, String status) throws ErrorDatabaseServiceException {
        try{
            Event event = eventRepository.findById(id).orElse(null);
            if(event != null){
                event.setStatus(status);
                return eventRepository.save(event);
            }
            return null;
        }catch (Exception e){
            throw new ErrorDatabaseServiceException("Error al actualizar el evento", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public Event updateEventDetails(Integer id, String status, String description) {
        Event event = eventRepository.findById(id).orElse(null);
        if(event != null){
            event.setStatus(status);
            event.setDescription(description);
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public List<Event> getEventsByCompany(Integer companyId) {
        try {
            return eventRepository.findByCompanyId(companyId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }


    @Override
    public Event getEventById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


}
