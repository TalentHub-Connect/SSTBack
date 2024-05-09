package talentsoft.sstback.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "capacitation")
public class Capacitation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "capacitationdate", length = 10)
    private String capacitationDate;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "place", length = 45)
    private String place;

    @Column(name = "typecapacitationid", nullable = false)
    private Integer typeCapacitationId;
}