package ua.knu.fknk.examisttp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name="Place")
@Table(name="Places")
public class Place {
    @SequenceGenerator(
            name = "places_sequence",
            sequenceName = "places_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "places_sequence"
    )
    @Column(
            name="place_id",
            updatable = false,
            nullable = false
    )

    @Getter
    @Setter
    private Long placeId;

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
            name="description",
            columnDefinition = "VARCHAR(25)"
    )
    @Size(min = 1, max = 25)
    private String description;

    @Getter
    @Setter
    @ManyToMany()
    @JoinTable(
            name = "dish_places",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Dish> dishes;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="town_id")
    private Town town;

    public Place(String name, String description, Set<Dish> dishes) {
        this.name = name;
        this.description = description;
        this.dishes = dishes;
    }

    public Place() {}
}
