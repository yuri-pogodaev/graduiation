package ru.topjava.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.topjava.app.entity.Vote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VotesRepository extends JpaRepository<Vote, UUID> {
    @Modifying
    @Query("delete from Vote v WHERE v.user.id = :id")
    void delete(@Param("id") UUID id);

    @Modifying
    @Query("delete from Vote v where v.restaurant.id = :id")
    void deleteWithRestaurantId(@Param("id") UUID id);

    Vote findByRestaurantIdAndUserId(UUID userId, UUID restaurantId);

    @Query("select v from Vote v where v.user.id = :userId")
    Optional<List<Vote>> findVoteByUserId(@Param("userId") UUID userId);
}
