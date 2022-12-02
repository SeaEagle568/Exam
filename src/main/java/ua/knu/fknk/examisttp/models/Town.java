package ua.knu.fknk.examisttp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name="Town")
@Table(name="Towns")
public class Town {
    @SequenceGenerator(
            name = "towns_sequence",
            sequenceName = "towns_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "towns_sequence"
    )
    @Column(
            name="town_id",
            updatable = false,
            nullable = false
    )

    @Getter
    @Setter
    private Long townId;

    @Getter
    @Setter
    @Column(
            name="name",
            columnDefinition = "VARCHAR(25)"
    )
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z]+")
    private String name;

    @Getter
    @Setter
    @Column(
            name="description",
            columnDefinition = "VARCHAR(25)"
    )
    @Size(min = 1, max = 25)
    private String description;

    @Getter
    @Setter
    @OneToMany(mappedBy = "town")
    Set<Place> places;
}
