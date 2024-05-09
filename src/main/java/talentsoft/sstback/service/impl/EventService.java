package talentsoft.sstback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Event;
import talentsoft.sstback.payload.request.EventRequest;
import talentsoft.sstback.payload.request.EventUpdateRequest;
import talentsoft.sstback.repository.EventRepository;
import talentsoft.sstback.service.intf.IEventService;

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
                .build());
    }

    @Override
    public void deleteEvent(Integer id) {


    }

    @Override
    public Event updateEvent(int id, EventUpdateRequest updateRequest) throws ErrorDatabaseServiceException {
        try{
            Event event = eventRepository.findById(id).orElse(null);
            if(event != null){
                return eventRepository.save(Event.builder()
                        .id(id)
                        .description(updateRequest.getDescription())
                        .dateEvent(updateRequest.getDateEvent())
                        .status(updateRequest.getStatus())
                        .place(updateRequest.getPlace())
                        .typeeventid(updateRequest.getTypeeventid())
                        .build());
            }
            return null;
        }catch (Exception e){
            throw new ErrorDatabaseServiceException("Error al actualizar el evento", HttpStatus.BAD_REQUEST.value());
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
