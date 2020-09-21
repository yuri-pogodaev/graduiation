package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.entity.Dish;

import java.util.UUID;
@Transactional(readOnly = true)
@Repository
public interface DishesRepository extends JpaRepository<Dish, UUID> {
}
