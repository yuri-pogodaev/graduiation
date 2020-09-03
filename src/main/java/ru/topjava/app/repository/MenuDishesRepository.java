package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.topjava.app.dto.response.MenuDishesForResponse;
import ru.topjava.app.entity.MenuDishes;

import javax.persistence.OrderBy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MenuDishesRepository extends JpaRepository<MenuDishes, UUID> {
    @Modifying
    @Query("delete from MenuDishes md WHERE md.dish.id = :id ")
    void delete(@Param("id") UUID id);

        @Modifying
    @Query("delete from MenuDishes md where md.restaurant.id = :id")
    void deleteWithRestaurantId(@Param("id") UUID id);

//        @EntityGraph(attributePaths = {"dish", "restaurant"})
//    @OrderBy(value = "id")
//        List<MenuDishes> findAllByUpdatedAt(LocalDate updateAt);
}

