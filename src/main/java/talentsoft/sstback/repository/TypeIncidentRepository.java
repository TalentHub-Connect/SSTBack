package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.TypeIncident;

@Repository
public interface TypeIncidentRepository extends JpaRepository<TypeIncident, Integer> {
}
