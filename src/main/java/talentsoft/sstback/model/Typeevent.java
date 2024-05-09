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
@Table(name = "typeevent")
public class Typeevent {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nameevent", length = 45)
    private String nameevent;

}