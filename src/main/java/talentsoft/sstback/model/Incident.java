package talentsoft.sstback.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "incidentdate", length = 10)
    private String incidentdate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "typeincidentid", nullable = false)
    private Integer typeincidentid;

    @Column(name = "employeeid", nullable = false)
    private Integer employeeid;

    @Column(name = "companyid", nullable = false)
    private Integer companyid;

}