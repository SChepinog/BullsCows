package ru.bullsncows.passive.passive.repository;

import ru.bullsncows.passive.passive.model.GameModel;

import java.util.Collection;
import java.util.UUID;

public interface GameRepository {

    GameModel save(GameModel newGameModel);

    GameModel getOne(UUID uuid);

    Collection<GameModel> getAll();
}
