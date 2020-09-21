package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.entity.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Transactional(readOnly = true)
@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurant, UUID> {
}
