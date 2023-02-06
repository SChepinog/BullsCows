package ru.bullsncows.passive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "GameModel")
@Table(name = "game")
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;

    private String secret;

    @OneToMany
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
        return new MoveResult(null, bulls, cows, move);
    }
}
