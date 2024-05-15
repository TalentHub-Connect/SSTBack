package talentsoft.sstback.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "date_event", length = 10)
    private String dateEvent;

    @Column(name = "place", length = 45)
    private String place;

    @Column(name = "typeeventid", nullable = false)
    private Integer typeeventid;

    @Column(name = "companyid", nullable = false)
    private Integer companyid;

}