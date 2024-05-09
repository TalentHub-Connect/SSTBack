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
@Table(name = "typecapacitation")
public class Typecapacitation {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

}