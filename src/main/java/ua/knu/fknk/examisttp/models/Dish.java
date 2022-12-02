package ua.knu.fknk.examisttp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name="Dish")
@Table(name="Dishes")
public class Dish {
    @SequenceGenerator(
            name = "dishes_sequence",
            sequenceName = "dishes_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dishes_sequence"
    )
    @Column(
            name="dish_id",
            updatable = false,
            nullable = false
    )

    @Getter
    @Setter
    private Long dishId;

    @Getter
    @Setter
    @Column(
            name="name",
            columnDefinition = "VARCHAR(25)"
    )
    @Size(min = 1, max = 25)
    private String name;

    @Getter
    @Setter
    @Column(
            name="slogan",
            columnDefinition = "VARCHAR(25)"
    )
    @Size(min = 1, max = 25)
    private String slogan;

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
    @ManyToMany(mappedBy = "dishes", fetch = FetchType.LAZY)
    private Set<Place> place;

    public Dish(String name, String slogan, String description) {
        this.name = name;
        this.slogan = slogan;
        this.description = description;
    }

    public Dish() {}
}
