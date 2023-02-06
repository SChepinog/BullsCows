package ru.bullsncows.passive.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bullsncows.passive.dtos.GameDto;
import ru.bullsncows.passive.mapper.GameMapper;
import ru.bullsncows.passive.model.GameModel;
import ru.bullsncows.passive.model.Move;
import ru.bullsncows.passive.model.MoveResult;
import ru.bullsncows.passive.repository.GameRepositoryJpa;
import ru.bullsncows.passive.repository.MoveRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    GameRepositoryJpa gameRepository;
    MoveRepository moveRepository;
    GameMapper gameMapper;

    @Override
    public GameDto createNewGame() {
        UUID newGameId = UUID.randomUUID();
        GameModel newGameModel = new GameModel();
        newGameModel.setUuid(newGameId);
        newGameModel.setSecret("1234");
        newGameModel.setMoves(new ArrayList<>());
        GameModel savedGameModel = gameRepository.save(newGameModel);
        return gameMapper.mapToDto(savedGameModel);
    }

    @Override
    public GameDto addMove(UUID uuid, Move move) {
        GameModel game = gameRepository.findByUuid(uuid);
        MoveResult resultForMove = game.getResultForMove(move);
        move = moveRepository.save(move.result(resultForMove));
        game.getMoves().add(move);
        gameRepository.save(game);
        return gameMapper.mapToDto(game);
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository.findAll()
                .stream().map(it -> gameMapper.mapToDto(it))
                .collect(Collectors.toList());
    }

    @Override
    public GameDto getGame(UUID uuid) {
        return gameMapper.mapToDto(gameRepository.findByUuid(uuid));
    }

}
