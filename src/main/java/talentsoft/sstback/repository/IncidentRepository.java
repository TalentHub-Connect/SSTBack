package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.Incident;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    @Query("SELECT i FROM Incident i WHERE i.companyid = ?1 AND i.status != 'Eliminado'")
    List<Incident> findByCompanyid(Integer companyId);
}
