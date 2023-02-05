package ru.bullsncows.passive.service;

import ru.bullsncows.passive.dtos.GameDto;
import ru.bullsncows.passive.model.Move;

import java.util.List;
import java.util.UUID;

public interface GameService {
    GameDto createNewGame();

    GameDto addMove(UUID uuid, Move move);

    List<GameDto> getAllGames();

    GameDto getGame(UUID uuid);
}
