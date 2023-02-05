package ru.bullsncows.passive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity(name = "game")
public class GameModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    public GameModel(UUID uuid, String secret) {
        this(uuid, secret, new ArrayList<>());
    }

    private UUID uuid;

    private String secret;

    private List<Move> moves = new ArrayList<>();


    public MoveResult getResultForMove(Move move) {
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            char guessChar = move.getGuess().charAt(i);
            if (guessChar == secret.charAt(i)) {
                bulls++;
            } else if (secret.contains(String.valueOf(guessChar))) {
                cows++;
            }
        }
        return new MoveResult(bulls, cows);
    }
}
