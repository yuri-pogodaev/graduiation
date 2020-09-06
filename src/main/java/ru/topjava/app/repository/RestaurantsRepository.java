package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.topjava.app.entity.Restaurant;

import java.util.UUID;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurant, UUID> {
}
