package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.topjava.app.entity.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurant, UUID> {
    @EntityGraph(value = "graph.restaurant.menus", type = EntityGraph.EntityGraphType.FETCH)
    List<Restaurant> findAllByMenuDishes(LocalDate date);
}
