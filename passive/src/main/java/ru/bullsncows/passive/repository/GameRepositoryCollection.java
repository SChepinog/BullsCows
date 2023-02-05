package ru.bullsncows.passive.repository;

import org.springframework.stereotype.Repository;
import ru.bullsncows.passive.model.GameModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class GameRepositoryCollection implements GameRepository {

    private final static Map<UUID, GameModel> allGames = new HashMap<>();

    @Override
    public GameModel save(GameModel newGameModel) {
        allGames.put(newGameModel.getUuid(), newGameModel);
        return newGameModel;
    }

    @Override
    public GameModel getOne(UUID uuid) {
        return allGames.get(uuid);
    }

    @Override
    public Collection<GameModel> getAll() {
        return allGames.values();
    }
}
