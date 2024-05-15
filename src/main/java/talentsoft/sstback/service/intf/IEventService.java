package talentsoft.sstback.service.intf;

import talentsoft.sstback.exception.ErrorDatabaseServiceException;
import talentsoft.sstback.model.Event;
import talentsoft.sstback.payload.request.EventRequest;
import talentsoft.sstback.payload.request.EventUpdateRequest;

import java.util.List;

public interface IEventService {
    Event saveEvent(EventRequest event);
    Event updateEventStatus(int id, String status) throws ErrorDatabaseServiceException;
    Event getEventById(Integer id);
    List<Event> getAllEvents();
    Event updateEventDetails(Integer id, String status, String description);
    List<Event> getEventsByCompany(Integer companyId);
}
