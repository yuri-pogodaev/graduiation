package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.entity.MenuItem;

import java.util.UUID;
@Transactional(readOnly = true)
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    @Modifying
    @Query("delete from MenuItem md WHERE md.dish.id = :id ")
    void delete(@Param("id") UUID id);

    @Modifying
    @Query("delete from MenuItem md where md.restaurant.id = :id")
    void deleteWithRestaurantId(@Param("id") UUID id);

}

