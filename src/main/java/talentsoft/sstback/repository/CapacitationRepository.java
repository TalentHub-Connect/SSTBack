package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.Capacitation;

import java.util.List;

@Repository
public interface CapacitationRepository extends JpaRepository<Capacitation, Integer> {
    @Query("SELECT c FROM Capacitation c WHERE c.CompanyId = ?1")
    List<Capacitation> findAllByCompanyId(Integer companyId);
}
