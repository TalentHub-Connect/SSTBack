package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.Capacitation;

@Repository
public interface CapacitationRepository extends JpaRepository<Capacitation, Integer> {
}
