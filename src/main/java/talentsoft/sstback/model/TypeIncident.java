package talentsoft.sstback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "typeincident")
public class TypeIncident {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nameincident", length = 45)
    private String nameIncident;

}