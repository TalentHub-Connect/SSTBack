package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    //traer todos los eventos por compañia y que el status no sea eliminado
    @Query("SELECT e FROM Event e WHERE e.companyid = ?1 AND e.status != 'Eliminado'")
    List<Event> findByCompanyId(Integer companyId);
}
