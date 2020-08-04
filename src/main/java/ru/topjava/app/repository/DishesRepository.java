package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.topjava.app.entity.Dish;

import java.util.UUID;
@Repository
public interface DishesRepository extends JpaRepository<Dish, UUID> {
}
