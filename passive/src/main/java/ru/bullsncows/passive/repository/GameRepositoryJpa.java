package ru.bullsncows.passive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bullsncows.passive.model.GameModel;

import java.util.UUID;

@Repository
public interface GameRepositoryJpa extends JpaRepository<GameModel, Long> {

//    @Query("Select g from GameModel g where g.uuid = :uuid")
//    @Query(value = "Select * from game where uuid = :uuid", nativeQuery = true)
//    GameModel findByUuid(@Param("uuid") UUID uuid);

    GameModel findByUuid(UUID uuid);

    GameModel findAllByUuid(UUID uuid);

    GameModel findGameModelByUuid(UUID uuid);

}
