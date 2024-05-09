package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
}
