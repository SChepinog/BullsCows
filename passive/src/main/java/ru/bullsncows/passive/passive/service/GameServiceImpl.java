package ru.bullsncows.passive.passive.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bullsncows.passive.passive.dtos.GameDto;
import ru.bullsncows.passive.passive.mapper.GameMapper;
import ru.bullsncows.passive.passive.model.GameModel;
import ru.bullsncows.passive.passive.model.Move;
import ru.bullsncows.passive.passive.model.MoveResult;
import ru.bullsncows.passive.passive.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    GameRepository gameRepository;
    GameMapper gameMapper;

    @Override
    public GameDto createNewGame() {
        UUID newGameId = UUID.randomUUID();
        GameModel newGameModel = new GameModel(newGameId, "1234", new ArrayList<>());
        GameModel savedGameModel = gameRepository.save(newGameModel);
        return gameMapper.mapToDto(savedGameModel);
    }

    @Override
    public GameDto addMove(UUID uuid, Move move) {
        GameModel game = gameRepository.getOne(uuid);
        MoveResult resultForMove = game.getResultForMove(move);
        game.getMoves().add(move.result(resultForMove));
        gameRepository.save(game);
        return gameMapper.mapToDto(game);
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository.getAll()
                .stream().map(it -> gameMapper.mapToDto(it))
                .collect(Collectors.toList());
    }

    @Override
    public GameDto getGame(UUID uuid) {
        return gameMapper.mapToDto(gameRepository.getOne(uuid));
    }

}
