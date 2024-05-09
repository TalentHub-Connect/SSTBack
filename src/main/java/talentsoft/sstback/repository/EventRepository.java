package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
