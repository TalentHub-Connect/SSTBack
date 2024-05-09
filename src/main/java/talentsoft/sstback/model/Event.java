package talentsoft.sstback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "dateEvent", length = 10)
    private String dateEvent;

    @Column(name = "place", length = 45)
    private String place;

    @Column(name = "typeeventid", nullable = false)
    private Integer typeeventid;

}