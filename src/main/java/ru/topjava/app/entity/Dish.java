package ru.topjava.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "dish")
@Access(AccessType.FIELD)
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(min = 10, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "PRICE")
    @NotNull
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private List<MenuItem> menuDishes;

    public Dish(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price.setScale(3, RoundingMode.HALF_DOWN);
    }
}
