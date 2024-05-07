package talentsoft.sstback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "typeeventid")
    private Set<Event> events = new LinkedHashSet<>();

}