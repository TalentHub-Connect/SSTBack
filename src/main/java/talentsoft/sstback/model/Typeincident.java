package talentsoft.sstback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "typeincident")
public class Typeincident {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nameincident", length = 45)
    private String nameincident;

    @OneToMany(mappedBy = "typeincidentid")
    private Set<Incident> incidents = new LinkedHashSet<>();

}