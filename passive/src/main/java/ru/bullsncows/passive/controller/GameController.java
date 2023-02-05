package ru.bullsncows.passive.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bullsncows.passive.dtos.GameDto;
import ru.bullsncows.passive.model.Move;
import ru.bullsncows.passive.service.GameService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    @GetMapping
    public List<GameDto> getAll() {
        return gameService.getAllGames();
    }

    @GetMapping("/{uuid}")
    public GameDto getOne(@PathVariable UUID uuid) {
        return gameService.getGame(uuid);
    }

    @PostMapping
    public GameDto createGame() {
        return gameService.createNewGame();
    }

    @PutMapping("/{uuid}")
    public GameDto makeMove(
            @PathVariable UUID uuid,
            @RequestParam String move
    ) {
        Move newMove = new Move(move);
        if (newMove.isCorrect()) {
            return gameService.addMove(uuid, newMove);
        } else {
            throw new IllegalArgumentException("Bad move");
        }
    }
}
