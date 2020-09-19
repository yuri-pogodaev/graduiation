package ru.topjava.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant", schema = "PUBLIC")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private List<MenuItem> menuDishes;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private List<Vote> voteList;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    @NotBlank
    private String address;

    public Restaurant(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
