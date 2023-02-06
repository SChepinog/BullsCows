package ru.bullsncows.passive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bullsncows.passive.model.Move;

public interface MoveRepository extends JpaRepository<Move, Long> {
}
