package talentsoft.sstback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talentsoft.sstback.model.TypeEvent;

@Repository
public interface TypeEventRepository extends JpaRepository<TypeEvent, Integer> {
}
